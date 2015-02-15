<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true"%>
<%@ attribute name="query" required="false"%>
<%@ attribute name="size" required="true" type="java.lang.Integer"%>
<%@ attribute name="pageNumber" required="false" type="java.lang.Integer"%>
<%@ attribute name="orderBy" required="true"%>
<%@ attribute name="pageCount" required="true" type="java.lang.Integer"%>

<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<ul class="pagination">
			<c:if test="${pageNumber>0}">
				<li><a
					href="${target}?page=0&query=${query}&orderBy=${orderBy}&size=${size}"
					aria-label="Next"> <span aria-hidden="true"><spring:message code="dashboard.first" /></span>
				</a></li>
				<li><a
					href="${target}?page=${pageNumber-1}&query=${query}&orderBy=${orderBy}&size=${size}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
			<c:if test="${page.pageNumber>2}">
				<li><a
					href="${target}?page=${pageNumber-2}&query=${query}&orderBy=${orderBy}&size=${size}">${pageNumber-2}</a></li>
			</c:if>
			<c:if test="${page.pageNumber>1}">
				<li><a
					href="${target}?page=${pageNumber-1}&query=${query}&orderBy=${orderBy}&size=${size}">${pageNumber-1}</a></li>

			</c:if>


			<li><a
				href="${target}?page=${pageNumber}&query=${query}&orderBy=${orderBy}&size=${size}">${pageNumber}</a></li>

			<c:if test="${pageNumber<pageCount-1}">
				<li><a
					href="${target}?page=${pageNumber+1}&query=${query}&orderBy=${orderBy}&size=${size}">${pageNumber+1}</a></li>

			</c:if>
			<c:if test="${pageNumber<pageCount-2}">
				<li><a
					href="${target}?page=${pageNumber+2}&query=${query}&orderBy=${orderBy}&size=${size}">${pageNumber+2}</a></li>

			</c:if>
			<c:if test="${pageNumber<pageCount}">
				<li><a
					href="${target}?page=${pageNumber+1}&query=${query}&orderBy=${orderBy}&size=${size}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
				<li><a
					href="${target}?page=${pageCount}&query=${query}&orderBy=${orderBy}&size=${size}"
					aria-label="Next"> <span aria-hidden="true"><spring:message code="dashboard.last" /></span>
				</a></li>
			</c:if>
		</ul>

		<div class="btn-group btn-group-sm pull-right" role="group">
			<a
				href="${target}?size=10&query=${query}&orderBy=${orderBy}"
				type="button" class="btn btn-default">10</a> <a
				href="${target}?size=50&query=${query}&orderBy=${orderBy}"
				type="button" class="btn btn-default">50</a> <a
				href="${target}?size=100&query=${query}&orderBy=${orderBy}"
				type="button" class="btn btn-default">100</a>
		</div>
	</div>
</footer>