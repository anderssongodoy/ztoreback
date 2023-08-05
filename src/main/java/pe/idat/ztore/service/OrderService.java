package pe.idat.ztore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Order;

@Service
public interface OrderService {

	Order save(Order order);
	
	Order findById(Long id);
	
	boolean delete(Long id);
	
	Order update(Order order);
	
	List<Order> getAll();
}
