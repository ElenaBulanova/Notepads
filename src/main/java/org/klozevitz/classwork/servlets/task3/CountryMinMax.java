package org.klozevitz.classwork.servlets.task3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.db.task3;
import org.klozevitz.classwork.model.MinMax;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/countryMinMax")
public class CountryMinMax extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        try {
            completeRequest(request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/templates/countryMinMax.jsp");
        dispatcher.forward(request, response);
    }

    private void completeRequest(HttpServletRequest request) throws SQLException {
        task3 dao = new DbDao();
        Map<String, Integer> all = dao.countriesSummary();
        MinMax result =  dao.findMinMax(all);
        Map<String, Integer> max = result.getMax();
        Map<String, Integer> min = result.getMin();
        try {
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("min", min);
        request.setAttribute("max", max);
    }

    public void destroy() {
    }
}
