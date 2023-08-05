package pe.idat.ztore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Customer;
import pe.idat.ztore.repository.CustomerRepository;
import pe.idat.ztore.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

private final CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(Long id) {
		if(!customerRepository.existsById(id)) {
			throw new RuntimeException("customer not found");
		}
		return customerRepository.findById(id).get();
	}

	@Override
	public boolean delete(Long id) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new RuntimeException("Vehicle not found");
        }

        Customer customer = customerOptional.get();
        customer.setSoftDelete(true);
        customerRepository.save(customer);
        return true;
	}

	@Override
	public Customer update(Customer customer) {
		Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        if (customerOptional.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }

        Customer customerUpdated = customerOptional.get();

        if (customer.getEmail() != null){
            customerUpdated.setEmail(customer.getEmail());
        }

        if (customer.getPassword() != null){
            customerUpdated.setPassword(customer.getPassword());;
        }

        return customerRepository.save(customerUpdated);
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> customer = customerRepository.findAll();
        if (customer.isEmpty()){
            throw new RuntimeException("No customer found");
        }
        return customer;
	}
}
