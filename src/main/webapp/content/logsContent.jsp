<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="io.github.salazar.ecommerce.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  List<Audit> audits = (List<Audit>) request.getAttribute("audits");
%>

<div class="p-6 bg-gray-100 min-h-screen">
  <h1 class="text-3xl font-bold text-gray-800 mb-6">Registro de Auditoría</h1>

  <div class="overflow-x-auto bg-white shadow rounded-lg">
    <table class="min-w-full table-auto border-collapse">
      <thead class="bg-gray-200 text-gray-700">
      <tr>
        <th class="px-4 py-2 text-left">ID</th>
        <th class="px-4 py-2 text-left">Tabla</th>
        <th class="px-4 py-2 text-left">Operación</th>
        <th class="px-4 py-2 text-left">Anterior</th>
        <th class="px-4 py-2 text-left">Nuevo</th>
        <th class="px-4 py-2 text-left">Fecha</th>
        <th class="px-4 py-2 text-left">Usuario</th>
      </tr>
      </thead>
      <tbody class="text-sm text-gray-800">
      <c:forEach var="log" items="${audits}">
        <tr class="border-t hover:bg-gray-50">
          <td class="px-4 py-2">${log.id}</td>
          <td class="px-4 py-2">${log.nameTable}</td>
          <td class="px-4 py-2">${log.operation}</td>
          <td class="px-4 py-2 whitespace-pre-line text-gray-500">${log.previousValue}</td>
          <td class="px-4 py-2 whitespace-pre-line text-green-600">${log.newValue}</td>
          <td class="px-4 py-2">${log.date}</td>
          <td class="px-4 py-2">${log.user}</td>
        </tr>
      </c:forEach>
      <c:if test="${empty audits}">
        <tr>
          <td colspan="7" class="px-4 py-4 text-center text-gray-400">No hay registros</td>
        </tr>
      </c:if>
      </tbody>
    </table>
  </div>
</div>
