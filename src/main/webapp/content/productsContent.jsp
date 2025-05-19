<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="io.github.salazar.ecommerce.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  List<Product> productList = (List<Product>) request.getAttribute("products");
  List<Category> categoryList = (List<Category>) request.getAttribute("categories");
%>

<%
  User user = (User) session.getAttribute("loggedUser");
  boolean isLoggedIn = (user != null);
%>


<div class="min-h-screen bg-gray-100 py-10 px-4">
  <div class="max-w-7xl mx-auto bg-white p-8 rounded-xl shadow-md">

    <!-- Título y filtro -->
    <h2 class="text-2xl font-bold text-blue-700 mb-6 text-center">Explora nuestros productos</h2>

    <form method="get" action="${pageContext.request.contextPath}/products"
          class="flex flex-col sm:flex-row gap-4 items-center justify-center mb-8">
      <label for="categoryId" class="text-sm font-medium text-gray-700">Filtrar por categoría:</label>
      <select name="categoryId" id="categoryId"
              class="border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
        <option value="">Todas las categorías</option>
        <c:forEach var="c" items="${categories}">
          <option value="${c.id}" ${param.categoryId == c.id ? "selected" : ""}>${c.description}</option>
        </c:forEach>
      </select>
      <button type="submit"
              class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 transition">
        Filtrar
      </button>
    </form>

    <!-- Resultados -->
    <c:choose>
      <c:when test="${empty products}">
        <p class="text-center text-gray-600 text-lg mt-8">
          No hay productos disponibles en esta categoría.
        </p>
      </c:when>
      <c:otherwise>
        <div class="grid gap-6 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
          <c:forEach var="p" items="${products}">
            <div class="bg-white shadow-md rounded-lg overflow-hidden flex flex-col justify-between">
              <!-- Imagen opcional -->
              <img src="https://via.placeholder.com/300x200?text=${p.name}" alt="${p.name}"
                   class="w-full h-48 object-cover">

              <div class="p-4 flex-1 flex flex-col">
                <h3 class="text-lg font-bold text-gray-800 mb-1">${p.name}</h3>
                <p class="text-sm text-gray-600 mb-2">Cantidad disponible: <strong>${p.quantity}</strong></p>
                <p class="text-xl text-blue-700 font-semibold mb-4">$${p.price}</p>

                <!-- Formulario para agregar al carrito -->
                <form class="add-to-cart-form mt-auto" data-product-id="${p.id}">
                  <div class="flex items-center gap-2 mb-3">
                    <input type="number" name="quantity" value="1" min="1"
                           class="w-16 border border-gray-300 rounded-md px-2 py-1 text-center focus:outline-none focus:ring-2 focus:ring-blue-500" />
                    <button type="submit"
                            class="bg-green-600 text-white px-3 py-2 rounded-md hover:bg-green-700">
                      Agregar al carrito
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </c:forEach>
        </div>
      </c:otherwise>
    </c:choose>
    <div id="toast" class="fixed top-5 right-5 z-50 hidden bg-green-600 text-white px-4 py-2 rounded shadow-lg transition-opacity duration-300">
      Producto agregado al carrito ✅
    </div>
  </div>
</div>
<script>
  const isLoggedIn = <%= isLoggedIn %>;
  const loginUrl = "<%= request.getContextPath() %>/pages/login.jsp";
  document.querySelectorAll('.add-to-cart-form').forEach(form => {
    form.addEventListener('submit', async (e) => {
      e.preventDefault();

      // 🔐 Verificar si el usuario está logueado
      if (!isLoggedIn) {
        window.location.href = loginUrl;
        return;
      }

      const productId = form.getAttribute('data-product-id');
      const quantity = form.querySelector('input[name="quantity"]').value;

      try {
        const response = await fetch("<%= request.getContextPath() %>/addToCart", {
          method: 'POST',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          body: new URLSearchParams({
            productId: productId,
            quantity: quantity
          })
        });

        if (response.ok) {
          showToast("Producto agregado al carrito ✅");
        } else {
          showToast("Error al agregar producto ❌", true);
        }

      } catch (error) {
        showToast("Error de conexión ❌", true);
      }
    });
  });

</script>

