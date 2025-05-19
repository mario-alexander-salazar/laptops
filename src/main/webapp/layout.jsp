<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Service Computer E-commerce</title>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
</head>
<body>

<main>
    <%@ include file="includes/header.jspf" %>
    <section class="main-content">
        <jsp:include page="${param.content}"/>
    </section>
    <%@ include file="includes/footer.jspf" %>
</main>

</body>
</html>
