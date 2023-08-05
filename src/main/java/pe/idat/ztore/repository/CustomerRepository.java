package pe.idat.ztore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.ztore.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Optional<Customer> findByEmail(String email);
}
