<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="io.github.salazar.ecommerce.model.Product" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  Product productToEdit = (Product) request.getAttribute("productToEdit");
  boolean isEditing = productToEdit != null;
%>

<div class="min-h-screen bg-gray-100 py-10 px-4">
  <div class="max-w-5xl mx-auto bg-white p-8 rounded-xl shadow-md">

    <h2 class="text-2xl font-bold text-blue-700 mb-6">Administrar Productos</h2>

    <!-- Formulario para agregar producto -->
    <form action="${pageContext.request.contextPath}/product" method="post" class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-8">
      <input type="hidden" name="action" value="<%= isEditing ? "update" : "create" %>">
      <% if (isEditing) { %>
      <input type="hidden" name="id" value="<%= productToEdit.getId() %>">
      <% } %>

      <div>
        <label class="block text-sm font-medium text-gray-700">Nombre</label>
        <input type="text" name="name" required
               value="<%= isEditing ? productToEdit.getName() : "" %>"
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700">ID de Categoría</label>
        <input type="number" name="categoryId" required
               value="<%= isEditing ? productToEdit.getIdCategory() : "" %>"
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700">Cantidad</label>
        <input type="number" name="quantity" required
               value="<%= isEditing ? productToEdit.getQuantity() : "" %>"
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700">Precio</label>
        <input type="number" step="0.01" name="price" required
               value="<%= isEditing ? productToEdit.getPrice() : "" %>"
               class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      </div>

      <div class="md:col-span-2 text-right">
        <button type="submit"
                class="bg-green-600 text-white px-6 py-2 rounded-md hover:bg-green-700 transition">
          <%= isEditing ? "Actualizar Producto" : "Agregar Producto" %>
        </button>
      </div>
    </form>


    <!-- Tabla de productos -->
    <div class="overflow-x-auto">
      <table class="min-w-full table-auto border border-gray-200 text-sm text-left">
        <thead class="bg-gray-100 text-gray-700 uppercase">
        <tr>
          <th class="px-4 py-2 border">ID</th>
          <th class="px-4 py-2 border">Nombre</th>
          <th class="px-4 py-2 border">Categoría</th>
          <th class="px-4 py-2 border">Cantidad</th>
          <th class="px-4 py-2 border">Precio</th>
          <th class="px-4 py-2 border text-center">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${products}">
          <tr class="hover:bg-gray-50">
            <td class="px-4 py-2 border">${p.id}</td>
            <td class="px-4 py-2 border">${p.name}</td>
            <td class="px-4 py-2 border">${p.category}</td>
            <td class="px-4 py-2 border">${p.quantity}</td>
            <td class="px-4 py-2 border">$${p.price}</td>
            <td class="px-4 py-2 border text-center space-x-2">
              <!-- Botón eliminar -->
              <form action="${pageContext.request.contextPath}/product" method="post" class="inline-block">
                <input type="hidden" name="action" value="delete" />
                <input type="hidden" name="id" value="${p.id}" />
                <button type="submit"
                        onclick="return confirm('¿Eliminar este producto?');"
                        class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 transition">
                  Eliminar
                </button>
              </form>
              <!-- Botón editar -->
              <form action="${pageContext.request.contextPath}/product" method="get" class="inline-block">
                <input type="hidden" name="action" value="edit" />
                <input type="hidden" name="id" value="${p.id}" />
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
</div>
