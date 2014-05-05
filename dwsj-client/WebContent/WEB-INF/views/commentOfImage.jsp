<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty comments}">
	<c:forEach var="comment" items="${comments}">
		<div class="media-body div-radius">
			<h4 class="media-heading">${comment.fullName}</h4>
		   	${comment.comment}
		</div>
		<div style="clear: both; margin-top: 5px;"></div>
	</c:forEach>
</c:if>