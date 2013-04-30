<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="brand" href='<c:url value="/" />'><s:message code="page.title" /></a>
      <ul class="nav">

        <sec:authorize access="hasRole('ROLE_ADMIN')">
          <div class="btn-group">
            <a class="btn dropdown-toggle" data-toggle="dropdown"><s:message code="link.admin" /> <span
              class="icon-caret-down"></span></a>
            <ul class="dropdown-menu">
              <li id="adminMigrationsLink"><a href='<c:url value="/admin/migrations" />'><s:message
                    code="link.admin.migrations" /></a></li>
              <li id="adminSchemaLink"><a href='<c:url value="/admin/schema" />'><s:message
                    code="link.admin.schema" /></a></li>
            </ul>
          </div>
        </sec:authorize>
      </ul>

      <sec:authorize access="isAuthenticated()">
        <div class="pull-right">
          <div class="btn-group">
            <a class="btn btn-info dropdown-toggle" data-toggle="dropdown"><i class="icon-user"></i> <sec:authentication
                property="principal.username" /> <span class="icon-caret-down"></span></a>
            <ul class="dropdown-menu">
              <li><a href="<c:url value='/logout' />"><i class="icon-signout"></i> <s:message
                    code="page.logout" /></a></li>
            </ul>
          </div>
        </div>
      </sec:authorize>

      <sec:authorize access="isAnonymous()">
        <div class="pull-right">
          <a class="btn btn-success" href="<c:url value='/login' />"><i class="icon-signin"></i> <s:message
              code="page.login" /></a> <a class="btn btn-primary" href="<c:url value='/signup' />"><i class="icon-edit"></i>
            <s:message code="login.sign_up" /></a>
        </div>
      </sec:authorize>

    </div>
  </div>
</div>

