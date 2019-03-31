package me.jae57.woodywoody.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ReqScentDto {
    @NotNull(message = "scentName 필드가 null입니다.")
    private String scentName;
    private String scentKorName;
    @NotNull(message = "brand 필드가 null입니다.")
    private String brand;
    private String fragrance;
    @NotNull(message = "family 필드가 null입니다.")
    private List<String> families;
}
