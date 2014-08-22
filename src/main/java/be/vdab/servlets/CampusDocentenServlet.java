package be.vdab.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Campus;
import be.vdab.services.CampusService;

@WebServlet("/campussen/docenten.htm")
public class CampusDocentenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/campussen/docenten.jsp";
	private final CampusService campusService = new CampusService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("campussen", campusService.findAll());
		String campusNr = request.getParameter("campusNr");
		if (campusNr != null) {
			Campus campus = campusService.read(Long.parseLong(campusNr));
			if (campus == null) {
				request.setAttribute("fouten",
						Arrays.asList("Campus bestaat niet"));
			} else {
				request.setAttribute("campus", campus);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}