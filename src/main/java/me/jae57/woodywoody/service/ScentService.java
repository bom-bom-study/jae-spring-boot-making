package me.jae57.woodywoody.service;

import me.jae57.woodywoody.dto.ReqScentDto;
import me.jae57.woodywoody.dto.ScentDetailDto;
import me.jae57.woodywoody.dto.ScentDto;

import java.util.List;

public interface ScentService {
    void addScent(ReqScentDto reqScentDto);
    List<ScentDto> getAllScents();
    ScentDetailDto getScent(int scentId);
    List<ScentDetailDto> getScentsByFamily(int familyId);
    void updateScent(int scentId, ReqScentDto reqScentDto);
    void deleteScent(int scentId);
}
