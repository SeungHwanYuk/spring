package dw.wholesale_company.controller;

import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.service.MileageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MileageController {
    MileageService mileageService;
    @Autowired
    public MileageController(MileageService mileageService) {
        this.mileageService = mileageService;
    }

    @GetMapping("/mileages")
    public ResponseEntity<List<Mileage>> getAllMileage() {
        return new ResponseEntity<>(mileageService.getAllMileage(), HttpStatus.OK);
    }
}
