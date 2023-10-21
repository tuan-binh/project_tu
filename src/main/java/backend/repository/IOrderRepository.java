package backend.repository;

import backend.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Orders,Long> {
}
