package me.jae57.woodywoody.service;

import me.jae57.woodywoody.dto.ReqScentDto;
import me.jae57.woodywoody.dto.ScentDto;

import java.util.List;

public interface ScentService {
    void addScent(ReqScentDto reqScentDto);
    List<ScentDto> getAllScents();
    ScentDto getScent(Long scentId);
    //List<ScentDto> getScentsByFamily(int familyId);
    void updateScent(Long scentId, ReqScentDto reqScentDto);
    void deleteScent(Long scentId);
}
