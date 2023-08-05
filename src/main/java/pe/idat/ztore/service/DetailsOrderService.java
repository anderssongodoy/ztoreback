package pe.idat.ztore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.DetailsOrder;

@Service
public interface DetailsOrderService {

	DetailsOrder save(DetailsOrder detailsOrder);
	
	DetailsOrder findById(Long id);
	
	List<DetailsOrder> getAll();
}
