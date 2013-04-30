<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="bb"%>

<form:form cssClass="form-horizontal" modelAttribute="signupForm" method="post">

  <fieldset>
    <legend>
      <s:message code="login.sign_up" />
    </legend>

    <s:bind path="signupForm.*">
      <c:if test="${status.error}">
        <c:if test="${status.errors.globalErrorCount > 0}">
          <div class="alert alert-error">
            <c:forEach items="${status.errors.globalErrors}" var="globalError">
              <s:message message="${globalError}" />
              <br />
            </c:forEach>
          </div>
        </c:if>
      </c:if>
    </s:bind>

    <bb:field messageCode="login.username" path="user.username">
      <form:input path="user.username" class="input-medium" maxlength="15" autofocus="autofocus" />
    </bb:field>

    <bb:field messageCode="signup.email" path="user.email">
      <form:input path="user.email" class="input-medium" maxlength="250" type="email" />
    </bb:field>

    <bb:field messageCode="signup.password" path="password">
      <form:password path="password" class="input-medium" maxlength="250" />
    </bb:field>

    <bb:field messageCode="signup.confirm_password" path="confirmPassword">
      <form:password path="confirmPassword" class="input-medium" maxlength="250" />
    </bb:field>

    <div class="control-group">
      <div class="controls">
        <input type="submit" class="btn" value='<s:message code="login.sign_up" />' />
      </div>
    </div>

  </fieldset>
</form:form>
