package com.codingraja.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codingraja.dao.CustomerDao;
import com.codingraja.dao.impl.CustomerDaoImpl;
import com.codingraja.domain.Customer;
/**
 * Created in Eclipse IDE.
 * Project : spring-mvc-jdbc-master
 * User: yash
 * Date: 18/01/2020
 */

@WebServlet("/customer/register")
public class CustomerRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDao customerDao = CustomerDaoImpl.getInstance();

	public CustomerRegistrationController() {
		// Do Nothing
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String custId = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");

		Customer customer = new Customer(firstName, lastName, email, mobile);

		if (custId == null || custId == "")
			customerDao.saveCustomer(customer);
		else {
			Long id = Long.parseLong(custId);
			customer.setId(id);
			customerDao.updateCustomer(customer);
		}

		response.sendRedirect(request.getContextPath() + "/");
	}

}
