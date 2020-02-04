package com.codingraja.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codingraja.dao.CustomerDao;
import com.codingraja.dao.impl.CustomerDaoImpl;
/**
 * Created in Eclipse IDE.
 * Project : spring-mvc-jdbc-master
 * User: yash
 * Date: 18/01/2020
 */

@WebServlet("/customer/delete")
public class DeleteCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteCustomerController() {
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

			customerDao.deleteCustomer(id);

			response.sendRedirect(request.getContextPath() + "/");
		}
	}
}
