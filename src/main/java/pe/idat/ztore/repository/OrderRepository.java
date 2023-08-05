package pe.idat.ztore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.ztore.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
