package fr.afpa.library.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.library.dao.DaoBook;
import fr.afpa.library.dao.DaoCopy;
import fr.afpa.library.model.Copy;
import fr.afpa.library.service.IServiceCopy;
import fr.afpa.library.service.ServiceCopy;

/**
 * Servlet implementation class copiesServlet
 */
public class copiesServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private IServiceCopy service;

	public void init() throws ServletException {
		DaoCopy daoC = new DaoCopy();
		DaoBook daoB = new DaoBook();
		this.service = new ServiceCopy(daoC, daoB);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		  switch (action) {
		  	case "/copies" :
		  		showCopiesPanel(request, response);
		  		break;
		  	case "/addCopies" :
		  		addCopies(request, response);
		  		break;
		  	case "/deleteCopy" :
		  		deleteCopy(request, response);
		  		break;
		  	case "/repairCopy" :
		  		repairCopy(request, response);
		  		break;
		  	case "/sendRepair" :
		  		sendRepair(request, response);
		  		break;
		  	default:
		  		showCopiesPanel(request, response);
		  		break;
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void showCopiesPanel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("books", service.getBooks());
		request.setAttribute("copies", service.getCopies());
		getServletContext().getRequestDispatcher("/WEB-INF/views/copies.jsp").forward(request, response);
	}
	
	public void addCopies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbOfCopies = Integer.valueOf(request.getParameter("numberOfCopies"));
		String isbn = request.getParameter("bookSelected");
		List<Copy> copiesToAdd = new ArrayList<Copy>();
		for(int i = 0; i<nbOfCopies; i++) {
			copiesToAdd.add(new Copy(service.getBook(isbn), true, false));
		}
		service.addCopies(copiesToAdd);
		response.sendRedirect("copies");
	}
	
	public void deleteCopy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service.deleteCopy(Integer.valueOf(request.getParameter("id")));
		response.sendRedirect("copies");
	}
	
	public void sendRepair(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		service.sendRepair(id);
		response.sendRedirect("copies");
	}
	
	public void repairCopy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		service.repairCopy(id);
		response.sendRedirect("copies");
	}
	
}
