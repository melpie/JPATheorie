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

@WebServlet("/docenten/vantotwedde.htm")
public class DocentenVanTotWeddeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/vantotwedde.jsp";
	private final DocentService docentService = new DocentService();
	private static final int AANTAL_RIJEN = 20;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameterMap().isEmpty()) {
			List<String> fouten = new ArrayList<>();
			BigDecimal van = null;
			try {
				van = new BigDecimal(request.getParameter("van"));
			} catch (NumberFormatException ex) {
				fouten.add("Van moet een getal zijn");
			}
			BigDecimal tot = null;
			try {
				tot = new BigDecimal(request.getParameter("tot"));
			} catch (NumberFormatException ex) {
				fouten.add("Tot moet een getal zijn");
			}
			if (fouten.isEmpty()) {
				int vanafRij = request.getParameter("vanafRij") == null ? 0	: Integer.parseInt(request.getParameter("vanafRij"));
				request.setAttribute("vanafRij", vanafRij);
				request.setAttribute("aantalRijen", AANTAL_RIJEN);
				Iterable<Docent> docenten = docentService.findByWeddeBetween(van, tot, vanafRij, AANTAL_RIJEN + 1);
				if (!docenten.iterator().hasNext()) {
					fouten.add("Geen docenten gevonden");
				} else {
					List<Docent> docentenList = (List<Docent>) docenten;
					if (docentenList.size() <= AANTAL_RIJEN) {
						request.setAttribute("laatstePagina", true);
					} else {
						docentenList.remove(AANTAL_RIJEN);
					}
					request.setAttribute("docenten", docentenList);
				}
			}
			if (!fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}