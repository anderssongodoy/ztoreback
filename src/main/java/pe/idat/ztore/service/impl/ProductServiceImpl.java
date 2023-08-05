package pe.idat.ztore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Product;
import pe.idat.ztore.repository.ProductRepository;
import pe.idat.ztore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findById(Long id) {
		if(!productRepository.existsById(id)) {
			throw new RuntimeException("product not found");
		}
		return productRepository.findById(id).get();
	}

	@Override
	public boolean delete(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Vehicle not found");
        }

        Product product = productOptional.get();
        product.setSoftDelete(true);
        productRepository.save(product);
        return true;
	}

	@Override
	public Product update(Product product) {
		Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        Product productUpdated = productOptional.get();

        if (product.getDescription() != null){
            productUpdated.setDescription(product.getDescription());
        }

        if (product.getName() != null){
            productUpdated.setName(product.getName());
        }

        return productRepository.save(productUpdated);
	}

	@Override
	public List<Product> getAll() {
		List<Product> product = productRepository.findAll();
        if (product.isEmpty()){
            throw new RuntimeException("No product found");
        }
        return product;
	}
}
