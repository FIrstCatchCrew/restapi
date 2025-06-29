package com.firstcatchcrew.restapi.fishCatch;

import com.firstcatchcrew.restapi.fishCatch.dto.CatchCreateDTO;
import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/catch")

public class CatchController {
    private final CatchService catchService;

    public CatchController(CatchService catchService) {
        this.catchService = catchService;
    }


    @GetMapping
    public ResponseEntity<List<CatchViewDTO>> getAll() {
        return ResponseEntity.ok(catchService.getAllCatches());
    }

    @GetMapping("/available")
    public ResponseEntity<List<CatchViewDTO>> getAllAvailableCatches() {
        return ResponseEntity.ok(catchService.getAllAvailableCatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatchViewDTO> getById(@PathVariable Long id) {
        CatchViewDTO dto = catchService.getCatchById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CatchViewDTO>> search(
            @RequestParam(value = "species_name", required = false) String speciesName,
            @RequestParam(value = "pickup_address", required = false) String pickupAddress,
            @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(value = "max_price", required = false) BigDecimal maxPrice
    ) {
        List<CatchViewDTO> results = catchService.getCatchesBySpeciesNameAndLocationAndPriceRange(
                speciesName, pickupAddress, minPrice, maxPrice
        );
        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<CatchViewDTO> create(@Validated @RequestBody CatchCreateDTO newCatch) {
        CatchViewDTO dto = catchService.createCatch(newCatch);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatchViewDTO> update(
            @PathVariable long id,
            @RequestBody CatchCreateDTO dto) {
        CatchViewDTO updatedCatch = catchService.updateCatch(id, dto);
        return (updatedCatch != null)
                ? ResponseEntity.ok(updatedCatch)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/refresh")
    public ResponseEntity<Void> refreshAvailability() {
        catchService.refreshAvailabilityForAllCatches();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        return catchService.deleteCatchById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
