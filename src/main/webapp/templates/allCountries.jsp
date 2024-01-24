<%@ page import="java.util.List" %>
<%@ page import="org.klozevitz.classwork.model.Notepad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>all</title>
  <link rel="stylesheet" href="../styles/style.css">
</head>
<body>
<div class="container">
  <div class="modal">
    <p>
    <h2>Все страны - производители:</h2>
    </p>
<%--    <%List<String> headers = (List<String>) request.getAttribute("headers");%>--%>
    <%List<String> all = (List<String>) request.getAttribute("all");%>
    <%for (String country : all) {%>
      <p class="str">
        <%= country%>
      </p>
    <%}%>

  </div>
</div>
</body>
</html>

