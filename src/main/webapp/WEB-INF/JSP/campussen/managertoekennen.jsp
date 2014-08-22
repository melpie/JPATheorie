<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<html lang="nl">
<head>
<title>Campusmanager toekennen</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>Campusmanager toekennen</h1>
	<form action="<c:url value='/campussen/managertoekennen.htm'/>"	method="post" id="toekenform">
		<label>Campus: 
			<select name="campusNr"	size="${campussen.size()}">
				<c:forEach items="${campussen}" var="campus">
					<option value="${campus.campusNr}" ${param.campusNr==campus.campusNr ? 'selected':''}>
						${campus.naam}
						<c:if test="${not empty campus.manager}">
							(manager:${campus.manager.voornaam} ${campus.manager.familienaam})
						</c:if>
					</option>
				</c:forEach>
			</select>
		</label> 
		<label>Manager: 
			<select name="managerNr" size="${managers.size()}">
				<c:forEach items="${managers}" var="manager">
					<option value="${manager.managerNr}" ${param.managerNr==manager.managerNr ? 'selected' :''}>
						${manager.voornaam}
						${manager.familienaam}
					</option>
				</c:forEach>
			</select>
		</label> 
		<input type="submit" value="Toekennen" id="toekenknop" />
	</form>
	<c:import url="/WEB-INF/JSP/fouten.jsp" />
	<script>
		document.getElementById('toekenform').onsubmit = function() {
			document.getElementById('toekenknop').disabled = true;
		};
	</script>
</body>
</html>