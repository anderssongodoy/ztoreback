package pe.idat.ztore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.ztore.model.DetailsOrder;

@Repository
public interface DetailsOrderRepository extends JpaRepository<DetailsOrder, Long> {

}
