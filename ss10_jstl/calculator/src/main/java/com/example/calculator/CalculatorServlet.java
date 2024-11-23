package com.example.calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CalculatorServlet", value = "/calculator")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float firstOperand = Integer.parseInt(req.getParameter("firstOperand"));
        float secondOperand = Integer.parseInt(req.getParameter("secondOperand"));
        char operators = req.getParameter("operators").charAt(0);
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<h1>Result:</h1>");
        try{
            float result = calculate(firstOperand, secondOperand, operators);
            writer.println(firstOperand + " " + operators + " " + secondOperand + " = " + result);
        }catch (Exception ex){
            writer.println("Error: " + ex.getMessage());
        }
        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public static float calculate(float firstOperand, float secondOperand, char operators) {
        switch (operators){
            case '+':
                return firstOperand + secondOperand;
            case '-':
                return firstOperand - secondOperand;
            case '*':
                return firstOperand * secondOperand;
            case '/':
                if(secondOperand != 0)
                    return firstOperand / secondOperand;
                else
                    throw new RuntimeException("Can't divide by zero");
            default:
                throw new RuntimeException("Invalid operation");
        }
    }
}
