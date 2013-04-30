<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<div class="alert alert-error">
  <h1><s:message code="error.title"  /></h1>
    <p><s:message code="error.message" arguments="${statusCode}" /></p>
</div>
