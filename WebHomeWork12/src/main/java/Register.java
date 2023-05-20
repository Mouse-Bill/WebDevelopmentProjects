import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@WebServlet(name = "register", value = "/register")
@MultipartConfig
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Collection<Part> coll = req.getParts();

        Iterator<Part> it = coll.iterator();

        boolean uploadIsOK = false;

        String newFilename = "";

        while (it.hasNext()) {
            Part part = it.next();
            System.out.println(part.getContentType());
            System.out.println(part.getName());
            System.out.println(part.getSubmittedFileName());

            String filename = part.getSubmittedFileName();

            if (filename == null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream()));
                String value = br.readLine();
                System.out.println(value);
            } else {
                String path = req.getServletContext().getRealPath("upload");
                newFilename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + filename;
                String pathAndFilename = path + "/" + newFilename;
                System.out.println(pathAndFilename);
                part.write(pathAndFilename);
                uploadIsOK = new File(pathAndFilename).exists();
            }
        }
        System.out.println(uploadIsOK);

        resp.getWriter().println("<!DOCTYPE html>");
        resp.getWriter().println("<html lang=\"en\">");
        resp.getWriter().println("<head>");
        resp.getWriter().println("<meta charset=\"UTF-8\">");
        resp.getWriter().println("<title>Userinfo</title>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body style=\"font-family: sans-serif\">");
        resp.getWriter().println("<h1>Userinfo</h1>");
        resp.getWriter().println("<p>username: " + username + "</p>");
        resp.getWriter().println("<p>password: " + password + "</p>");
        resp.getWriter().println("<p>Avatar: " + uploadIsOK + "</p>");
        resp.getWriter().println("<img src=\"upload/"+ newFilename +"\" alt=\"\">");
        resp.getWriter().println("</body>");
    }
}
