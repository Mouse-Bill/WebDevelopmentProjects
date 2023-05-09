package org.bill.webhomework09.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.io.*;

public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("count.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        try{
            String count = br.readLine();
            br.close();
            sce.getServletContext().setAttribute("count", Integer.parseInt(count));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        int count = (int) sce.getServletContext().getAttribute("count");
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(sce.getServletContext().getRealPath("/WEB-INF/classes/count.txt")));
            bw.write(String.valueOf(count));
            bw.newLine();
            bw.flush();
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
