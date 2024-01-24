package org.klozevitz.classwork.servlets.task5;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.db.task5;
import org.klozevitz.classwork.model.Notepad;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@WebServlet("/update")
public class Update extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            completeRequest(request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/templates/update.jsp");
        dispatcher.forward(request, response);
    }

    private void completeRequest(HttpServletRequest request) throws SQLException {
        task5 dao = new DbDao();
        List<Notepad> all = dao.all();
        List<String> selected = all.stream().map(n -> String.format("%d: %s - %s", n.getId(), n.getBrand(), n.getModel())).toList();
        request.setAttribute("types", dao.pageTypes());
        request.setAttribute("covers", dao.covers());

        request.setAttribute("all", selected);
        String idString = request.getParameter("id");
        if (idString != null) {
            String id = idString.substring(0, idString.indexOf(':'));
            Notepad not = dao.findById(Integer.parseInt(id));
            request.setAttribute("notepadToUpdate",not);
        }
        request.setAttribute("headers",
                Arrays.stream(Notepad.class.getDeclaredFields()).map(Field::getName).toList());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Notepad notepadToUpdate = new Notepad(request);
        notepadToUpdate.setId(Integer.parseInt(request.getParameter("id")));
        response.setContentType("text/html");
        task5 dao = new DbDao();
        System.out.println(notepadToUpdate);
        dao.update(notepadToUpdate);
        try {
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //почистить все параметры вызвать doGet
    //doGet(r  r );
}
