package com.codingraja.dao;

import java.util.List;

import com.codingraja.domain.Customer;
/**
 * Created in Eclipse IDE.
 * Project : spring-mvc-jdbc-master
 * User: yash
 * Date: 18/01/2020
 */

public interface CustomerDao {
	long saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(Long id);

	Customer findCustomerById(Long id);

	List<Customer> findAllCustomers();
}
