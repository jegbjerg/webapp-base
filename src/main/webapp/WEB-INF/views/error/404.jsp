<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<div class="alert alert-error">
  <h1><s:message code="error.title_404" /></h1>
    <p><s:message code="error.message_404" arguments="${originalUri}" /></p>
</div>
