package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.Customer;

public interface CustomerService {
	/* Add Customer */
	public void saveCustomer(Customer customer);

	/* Get All Customers */
	public List<Customer> getAllCustomers();

	/* Check Existing email */
	public boolean isEmailExist(String email);

	/* Check Existing id */
	boolean isCustomerExist(Long id);

	/* Get Customer By Id */
	public Customer getCustomerById(Long id);

	/* Check updated Email */
	public boolean isUpdatedCustomerEmailExist(Long id, String email);

	/* Update Customer */
	public Customer updateCustomer(Customer customer);

	/* Delete Customer */
	public void deleteCustomer(Long id);

}
