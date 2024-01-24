<%@ page import="java.util.List" %>
<%@ page import="org.klozevitz.classwork.model.Notepad" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>countriesCountNotepads</title>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>
<div class="container">
    <div class="modal">
        <p>
        <h2>Страны и количество блокнотов в них: </h2>
        </p>

        <%Map<String, Integer> all = (Map<String, Integer>) request.getAttribute("all");%>

        <div class="tableDiv">
            <table class="responseTable">

                <tr>
                    <th class="cell"> Country</th>
                    <th class="cell"> Count</th>
                </tr>

                <tr>
                    <%for (Map.Entry<String, Integer> entry : all.entrySet()) {%>
                    <td class="cell"> <%= entry.getKey()%> </td>
                    <td class="cell"> <%= entry.getValue()%> </td>
                </tr>

                    <%}%>


            </table>
        </div>

    </div>
</div>
</body>
</html>

