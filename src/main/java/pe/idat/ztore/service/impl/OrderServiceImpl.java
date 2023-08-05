package pe.idat.ztore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Order;
import pe.idat.ztore.repository.OrderRepository;
import pe.idat.ztore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

private final OrderRepository orderRepository;
	
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order findById(Long id) {
		if(!orderRepository.existsById(id)) {
			throw new RuntimeException("order not found");
		}
		return orderRepository.findById(id).get();
	}

	@Override
	public boolean delete(Long id) {
		Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Vehicle not found");
        }

        Order order = orderOptional.get();
        order.setSoftDelete(true);
        orderRepository.save(order);
        return true;
	}

	@Override
	public Order update(Order order) {
		Optional<Order> orderOptional = orderRepository.findById(order.getId());
        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Order not found");
        }

        Order orderUpdated = orderOptional.get();

        if (order.getDate() != null){
            orderUpdated.setDate(order.getDate());;
        }

        return orderRepository.save(orderUpdated);
	}

	@Override
	public List<Order> getAll() {
		List<Order> order = orderRepository.findAll();
        if (order.isEmpty()){
            throw new RuntimeException("No order found");
        }
        return order;
	}
}
