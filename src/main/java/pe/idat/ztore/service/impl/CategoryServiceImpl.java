package pe.idat.ztore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Category;
import pe.idat.ztore.repository.CategoryRepository;
import pe.idat.ztore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category findById(Long id) {
		if(!categoryRepository.existsById(id)) {
			throw new RuntimeException("category not found");
		}
		return categoryRepository.findById(id).get();
	}

	@Override
	public boolean delete(Long id) {
		Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Vehicle not found");
        }

        Category category = categoryOptional.get();
        category.setSoftDelete(true);
        categoryRepository.save(category);
        return true;
	}

	@Override
	public Category update(Category category) {
		Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Category categoryUpdated = categoryOptional.get();

        if (category.getDescription() != null){
            categoryUpdated.setDescription(category.getDescription());
        }

        if (category.getName() != null){
            categoryUpdated.setName(category.getName());
        }

        return categoryRepository.save(categoryUpdated);
	}

	@Override
	public List<Category> getAll() {
		List<Category> category = categoryRepository.findAll();
        if (category.isEmpty()){
            throw new RuntimeException("No category found");
        }
        return category;
	}

}
