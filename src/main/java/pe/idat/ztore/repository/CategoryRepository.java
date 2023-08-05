package pe.idat.ztore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.ztore.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
