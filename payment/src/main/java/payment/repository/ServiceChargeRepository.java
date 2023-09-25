package payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payment.entity.ServiceCharge;

@Repository
public interface ServiceChargeRepository extends JpaRepository<ServiceCharge, Long> {
}
