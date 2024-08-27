package comCrudOperationSpringboot.example.CrudOperations.Services;
//1 . returning directly Entity(employeeEntity) to controller , which is incorrect



//package com.springCrud.example.SpringCrudOperations.Services;
//
//import com.springCrud.example.SpringCrudOperations.Entities.EmployeeEntity;
//import com.springCrud.example.SpringCrudOperations.Repositories.EmployeeRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//
//@Service
//public class EmployeeService {
//
//    private final EmployeeRepository empRepo;
//
//    public EmployeeService(EmployeeRepository empRepo)
//    {
//        this.empRepo = empRepo;
//    }
//
//
//    public List<EmployeeEntity> getAllEmployee()
//    {
//        return empRepo.findAll();
//    }
//
//
//    public EmployeeEntity getOneEmployee(Long id)
//    {
//        return empRepo.findById(id).orElse(null);
//    }
//
//    public EmployeeEntity createEmployee(EmployeeEntity inputEmp)
//    {
//        return empRepo.save(inputEmp);
//    }
//}



















//2. with the help of ModelMapper, we can convert Entity to DTO and can return it to controller as recommended too


import comCrudOperationSpringboot.example.CrudOperations.DTO.EmployeeDTO;
import comCrudOperationSpringboot.example.CrudOperations.Entity.EmployeeEntity;
import comCrudOperationSpringboot.example.CrudOperations.Repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository empRepo;

    private final ModelMapper modelmapper;
    public EmployeeService(EmployeeRepository empRepo,ModelMapper modelmapper)
    {
        this.modelmapper = modelmapper;
        this.empRepo = empRepo;
    }


    public List<EmployeeDTO> getAllEmployee()
    {
        List<EmployeeEntity> allEmpEntities = empRepo.findAll();

        return allEmpEntities
                .stream()
                .map(employeeEntity -> modelmapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());


    }


    public EmployeeDTO getOneEmployee(Long id)
    {
        EmployeeEntity empEntity =  empRepo.findById(id).orElse(null);
        return modelmapper.map(empEntity,EmployeeDTO.class);
    }



    public ResponseEntity<?> createEmployee(EmployeeDTO inputEmpDto) {
        try {
            // Convert DTO to Entity
            EmployeeEntity toSaveEntity = modelmapper.map(inputEmpDto, EmployeeEntity.class);

            // Save the entity to the database
            EmployeeEntity savedEntity = empRepo.save(toSaveEntity);

            // Convert saved Entity back to DTO
            EmployeeDTO savedEmpDto = modelmapper.map(savedEntity, EmployeeDTO.class);

            // Return a success response with created status
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmpDto);
        } catch (Exception e) {
            // Log the error and return a proper error response
            System.err.println("Error occurred in the service layer: " + e.getMessage());

            // Returning a JSON formatted error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error creating employee\", \"details\": \"" + e.getMessage() + "\"}");
        }
    }





    //    DELETE
    public EmployeeDTO updateEmpById(@RequestBody EmployeeDTO inputEmp , Long empId)
    {

        System.out.println("inputEmp : " + inputEmp.getName());
        EmployeeEntity empEntity = modelmapper.map(inputEmp,EmployeeEntity.class);

        empEntity.setEmpId(empId);

        EmployeeEntity savedEmp = empRepo.save(empEntity);
        return modelmapper.map(savedEmp,EmployeeDTO.class);

    }

}




