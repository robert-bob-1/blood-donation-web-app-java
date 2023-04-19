package com.BloodDonation.BloodDonation.controller;

import com.BloodDonation.BloodDonation.entity.users.Admin;
import com.BloodDonation.BloodDonation.entity.users.Admin;
import com.BloodDonation.BloodDonation.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private final AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{email}")
    ResponseEntity<Admin> getAdmin(@PathVariable("email") String email){
        Admin foundAdmin = adminService.getAdminByEmail(email);
        if (foundAdmin != null)
            return ResponseEntity.ok(foundAdmin);
        else
            return (ResponseEntity<Admin>) ResponseEntity.notFound();
    }
}
