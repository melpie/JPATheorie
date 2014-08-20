<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<title>Algemene opslag docenten</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>Algemene opslag docenten</h1>
	<form action="<c:url value='/docenten/algemeneopslag.htm'/>" method="post">
		<label>Percentage 
			<input name="percentage" value="${param.percentage}" type="number" autofocus />
		</label> 
		<label>Tot en met wedde: 
			<input name="totEnMetWedde" value="${param.totEnMetWedde}" type="number" />
		</label> 
		<input type="submit" value="Opslag" id="submitknop" />
	</form>
	<script>
		document.forms[0].onsubmit = function() {
			document.getElementById('submitknop').disabled = true;
		};
	</script>
	<c:import url="/WEB-INF/JSP/fouten.jsp" />
</body>
</html>