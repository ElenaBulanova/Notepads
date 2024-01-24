package org.klozevitz.classwork.servlets.task4;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.klozevitz.classwork.db.DbDao;

import org.klozevitz.classwork.db.task4;
import org.klozevitz.classwork.model.Notepad;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/pagesAmountFilter")
public class PagesAmountFilter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            completeRequest(request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/templates/pagesAmountFilter.jsp");
        dispatcher.forward(request, response);
    }

    private void completeRequest(HttpServletRequest request) throws SQLException {
        task4 dao = new DbDao();
        request.setAttribute("headers",
                Arrays.stream(Notepad.class.getDeclaredFields()).map(Field::getName).toList());

        if((request.getParameter("amountStart") != null) && (request.getParameter("amountEnd") != null)) {
        List<Notepad> all = dao.pagesAmount(Integer.parseInt(request.getParameter("amountStart")), Integer.parseInt(request.getParameter("amountEnd")));
        request.setAttribute("all", all);
        request.setAttribute("amountStart", request.getParameter("amountStart"));
        request.setAttribute("amountEnd", request.getParameter("amountEnd"));
        }

        dao.closeConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}


