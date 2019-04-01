package me.jae57.woodywoody.service.serviceImpl;

import me.jae57.woodywoody.dto.ReqScentDto;
import me.jae57.woodywoody.dto.ScentDto;
import me.jae57.woodywoody.exception.NoChangeException;
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
        scentRepository.addScent(Scent
                .builder()
                .scentName(reqScentDto.getScentName())
                .scentKorName(reqScentDto.getScentKorName())
                .brand(reqScentDto.getBrand())
                .fragrance(reqScentDto.getFragrance())
                .build());
        List<String> families = reqScentDto.getFamilies();
        Long scentId = scentRepository.getScentId(reqScentDto.getScentName());
        for (String familyName : families) {
            int familyId = familyRepository.getFamilyId(familyName);
            scentFamilyRepository.addScentFamily(scentId, familyId);
        }
    }

    @Override
    public List<ScentDto> getAllScents() {
        List<Scent> scents = scentRepository.getAllScents();
        return scents.stream().map(scent -> {
            Long scentId = scent.getScentId();
            return getScent(scentId);
        }).collect(Collectors.toList());
    }

    @Override
    public ScentDto getScent(Long scentId) {
        Scent scent = scentRepository.getScent(scentId);
        List<Integer> familyIds = scentFamilyRepository.getFamiliesByScentId(scentId);
        List<Family> families = familyIds
                .stream()
                .map(familyRepository::getFamilyById)
                .collect(Collectors.toList());
        return ScentDto.from(scent, families);
    }

    @Override
    public List<ScentDto> getScentsByFamily(int familyId) {
        familyRepository.getFamilyById(familyId);
        List<Long> scentIds = scentFamilyRepository.getScentsByFamilyId(familyId);
        return scentIds.stream().map(this::getScent).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateScent(Long scentId, ReqScentDto reqScentDto) {
        Scent oldScent = scentRepository.getScent(scentId);
        Scent newScent = Scent
                .builder()
                .scentName(reqScentDto.getScentName())
                .scentKorName(reqScentDto.getScentKorName())
                .brand(reqScentDto.getBrand())
                .fragrance(reqScentDto.getFragrance())
                .build();

        Set<Integer> oldFamilyIds = new HashSet<>(scentFamilyRepository.getFamiliesByScentId(scentId));
        Set<Integer> newFamilyIds = familyNamesToIds(reqScentDto.getFamilies());

        if (SameFamilies(oldFamilyIds, newFamilyIds) && oldScent.equals(newScent)) {
            throw new NoChangeException("No change");
        }

        if (!oldScent.equals(newScent)) {
            scentRepository.updateScent(scentId, newScent);
        }

        if (!SameFamilies(oldFamilyIds, newFamilyIds)) {
            scentFamilyRepository.deleteAllByScentId(scentId);
            for (Integer familyId : newFamilyIds) {
                scentFamilyRepository.addScentFamily(scentId, familyId);
            }
        }
    }

    @Transactional
    @Override
    public void deleteScent(Long scentId) {
        scentRepository.getScent(scentId);
        scentFamilyRepository.deleteAllByScentId(scentId);
        scentRepository.deleteScent(scentId);
    }

    private Set<Integer> familyNamesToIds(List<String> families) {
        return families.stream().map(familyRepository::getFamilyId).collect(Collectors.toSet());
    }

    private boolean SameFamilies(Set<Integer> familyOne, Set<Integer> familyTwo) {
        return familyOne.containsAll(familyTwo) && familyTwo.containsAll(familyOne);
    }

}
