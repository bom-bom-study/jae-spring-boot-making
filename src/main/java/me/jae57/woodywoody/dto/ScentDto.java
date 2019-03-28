package me.jae57.woodywoody.dto;

import me.jae57.woodywoody.model.Family;
import me.jae57.woodywoody.model.Scent;

import java.util.List;
import java.util.stream.Collectors;

public class ScentDto {
    private Long scentId;
    private String scentName;
    private String scentKorName;
    private String brand;
    private String fragrance;
    private List<String> families;

    private ScentDto(Scent scent, List<Family> families) {
        this.scentId = scent.getScentId();
        this.scentName = scent.getScentName();
        this.scentKorName = scent.getScentKorName();
        this.brand = scent.getBrand();
        this.fragrance = scent.getFragrance();
        this.families = families.stream()
                .map(Family::getFamilyName)
                .collect(Collectors.toList());
    }

    public static ScentDto from(Scent scent, List<Family> families) {
        return new ScentDto(scent, families);
    }
}
