package me.jae57.woodywoody.service;

import me.jae57.woodywoody.dto.ReqScentDto;
import me.jae57.woodywoody.model.Scent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    ScentService scentService;

    private List<ReqScentDto> scents;

    @Before
    public void setUp() {
        scents = Arrays.asList(
                ReqScentDto.builder()
                        .scentName("Wood Sage & Sea Solt")
                        .scentKorName("우드 세이지 앤 씨 솔트")
                        .brand("Jo Malone")
                        .fragrance("Cologne")
                        .families(Arrays.asList("Woody","Oceanic"))
                        .build(),
                ReqScentDto.builder()
                        .scentName("Tam Dao")
                        .scentKorName("탐다오")
                        .brand("Diptyque")
                        .fragrance("EDP")
                        .families(Arrays.asList("Woody","Oriental"))
                        .build(),
                ReqScentDto.builder()
                        .scentName("English Oak & Redcurrant")
                        .scentKorName("잉글리쉬 오크 앤 레드커런트")
                        .brand("Jo Malone")
                        .fragrance("Cologne")
                        .families(Arrays.asList("Woody","Fruity","Floral"))
                        .build(),
                ReqScentDto.builder()
                        .scentName("PerfumeNote vol.1 Fresh CedarWood")
                        .scentKorName("퍼퓸노트 vol.1 프레쉬 시더우드")
                        .brand("Innisfree")
                        .fragrance("EDT")
                        .families(Arrays.asList("Woody","Green","Citrus"))
                        .build());
    }

    @Test
    public void family에_해당하는_scent조회테스트(){
        for(ReqScentDto scent : scents){
            scentService.addScent(scent);
        }




    }
}
