<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/font-awesome.css"/>" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet"
	media="screen">
<script src="<c:url value="/js/jquery.min.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="../computers"> Application -
			Computer Database </a>
	</div>
	</header>
	<!doctype html>
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>
					<spring:message code="label.add" />
				</h1>
				<c:if test="${fn:length(errors) gt 0}">
					<div class="alert-bar">
						<div class="alert alert-danger alert-error">
							<a onClick="$(this).parent().parent().remove();" class="close"
								data-dismiss="alert">&times;</a> <strong>Error!</strong> A
							problem has been occurred while submitting your data.
							<c:forEach items="${errors}" var="error">
								<li><spring:message code="${error}" /></li>
							</c:forEach>
						</div>
					</div>
				</c:if>

				<form id="add" action="add" method="POST">
					<fieldset>
						<div class="form-group has-feedback">
							<label for="name"> <spring:message code="label.cptName" />
							</label> <input type="text" class="form-control" id="name" name="name"
								placeholder="<spring:message
									code="label.cptName" />">
						</div>
						<div class="form-group has-feedback">
							<label for="introduced"><spring:message
									code="label.introduced" /></label> <input type="date"
								class="form-control" name="introduced" id="introduced"
								placeholder="<spring:message
									code="label.introduced"/>">
						</div>
						<div class="form-group has-feedback">
							<label for="discontinued"><spring:message
									code="label.discontinued" /></label> <input type="date"
								class="form-control" name="discontinued" id="discontinued"
								placeholder="<spring:message
									code="label.discontinued"/>">
						</div>
						<div class="form-group has-feedback">
							<label for="companyId"><spring:message
									code="label.cmpName" /></label> <select class="form-control"
								id="companyId" name="companyId">
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
						<input type="submit"
							value="<spring:message
									code="label.add"/>" id="submit"
							class="btn btn-primary">
						<spring:message code="label.or" />
						<a href="../computers" class="btn btn-default"><spring:message
								code="label.cancel" /></a>
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