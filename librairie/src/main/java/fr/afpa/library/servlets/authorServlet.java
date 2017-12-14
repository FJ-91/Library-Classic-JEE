package fr.afpa.library.servlets;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.library.dao.DaoAuthors;
import fr.afpa.library.dao.DuplicateException;
import fr.afpa.library.model.Author;
import fr.afpa.library.service.IServiceAuthors;
import fr.afpa.library.service.ServiceAuthors;


public class authorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String duplicateAuthor = "";
	
	IServiceAuthors serviceAuthors;

	public void init() throws ServletException {
		DaoAuthors daoAuthors = new DaoAuthors();
		this.serviceAuthors = new ServiceAuthors(daoAuthors);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		  switch (action) {
		  	case "/authors" :
		  		showAuthorsPanel(request, response);
		  		break;
		  	case "/addAuthor" :
		  		addAuthor(request, response);
		  		break;
		  	case "/editAuthor" :
		  		editAuthor(request, response);
		  		break;
		  	case "/editAuthorForm" :
		  		showEditForm(request, response);
		  		break;
		  	case "/deleteAuthor" :
		  		deleteAuthor(request, response);
		  		break;
		  	case "/authorDetails" :
		  		authorDetails(request, response);
		  		break;
		  	default:
		  		showAuthorsPanel(request, response);
		  		break;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void showAuthorsPanel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Author> authors = new ArrayList<Author>();
		authors = serviceAuthors.getAuthors();
		request.setAttribute("authors", authors);
		request.setAttribute("duplicateAuthor", duplicateAuthor);
		duplicateAuthor = "";
		getServletContext().getRequestDispatcher("/WEB-INF/views/authors.jsp").forward(request, response);
	}
	
	public void addAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ln = request.getParameter("lastname");
		String fn = request.getParameter("firstname");
		String dob = request.getParameter("dob");
				
		Author a = new Author(ln, fn, dob);
		try {
			serviceAuthors.addAuthor(a);
		} catch (DuplicateException e) {
			duplicateAuthor = e.getMessage();
		}
		
		response.sendRedirect("authors");
	}
	
	public void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceAuthors.deleteAuthor(Integer.valueOf(request.getParameter("id")));
		response.sendRedirect("authors");
	}
	
	public void editAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String ln = request.getParameter("lastname");
		String fn = request.getParameter("firstname");
		String dob = request.getParameter("dob");
		Author a = serviceAuthors.getAuthor(id);
		a.setFirstname(fn); a.setLastname(ln); a.setDob(dob);
		serviceAuthors.editAuthor(a);
		response.sendRedirect("authors");
	}
	
	public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Author a = serviceAuthors.getAuthor(id);
		request.setAttribute("authorToEdit", a);
		getServletContext().getRequestDispatcher("/WEB-INF/views/editAuthorForm.jsp").forward(request, response);
	}
	
	public void authorDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Author a = serviceAuthors.getAuthor(id);
		DateFormat outputFormat = new SimpleDateFormat("MMMM d', 'yyyy", Locale.ENGLISH);
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		String inputText = a.getDob();
		Date date = null;
		try {
			date = inputFormat.parse(inputText);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String outputText = outputFormat.format(date);
		request.setAttribute("author", a);
		request.setAttribute("dob", outputText);
		getServletContext().getRequestDispatcher("/WEB-INF/views/authorDetails.jsp").forward(request, response);
	}
	
}
