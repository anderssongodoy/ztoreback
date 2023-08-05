package pe.idat.ztore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Product;

@Service
public interface ProductService {

	Product save(Product product);
	
	Product findById(Long id);
	
	boolean delete(Long id);
	
	Product update(Product product);
	
	List<Product> getAll();
}
