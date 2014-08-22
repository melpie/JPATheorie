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

import be.vdab.entities.Campus;
import be.vdab.entities.Docent;
import be.vdab.enums.Geslacht;
import be.vdab.exceptions.EmailAdresAlInGebruikException;
import be.vdab.services.CampusService;
import be.vdab.services.DocentService;
import be.vdab.valueobjects.EmailAdres;

@WebServlet("/docenten/toevoegen.htm")
public class DocentToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/toevoegen.jsp";
	private static final String REDIRECT_URL = "/docenten/toegevoegd.htm";
	private final DocentService docentService = new DocentService();
	private final CampusService campusService = new CampusService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("campussen", campusService.findAll());
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
		EmailAdres emailAdres = null;
		try {
			emailAdres = new EmailAdres(request.getParameter("email"));
		} catch (IllegalArgumentException ex) {
			fouten.add("E-mailadres verkeerd.");
		}
		Campus campus = null;
		String campusNr = request.getParameter("campussen");
		if (campusNr == null) {
			fouten.add("Kies een campus");
		} else {
			campus = campusService.read(Long.parseLong(campusNr));
			if (campus == null) {
				fouten.add("Campus bestaat niet");
			}
		}
		if (fouten.isEmpty()) {
			Docent docent = new Docent(voornaam, familienaam, wedde,
					Geslacht.valueOf(geslacht), emailAdres);
			docent.setCampus(campus);
			try {
				docentService.create(docent);
				response.sendRedirect(response.encodeRedirectURL(request
						.getContextPath()
						+ REDIRECT_URL
						+ "?docentNr="
						+ docent.getDocentNr()));
			} catch (EmailAdresAlInGebruikException ex) {
				fouten.add("E-mail adres is al in gebruik");
			}
		}
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			request.setAttribute("campussen", campusService.findAll());
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
