package fr.afpa.library.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.library.dao.*;
import fr.afpa.library.model.*;
import fr.afpa.library.service.*;


public class borrowingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String MassageDateError = "";

	private IServiceBor service;

	public void init() throws ServletException {
		DaoBor dao = new DaoBor();
		this.service = new ServiceBor(dao);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		  switch (action) {
		  	case "/borrowings" :
		  		showBorPanel(request, response);
		  		break;
		  	case "/createBorrowing" :
		  		addBor(request, response);
		  		break;
		  	case "/editBor" :
		  		editBor(request, response);
		  		break;
		  	case "/editBorForm" :
		  		showForm(request, response);
		  		break;
		  	case "/returnCopy" :
		  		returnCopy(request, response);
		  		break;
		  	case "/deleteBor" :
		  		deleteBor(request, response);
		  		break;
		  	default:
		  		showBorPanel(request, response);
		  		break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void showBorPanel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("copies", service.getCopies());
		request.setAttribute("subs", subsLimitNotReached());
		request.setAttribute("bors", service.getBors());
		request.setAttribute("dateError", MassageDateError);
		MassageDateError = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String todayDate = sdf.format(today);
		request.setAttribute("today", todayDate);
		
		int noOfDays = 14;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		Date date = calendar.getTime();
		String expectedDate = sdf.format(date);
		request.setAttribute("expected", expectedDate);
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/borrowings.jsp").forward(request, response);
	}
	
	private void addBor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Copy c = service.getCopy(Integer.valueOf(request.getParameter("copySelected")));
		Subscriber s = service.getSub(Integer.valueOf(request.getParameter("subSelected")));
		try {
			service.addBor(new Borrowing(request.getParameter("borrowDate"), request.getParameter("expectedDate"), s, c));
		} catch (DateException e) {
			MassageDateError = e.getMessage();
		}
		response.sendRedirect("borrowings");
	}
	
	private List<Subscriber> subsLimitNotReached() {
		List<Subscriber> validSubs = new ArrayList<Subscriber>();
		for(Subscriber s : service.getSubs()) {
			int cpt = 0;
			for(Borrowing b : s.getBorrowings())
				if(b.getBorDateReturn() == null) {
					cpt++;
			}
			
			if(cpt < 5) validSubs.add(s);
		}
		
		return validSubs;
	}
	
	private void returnCopy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = Integer.valueOf(request.getParameter("id"));
		Borrowing b = service.getBor(id);
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);
		b.setBorDateReturn(currentTime);
		service.returnCopy(b);
		response.sendRedirect("borrowings");
	}
	
	private void deleteBor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		service.deleteBor(Integer.valueOf(request.getParameter("id")));
		response.sendRedirect("borrowings");
	}
	
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("borToEdit", service.getBor(Integer.valueOf(request.getParameter("id"))));
		request.setAttribute("dateError", MassageDateError);
		MassageDateError = "";
		getServletContext().getRequestDispatcher("/WEB-INF/views/editBorForm.jsp").forward(request, response);
	}
	
	private void editBor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = Integer.valueOf(request.getParameter("id"));
		Borrowing b = service.getBor(id);
		String borDate = request.getParameter("borDate");
		String exp = request.getParameter("expectedDate");
		b.setBorDate(borDate); b.setBorDateExpected(exp);
		
		if(b.getBorDateReturn() != null) {
			b.setBorDateReturn(request.getParameter("returnDate"));
		}
		
		try {
			service.editBor(b);
			response.sendRedirect("borrowings");
		} catch (DateException e) {
			MassageDateError = e.getMessage();
			response.sendRedirect("editBorForm?id="+id);
		}
	}
	
}