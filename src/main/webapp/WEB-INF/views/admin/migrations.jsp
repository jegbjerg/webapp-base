<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h2><s:message code="admin.migrations.title" /></h2>

<c:choose>
  <c:when test="${not empty migrations}">
    <table class="table table-bordered table-striped">
      <thead>
        <tr>
          <th><s:message code="admin.migrations.version" /></th>
          <th><s:message code="admin.migrations.description" /></th>
          <th><s:message code="admin.migrations.type" /></th>
          <th><s:message code="admin.migrations.script_name" /></th>
          <th><s:message code="admin.migrations.checksum" /></th>
          <th><s:message code="admin.migrations.installed_on" /></th>
          <th><s:message code="admin.migrations.time_to_execute" /></th>
          <th><s:message code="admin.migrations.state" /></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${migrations}" var="migration">
          <tr>
            <td>${migration.version}</td>
            <td>${migration.description}</td>
            <td>${migration.type}</td>
            <td>${migration.script}</td>
            <td>${migration.checksum}</td>
            <td>${migration.installedOn}</td>
            <td>${migration.executionTime}</td>
            <td>${migration.state}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </c:when>
  <c:otherwise>
    <s:message code="admin.migrations.no_migrations" />
  </c:otherwise>
</c:choose>
