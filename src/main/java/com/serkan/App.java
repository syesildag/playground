package com.serkan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class App {

    public static void main(String[] args) throws LifecycleException, FileNotFoundException, IOException {

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(new File("/tmp/tomcat").getAbsolutePath());
        tomcat.setHostname(Config.INSTANCE.getProperty("SERVER_HOSTNAME"));
        tomcat.setPort(Config.INSTANCE.getProperty("SERVER_PORT", Integer.class));
        tomcat.getConnector();

        var contextPath = Config.INSTANCE.getProperty("CONTEXT_PATH");
        var docBase = new File(".").getAbsolutePath();

        var context = tomcat.addContext(contextPath, docBase);
        context.setCookies(true);
        //context.setAltDDName(new File("src/main/resources/web.xml").getAbsolutePath());

        final var servlet = new HttpServlet() {

            @Override
            protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
                    throws ServletException, IOException {
                final PrintWriter writer = resp.getWriter();

                writer.println("<html><title>Welcome</title><body>");
                writer.println("<h1>Have a Great Day!</h1>");
                writer.println("</body></html>");
            }
        };

        var servletName = "Home";
        var urlPattern = "/home";

        tomcat.addServlet(contextPath, servletName, servlet);
        context.addServletMappingDecoded(urlPattern, servletName);

        tomcat.start();
        tomcat.getServer().await();
    }
}