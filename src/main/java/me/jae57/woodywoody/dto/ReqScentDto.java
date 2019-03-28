package me.jae57.woodywoody.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReqScentDto {
    private String scentName;
    private String scentKorName;
    private String brand;
    private String fragrance;
    private List<String> families;
}
