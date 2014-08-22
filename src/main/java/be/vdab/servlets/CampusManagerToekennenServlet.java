package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.exceptions.CampusNietGevondenException;
import be.vdab.exceptions.ManagerNietGevondenException;
import be.vdab.services.CampusService;
import be.vdab.services.ManagerService;

@WebServlet("/campussen/managertoekennen.htm")
public class CampusManagerToekennenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/campussen/managertoekennen.jsp";
	private static final String REDIRECT_URL = "/campussen/managertoekennen.htm";
	private final CampusService campusService = new CampusService();
	private final ManagerService managerService = new ManagerService();

	private void getCampussenEnManagers(HttpServletRequest request) {
		request.setAttribute("campussen", campusService.findAll());
		request.setAttribute("managers", managerService.findAll());
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getCampussenEnManagers(request);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> fouten = new ArrayList<>();
		String campusNrString = request.getParameter("campusNr");
		if (campusNrString == null) {
			fouten.add("Kies een campus.");
		}
		String managerNrString = request.getParameter("managerNr");
		if (managerNrString == null) {
			fouten.add("Kies een manager.");
		}
		if (fouten.isEmpty()) {
			try {
				long campusNr = Long.parseLong(campusNrString);
				long managerNr = Long.parseLong(managerNrString);
				campusService.kenManagerToe(campusNr, managerNr);
				response.sendRedirect(response.encodeRedirectURL(request
						.getContextPath() + REDIRECT_URL));
			} catch (CampusNietGevondenException ex) {
				fouten.add("Campus niet gevonden");
			} catch (ManagerNietGevondenException ex) {
				fouten.add("Manager niet gevonden");
			}
		}
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			getCampussenEnManagers(request);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}