<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty fouten}">
	<ul class="fout">
		<c:forEach items="${fouten}" var="fout">
			<li>${fout}</li>
		</c:forEach>
	</ul>
</c:if>