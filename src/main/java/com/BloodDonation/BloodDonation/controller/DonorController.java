package com.BloodDonation.BloodDonation.controller;

import com.BloodDonation.BloodDonation.dto.DonorCreateDTO;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.mapper.DonorMapper;
import com.BloodDonation.BloodDonation.service.DonorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/donor")
@CrossOrigin(origins = "http://localhost:4200")
public class DonorController {

    private final DonorService donorService;
    private final DonorMapper donorMapper;

    public DonorController(DonorService donorService, DonorMapper donorMapper){
        this.donorService = donorService;
        this.donorMapper = donorMapper;
    }

    @PostMapping("")
    ResponseEntity<Donor> registerDonor(@RequestBody DonorCreateDTO dto){
        Donor newDonor = donorMapper.fromCreateDTOToDonor(dto);
        Donor registeredDonor = donorService.registerDonor(newDonor);
        return ResponseEntity.ok(registeredDonor);
    }

    @GetMapping("/{email}")
    ResponseEntity<?> getDonor(@PathVariable("email") String email){
        Donor foundDonor = donorService.getDonorByEmail(email);
        if (foundDonor != null)
            return ResponseEntity.ok(foundDonor);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("")
    ResponseEntity<?> updateDonor(@RequestBody Donor donor){
        Donor updatedDonor = donorService.updateDonor(donor);
        if (updatedDonor != null)
            return ResponseEntity.ok(updatedDonor);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{uuid}")
    ResponseEntity<?> deleteDonor(@PathVariable("uuid") UUID uuid){
        donorService.deleteDonor(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
