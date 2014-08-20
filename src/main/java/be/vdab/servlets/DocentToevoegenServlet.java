package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.vdab.entities.Docent;
import be.vdab.services.DocentService;
import be.vdab.enums.Geslacht;

@WebServlet("/docenten/toevoegen.htm")
public class DocentToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/toevoegen.jsp";
	private static final String REDIRECT_URL = "/docenten/toegevoegd.htm";
	private final DocentService docentService = new DocentService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> fouten = new ArrayList<>();
		String voornaam = request.getParameter("voornaam");
		if (voornaam == null || voornaam.isEmpty()) {
			fouten.add("Voornaam verplicht");
		}
		String familienaam = request.getParameter("familienaam");
		if (familienaam == null || familienaam.isEmpty()) {
			fouten.add("Familienaam verplicht");
		}
		BigDecimal wedde = null;
		try {
			wedde = new BigDecimal(request.getParameter("wedde"));
			if (wedde.compareTo(BigDecimal.ZERO) < 0) {
				fouten.add("Wedde moet een positief getal zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("Wedde moet een getal zijn");
		}
		String geslacht = request.getParameter("geslacht");
		if (geslacht == null) {
			fouten.add("Geslacht verplicht");
		}
		if (fouten.isEmpty()) {
			Docent docent = new Docent(voornaam, familienaam, wedde,
					Geslacht.valueOf(geslacht));
			docentService.create(docent);
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath()
					+ REDIRECT_URL
					+ "?docentNr="
					+ docent.getDocentNr()));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
