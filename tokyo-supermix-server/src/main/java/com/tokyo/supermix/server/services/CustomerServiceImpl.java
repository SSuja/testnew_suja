package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.repositories.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	  private CustomerRepository customerRepository;

	  @Transactional()
	  public List<Customer> getAllCustomers() {
	    return customerRepository.findAll();
	  }

	  @Transactional()
	  public void saveCustomer(Customer customer) {
	    customerRepository.save(customer);
	  }

	  @Transactional(readOnly = true)
	  public boolean isEmailExist(String email) {
	    return customerRepository.existsByEmail(email);
	  }

	  @Transactional(readOnly = true)
	  public boolean isCustomerExist(Long id) {
	    return customerRepository.existsById(id);
	  }

	  @Transactional(readOnly = true)
	  public Customer getCustomerById(Long id) {

	    return customerRepository.findById(id).get();
	  }

	  @Override
	  public boolean isUpdatedCustomerEmailExist(Long id, String email) {
	    if ((!getCustomerById(id).getEmail().equalsIgnoreCase(email)) && (isEmailExist(email))) {
	      return true;
	    }
	    return false;
	  }


	  @Transactional()
	  public Customer updateCustomer(Customer customer) {
	    return customerRepository.save(customer);
	  }

	  @Transactional()
	  public void deleteCustomer(Long id) {
	    customerRepository.deleteById(id);

	  }

}
