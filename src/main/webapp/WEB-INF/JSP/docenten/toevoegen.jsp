<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<title>Docent toevoegen</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>Docent toevoegen</h1>
	<form action="<c:url value='/docenten/toevoegen.htm'/>" method="post" id="toevoegform">
		<label>Voornaam: <input name="voornaam"
			value="${param.voornaam}" autofocus />
		</label> <label>Familienaam: <input name="familienaam"
			value="${param.familienaam}" />
		</label> <label>Wedde: <input name="wedde"
			value="${param.wedde}" type="number"/>
		</label>
		<div>
			<label> <input type="radio" name="geslacht" value="MAN"
				${param.geslacht=='MAN' ? 'checked' : ''} />Man
			</label>
		</div>
		<div>
			<label> <input type="radio" name="geslacht" value="VROUW"
				${param.geslacht=='VROUW' ? 'checked' : ''} />Vrouw
			</label>
		</div>
		<input type="submit" value="Toevoegen" id="toevoegknop" />
	</form>
	<c:import url="/WEB-INF/JSP/fouten.jsp" />
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>