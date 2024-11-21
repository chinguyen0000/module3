package com.example.product_discount;

import javafx.print.Printer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProductDiscountCalculator", value = "/display-discount")
public class ProductDiscountCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float price = Float.parseFloat(req.getParameter("price"));
        short discount = Short.parseShort(req.getParameter("discount"));
        double discountedAmount = price * discount * 0.01;
        double discountedPrice = price - discountedAmount;

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<table>\n" +
                "        <tr>\n" +
                "            <td>Product Description</td>\n" +
                "            <td>"+req.getParameter("description")+"</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>List Price</td>\n" +
                "            <td>"+price+" VND</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>Discount Percent</td>\n" +
                "            <td>"+discount+" %</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>Discount Amount</td>\n" +
                "            <td>"+discountedAmount+" VND</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>Discount Price</td>\n" +
                "            <td>"+discountedPrice+" VND</td>\n" +
                "        </tr>\n" +
                "    </table>");
        out.println("</body>");
        out.println("</html>");
    }
}
