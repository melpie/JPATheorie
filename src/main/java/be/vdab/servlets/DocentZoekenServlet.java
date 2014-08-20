package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Docent;
import be.vdab.exceptions.DocentNietGevondenException;
import be.vdab.services.DocentService;

@WebServlet("/docenten/zoeken.htm")
public class DocentZoekenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/zoeken.jsp";
	public static final String REDIRECT_URL = "/docenten/zoeken.htm";
	private DocentService docentService = new DocentService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameterMap().isEmpty()) {
			List<String> fouten = new ArrayList<>();
			try {
				long docentNr = Long
						.parseLong(request.getParameter("docentNr"));
				Docent docent = docentService.read(docentNr);
				if (docent == null) {
					fouten.add("Docent niet gevonden");
				} else {
					request.setAttribute("docent", docent);
				}
			} catch (NumberFormatException ex) {
				fouten.add("Tik een getal");
			}
			if (!fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	private void bijnamenVerwijderen(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<String> fouten = new ArrayList<>();
		long docentNr = Long.parseLong(request.getParameter("docentNr"));
		String[] bijnamen = request.getParameterValues("bijnaam");
		if (bijnamen != null) {
			try {
				docentService.bijnamenVerwijderen(docentNr, bijnamen);
			} catch (DocentNietGevondenException ex) {
				fouten.add("Docent niet gevonden");
			}
		}
		if (fouten.isEmpty()) {
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath() + REDIRECT_URL + "?docentNr=" + docentNr));
		} else {
			request.setAttribute("docent", docentService.read(docentNr));
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	private void bijnamenToevoegen(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<String> fouten = new ArrayList<>();
		long docentNr = Long.parseLong(request.getParameter("docentNr"));
		String bijnaam = request.getParameter("bijnaam");
		if (bijnaam == null || bijnaam.isEmpty()) {
			fouten.add("Bijnaam verplicht");
		} else {
			try {
				docentService.bijnaamToevoegen(docentNr, bijnaam);
			} catch (DocentNietGevondenException ex) {
				fouten.add("Docent niet gevonden");
			}
		}
		if (fouten.isEmpty()) {
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath() + REDIRECT_URL + "?docentNr=" + docentNr));
		} else {
			request.setAttribute("docent", docentService.read(docentNr));
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("verwijderen") == null) {
			bijnamenToevoegen(request, response);
		} else {
			bijnamenVerwijderen(request, response);
		}
	}

}