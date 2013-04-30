<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h2><s:message code="admin.schema.title" /></h2>

<p><s:message code="admin.schema.message" arguments="${file}" htmlEscape="false" /></p>
<pre>${ddl}</pre>
