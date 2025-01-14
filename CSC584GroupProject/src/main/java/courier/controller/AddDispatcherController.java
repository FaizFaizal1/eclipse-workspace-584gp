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
		staff.setStaffFirstName(request.getParameter("staff_first_name"));		
		staff.setStaffLastName(request.getParameter("staff_last_name"));		
		staff.setStaffPhoneNumber(request.getParameter("staff_phone_number"));		
		staff.setStaffEmail(request.getParameter("staff_email"));		
		staff.setStaffAddress(request.getParameter("staff_address"));		
		staff.setStaffPassword(request.getParameter("staff_password"));
		staff.setStaffRole(request.getParameter("staff_role"));
		
		dispatcher.setDispatcherStatus("dispatcher_employment_type");
		staff.setDispatcher(dispatcher);

		staff = StaffDAO.getStaff(staff);
		
		//check if staff exists
		if(!staff.isValid()){
			try {
				//if staff not exist, add/register the staff
				StaffDAO.addDispatcher(staff);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//redirect to dashboard.jsp
			RequestDispatcher view = request.getRequestDispatcher("index.html"); // staff page
			view.forward(request, response);
		}        
	}
}