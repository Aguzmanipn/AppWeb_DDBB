/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrysi.servlets;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mrysi.beans.Departamentos;

/**
 *
 * @author SebaxPC
 */
public class ServletEscuela extends HttpServlet {

Connection connect;
    Statement statement;
    ResultSet resultSet;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
             Class.forName("com.mysql.jdbc.Driver");
             
             String URL = "jdbc:mysql://localhost/universidad?user=root&password=katoninja";
             
             connect = (Connection) DriverManager.getConnection(URL);
             statement = (Statement) connect.createStatement();
             
             request.setAttribute("ListaDepartamentos", getListaDepartamentos());
             
             connect.close();
             
            } catch(ClassNotFoundException | SQLException ex){
                System.out.println(ex);
        }     
    }
   
    public List<Departamentos> getListaDepartamentos() throws SQLException{
        String query = "SELECT * FROM departamento";
        resultSet = (ResultSet) statement.executeQuery(query);
        
        List<Departamentos> Departamentos = new ArrayList(); 
        Departamentos departamento;
        while (resultSet.next()){
            departamento = new Departamentos();
            departamento.setIDDepartamento(resultSet.getInt(1));
            departamento.setNombreDepartamento(resultSet.getString(2));
            Departamentos.add(departamento);
        }
        return Departamentos;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
