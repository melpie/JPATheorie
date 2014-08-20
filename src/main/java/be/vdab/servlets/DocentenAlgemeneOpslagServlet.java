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

import be.vdab.services.DocentService;

// enkele imports ...
@WebServlet("/docenten/algemeneopslag.htm")
public class DocentenAlgemeneOpslagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/algemeneopslag.jsp";
	private static final String REDIRECT_URL = "/docenten/weddesaangepast.htm";
	private final DocentService docentService = new DocentService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> fouten = new ArrayList<>();
		BigDecimal percentage = null;
		try {
			percentage = new BigDecimal(request.getParameter("percentage"));
			if (percentage.compareTo(BigDecimal.ZERO) <= 0) {
				fouten.add("Percentage moet een positief getal zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("Percentage moet een getal zijn");
		}
		BigDecimal totEnMetWedde = null;
		try {
			totEnMetWedde = new BigDecimal(
					request.getParameter("totEnMetWedde"));
		} catch (NumberFormatException ex) {
			fouten.add("Tot en met wedde moet een getal zijn");
		}
		if (fouten.isEmpty()) {
			int aantalDocentenAangepast = docentService.algemeneOpslag(
					percentage, totEnMetWedde);
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath()
					+ REDIRECT_URL
					+ "?aantalAangepast="
					+ aantalDocentenAangepast));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}