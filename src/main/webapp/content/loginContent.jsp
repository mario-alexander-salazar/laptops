<section class="flex items-center justify-center min-h-[80vh] bg-gray-100">
  <form action="<%= request.getContextPath() %>/login"
        method="post"
        class="bg-white p-8 rounded-lg shadow-md w-full max-w-md space-y-6">

    <h2 class="text-2xl font-semibold text-center text-gray-800">Log in to your account</h2>

    <div>
      <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
      <input type="email" name="email" required
             class="mt-1 block w-full border border-gray-300 rounded-md px-4 py-2 focus:ring-2 focus:ring-blue-500 focus:outline-none">
    </div>

    <div>
      <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
      <input type="password" name="password" required
             class="mt-1 block w-full border border-gray-300 rounded-md px-4 py-2 focus:ring-2 focus:ring-blue-500 focus:outline-none">
    </div>

    <button type="submit"
            class="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded">
      Login
    </button>

    <% if (request.getAttribute("error") != null) { %>
    <p class="text-red-600 text-center text-sm"><%= request.getAttribute("error") %></p>
    <% } %>
  </form>
</section>
