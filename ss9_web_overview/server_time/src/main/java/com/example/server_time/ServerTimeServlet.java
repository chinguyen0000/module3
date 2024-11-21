package com.example.server_time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import static java.lang.System.out;

@WebServlet(name = "ServerTimeServlet", urlPatterns = "/index")
public class ServerTimeServlet extends HttpServlet {
    private Date serverTime;
    @Override
    public void init(){
        out.println("init running ...");
        serverTime = new Date();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out.println("doGet running ...");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>" + serverTime + "</h1>");
        writer.println("</body></html>");
    }
    public void destroy(){
        out.println("destroy running ...");
    }
}
