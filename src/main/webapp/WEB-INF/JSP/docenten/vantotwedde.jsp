<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<title>Docenten van tot wedde</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>Docenten van tot wedde</h1>
<body>
	<form action="<c:url value='/docenten/vantotwedde.htm'/>" method="get">
		<label>Van: <input name="van" value="${param.van}"
			type="number" autofocus />
		</label> <label>Tot: <input name="tot" type="number"
			value="${param.tot}" />
		</label> <input type="submit" value="Zoeken" />
	</form>
	<c:import url="/WEB-INF/JSP/fouten.jsp" />
	<c:if test="${not empty docenten}">
		<table>
			<tr>
				<th>Nummer</th>
				<th>Naam</th>
				<th>Wedde</th>
			</tr>
			<c:forEach items="${docenten}" var="docent">
				<tr>
					<td style="text-align: right">${docent.docentNr}</td>
					<td>${docent.naam}</td>
					<td style="text-align: right"><fmt:formatNumber
							value="${docent.wedde}" minFractionDigits="2"
							maxFractionDigits="2" /></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${vanafRij != 0}">
			<c:url value="/docenten/vantotwedde.htm" var="vorigePaginaURL">
				<c:param name="van" value="${param.van}" />
				<c:param name="tot" value="${param.tot}" />
				<c:param name="vanafRij" value="${vanafRij - aantalRijen}" />
			</c:url>
			<a href="<c:out value='${vorigePaginaURL}'/>" title="vorige pagina">&lt;</a>
		</c:if>
		<c:if test="${empty laatstePagina}">
			<c:url value="/docenten/vantotwedde.htm" var="volgendePaginaURL">
				<c:param name="van" value="${param.van}" />
				<c:param name="tot" value="${param.tot}" />
				<c:param name="vanafRij" value="${vanafRij + aantalRijen}" />
			</c:url>
			<a href="<c:out value='${volgendePaginaURL}'/>"
				title="volgende pagina">&gt;</a>
		</c:if>
	</c:if>
</body>


</html>