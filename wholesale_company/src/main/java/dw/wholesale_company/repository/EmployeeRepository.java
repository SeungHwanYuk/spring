package dw.wholesale_company.repository;


import dw.wholesale_company.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, String> {


//    @Query("select e1 from Employee e1 where e1.position = 사원 order by e1.hiredate desc")

}
