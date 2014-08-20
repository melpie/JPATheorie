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
import be.vdab.exceptions.DocentNietGevondenException;
import be.vdab.services.DocentService;

@WebServlet("/docenten/opslag.htm")
public class DocentOpslagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/opslag.jsp";
	private static final String REDIRECT_URL = "/";
	private final DocentService docentService = new DocentService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BigDecimal percentage = null;
		List<String> fouten = new ArrayList<>();
		try {
			percentage = new BigDecimal(request.getParameter("percentage"));
			if (percentage.compareTo(BigDecimal.ZERO) <= 0) {
				fouten.add("Percentage moet een positief getal zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("Percentage moet een getal zijn");
		}
		long docentNr = Long.parseLong(request.getParameter("docentNr"));
		if (fouten.isEmpty()) {
			try {
				docentService.opslag(docentNr, percentage);
			} catch (DocentNietGevondenException ex) {
				fouten.add("Docent niet gevonden");
			}
		}
		if (fouten.isEmpty()) {
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath() + REDIRECT_URL));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}