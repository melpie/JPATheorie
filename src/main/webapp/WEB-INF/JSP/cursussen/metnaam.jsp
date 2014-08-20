<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />
<html lang="nl">
<head>
<title>Cursussen met woord in naam</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>Cursussen met woord in naam</h1>
	<form action="<c:url value='/cursussen/metnaam.htm'/>" method="get">
		<label>Woord: 
			<input name="woord" value="${param.woord}" autofocus />
		</label> 
		<input type="submit" value="Zoeken" />
	</form>
	<c:if test="${not empty cursussen}">
		<ul>
			<c:forEach items="${cursussen}" var="cursus">
				<c:set var="soortCursus" value="${cursus['class'].simpleName}" />
				<li>
					${cursus.naam}<img src="${contextPath}/images/${soortCursus}.png" alt="${soortCursus}" title="${soortCursus}" />
				</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:import url="/WEB-INF/JSP/fouten.jsp" />
</body>
</html>