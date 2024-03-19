package com.example.hrconnectpro.controller;

import com.example.hrconnectpro.entities.Absense;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.service.AbsenseService;
import com.example.hrconnectpro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1 /absenses")
public class AbsenseController {

    @Autowired
    private AbsenseService absenseService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public String index(Model model) {
        model.addAttribute("Absenses", absenseService.getAllAbsenses());
        return "absenses/all";
    }

    // @GetMapping("/new")
    // public String showNewEmployeeForm(Model model) {
    // model.addAttribute("employee", new Employee());
    // return "employees/new";
    // }

    @PostMapping("/save/{employee_id}")
    public String saveAbsense(@ModelAttribute("absense") Absense absense,
            @PathVariable(value = "employee_id") long id) {
        Employee employee = employeeService.getEmployeeById(id);

        absenseService.saveAbsense(absense, employee);
        return "redirect:/employees/details/" + employee.getId();
    }

    // @GetMapping("/details/{id}")
    // public String details(@PathVariable(value = "id") long id, Model model) {
    // Employee employee = absenseService.getEmployeeById(id);

    // model.addAttribute("employee", employee);
    // return "employees/details";
    // }

    // @GetMapping("/edit/{id}")
    // public String showFormForUpdate(@PathVariable(value = "id") long id, Model
    // model) {
    // Employee employee = absenseService.getEmployeeById(id);

    // model.addAttribute("employee", employee);
    // return "employees/edit";
    // }

    // @GetMapping("/delete/{id}")
    // public String deleteEmployee(@PathVariable(value = "id") long id) {

    // this.absenseService.deleteEmployeeById(id);
    // return "redirect:/employees/all";
    // }
}
