<%@ page import="java.util.List" %>
<%@ page import="org.klozevitz.classwork.model.Notepad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pages amount Filter</title>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>
<div class="container">
    <div class="modal">
        <h2>Введите диапазон страниц:</h2>

        <form method="get" action="${pageContext.request.contextPath}/pagesAmountFilter">
            <%List<String> headers = (List<String>) request.getAttribute("headers");%>
            <%List<Notepad> all = (List<Notepad>) request.getAttribute("all");%>

            <input type="text" class="requestInput" name="amountStart" placeholder="от" value="0">
            <input type="text" class="requestInput" name="amountEnd" placeholder="до" value="100">

            <input type="submit" class="requestButton" value="filter">
        </form>


        <%if ((request.getParameter("amountStart") != null) && (request.getParameter("amountEnd") != null)) {%>
        <div class="tableDiv">
            <table class="responseTable">
                <tr>
                    <%for (String field : headers) {%>
                    <th class="cell"><%=field%>
                    </th>
                    <%}%>
                </tr>
                <%for (Notepad notepad : all) {%>
                <tr>
                    <td class="cell"><%=notepad.getId()%></td>
                    <td class="cell"><%=notepad.getBrand()%></td>
                    <td class="cell"><%=notepad.getModel()%></td>
                    <td class="cell"><%=notepad.getPagesAmount()%></td>
                    <td class="cell"><%=notepad.getCover()%></td>
                    <td class="cell"><%=notepad.getCountry()%></td>
                    <td class="cell"><%=notepad.getType()%></td>
                </tr>
                <%}%>

            </table>
        </div>
        <%}%>
    </div>
</div>
</body>

