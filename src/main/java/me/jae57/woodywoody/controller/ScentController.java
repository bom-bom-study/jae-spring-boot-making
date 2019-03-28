package me.jae57.woodywoody.controller;

import me.jae57.woodywoody.dto.ReqScentDto;
import me.jae57.woodywoody.dto.ScentDto;
import me.jae57.woodywoody.service.ScentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scents")
public class ScentController {

    private final ScentService scentService;

    public ScentController(ScentService scentService) {
        this.scentService = scentService;
    }

    @PostMapping
    public ResponseEntity<String> addScent(@RequestBody ReqScentDto reqScentDto) {
        scentService.addScent(reqScentDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<ScentDto>> getAllScents() {
        return ResponseEntity.status(HttpStatus.OK).body(scentService.getAllScents());
    }

    @GetMapping("/{scent-id}")
    public ResponseEntity<ScentDto> getScent(@PathVariable("scent-id") Long scentId) {
        return ResponseEntity.status(HttpStatus.OK).body(scentService.getScent(scentId));
    }

    @GetMapping("/families/{family-id}")
    public ResponseEntity<List<ScentDto>> getScentsByFamily(@PathVariable("family-id") int familyId) {
        return ResponseEntity.status(HttpStatus.OK).body(scentService.getScentsByFamily(familyId));
    }

    @PatchMapping("/{scent-id}")
    public ResponseEntity<Void> updateScent(@RequestParam("scent-id") Long scentId, @ModelAttribute ReqScentDto reqScentDto) {
        scentService.updateScent(scentId, reqScentDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{scent-id}")
    public ResponseEntity deleteScent(@RequestParam("scent-id") Long scentId) {
        scentService.deleteScent(scentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
