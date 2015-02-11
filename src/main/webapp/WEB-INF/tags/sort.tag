<%@ tag body-content="scriptless"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="column" required="true"%>
<%@ attribute name="target" required="true"%>
<%@ attribute name="query" required="false"%>
<%@ attribute name="size" required="false" type="java.lang.Integer"%>
<%@ attribute name="pageNumber" required="false"
	type="java.lang.Integer"%>
<%@ attribute name="orderBy" required="true" %>
<jsp:doBody var="body" />

<c:choose>
	<c:when test="${fn:contains(orderBy, column )}">

		<c:if test="${fn:contains(orderBy, 'ASC')}">
			<custom:url target="${target}" pageNumber="0" query="${query}"
				orderBy="${column}_DESC" size="${size}">
				
				<spring:message code="label.${column}" />
			</custom:url>
		</c:if>
		<c:if test="${fn:contains(orderBy, 'DESC')}">
			<custom:url target="${target}" pageNumber="0" query="${query}"
				orderBy="${column}_ASC" size="${size}">
				<spring:message code="label.${column}" />
			</custom:url>

		</c:if>
	</c:when>
	<c:otherwise>
		<custom:url target="${target}" pageNumber="0" query="${query}"
			orderBy="${column}_ASC" size="${size}">
			<spring:message code="label.${column}" />
		</custom:url>
	</c:otherwise>
</c:choose>