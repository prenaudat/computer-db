<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/font-awesome.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/main.css" />" rel="stylesheet"
	media="screen">
<script src="<c:url value="js/jquery.min.js"/>"></script>
<script src="<c:url value="js/bootstrap.min.js"/>"></script>
<script src="<c:url value="js/dashboard.js"/>" /></script>
</head>



<body>
	<header class="navbar navbar-inverse navbar-fixed-top">

		<div class="container">
			<a class="navbar-brand" href="" onclick='location.reload(true)'>
				Application - Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${page.count} " />
				<spring:message code="dashboard.computersFound" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm"
						action="computers?page=0&order=<c:out value="${orderBy}"/>&sort=<c:out value="${sort}"/>&size=<c:out value="${sort}"/>"
						method="GET" class="form-inline">
						<input type="search" id="searchbox" name="query"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="<spring:message code="dashboard.filter" />"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="computers/add"><spring:message
							code="label.add" /> </a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="label.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" method="POST" action="computers">
			<input type="hidden" name="selection" value="">
		</form>
		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a id="deleteSelected"
								onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>

						<th><c:choose>
								<c:when test="${page.orderBy == 'NAME'}">
									<c:if test="${page.sort == 'ASC'}">
										<custom:url target="${page.target}" pageNumber="0"
											query="${page.query}" orderBy="NAME" sort="DESC"
											size="${page.size}">
											<spring:message code="label.cptName" />
										</custom:url>
									</c:if>
									<c:if test="${page.sort == 'DESC'}">
										<custom:url target="${page.target}" pageNumber="0"
											query="${page.query}" orderBy="NAME" sort="ASC"
											size="${page.size}">
											<spring:message code="label.cptName" />
										</custom:url>

									</c:if>
								</c:when>
								<c:otherwise>
									<custom:url target="${page.target}" pageNumber="0"
										query="${page.query}" orderBy="NAME" sort="ASC"
										size="${page.size}">
										<spring:message code="label.cptName" />
									</custom:url>
								</c:otherwise>
							</c:choose></th>

						<th><spring:message code="label.introduced" /></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="label.discontinued" /></th>
						<!-- Table header for Company -->
						<th><c:choose>
								<c:when test="${page.orderBy == 'COMPANY_NAME'}">
									<c:if test="${page.sort == 'ASC'}">
										<custom:url target="${page.target}" pageNumber="0"
											query="${page.query}" orderBy="COMPANY_NAME" sort="DESC"
											size="${page.size}"><spring:message code="label.cmpName" /></custom:url>

									</c:if>
									<c:if test="${page.sort == 'DESC'}">
										<custom:url target="${page.target}" pageNumber="0"
											query="${page.query}" orderBy="COMPANY_NAME" sort="ASC"
											size="${page.size}"><spring:message code="label.cmpName" /></custom:url>
									</c:if>
								</c:when>
								<c:otherwise>
									<custom:url target="${page.target}" pageNumber="0"
										query="${page.query}" orderBy="COMPANY_NAME" sort="ASC"
										size="${page.size}"><spring:message code="label.cmpName" /></custom:url>
								</c:otherwise>
							</c:choose></th>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${page.list}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="<c:out value="${computer.id}" />"> </td>
							<td><a
								href="computers/edit?id=<c:out value="${computer.id}"/>"><c:out
										value="${computer.name}"></c:out></a></td>
							<td><c:out value="${computer.introduced}"></c:out></td>
							<td><c:out value="${computer.discontinued}"></c:out></td>
							<td><c:out value="${computer.companyName}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<custom:pagination target="${page.target}" query="${page.query}"
		pageCount="${page.pageCount}" orderBy="${page.orderBy}"
		sort="${page.sort}" pageNumber="${page.pageNumber}"
		size="${page.size}"></custom:pagination>

</body>
</html>