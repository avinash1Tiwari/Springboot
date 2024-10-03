
//1. before using Repository-layer

//package com.springCrud.example.SpringCrudOperations.Controllers;
//
//
//import com.springCrud.example.SpringCrudOperations.dto.Employee;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDate;


//@RestController
//public class EmployeeController {
//    @GetMapping(path = "/getapi")
//    public String getFirstGetApi() {
//        return "Jai shree Ram";
//    }
//
//    //1. => http://localhost:8080/3
//    @GetMapping(path = "/getbyemployee/{empid}")
//    public Employee getEmpDetails(@PathVariable int empid)
//    {
//        return new Employee(empid,"Avinash","avinash@gmail.com", LocalDate.of(2024,8,1),true);
//    }
//
//
//
//    //    2.a => making URL with params (params is compulsory)
////    http://localhost:8080/employee?age=21
//    @GetMapping("/employee")
//    public String getEmployeeByage(@RequestParam Integer age)
//    {
//        return "Employee age is " + age;
//    }
//
//
//    //    2.b => making URL with params (params is not compulsory, optional)
////    http://localhost:8080/employee?age=21
//    @GetMapping("/employee1")
//    public String getEmployeeByage1(@RequestParam(required = false) Integer age)
//    {
//        return "Employee age is " + age;
//    }
//    //    3. =>passing multiple params
//    @GetMapping("/employee2")
//    public String getEmployeeByage2(@RequestParam(required = false) Integer age,@RequestParam(required = false) String name)
//    {
//        return "Employee age is " + age + " " + name;
//    }
//
//}

















//2. After using only Repository-Layer and not service-layer.

//package com.springCrud.example.SpringCrudOperations.Controllers;
//import com.springCrud.example.SpringCrudOperations.Entities.EmployeeEntity;
//import com.springCrud.example.SpringCrudOperations.Repositories.EmployeeRepository;
//import com.springCrud.example.SpringCrudOperations.dto.Employeedto;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class EmployeeController {
//
//    private final EmployeeRepository employeeRepository;
//
//    public EmployeeController(EmployeeRepository employeeRepository)
//    {
//        this.employeeRepository = employeeRepository;
//    }
//
//
//
//
//    @GetMapping(path = "/getapi")
//    public String getFirstGetApi() {
//        return " !! Jai shree Ram !!";
//    }
//
//    //1. => http://localhost:8080/3
//    @GetMapping(path = "/getbyemployee/{id}")
//    public EmployeeEntity getEmployeeById(@PathVariable Long id)
//    {
//        return employeeRepository.findById(id).orElse(null);
//    }
//
//
//
//    //    2.a => making URL with params (params is compulsory)
////    http://localhost:8080/employee?age=21
//    @GetMapping("/allemployee")
//    public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false) Integer age)
//    {
//        return employeeRepository.findAll();
//    }
//
//
//
//
////    createEmployee
//    @PostMapping(path = "/createEmployee")
//    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity inputEmployee)
//    {
//        System.out.println("inputEmployee : " + inputEmployee.getName());
//        return employeeRepository.save(inputEmployee);
//    }
//
//}


















//3. After using service-layer and controller-layer
//package com.springCrud.example.SpringCrudOperations.Controllers;
//
//import com.springCrud.example.SpringCrudOperations.Entities.EmployeeEntity;
//import com.springCrud.example.SpringCrudOperations.Services.EmployeeService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/employee")
//public class EmployeeController{
//
//    private final EmployeeService empservice;
//
//    public EmployeeController(EmployeeService empservice)
//    {
//        System.out.println("comming");
//        this.empservice = empservice;
//    }
//
//    @GetMapping(path = "/cheking")
//    public String Checking()
//    {
//        return "all are working";
//    }
//
//    @GetMapping(path = "/getall")
//    public List<EmployeeEntity> getAllEmployee()
//    {
//        return empservice.getAllEmployee();
//    }
//
//    @GetMapping(path = "/{id}")
//    public EmployeeEntity getOneEmployee(@PathVariable Long id)
//    {
//        return empservice.getOneEmployee(id);
//    }
//
//    @PostMapping(path = "/create")
//    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity inputEmp)
//    {
//        return empservice.createEmployee(inputEmp);
//    }
//}



















//4. getting DTO instead of Entity(as 3. is getting Entity)
package com.springCrud.example.SpringCrudOperations.Controllers;

import com.springCrud.example.SpringCrudOperations.Entities.EmployeeEntity;
import com.springCrud.example.SpringCrudOperations.Services.EmployeeService;
import com.springCrud.example.SpringCrudOperations.dto.Employeedto;
import com.springCrud.example.SpringCrudOperations.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.ErrorResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class                                                                                                                                                                                                                        EmployeeController {

    private final EmployeeService empservice;

    public EmployeeController(EmployeeService empservice) {
        System.out.println("comming");
        this.empservice = empservice;
    }

    @GetMapping(path = "/cheking")
    public String Checking() {
        return "all are working";
    }

    @GetMapping(path = "/getall")
    public List<Employeedto> getAllEmployee() {
        return empservice.getAllEmployee();
    }

    @GetMapping(path = "/{id}")
    public Employeedto getOneEmployee(@PathVariable Long id) {
        return empservice.getOneEmployee(id);
    }


//@PostMapping(path = "/create")
//public ResponseEntity<?> createEmployee(@RequestBody Employeedto inputEmp) {
//    try {
//        Employeedto createdEmployee = empservice.createEmployee(inputEmp);;
//        return ResponseEntity.ok(createdEmployee);  // Return 200 OK with the created employee DTO
//    } catch (RuntimeException e) {
//        // Create a JSON error response and return it
//        ErrorResponse errorResponse = new ErrorResponse(
//                "Failed to create employee",
//                e.getMessage(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value()
//        );
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//    }
//}


    @PostMapping(path = "/create")
    public ResponseEntity<?> createEmployee(@RequestBody Employeedto inputEmp) {
        try {
            ResponseEntity<?> response = empservice.createEmployee(inputEmp);
            return response;
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "Failed to create employee",
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @PutMapping("/update/{empid}")
    public Employeedto updateEmpById(Employeedto inputEmp,Long empid)
    {
        return empservice.updateEmpById(inputEmp,empid);
    }


    @DeleteMapping("delete/{empid}")
    public String deleteById(Long empid)
    {
        return empservice.deleteById(empid);
    }

@PatchMapping("/{empid}")
public Employeedto updateFieldById(@PathVariable Long empid, @RequestBody Map<String,Object> updates)
{
    Employeedto updatedEmployee =  empservice.updateFieldById(empid,updates);
    System.out.println("employee updation done successfully");

    return updatedEmployee;
}


}