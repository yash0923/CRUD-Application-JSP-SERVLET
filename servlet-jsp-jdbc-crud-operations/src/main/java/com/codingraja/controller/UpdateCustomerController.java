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

@WebServlet("/customer/update")
public class UpdateCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateCustomerController() {
		// Do Nothing
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String custId = request.getParameter("custId");

		if (custId == "" || custId == null)
			request.getRequestDispatcher("/").forward(request, response);
		else {
			Long id = Long.parseLong(custId);
			CustomerDao customerDao = CustomerDaoImpl.getInstance();
			Customer customer = customerDao.findCustomerById(id);

			request.setAttribute("customer", customer);

			request.getRequestDispatcher("/").forward(request, response);
		}
	}
}
