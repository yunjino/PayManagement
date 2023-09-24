package payment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    private Integer empId;
    private String name;
    private String address;

    public Employee(Integer empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }
}
