<%@ page import="io.github.salazar.ecommerce.model.*" %>
<%
  User user = (User) session.getAttribute("loggedUser");
  if (user == null) {
    response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
    return;
  }

  if (user.getIdProfile() != 1) {
    response.sendRedirect(request.getContextPath() + "/index.jsp");
    return;
  }
%>

<jsp:include page="../layout.jsp">
  <jsp:param name="content" value="/content/manageUsersContent.jsp" />
</jsp:include>
