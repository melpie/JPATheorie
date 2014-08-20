<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />

<!doctype html>

<html lang="nl">

<head>
<title>Docent zoeken</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>

<body>
	<h1>Docent zoeken</h1>
	<form action="<c:url value='/docenten/zoeken.htm'/>" method="get">
		<label>Nummer: <input name="docentNr"
			value="${param.docentNr}" autofocus size="6" type="number" />
		</label> <input type="submit" value="Zoeken" />
	</form>
	<c:if test="${not empty docent}">
		<div>${docent.naam},
			wedde:
			<fmt:formatNumber value="${docent.wedde}" />
			<img src="${contextPath}/images/${docent.geslacht}.png"
				alt="${docent.geslacht}" title="${docent.geslacht}" />
		</div>
		<c:if test="${not empty docent.bijnamen}">
			<h2>Bijnamen</h2>
			<c:url value="/docenten/zoeken.htm" var="verwijderURL">
				<c:param name="docentNr" value="${docent.docentNr}" />
				<c:param name="verwijderen" value="${docent.docentNr}" />
			</c:url>
			<form method="post" action="${verwijderURL}" id="verwijderform">
				<ul>
					<c:forEach items="${docent.bijnamen}" var="bijnaam">
						<li><label>${bijnaam} <input type="checkbox"
								name="bijnaam" value="${bijnaam}" /></label></li>
					</c:forEach>
				</ul>
				<input type="submit" value="Bijnamen verwijderen" name="verwijderen"
					id="verwijderknop" />
			</form>
			<script>
				document.getElementById('verwijderform').onsubmit = function() {
					document.getElementById('verwijderknop').disabled = true;
				};
			</script>
		</c:if>
		<c:url value="/docenten/zoeken.htm" var="toevoegURL">
			<c:param name="docentNr" value="${docent.docentNr}" />
		</c:url>
		<form method="post" action="${toevoegURL}" id="toevoegform">
			<label>Bijnaam: <input name="bijnaam"
				value="${param.bijnaam}" /></label> 
			<input type="submit" value="Toevoegen" id="toevoegknop" />
		</form>
		<script>
			document.getElementById("toevoegform").onsubmit = function() {
				document.getElementById("toevoegknop").disabled = true;
			};
		</script>
		<c:url value="/docenten/verwijderen.htm" var="verwijderURL">
			<c:param name="docentNr" value="${docent.docentNr}" />
		</c:url>
		<h2>Acties</h2>
		<form action="${verwijderURL}" method="post" id="verwijderform">
			<input type="submit" value="Verwijderen" id="verwijderknop" />
		</form>
		<script>
			document.getElementById('verwijderform')
			onsubmit = function() {
				document.getElementById('verwijderknop').disabled = true;
			};
		</script>
		<form action="<c:url value='/docenten/opslag.htm'/>" method="get">
			<input type="hidden" name="docentNr" value="${docent.docentNr}" /> <input
				type="submit" value="Opslag" />
		</form>
	</c:if>
	<c:import url="/WEB-INF/JSP/fouten.jsp" />
</body>

</html>