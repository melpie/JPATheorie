<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />

<!doctype html>

<html lang="nl">
	<head>
		<title>JPA website</title>
		<link rel="stylesheet" href="${contextPath}/styles/default.css" />
	</head>
	<body>
		<header>
		<h1>JPA website</h1>
		</header>
			<nav>
			<ul>
				<li><a href="<c:url value='/docenten/zoeken.htm'/>">Docent zoeken</a></li>
				<li><a href="<c:url value='/docenten/toevoegen.htm'/>">Docent toevoegen</a></li>
				<li><a href="<c:url value='/docenten/vantotwedde.htm'/>">Docenten van tot wedde</a></li>
				<li><a href="<c:url value='/docenten/voornamen.htm'/>">Docenten voornamen</a></li>
				<li><a href="<c:url value='/docenten/maxwedde.htm'/>">Maximum wedde docenten</a></li>
				<li><a href="<c:url value='/docenten/algemeneopslag.htm'/>">Algemene opslag docenten</a></li>
				<li><a href="<c:url value='/cursussen/metnaam.htm'/>">Cursussen met woord in naam</a></li>
				<li><a href="<c:url value='/campussen/ingemeente.htm'/>">Campussen in een gemeente</a></li>
				<li><a href="<c:url value='/campussen/docenten.htm'/>">Docent per campus</a></li>
			</ul>
		</nav>
	</body>
</html>