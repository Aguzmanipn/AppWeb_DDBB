/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import mrysi.beans.Entidad;

/**
 *
 * @author SebaxPC
 */
public class ConexionMySQL {
    
    private Connection conn;
    private String bd;
    private String host;
    private String port;
    
    public ConexionMySQL(){
        bd = "universidad";
        host = "localhost";
        port = "3306";
        conn = null;
    } 
    
    private boolean setDriver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
            
        } catch (ClassNotFoundException ex){
            return false;
        }
    }
    
    public Connection getConnection() throws SQLException{
        if(setDriver()){
            String URL="jdbc:mysql://"+host+":"+port+"/"+bd;
            conn = DriverManager.getConnection(URL, "lania", "admin");
        }
        return conn;
    } 
}
