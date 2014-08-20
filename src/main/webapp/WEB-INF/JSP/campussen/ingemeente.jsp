<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<html lang="nl">
<head>
<title>Campussen in een gemeente</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>Campussen in een gemeente</h1>
	<form action="<c:url value='/campussen/ingemeente.htm'/>" method="get">
		<label>Gemeente: <input name="gemeente"
			value="${param.gemeente}" autofocus />
		</label> <input type="submit" value="Zoeken" />
	</form>
	<c:if test="${not empty campussen}">
		<ul>
			<c:forEach items="${campussen}" var="campus">
				<li>${campus.naam}(${campus.adres.straat}
					${campus.adres.huisNr})</li>
				<ul>
					<c:forEach items="${campus.telefoonNrs}" var="telefoonNr">
						<li>${telefoonNr.fax ? "Fax" : "Telefoon"}<strong>${telefoonNr.nummer}</strong>
							${telefoonNr.opmerking}
						</li>
					</c:forEach>
				</ul>
			</c:forEach>
		</ul>
	</c:if>
	<c:import url="/WEB-INF/JSP/fouten.jsp" />
</body>
</html>