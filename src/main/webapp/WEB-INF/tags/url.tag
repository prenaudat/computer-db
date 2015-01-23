<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true"%>
<%@ attribute name="query" required="false"%>
<%@ attribute name="size" required="false" type="java.lang.Integer"%>
<%@ attribute name="pageNumber" required="false" type="java.lang.Integer"%>
<%@ attribute name="orderBy" required="false"%>
<%@ attribute name="sort" required="false"%>
<%@ attribute name="value" required="true"%>


<a href="${target}?page=${pageNumber}&query=${query}&order=${orderBy}&sort=${sort}&size=${size}">${value}</a>