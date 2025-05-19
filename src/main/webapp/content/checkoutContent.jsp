<%@ page import="io.github.salazar.ecommerce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>


<div class="min-h-screen bg-gray-100 flex items-center justify-center p-6">
    <div class="bg-white shadow-lg rounded-2xl p-8 w-full max-w-md">
        <h2 class="text-2xl font-bold text-center text-blue-700 mb-6">Detalles de Pago</h2>

        <form action="<%= request.getContextPath() %>/checkout" method="post" onsubmit="return validatePaymentForm();">
            <div class="mb-4">
                <label for="cardNumber" class="block text-sm font-medium text-gray-700 mb-1">Número de tarjeta</label>
                <input type="text" id="cardNumber" name="cardNumber" maxlength="16" required
                       class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>

            <div class="mb-4">
                <label for="expiry" class="block text-sm font-medium text-gray-700 mb-1">Fecha de expiración (MM/YY)</label>
                <input type="text" id="expiry" name="expiry" required
                       class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>

            <div class="mb-6">
                <label for="cvv" class="block text-sm font-medium text-gray-700 mb-1">CVV</label>
                <input type="text" id="cvv" name="cvv" maxlength="3" required
                       class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>

            <button type="submit"
                    class="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition duration-300 font-semibold">
                Pagar
            </button>
        </form>
    </div>
</div>

<script>
    function validatePaymentForm() {
        const cardNumber = document.getElementById("cardNumber").value;
        const expiry = document.getElementById("expiry").value;
        const cvv = document.getElementById("cvv").value;

        if (!/^\d{16}$/.test(cardNumber)) {
            alert("El número de tarjeta debe tener 16 dígitos.");
            return false;
        }

        if (!/^\d{2}\/\d{2}$/.test(expiry)) {
            alert("La fecha debe estar en formato MM/YY.");
            return false;
        }

        if (!/^\d{3}$/.test(cvv)) {
            alert("El CVV debe tener 3 dígitos.");
            return false;
        }

        return true;
    }
</script>
