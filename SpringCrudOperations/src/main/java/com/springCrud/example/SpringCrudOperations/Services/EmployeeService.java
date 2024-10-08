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

package com.springCrud.example.SpringCrudOperations.Services;

import com.springCrud.example.SpringCrudOperations.Entities.EmployeeEntity;
import com.springCrud.example.SpringCrudOperations.Repositories.EmployeeRepository;
import com.springCrud.example.SpringCrudOperations.dto.Employeedto;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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


    public List<Employeedto> getAllEmployee()
    {
        List<EmployeeEntity> allEmpEntities = empRepo.findAll();

        return allEmpEntities
                .stream()
                .map(employeeEntity -> modelmapper.map(employeeEntity,Employeedto.class))
                .collect(Collectors.toList());


    }


    public Employeedto getOneEmployee(Long id)
    {
        EmployeeEntity empEntity =  empRepo.findById(id).orElse(null);
         return modelmapper.map(empEntity,Employeedto.class);
    }



    public ResponseEntity<?> createEmployee(Employeedto inputEmpDto) {
        try {
            // Convert DTO to Entity
            EmployeeEntity toSaveEntity = modelmapper.map(inputEmpDto, EmployeeEntity.class);

            // Save the entity to the database
            EmployeeEntity savedEntity = empRepo.save(toSaveEntity);

            // Convert saved Entity back to DTO
            Employeedto savedEmpDto = modelmapper.map(savedEntity, Employeedto.class);

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
    public Employeedto updateEmpById(@RequestBody Employeedto inputEmp , Long empId)
    {
        EmployeeEntity empEntity = modelmapper.map(inputEmp,EmployeeEntity.class);

        empEntity.setId(empId);
        EmployeeEntity savedEmp = empRepo.save(empEntity);
        return modelmapper.map(savedEmp,Employeedto.class);

//        checking

    }


    public String deleteById(Long empid)
    {
        boolean isFound = empRepo.existsById(empid);
        if(!isFound)
        {
            return "Employee with given empId is not found";
        }

        empRepo.deleteById(empid);
        return "Employee deleted successfully";
    }



    public boolean isEmployeeExisting(Long empId)
    {
        return empRepo.existsById(empId);
    }

//    public Employeedto updateFieldById(Long empid, Map<String, Object> updates) {
//
//        boolean exists = isEmployeeExisting((empid));
//        if(!exists) return null;
//
//        EmployeeEntity employeeEntity = empRepo.findById(empid).get();
//        updates.forEach((field,value) ->{
//            Field fieldTobeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
//            fieldTobeUpdated.setAccessible(true);
//            ReflectionUtils.setField(fieldTobeUpdated,employeeEntity,value);
//        });
//        return modelmapper(empRepo.save(employeeEntity),Employeedto.class);
//
//
//
//    }













    public Employeedto updateFieldById(Long empid, Map<String, Object> updates) {

        boolean exists = isEmployeeExisting(empid);
        if (!exists) return null;

        EmployeeEntity employeeEntity = empRepo.findById(empid).get();

        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });

        // Save updated entity
        EmployeeEntity updatedEntity = empRepo.save(employeeEntity);

        // Use ModelMapper to map the entity to DTO
        return modelmapper.map(updatedEntity, Employeedto.class);
    }
}
