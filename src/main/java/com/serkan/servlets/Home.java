package com.serkan.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {

   @Override
   protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
         throws ServletException, IOException {
      final PrintWriter writer = resp.getWriter();

      writer.println("<html><title>Welcome</title><body>");
      writer.println("<h1>Have a Great Day!</h1>");
      writer.println("</body></html>");
   }
}