<%@ page contentType="text/html;charset=UTF-8" %>

<div class="min-h-screen flex items-center justify-center bg-gray-100 py-10 px-4">
    <div class="w-full max-w-md bg-white rounded-xl shadow-lg p-8">
        <h2 class="text-2xl font-bold text-center text-blue-700 mb-6">Registro de Usuario</h2>

        <form action="<%= request.getContextPath() %>/register" method="post" onsubmit="return validateForm();">
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Nombre</label>
                <input type="text" name="name" required
                       class="w-full border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Cédula (DNI)</label>
                <input type="text" name="dni" required pattern="\\d{10}" title="10-digit number"
                       class="w-full border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Correo Electrónico</label>
                <input type="email" name="email" required
                       class="w-full border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Contraseña</label>
                <input type="password" name="password" id="password" required
                       class="w-full border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Confirmar Contraseña</label>
                <input type="password" id="confirm" required
                       class="w-full border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Estado Civil</label>
                <select name="maritalStatusId"
                        class="w-full border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <option value="1">Casado/a</option>
                    <option value="2">Soltero/a</option>
                    <option value="3">Divorciado/a</option>
                </select>
            </div>

            <div class="mb-6">
                <label class="block text-sm font-medium text-gray-700">Perfil</label>
                <select name="profileId"
                        class="w-full border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <option value="2">Cliente</option>
                </select>
            </div>

            <button type="submit"
                    class="w-full bg-blue-600 text-white py-2 rounded-md font-semibold hover:bg-blue-700 transition duration-300">
                Registrarse
            </button>
        </form>

        <% if (request.getAttribute("error") != null) { %>
        <p class="text-red-600 text-sm mt-4 text-center font-medium">
            <%= request.getAttribute("error") %>
        </p>
        <% } %>
    </div>
</div>

<script>
    function validateForm() {
        const password = document.getElementById("password").value;
        const confirm = document.getElementById("confirm").value;
        if (password !== confirm) {
            alert("¡Las contraseñas no coinciden!");
            return false;
        }
        return true;
    }
</script>
