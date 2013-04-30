<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<form class="form-horizontal" action='<c:url value="/processlogin" />' method="post">

  <c:if test="${not empty failed and not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}">
    <div class="alert alert-error">
      ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
  </c:if>

  <c:if test="${not empty requestUrl}">
    <div class="alert alert-warning">
      <s:message code="login.authorization_required_for" arguments="${requestUrl}"></s:message>
    </div>
  </c:if>

  <fieldset>
    <legend>
      <s:message code="login.message" />
    </legend>
    <div class="control-group">
      <label class="control-label" for="username"><s:message code="login.username" /></label>
      <div class="controls">
        <input type="text" class="input-medium" id="username" name="username"  maxlength="15" autofocus="autofocus" />
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="password"><s:message code="login.password" /></label>
      <div class="controls">
        <input type="password" class="input-medium" id="password" name="password" />
      </div>
    </div>
    <div class="control-group">
      <div class="controls">
        <label class="checkbox" for="_spring_security_remember_me"> <input type="checkbox"
          id="_spring_security_remember_me" name="_spring_security_remember_me" checked="checked" /> <s:message
            code="login.remember_me" />
        </label>
        <button type="submit" class="btn">
          <s:message code="login.message" />
        </button>
      </div>
    </div>
  </fieldset>
</form>
