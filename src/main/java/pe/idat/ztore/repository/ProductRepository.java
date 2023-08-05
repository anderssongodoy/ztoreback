package pe.idat.ztore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.ztore.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
