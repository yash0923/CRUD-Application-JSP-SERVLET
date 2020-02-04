package com.codingraja.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.codingraja.dao.CustomerDao;
import com.codingraja.db.Database;
import com.codingraja.domain.Customer;

public class CustomerDaoImpl implements CustomerDao {

	private static CustomerDaoImpl customerDaoImpl = null;

	private Connection connection = Database.getConnection();

	public long saveCustomer(Customer customer) {
		String sql = "INSERT INTO customer_master.customer_master(first_name, last_name, email, mobile)" 
					+ "VALUES(?,?,?,?)";
		long id = 0;

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getMobile());

			// Creating Customer Account
			if (pstmt.executeUpdate() > 0) {
				ResultSet rs = pstmt.getGeneratedKeys(); // Returns Generated
															// Primary Key

				if (rs.next())
					id = rs.getLong(1);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return id;
	}

	public void updateCustomer(Customer customer) {
		String sql = "UPDATE customer_master.customer_master SET first_name=?, last_name=?, email=?, mobile=? " 
					+ "WHERE customer_id=?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getMobile());
			pstmt.setLong(5, customer.getId());

			// Update Customer Account
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void deleteCustomer(Long id) {
		String sql = "DELETE FROM customer_master.customer_master WHERE customer_id=?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);

			// Delete Customer Account
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public Customer findCustomerById(Long id) {
		String sql = "SELECT * FROM customer_master.customer_master WHERE customer_id=?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);

			// Getting Customer Detail
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getLong(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setEmail(resultSet.getString(4));
				customer.setMobile(resultSet.getString(5));

				return customer;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}

	public List<Customer> findAllCustomers() {
		String sql = "SELECT * FROM customer_master.customer_master";
		List<Customer> customers = null;
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);

			// Getting Customer's Detail
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				if (customers == null)
					customers = new ArrayList<>();

				Customer customer = new Customer();
				customer.setId(resultSet.getLong(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setEmail(resultSet.getString(4));
				customer.setMobile(resultSet.getString(5));

				customers.add(customer);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return customers;
	}

	public static CustomerDao getInstance() {
		if (customerDaoImpl == null)
			customerDaoImpl = new CustomerDaoImpl();

		return customerDaoImpl;
	}
}
