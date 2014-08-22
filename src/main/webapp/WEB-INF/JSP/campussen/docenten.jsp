<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<title>Docenten per campus</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>Docenten per campus</h1>
	<ul class="zonderbolletjes">
		<c:forEach items="${campussen}" var="campus">
			<c:url value="/campussen/docenten.htm" var="url">
				<c:param name="campusNr" value="${campus.campusNr}" />
			</c:url>
			<li><a href="${url}">${campus.naam}</a></li>
		</c:forEach>
	</ul>
	<c:if test="${not empty campus}">
		<h2>${campus.naam}(${campus.adres.gemeente})</h2>
		<ul>
			<c:forEach items="${campus.docenten}" var="docent">
				<li>${docent.naam}</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:import url="/WEB-INF/JSP/fouten.jsp" />
</body>
</html>