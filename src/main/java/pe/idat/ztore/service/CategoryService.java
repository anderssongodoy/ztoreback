package pe.idat.ztore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Category;

@Service
public interface CategoryService {
	
	Category save(Category category);
	
	Category findById(Long id);
	
	boolean delete(Long id);
	
	Category update(Category category);
	
	List<Category> getAll();
}
