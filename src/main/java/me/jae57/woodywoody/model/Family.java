package me.jae57.woodywoody.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Family {
    private int familyId;
    private String familyName;
    private String familyKorName;
    private String explanation;
}
