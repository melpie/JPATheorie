<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<title>Docenten voornamen</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>Docenten voornamen</h1>
	<ul>
		<c:forEach items="${voornamen}" var="voornaam">
			<li>${voornaam}</li>
		</c:forEach>
	</ul>
</body>
</html>