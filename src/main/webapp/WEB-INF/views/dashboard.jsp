<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
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
			<h1 id="homeTitle">${page.count} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm"
						action="computers?page=0&order=${orderBy}&sort=${sort}&size=${size}"
						method="GET" class="form-inline">
						<input type="search" id="searchbox" name="query"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="computers/add">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST" action="delete">
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
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>

						<th><c:choose>
								<c:when test="${page.orderBy == 'NAME'}">
									<c:if test="${page.sort == 'ASC'}">
										<custom:url target="${page.target}" pageNumber="0"
											query="${page.query}" orderBy="NAME" sort="DESC"
											size="${page.size}" value="Computer name"></custom:url>
									</c:if>
									<c:if test="${page.sort == 'DESC'}">
										<custom:url target="${page.target}" pageNumber="0"
											query="${page.query}" orderBy="NAME" sort="ASC"
											size="${page.size}" value="Computer name"></custom:url>

									</c:if>
								</c:when>
								<c:otherwise>
									<custom:url target="${page.target}" pageNumber="0"
										query="${page.query}" orderBy="NAME" sort="ASC"
										size="${page.size}" value="Computer name"></custom:url>
								</c:otherwise>
							</c:choose></th>

						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th><c:choose>
								<c:when test="${page.orderBy == 'COMPANY_NAME'}">
									<c:if test="${page.sort == 'ASC'}">
										<custom:url target="${page.target}" pageNumber="0"
											query="${page.query}" orderBy="COMPANY_NAME" sort="DESC"
											size="${page.size}" value="Company name"></custom:url>

									</c:if>
									<c:if test="${page.sort == 'DESC'}">
										<custom:url target="${page.target}" pageNumber="0"
											query="${page.query}" orderBy="COMPANY_NAME" sort="ASC"
											size="${page.size}" value="Company name"></custom:url>
									</c:if>
								</c:when>
								<c:otherwise>
									<custom:url target="${page.target}" pageNumber="0"
										query="${page.query}" orderBy="COMPANY_NAME" sort="ASC"
										size="${page.size}" value="Company name"></custom:url>
								</c:otherwise>
							</c:choose></th>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${page.list}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="computers/edit?id=${computer.id}" onclick=""><c:out
										value="${computer.name}"></c:out></a></td>
							<td><c:out value="${computer.introduced}"></c:out></td>
							<td><c:out value="${computer.discontinued}"></c:out></td>
							<td><c:out value="${computer.company.name}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

	<custom:pagination target="${page.target}" query="${page.query}"
		pageCount="${page.pageCount}" orderBy="${page.orderBy}"
		sort="${page.sort}" pageNumber="${page.pageNumber}"
		size="${page.size}"></custom:pagination>

</body>
</html>