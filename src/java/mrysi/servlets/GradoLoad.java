/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrysi.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mrysi.beans.Entidad.EntidadDAOImp;
import mrysi.beans.Grado;
import mrysi.beans.Grado.GradoDAOImp;

/**
 *
 * @author SebaxPC
 */
@WebServlet(name = "GradoLoad", urlPatterns = {"/GradoLoad"})
public class GradoLoad extends HttpServlet {

    private GradoDAOImp ldi;
    private EntidadDAOImp edi; 
    
    @Override
    public void init() throws ServletException {
        String host = null;
        host = getServletContext().getInitParameter(host);
        ldi = new GradoDAOImp();
        edi = new EntidadDAOImp();
        super.init();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("ListaGrados", ldi.readAll());
            request.setAttribute("ListaEntidades", edi.readAll());
        } catch (SQLException ex){
            getServletContext().setAttribute("Err", ex);
            response.sendRedirect("Err.jsp");
        }
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
        if (esValido(request)){
            String name = request.getParameter("nombreGrado").toUpperCase().trim();
            int idGrado = Integer.valueOf(request.getParameter("idGrado"));
            try{
                ldi.insert((new Grado(0,name,idGrado)));
            } catch(SQLException ex){
                getServletContext().setAttribute("Err", ex);
                response.sendRedirect("Err.jsp");
            }
            response.sendRedirect("Grados/index.jsp");
        } else{
            getServletContext().setAttribute("msj", "No se pudo guardar"
                    + "registro, debido a que"
                    +"uno de los campos no es valido.");
            response.sendRedirect("Grados/new.jsp");
        }
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
