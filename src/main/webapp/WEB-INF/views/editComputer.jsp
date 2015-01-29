<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/css/font-awesome.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/css/main.css" />" rel="stylesheet" media="screen">
<script src="<c:url value="/js/jquery.min.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>">"</script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="../computers"> Application - Computer
			Database </a>
	</div>
	</header>
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">id: ${computer.id}</div>
				<h1>Edit Computer</h1>

				<form action="edit" method="POST">
					<fieldset>
						<div class="form-group">
							<input type="hidden" name="id" value="${computer.id}" />
						</div>
						<div class="form-group has-feedback">
							<label for="name">Computer name</label> <input
								type="text" class="form-control" id="name" name="name"
								placeholder="Computer name" value="${computer.name}">
						</div>
						<div class="form-group has-feedback">
							<label for="introduced">Introduced date</label> <input
								type="date" class="form-control" id="introduced"
								name="introduced" placeholder="Introduced date"
								value="${computer.introduced}">
						</div>
						<div class="form-group has-feedback">
							<label for="discontinued">Discontinued date</label> <input
								type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder="Discontinued date"
								value="${computer.discontinued}">
						</div>
						<div class="form-group has-feedback">
							<label for="companyId">Company</label> <select
								class="form-control" id="companyId" name="companyId">
								<option value="0">--</option>
								<c:forEach items="${companies}" var="company">
									<c:choose>
										<c:when test="${company.id!=computer.company.id}">
											<option value="${company.id}">${company.name}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${company.id}">${company.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Edit" id="submit"
							class="btn btn-primary"> or <a href="../computers"
							class="btn btn-default">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
<script type="text/javascript">
		checkName();
		checkDate("introduced");
		checkDate("discontinued");
		checkCompany();
		$("#name").on('keyup change', function() {
			checkName();
		});
		$("#introduced").on('keyup change', function() {
			checkDate("introduced");
		});
		$("#discontinued").on('keyup change', function() {
			checkDate("discontinued");
		});
		$("#companyId").on('keyup change', function() {
			checkCompany();
					});		
	</script>
</body>
</html>