package org.bill.foodsystem;

import java.io.*;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.bill.foodsystem.entity.Ftype;
import org.bill.foodsystem.service.FtypeService;
import org.bill.foodsystem.service.impl.FtypeServiceImpl;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        FtypeService service = new FtypeServiceImpl();

        List<Ftype> list = service.getAllWithFood();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        //for(Ftype f : list) {
        //    out.println("<p>" + f + "</p>");
        //    out.println("<p>" + f.getFoodList()+ "</p>");
        // }

        response.setContentType("text/html");

        // Hello

        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}