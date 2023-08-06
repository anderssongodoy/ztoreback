package pe.idat.ztore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Cart;
import pe.idat.ztore.repository.CartRepository;

@Service
@Transactional
public class CartService {
    
    private final CartRepository cartRepository;
    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getListByCustomer(String username){
        return this.cartRepository.findByCustomerUsername(username);
    }

    public void cleanCart(Long customerId){
        this.cartRepository.deleteByCustomerId(customerId);
    }

    public void removeProduct(Long id){
        this.cartRepository.deleteById(id);
    }

    public void addProduct(Cart cart){
        this.cartRepository.save(cart);
    }

    public Long getCountByCustomer(Long customerId){
        return this.cartRepository.countByCustomerId(customerId);
    }
    
}
