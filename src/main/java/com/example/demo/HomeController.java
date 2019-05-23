package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String listDepartment(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentlist";
    }

    @GetMapping("/adddepartment")
    public String deparmentForm(Model model) {
        model.addAttribute("employee", employeeRepository.findAll());
        model.addAttribute("department", new Department());
        return "departmentform";
    }

    @PostMapping("/processdepartment")
    public String processForm(@Valid Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeRepository.findAll());
            return "departmentform";
        }
        departmentRepository.save(department);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")

    public String showDepartment(@PathVariable("id") long id, Model model) {
        model.addAttribute("department", departmentRepository.findById(id).get());

        return "employeelist";
    }

    @RequestMapping("/update/{id}")
    public String updateAlbum(@PathVariable("id") long id, Model model) {
        model.addAttribute("employees", employeeRepository.findAll());

        model.addAttribute("department", departmentRepository.findById(id).get());
        return "departmentform";

    }

    @RequestMapping("/delete/{id}")
    public String delAlbum(@PathVariable("id") long id) {
        departmentRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/addemployee/{id}")
    public String addSongs(@PathVariable("id") long id, Model model) {
        model.addAttribute("department", departmentRepository.findById(id).get());

        return "redirect:/addemployees";
    }


    @GetMapping("/addemployees")
    public String employeeForm(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", new Employee());

        return "employeeform";
    }

    @PostMapping("/processemployee")
    public String processForm(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employeeform";
        }

        employeeRepository.save(employee);
        return "redirect:/";
    }

    @RequestMapping("/detail/employee/{id}")
    public String showemployee(@PathVariable("id") long id, Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id).get());

        return "employeelist";
    }
    @RequestMapping("/update/employee{id}")
    public String updateemployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employeeform";

    }
    @RequestMapping("/delete/employee{id}")
    public  String delemployee(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/";
    }
}
