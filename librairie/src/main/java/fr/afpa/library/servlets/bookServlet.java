package fr.afpa.library.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.library.dao.DaoBook;
import fr.afpa.library.dao.DuplicateException;
import fr.afpa.library.model.Author;
import fr.afpa.library.model.Book;
import fr.afpa.library.model.Catalogue;
import fr.afpa.library.service.IServiceBook;
import fr.afpa.library.service.ServiceBook;

public class bookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String message = "";

	private IServiceBook serviceBook;

	public void init() throws ServletException {
		DaoBook dao = new DaoBook();
		serviceBook = new ServiceBook(dao);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		  switch (action) {
		  	case "/books" :
		  		showBooksPanel(request, response);
		  		break;
		  	case "/addBook" :
		  		addBook(request, response);
		  		break;
		  	case "/editBook" :
		  		editBook(request, response);
		  		break;
		  	case "/editBookForm" :
		  		showEditForm(request, response);
		  		break;
		  	case "/deleteBook" :
		  		deleteBook(request, response);
		  		break;
		  	default:
		  		showBooksPanel(request, response);
		  		break;
		}
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void showBooksPanel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("authors", serviceBook.getAuthors());
		request.setAttribute("catalogues", serviceBook.getCats());
		request.setAttribute("books", serviceBook.getBooks());
		request.setAttribute("duplicateMessage", message);
		message = "";
		getServletContext().getRequestDispatcher("/WEB-INF/views/books.jsp").forward(request, response);
	}
	
	private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		Author a = serviceBook.getAuthor(Integer.parseInt(request.getParameter("author")));
		Catalogue cat = serviceBook.getCat(Integer.valueOf(request.getParameter("catalogue")));
		Book b = new Book(isbn, title, a, cat);
		try {
			serviceBook.addBook(b);
		} catch (DuplicateException e) {
			message = e.getMessage();
		}
		response.sendRedirect("books");
	}
	
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceBook.deleteBook(request.getParameter("id"));
		response.sendRedirect("books");
	}
	
	private void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("id");
		String title = request.getParameter("title");
		Author a = serviceBook.getAuthor(Integer.parseInt(request.getParameter("author")));
		Catalogue cat = serviceBook.getCat(Integer.valueOf(request.getParameter("cat")));
		Book b = serviceBook.getBook(isbn);
		b.setTitle(title); b.setAuthor(a); b.setCat(cat);
		try {
			serviceBook.editBook(b);
			response.sendRedirect("books");
		} catch (DuplicateException e) {
			message = e.getMessage();
			response.sendRedirect("editBookForm?id="+isbn);
		}
		
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("id");
		request.setAttribute("bookToEdit", serviceBook.getBook(isbn));
		request.setAttribute("authors", serviceBook.getAuthors());
		request.setAttribute("catalogues", serviceBook.getCats());
		request.setAttribute("duplicateMessage", message);
		message = "";
		getServletContext().getRequestDispatcher("/WEB-INF/views/editBookForm.jsp").forward(request, response);
	}

}
