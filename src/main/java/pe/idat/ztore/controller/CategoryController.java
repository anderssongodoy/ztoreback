package pe.idat.ztore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.ztore.model.Category;
import pe.idat.ztore.service.impl.CategoryServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

	private final CategoryServiceImpl categoryService;
	
	@Autowired
	public CategoryController(CategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}

    @PostMapping("")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        try {
            if (category == null) {
                return ResponseEntity.badRequest().body(null);
            }

            Category savedCategory = categoryService.save(category);
            return ResponseEntity.ok(savedCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.findById(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategorys() {
        try {
            List<Category> categorys = categoryService.getAll();
            return ResponseEntity.ok(categorys);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.update(category);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
