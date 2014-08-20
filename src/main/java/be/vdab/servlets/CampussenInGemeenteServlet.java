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

@WebServlet("/campussen/ingemeente.htm")
public class CampussenInGemeenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/campussen/ingemeente.jsp";
	private final CampusService campusService = new CampusService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gemeente = request.getParameter("gemeente");
		if (gemeente != null) {
			if (gemeente.isEmpty()) {
				request.setAttribute("fouten", Arrays.asList("Gemeente verplicht"));
			} else {
				Iterable<Campus> campussen = campusService.findByGemeente(gemeente);
				if (!campussen.iterator().hasNext()) {
					request.setAttribute("fouten",
							Arrays.asList("Geen campussen gevonden"));
				} else {
					request.setAttribute("campussen", campussen);
				}
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}