package fr.afpa.library.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.library.dao.DaoSub;
import fr.afpa.library.model.Subscriber;
import fr.afpa.library.service.IServiceSubs;
import fr.afpa.library.service.ServiceSub;

/**
 * Servlet implementation class subServlet
 */
public class subServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	IServiceSubs service; 

	public void init() throws ServletException {
		DaoSub dao = new DaoSub();
		this.service = new ServiceSub(dao);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		  switch (action) {
		  	case "/subs" :
		  		showSubsPanel(request, response);
		  		break;
		  	case "/addSub" :
		  		addSub(request, response);
		  		break;
		  	case "/editSub" :
		  		editSub(request, response);
		  		break;
		  	case "/editSubForm" :
		  		showEditForm(request, response);
		  		break;
		  	case "/deleteSub" :
		  		deleteSub(request, response);
		  		break;
		  	case "/subDetails" :
		  		subDetails(request, response);
		  		break;
		  	default:
		  		showSubsPanel(request, response);
		  		break;
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void showSubsPanel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("subs", service.getSubs());
		getServletContext().getRequestDispatcher("/WEB-INF/views/subs.jsp").forward(request, response);
	}
	
	public void addSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ln = request.getParameter("lastname");
		String fn = request.getParameter("firstname");
		String street = request.getParameter("street");
		String zip = request.getParameter("zipcode");
		String city = request.getParameter("city");
		
		service.addSub(new Subscriber(ln, fn, street, zip, city));
		
		response.sendRedirect("subs");
	}
	
	public void deleteSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service.deleteSub(Integer.valueOf(request.getParameter("id")));
		response.sendRedirect("subs");
	}
	
	public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Subscriber s = service.getSub(id);
		request.setAttribute("subToEdit", s);
		getServletContext().getRequestDispatcher("/WEB-INF/views/editSubForm.jsp").forward(request, response);
	}
	
	public void editSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String ln = request.getParameter("lastname");
		String fn = request.getParameter("firstname");
		String street = request.getParameter("street");
		String zip = request.getParameter("zip");
		String city = request.getParameter("city");
		Subscriber s = service.getSub(id);
		s.setLastname(ln);s.setFirstname(fn);s.setStreet(street);s.setZipcode(zip);s.setCity(city);
		service.editSub(s);
		response.sendRedirect("subs");
	}
	
	public void subDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Subscriber s = service.getSub(Integer.valueOf(request.getParameter("id")));
		request.setAttribute("sub", s);
		getServletContext().getRequestDispatcher("/WEB-INF/views/subDetails.jsp").forward(request, response);
	}

}
