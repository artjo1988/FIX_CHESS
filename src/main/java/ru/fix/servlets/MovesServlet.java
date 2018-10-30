package ru.fix.servlets;

import com.google.gson.Gson;
import ru.fix.models.Information;
import ru.fix.services.CountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MovesServlet extends HttpServlet {

    Information information = new Information();

    CountService countService = new CountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String width = req.getParameter("width");
        String height = req.getParameter("height");
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        information.setAnswer(countService.calculateMoves(width, height, start, end));
        resp.setContentType("application/json");
        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(information));
    }
}
