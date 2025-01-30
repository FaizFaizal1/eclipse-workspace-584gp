package courier.controller;

/**
 * Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
 * Date: June 2024
 */

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import courier.dao.DispatcherDAO;
import courier.dao.StaffDAO;
import courier.model.Dispatcher;
import courier.model.Staff;

import java.io.IOException;

/**
 * Servlet implementation class RegisterController
 */
public class AddDispatcherController extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	private RequestDispatcher view;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDispatcherController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Staff staff = new Staff();
		Dispatcher dispatcher = new Dispatcher();
		//retrieve input and set
		staff.setStaffFirstName(request.getParameter("dispatcher_first_name"));
		staff.setStaffLastName(request.getParameter("dispatcher_last_name"));
		staff.setStaffPhoneNumber(request.getParameter("dispatcher_phone_number"));
		staff.setStaffEmail(request.getParameter("dispatcher_email"));
		staff.setStaffAddress(request.getParameter("dispatcher_address"));
		staff.setStaffPassword(request.getParameter("dispatcher_password"));
		staff.setStaffRole(request.getParameter("dispatcher_role"));
		dispatcher.setDispatcherEmploymentType("dispatcher_employment_type");

		staff = StaffDAO.getStaff(staff);
		//check if dispatcher exists
		if(!staff.isValid()){
			try {
				//if dispatcher not exist, add/register the dispatcher
				StaffDAO.addStaff(staff);
				staff = StaffDAO.getStaffByEmail("dispatcher_email");
				staff.setDispatcher(dispatcher);
				DispatcherDAO.updateDispatcher(staff.getDispatcher());
			} catch (Exception e) {
				e.printStackTrace();
			}

			RequestDispatcher view = request.getRequestDispatcher("index.html");
			view.forward(request, response);
		}        
	}
}