package pe.idat.ztore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.ztore.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomerId(Long customerId);
    List<Cart> findByCustomerUsername(String username);
    void deleteByCustomerId(Long customerId);
    Long countByCustomerId(Long id);
}
