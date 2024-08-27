package com.avinash.springbootwebservices.Springboot_webservices_Architecture.Controller;

import com.avinash.springbootwebservices.Springboot_webservices_Architecture.dto.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {
    @GetMapping(path = "/getapi")
    public String getFirstGetApi() {
        return "Jai shree Ram";
    }

//1. => http://localhost:8080/3
    @GetMapping(path = "/getbyemployee/{empid}")
    public Employee getEmpDetails(@PathVariable int empid)
    {
        return new Employee(empid,"Avinash","avinash@gmail.com", LocalDate.of(2024,8,1),true);
    }



//    2.a => making URL with params (params is compulsory)
//    http://localhost:8080/employee?age=21
    @GetMapping("/employee")
    public String getEmployeeByage(@RequestParam Integer age)
    {
        return "Employee age is " + age;
    }


    //    2.b => making URL with params (params is not compulsory, optional)
//    http://localhost:8080/employee?age=21
    @GetMapping("/employee1")
    public String getEmployeeByage1(@RequestParam(required = false) Integer age)
    {
        return "Employee age is " + age;
    }
//    3. =>passing multiple params
@GetMapping("/employee2")
public String getEmployeeByage2(@RequestParam(required = false) Integer age,@RequestParam(required = false) String name)
{
    return "Employee age is " + age + " " + name;
}





}
