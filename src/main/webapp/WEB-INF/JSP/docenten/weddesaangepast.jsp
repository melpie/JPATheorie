<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html>
<head>
<title>Wedde(s) aangepast</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>De wedde van ${param.aantalAangepast} docenten werd
	aangepast.
</body>
</html>