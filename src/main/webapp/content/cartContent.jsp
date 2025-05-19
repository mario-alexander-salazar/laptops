<%@ page import="io.github.salazar.ecommerce.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
  if (cart == null) cart = new ArrayList<>();
  double total = 0;
  request.setAttribute("cartSafe", cart); // para usarlo en JSTL
%>

<div class="min-h-screen bg-gray-100 py-10 px-4">
  <div class="max-w-5xl mx-auto bg-white p-8 rounded-xl shadow-md">

    <h2 class="text-2xl font-bold text-blue-700 mb-6 text-center">Tu Carrito</h2>

    <c:choose>
      <c:when test="${empty cartSafe}">
        <p class="text-center text-gray-600 text-lg mt-8">No tienes productos en el carrito.</p>
      </c:when>
      <c:otherwise>
        <div class="overflow-x-auto">
          <table class="min-w-full table-auto border border-gray-200 text-sm text-left">
            <thead class="bg-gray-100 text-gray-700 uppercase">
            <tr>
              <th class="px-4 py-2 border">Producto</th>
              <th class="px-4 py-2 border">Cantidad</th>
              <th class="px-4 py-2 border">Precio</th>
              <th class="px-4 py-2 border">Subtotal</th>
              <th class="px-4 py-2 border text-center">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cartSafe}">
              <tr class="hover:bg-gray-50">
                <td class="px-4 py-2 border">${item.product.name}</td>
                <td class="px-4 py-2 border">${item.quantity}</td>
                <td class="px-4 py-2 border">$${item.product.price}</td>
                <td class="px-4 py-2 border">$${item.totalPrice}</td>
                <td class="px-4 py-2 border text-center">
                  <form action="${pageContext.request.contextPath}/removeFromCart" method="post" class="inline-block">
                    <input type="hidden" name="productId" value="${item.product.id}" />
                    <button type="submit"
                            class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 transition text-sm">
                      Quitar
                    </button>
                  </form>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>

        <%
          for (CartItem item : cart) {
            total += item.getTotalPrice();
          }
        %>

        <!-- Total y Checkout -->
        <div class="mt-6 flex flex-col md:flex-row justify-between items-center gap-4">
          <h3 class="text-xl font-semibold text-gray-800">
            Total: $<%= String.format("%.2f", total) %>
          </h3>
          <form action="checkout.jsp">
            <button type="submit"
                    class="bg-green-600 text-white px-6 py-2 rounded-md hover:bg-green-700 transition text-base">
              Proceder al Pago
            </button>
          </form>
        </div>
      </c:otherwise>
    </c:choose>

  </div>
</div>
