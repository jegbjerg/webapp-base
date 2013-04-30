<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<tiles:importAttribute name="title" />
<title><s:message code="${title}" /></title>
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href='<c:url value="/resources/css/vendor/bootstrap.css" />'>
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<link rel="stylesheet" href='<c:url value="/resources/css/vendor/bootstrap-responsive.css" />'>
<link rel="stylesheet" href='<c:url value="/resources/css/vendor/font-awesome.css" />'>

<tiles:importAttribute name="stylesheet" ignore="true" />
<c:if test="${not empty stylesheet}">
  <link rel="stylesheet" href='<c:url value="/resources/css/${stylesheet}.css" />'>
</c:if>

<tiles:importAttribute name="script" ignore="true" />
<c:if test="${not empty script}">
  <script data-main='<c:url value="/resources/js/${script}" />'
    src='<c:url value="/resources/js/vendor/require-jquery.js" />'></script>
</c:if>

</head>

<body>
  <tiles:insertAttribute name="header" ignore="true" />

  <div class="container">

    <c:if test="${not empty info}">
      <div class="alert alert-info">${info}</div>
    </c:if>

    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
  </div>
</body>
</html>
