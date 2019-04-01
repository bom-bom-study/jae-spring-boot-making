package me.jae57.woodywoody.service.serviceImpl;

import me.jae57.woodywoody.dto.ReqScentDto;
import me.jae57.woodywoody.dto.ScentDto;
import me.jae57.woodywoody.exception.DuplicateScentIdException;
import me.jae57.woodywoody.exception.EmptyDataException;
import me.jae57.woodywoody.exception.FamilyNotFoundException;
import me.jae57.woodywoody.exception.ScentNotFoundException;
import me.jae57.woodywoody.model.Family;
import me.jae57.woodywoody.model.Scent;
import me.jae57.woodywoody.repository.FamilyRepository;
import me.jae57.woodywoody.repository.ScentFamilyRepository;
import me.jae57.woodywoody.repository.ScentRepository;
import me.jae57.woodywoody.service.ScentService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
            int familyId = Optional.ofNullable(familyRepository.getFamilyId(familyName)).orElseThrow(()->new FamilyNotFoundException("no family for family-name("+familyName+")"));
            scentFamilyRepository.addScentFamily(scentId, familyId);
        }
    }

    @Override
    public List<ScentDto> getAllScents() {
        List<Scent> scents = Optional.ofNullable(scentRepository.getAllScents()).orElseThrow(()->new EmptyDataException("There is no scent"));
        return scents.stream().map(scent -> {
            Long scentId = scent.getScentId();
            return getScent(scentId);
        }).collect(Collectors.toList());
    }

    @Override
    public ScentDto getScent(Long scentId) {
        Scent scent = Optional.ofNullable(scentRepository.getScent(scentId)).orElseThrow(() -> new ScentNotFoundException("scent not found"));
        List<Integer> familyIds = scentFamilyRepository.getFamiliesByScentId(scentId);
        List<Family> families = familyIds
                .stream()
                .map(id -> Optional.ofNullable(familyRepository.getFamilyById(id)).orElseThrow(() -> new FamilyNotFoundException("no family for family-id(" + id + ")")))
                .collect(Collectors.toList());
        return ScentDto.from(scent, families);
    }

    @Override
    public List<ScentDto> getScentsByFamily(int familyId) {
        List<Long> scentIds = Optional.ofNullable(scentFamilyRepository.getScentsByFamilyId(familyId))
                .orElseThrow(() -> new EmptyDataException("no scent for family-id(" + familyId + ")"));

        return scentIds.stream().map(this::getScent).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateScent(Long scentId, ReqScentDto reqScentDto) {
        Set<Integer> oldFamilyIds = new HashSet<>(Optional.ofNullable(scentFamilyRepository.getFamiliesByScentId(scentId)).orElseThrow(()->new ScentNotFoundException("scent not found")));
        Set<Integer> newFamilyIds = familyNamesToIds(reqScentDto.getFamilies());

        if (notEqualFamilies(oldFamilyIds, newFamilyIds)) {
            scentFamilyRepository.deleteAllByScentId(scentId);

            for (Integer familyId : newFamilyIds) {
                scentFamilyRepository.addScentFamily(scentId, familyId);
            }
        }

        scentRepository.updateScent(scentId, Scent
                .builder()
                .scentName(reqScentDto.getScentName())
                .scentKorName(reqScentDto.getScentKorName())
                .brand(reqScentDto.getBrand())
                .fragrance(reqScentDto.getFragrance())
                .build());
    }

    @Transactional
    @Override
    public void deleteScent(Long scentId) {
        scentFamilyRepository.deleteAllByScentId(scentId);
        scentRepository.deleteScent(scentId);
    }

    private Set<Integer> familyNamesToIds(List<String> families) {
        return families.stream().map(familyRepository::getFamilyId).collect(Collectors.toSet());
    }

    private boolean notEqualFamilies(Set<Integer> familyOne, Set<Integer> familyTwo) {
        return ! (familyOne.containsAll(familyTwo) && familyTwo.containsAll(familyOne) );
    }

}
