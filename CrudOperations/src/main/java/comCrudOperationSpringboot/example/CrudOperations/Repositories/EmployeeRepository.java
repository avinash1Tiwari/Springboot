package comCrudOperationSpringboot.example.CrudOperations.Repositories;

import comCrudOperationSpringboot.example.CrudOperations.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long>
{
//        List<EmployeeEntity> findByName(String name);

}