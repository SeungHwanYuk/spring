package dw.wholesale_company.controller;

import dw.wholesale_company.model.Department;
import dw.wholesale_company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {
    DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartment() {
        return new ResponseEntity<>(departmentService.getAllDepartment(), HttpStatus.OK);}
}

