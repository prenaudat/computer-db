<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${page.pageNumber>0}">
					<li><a href="home?page=${page.pageNumber-1}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>

					</a></li>
				</c:if>
				<c:if test="${page.pageNumber>2}">
								<li><a href="home?page=${page.pageNumber-2}">${page.pageNumber-2}</a></li>
				
				</c:if>
				<c:if test="${page.pageNumber>1}">
								<li><a href="home?page=${page.pageNumber-1}">${page.pageNumber-1}</a></li>
				
				</c:if>


				<li><a href="home?page=${page.pageNumber}">${page.pageNumber}</a></li>
				
				<c:if test="${page.pageNumber<page.pageCount-1}">
								<li><a href="home?page=${page.pageNumber+1}">${page.pageNumber+1}</a></li>
				
				</c:if>
				<c:if test="${page.pageNumber<page.pageCount-2}">
								<li><a href="home?page=${page.pageNumber+2}">${page.pageNumber+2}</a></li>
				
				</c:if>
				<c:if test="${page.pageNumber<page.pageCount}">
					<li><a href="home?page=${page.pageNumber+1}" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default">10</button>
				<button type="button" class="btn btn-default">50</button>
				<button type="button" class="btn btn-default">100</button>
			</div>
	</footer>