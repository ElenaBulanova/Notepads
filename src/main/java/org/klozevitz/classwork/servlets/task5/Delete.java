package org.klozevitz.classwork.servlets.task5;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.db.task2;
import org.klozevitz.classwork.db.task5;
import org.klozevitz.classwork.model.Notepad;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@WebServlet("/delete")
public class Delete extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        completeRequest(request);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/templates/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void completeRequest(HttpServletRequest request) {
        task5 dao = new DbDao();
        List<Notepad> all = dao.all();

        request.setAttribute("headers",
                Arrays.stream(Notepad.class.getDeclaredFields()).map(Field::getName).toList());
        request.setAttribute("all", all);

        if(request.getParameter("deleteNumber") != null) {
            try {
                dao.deleteRow(Integer.parseInt(request.getParameter("deleteNumber")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(request.getParameter("deleteNumber"));
        }
        try {
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void destroy() {
    }
}
