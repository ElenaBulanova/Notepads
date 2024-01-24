<%@ page import="java.util.List" %>
<%@ page import="org.klozevitz.classwork.model.Notepad" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>countryMinMax</title>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>
<div class="container">
    <div class="modal">

        <%Map<String, Integer> min = (Map<String, Integer>) request.getAttribute("min");%>
        <%Map<String, Integer> max = (Map<String, Integer>) request.getAttribute("max");%>

        <p>
        <h2>Страна(ы) с наименьшим количеством блокнотов: </h2>
        </p>
        <div class="tableDiv">
            <table class="responseTable">
                <tr>
                    <th class="cell"> Country</th>
                    <th class="cell"> Count</th>
                </tr>

                <tr>
                    <%for (Map.Entry<String, Integer> entry : min.entrySet()) {%>
                    <td class="cell"> <%= entry.getKey()%> </td>
                    <td class="cell"> <%= entry.getValue()%> </td>
                </tr>
                <%}%>
            </table>
        </div>
        <p>
        <h2>Страна(ы) с наибольшим количеством блокнотов: </h2>
        </p>
        <div class="tableDiv">
            <table class="responseTable">
                <tr>
                    <th class="cell"> Country</th>
                    <th class="cell"> Count</th>
                </tr>

                <tr>
                    <%for (Map.Entry<String, Integer> entry : max.entrySet()) {%>
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


