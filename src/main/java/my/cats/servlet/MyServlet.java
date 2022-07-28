package my.cats.servlet;

import my.cats.model.Cat;
import my.cats.service.MyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyServlet extends HttpServlet {

    private final MyService myService = new MyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cats", myService.getAll());
        req.getRequestDispatcher("my.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        myService.save(new Cat(req.getParameter("name")));
        resp.sendRedirect(req.getContextPath() + "/check");
    }
}
