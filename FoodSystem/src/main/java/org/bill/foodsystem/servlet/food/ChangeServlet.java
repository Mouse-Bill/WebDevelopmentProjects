package org.bill.foodsystem.servlet.food;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/img/upload")
@MultipartConfig
public class ChangeServlet extends HttpServlet {
    private FoodService foodService = new FoodServiceImpl();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Collection<Part> coll = request.getParts();

        Iterator<Part> it = coll.iterator();

        boolean uploadIsOK = false;

        Map<String, Object> map = new HashMap<String,Object>();

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
                String path = request.getServletContext().getRealPath("foods");
                String newFilename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + filename;
                String pathAndFilename = path + "/" + newFilename;
                System.out.println(pathAndFilename);
                part.write(pathAndFilename);

                map.put("url", newFilename);

                uploadIsOK = new File(pathAndFilename).exists();

            }

            System.out.println("---------------------");

        }
        System.out.println(uploadIsOK);

        map.put("isOK", uploadIsOK);

        response.getWriter().write(new Gson().toJson(map));

    }

}
