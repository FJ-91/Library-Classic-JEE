package fr.afpa.library.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.library.dao.DaoCat;
import fr.afpa.library.dao.DuplicateException;
import fr.afpa.library.model.Catalogue;
import fr.afpa.library.service.IServiceCat;
import fr.afpa.library.service.ServiceCat;

public class catServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String MessageDupCat = "";
	private IServiceCat serviceCat;

	public void init() throws ServletException {
		DaoCat daoCat = new DaoCat();
		this.serviceCat = new ServiceCat(daoCat);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/catalogues":
			showCatsPanel(request, response);
			break;
		case "/addCat":
			addCat(request, response);
			break;
		case "/editCat":
			editCat(request, response);
			break;
		case "/editCatForm":
			showEditForm(request, response);
			break;
		case "/deleteCat":
			deleteCat(request, response);
			break;
		case "/catDetails":
			catDetails(request, response);
			break;
		default:
			showCatsPanel(request, response);
			break;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void showCatsPanel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Catalogue> cats = new ArrayList<Catalogue>();
		cats = serviceCat.getCats();
		request.setAttribute("catalogues", cats);
		request.setAttribute("dupCatError", MessageDupCat);
		MessageDupCat = "";
		getServletContext().getRequestDispatcher("/WEB-INF/views/catalogues.jsp").forward(request, response);
	}

	public void addCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			serviceCat.addCat(new Catalogue(request.getParameter("name")));
		} catch (DuplicateException e) {
			MessageDupCat = e.getMessage();
		}
		response.sendRedirect("catalogues");
	}

	public void deleteCat(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		serviceCat.deleteCat(Integer.valueOf(request.getParameter("id")));
		response.sendRedirect("catalogues");
	}

	public void catDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Catalogue c = serviceCat.getCat(id);
		request.setAttribute("cat", c);
		getServletContext().getRequestDispatcher("/WEB-INF/views/catDetails.jsp").forward(request, response);
	}

	public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Catalogue c = serviceCat.getCat(id);
		request.setAttribute("catToEdit", c);
		request.setAttribute("dupCatError", MessageDupCat);
		MessageDupCat = "";
		getServletContext().getRequestDispatcher("/WEB-INF/views/editCatForm.jsp").forward(request, response);
	}

	public void editCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("catname");
		Catalogue c = serviceCat.getCat(id);
		c.setName(name);
		try {
			serviceCat.editCat(c);
			MessageDupCat = "";
			response.sendRedirect("catalogues");
		} catch (DuplicateException e) {
			response.sendRedirect("editCatForm?id="+id);
			MessageDupCat = e.getMessage();
		}
		
	}

}
