<%@ page import="java.util.*" %>
<%@ page import="io.github.salazar.ecommerce.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  User user = (User) session.getAttribute("loggedUser");
  if (user == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>

<jsp:include page="../layout.jsp">
  <jsp:param name="content" value="/content/cartContent.jsp" />
</jsp:include>
