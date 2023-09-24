package payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payment.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
