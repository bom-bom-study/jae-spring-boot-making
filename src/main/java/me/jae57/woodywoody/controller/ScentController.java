package me.jae57.woodywoody.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.jae57.woodywoody.dto.ReqScentDto;
import me.jae57.woodywoody.dto.ScentDto;
import me.jae57.woodywoody.exception.ScentNotFoundException;
import me.jae57.woodywoody.service.ScentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scents")
public class ScentController {

    private final ScentService scentService;

    public ScentController(ScentService scentService) {
        this.scentService = scentService;
    }

    @ApiOperation(value = "scent 등록")
    @PostMapping
    public ResponseEntity<String> addScent(@Validated @RequestBody ReqScentDto reqScentDto) {
        scentService.addScent(reqScentDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "전체 scent 조회")
    @GetMapping
    public ResponseEntity<List<ScentDto>> getAllScents() {
        List<ScentDto> scents = scentService.getAllScents();
        return ResponseEntity.status(HttpStatus.OK).body(scents);
    }

    @ApiOperation(value = "id로 scent 조회")
    @ApiImplicitParams({@ApiImplicitParam(name = "scent-id", value = "향 고유번호", required = true)})
    @GetMapping("/{scent-id}")
    public ResponseEntity<ScentDto> getScent(@PathVariable("scent-id") Long scentId) {
        ScentDto scent = scentService.getScent(scentId);
        return ResponseEntity.status(HttpStatus.OK).body(scent);
    }

    @ApiOperation(value = "특정 family에 해당하는 scent 조회")
    @ApiImplicitParams({@ApiImplicitParam(name = "family-id", value = "계열 고유번호", required = true)})
    @GetMapping("/families/{family-id}")
    public ResponseEntity<List<ScentDto>> getScentsByFamily(@PathVariable("family-id") int familyId) {
        List<ScentDto> scentsByFamily = scentService.getScentsByFamily(familyId);
        return ResponseEntity.status(HttpStatus.OK).body(scentsByFamily);
    }

    @ApiOperation(value = "scent 수정")
    @ApiImplicitParams({@ApiImplicitParam(name = "scent-id", value = "향 고유번호", required = true)})
    @PatchMapping("/{scent-id}")
    public ResponseEntity<Void> updateScent(@PathVariable("scent-id") Long scentId, @RequestBody ReqScentDto reqScentDto) {
        scentService.updateScent(scentId, reqScentDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "scent 삭제")
    @ApiImplicitParams({@ApiImplicitParam(name = "scent-id", value = "향 고유번호", required = true)})
    @DeleteMapping("/{scent-id}")
    public ResponseEntity deleteScent(@PathVariable("scent-id") Long scentId) {
        scentService.deleteScent(scentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
