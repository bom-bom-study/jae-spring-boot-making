package me.jae57.woodywoody.model;

import lombok.*;

@Builder
@Getter
public class Scent {
    private int scentId;
    private String scentName;
    private String scentKorName;
    private String brand;
    private String fragrance;
}
