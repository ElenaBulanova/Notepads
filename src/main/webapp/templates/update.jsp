<%@ page import="java.util.List" %>
<%@ page import="org.klozevitz.classwork.model.Notepad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>
<div class="container">
    <div class="modal">
        <p>
        <h2 class="str">Изменить блокнот: </h2>

        <%List<String> all = (List<String>) request.getAttribute("all");%>

        <form method="get" action="${pageContext.request.contextPath}/update">

            <select class="requestInput" name="id">
                <%for (String n : all) {%>
                <option><%=n%>
                </option>
                <%}%>
            </select>
            <input type="submit" value="choose" class="requestButton">
        </form>

        <form method="post" action="${pageContext.request.contextPath}/update">
            <%Notepad notepadToUpdate = (Notepad) request.getAttribute("notepadToUpdate");%>

            <% if (notepadToUpdate != null) {%>
                <%List<String> types = (List<String>) request.getAttribute("types");%>
                <%List<String> covers = (List<String>) request.getAttribute("covers");%>
                <input type="text" class="requestInput" name="id"  value=<%=notepadToUpdate.getId()%> readonly>
                <input type="text" class="requestInput" name="brand"  value=<%=notepadToUpdate.getBrand()%>>
                <input type="text" class="requestInput" name="model" value=<%=notepadToUpdate.getModel()%>>
                <input type="text" class="requestInput" name="pagesAmount" value=<%=notepadToUpdate.getPagesAmount()%>>
                <select class="requestInput" name="cover">
                    <option value=<%=notepadToUpdate.getCover()%> selected><%=notepadToUpdate.getCover()%> </option>
                    <%for (String cover : covers) {
                        if(!cover.equals(notepadToUpdate.getCover())) {%>
                            <option><%=cover%>
                            </option>
                        <%}%>
                    <%}%>
                </select>
                <input type="text" class="requestInput" name="country" value=<%=notepadToUpdate.getCountry()%>>
                <select class="requestInput" name="type">
                    <option value=<%=notepadToUpdate.getType()%> selected><%=notepadToUpdate.getType()%> </option>
                    <%for (String type : types) {
                        if(!type.equals(notepadToUpdate.getType())) {%>
                            <option><%=type%>
                            </option>
                        <%}%>
                    <%}%>
                </select>
            <%}%>
            <input type="submit" value="update" class="requestButton">
        </form>

        </p>

    </div>
</div>
</body>
</html>



<%--<%List<String> headers = (List<String>) request.getAttribute("headers");%>--%>
<%--            <input type="text" class="requestInput" name="updateNumber" placeholder="" value="1">--%>
<%--            <input type="submit" value="update" class="requestButton">--%>
<%--<div class="tableDiv">--%>
<%--    <table class="responseTable">--%>
<%--        <tr>--%>
<%--            <%for (String field : headers) {%>--%>
<%--            <th class="cell"><%=field%>--%>
<%--            </th>--%>
<%--            <%}%>--%>
<%--        </tr>--%>

<%--        <%for (Notepad notepad : all) {%>--%>
<%--        <tr>--%>
<%--            <td class="cell"><%=notepad.getId()%>--%>
<%--            </td>--%>
<%--            <td class="cell"><%=notepad.getBrand()%>--%>
<%--            </td>--%>
<%--            <td class="cell"><%=notepad.getModel()%>--%>
<%--            </td>--%>
<%--            <td class="cell"><%=notepad.getPagesAmount()%>--%>
<%--            </td>--%>
<%--            <td class="cell"><%=notepad.getCover()%>--%>
<%--            </td>--%>
<%--            <td class="cell"><%=notepad.getCountry()%>--%>
<%--            </td>--%>
<%--            <td class="cell"><%=notepad.getType()%>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <%}%>--%>
<%--    </table>--%>
<%--</div>--%>

