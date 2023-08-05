package pe.idat.ztore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Customer;

@Service
public interface CustomerService {

	Customer save(Customer customer);
	
	Customer findById(Long id);
	
	boolean delete(Long id);
	
	Customer update(Customer customer);
	
	List<Customer> getAll();
}
