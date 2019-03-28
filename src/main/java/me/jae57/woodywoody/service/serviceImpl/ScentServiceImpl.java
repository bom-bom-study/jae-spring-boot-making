package me.jae57.woodywoody.service.serviceImpl;

import me.jae57.woodywoody.dto.ReqScentDto;
import me.jae57.woodywoody.dto.ScentDto;
import me.jae57.woodywoody.model.Family;
import me.jae57.woodywoody.model.Scent;
import me.jae57.woodywoody.repository.FamilyRepository;
import me.jae57.woodywoody.repository.ScentFamilyRepository;
import me.jae57.woodywoody.repository.ScentRepository;
import me.jae57.woodywoody.service.ScentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScentServiceImpl implements ScentService {

    private final ScentRepository scentRepository;
    private final FamilyRepository familyRepository;
    private final ScentFamilyRepository scentFamilyRepository;

    public ScentServiceImpl(ScentRepository scentRepository,
                            FamilyRepository familyRepository,
                            ScentFamilyRepository scentFamilyRepository) {
        this.scentRepository = scentRepository;
        this.familyRepository = familyRepository;
        this.scentFamilyRepository = scentFamilyRepository;
    }

    @Transactional
    @Override
    public void addScent(ReqScentDto reqScentDto) {
        int stored = scentRepository.getScentByName(reqScentDto.getScentName());
        if(stored > 0){

        }else{
            int result = scentRepository.addScent(Scent
                    .builder()
                    .scentName(reqScentDto.getScentName())
                    .scentKorName(reqScentDto.getScentKorName())
                    .brand(reqScentDto.getBrand())
                    .fragrance(reqScentDto.getFragrance())
                    .build());
            if(result ==0){

            }else{
                List<String> families = reqScentDto.getFamilies();
                Long scentId = scentRepository.getScentId(reqScentDto.getScentName());
                for(String familyName : families){
                    int familyId = familyRepository.getFamilyId(familyName);
                    int mappingResult = scentFamilyRepository.addScentFamily(scentId, familyId);
                    if(mappingResult == 0){

                    }
                }
            }
        }
    }

    @Override
    public List<ScentDto> getAllScents() {
        List<Scent> scents = scentRepository.getAllScents();
        return scents.stream().map(scent-> {
            Long scentId = scent.getScentId();
            return getScent(scentId);
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ScentDto getScent(Long scentId) {
        Scent scent = scentRepository.getScent(scentId);
        List<Integer> familyIds = scentFamilyRepository.getFamiliesByScentId(scentId);
        List<Family> families = familyIds.stream().map(familyRepository::getFamilyById).collect(Collectors.toList());
        return ScentDto.from(scent, families);
    }

    @Transactional
    @Override
    public void updateScent(Long scentId, ReqScentDto reqScentDto) {
        Set<Integer> oldFamilies = new HashSet<>(scentFamilyRepository.getFamiliesByScentId(scentId));
        Set<Integer> newFamilyIds = familyNamesToIds(reqScentDto.getFamilies());
        if(oldFamilies.equals(newFamilyIds)){

        }else{
            scentFamilyRepository.deleteAllByScentId(scentId);
            int result = scentFamilyRepository.getCountByScentId(scentId);
            if(result ==0){
                for(Integer familyId : newFamilyIds){
                    scentFamilyRepository.addScentFamily(scentId,familyId);
                }
            }else{
                //exception 다 안지워짐
            }
        }

        int result = scentRepository.updateScent(scentId, Scent
                .builder()
                .scentName(reqScentDto.getScentName())
                .scentKorName(reqScentDto.getScentKorName())
                .brand(reqScentDto.getBrand())
                .fragrance(reqScentDto.getFragrance())
                .build());
        if (result == 0) {
            // throw exception
        }
    }

    @Transactional
    @Override
    public void deleteScent(Long scentId) {
        scentFamilyRepository.deleteAllByScentId(scentId);
        int stored = scentFamilyRepository.getCountByScentId(scentId);
        if(stored != 0){
            // exception
        }
        int result = scentRepository.deleteScent(scentId);
        if (result == 0) {
            // throw exception
        }
    }

    private Set<Integer> familyNamesToIds(List<String> families){
        return families.stream().map(familyRepository::getFamilyId).collect(Collectors.toSet());
    }

}
