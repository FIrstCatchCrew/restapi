package com.firstcatchcrew.restapi.fishCatch;

import com.firstcatchcrew.restapi.fishCatch.dto.CatchCreateDTO;
import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/catch")

public class CatchController {
    private final CatchService catchService;

    public CatchController(CatchService catchService) {
        this.catchService = catchService;
    }

    @GetMapping
    public List<CatchViewDTO> getAllCatches() {
        return catchService.getAllCatches();
    }

    @GetMapping("/available")
    public List<CatchViewDTO> getAllAvailableCatches() {
        return catchService.getAllAvailableCatches();
    }

//    @GetMapping("/fisherProfile")
//    public List<CatchViewDTO> getCatchesByFisherId(@RequestParam long fisherId) {
//        return catchService.getCatchesByFisherId(fisherId);
//    }

    @GetMapping("/fisherProfile")
    public List<CatchViewDTO> getCatchesByFisherId(
            @RequestParam long fisherId,
            @RequestParam(value = "status", required = false) String status // available or sold
    ) {
        if ("available".equalsIgnoreCase(status)) {
            return catchService.getAvailableCatchesByFisherId(fisherId);
        } else if ("sold".equalsIgnoreCase(status)) {
            return catchService.getSoldCatchesByFisherId(fisherId);
        } else {
            return catchService.getCatchesByFisherId(fisherId);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatchViewDTO> getCatchById(@PathVariable Long id) {
        CatchViewDTO dto = catchService.getCatchById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search")
    public List<CatchViewDTO> searchCatches(
            @RequestParam(value = "species_name", required = false) String speciesName,
            @RequestParam(value = "pickup_address", required = false) String pickupAddress,
            @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(value = "max_price", required = false) BigDecimal maxPrice
    ) {
        if (speciesName != null && pickupAddress != null && minPrice != null && maxPrice != null) {
            return catchService.getCatchesBySpeciesNameAndLocationAndPriceRange(speciesName, pickupAddress, minPrice, maxPrice);
        } else if (speciesName != null && pickupAddress != null) {
            return catchService.getCatchesBySpeciesNameAndLocation(speciesName, pickupAddress);
        } else if (speciesName != null) {
            return catchService.getCatchesBySpeciesName(speciesName);
        } else if (pickupAddress != null) {
            return catchService.getCatchesByLocation(pickupAddress);
        } else {
            return catchService.getAllCatches();
        }
    }

    @PostMapping
    public ResponseEntity<Catch> createCatch(@RequestBody CatchCreateDTO catchToCreate) {
        Catch newCatch = catchService.createCatch(catchToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCatch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catch> updateCatch(
            @PathVariable long id,
            @RequestBody CatchCreateDTO dto) {  // reuse CatchCreateDTO if identical
        Catch updatedCatch = catchService.updateCatch(id, dto);
        return (updatedCatch != null) ? ResponseEntity.ok(updatedCatch) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCatchById(@PathVariable long id) {
        if (catchService.getCatchById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        catchService.deleteCatchById(id);
        return ResponseEntity.noContent().build();
    }
}
