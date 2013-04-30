<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="path" required="true"%>
<%@ attribute name="messageCode" required="true"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:bind path="${path}">
  <c:choose>
    <c:when test="${status.error}">
      <c:set var="groupClass" value="control-group error" />
    </c:when>
    <c:otherwise>
      <c:set var="groupClass" value="control-group" />
    </c:otherwise>
  </c:choose>

  <div class="${groupClass}">
    <form:label path="${path}" cssClass="control-label">
      <s:message code="${messageCode}"  />
    </form:label>

    <div class="controls">

      <jsp:doBody />

      <c:if test="${status.error}">
        <div class="help-block">
            <c:forEach items="${status.errorMessages}" var="error">
              ${error}<br />
            </c:forEach>
        </div>
      </c:if>
    </div>
  </div>
</s:bind>
