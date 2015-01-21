<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true" %>
<%@ attribute name="query" required="false" %>
<%@ attribute name="pageNumber" required="true" %>
<%@ attribute name="orderBy" required="true" %>
<%@ attribute name="pageCount" required="true" %>
<%@ attribute name="sort" required="false" %>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${pageNumber>0}">
					<li><a href="${target}?page=${pageNumber-1}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>

					</a></li>
				</c:if>
				<c:if test="${page.pageNumber>2}">
								<li><a href="${target}?page=${pageNumber-2}">${pageNumber-2}</a></li>
				</c:if>				
				<c:if test="${page.pageNumber>1}">
								<li><a href="${target}?page=${pageNumber-1}">${pageNumber-1}</a></li>
				
				</c:if>


				<li><a href="${target}?page=${pageNumber}">${pageNumber}</a></li>
				
				<c:if test="${pageNumber<pageCount-1}">
								<li><a href="${target}?page=${pageNumber+1}">${pageNumber+1}</a></li>
				
				</c:if>
				<c:if test="${pageNumber<pageCount-2}">
								<li><a href="${target}?page=${pageNumber+2}">${pageNumber+2}</a></li>
				
				</c:if>
				<c:if test="${pageNumber<pageCount}">
					<li><a href="${target}?page=${pageNumber+1}" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="${target}?page=${pageNumber}&pageSize=10" type="button" class="btn btn-default">10</a>
				<a href="${target}?page=${pageNumber}&pageSize=50" type="button" class="btn btn-default">50</a>
				<a href="${target}?page=${pageNumber}&pageSize=100" type="button" class="btn btn-default">100</a>
			</div>
	</footer>