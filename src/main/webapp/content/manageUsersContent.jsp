<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="io.github.salazar.ecommerce.model.User" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  List<User> userList = (List<User>) request.getAttribute("users");
%>

<div class="min-h-screen bg-gray-100 px-4 py-10">
  <div class="max-w-6xl mx-auto bg-white rounded-xl shadow-md p-8">

    <h2 class="text-2xl font-bold text-blue-700 mb-6">Gestión de Usuarios</h2>

    <!-- Formulario para crear usuario -->
    <form action="${pageContext.request.contextPath}/user" method="post" class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-8">
      <input type="hidden" name="action" value="create">

      <div>
        <label class="block text-sm font-medium text-gray-700">Nombre</label>
        <input type="text" name="name" required
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700">DNI</label>
        <input type="text" name="dni" required pattern="\\d{10}"
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700">Email</label>
        <input type="email" name="email" required
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700">Contraseña</label>
        <input type="password" name="password" required
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700">ID Estado Civil</label>
        <input type="number" name="maritalStatusId" required
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700">ID Perfil</label>
        <input type="number" name="profileId" required
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div class="md:col-span-2 text-right">
        <button type="submit"
                class="bg-green-600 text-white px-6 py-2 rounded-md hover:bg-green-700 transition">
          Agregar Usuario
        </button>
      </div>
    </form>

    <!-- Tabla de usuarios -->
    <div class="overflow-x-auto">
      <table class="min-w-full table-auto border border-gray-200 text-sm text-left">
        <thead class="bg-gray-100 text-gray-700 uppercase">
        <tr>
          <th class="px-4 py-2 border">ID</th>
          <th class="px-4 py-2 border">Nombre</th>
          <th class="px-4 py-2 border">Email</th>
          <th class="px-4 py-2 border">DNI</th>
          <th class="px-4 py-2 border">Perfil</th>
          <th class="px-4 py-2 border text-center">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${users}">
          <tr class="hover:bg-gray-50">
            <td class="px-4 py-2 border">${u.id}</td>
            <td class="px-4 py-2 border">${u.name}</td>
            <td class="px-4 py-2 border">${u.email}</td>
            <td class="px-4 py-2 border">${u.dni}</td>
            <td class="px-4 py-2 border">${u.profile}</td>
            <td class="px-4 py-2 border text-center space-x-2">
              <!-- Eliminar -->
              <form action="${pageContext.request.contextPath}/user" method="post" class="inline-block">
                <input type="hidden" name="action" value="delete" />
                <input type="hidden" name="id" value="${u.id}" />
                <button type="submit"
                        onclick="return confirm('¿Eliminar este usuario?');"
                        class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 transition">
                  Eliminar
                </button>
              </form>

              <!-- Editar -->
              <form action="${pageContext.request.contextPath}/user" method="get" class="inline-block">
                <input type="hidden" name="action" value="edit" />
                <input type="hidden" name="id" value="${u.id}" />
                <button type="submit"
                        class="bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700 transition">
                  Editar
                </button>
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

  </div>
  <c:out value="${products}" />
</div>
