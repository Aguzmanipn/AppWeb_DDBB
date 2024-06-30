/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrysi.beans;

import Conexion.ConexionMySQL;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SebaxPC
 */
public class Grado implements Serializable {
    
   private int idGrado;
   private String nombreGrado;
   private int idEntidad;
   private Entidad entidad;
   
   public Grado(){}
   
   public Grado(int idGrado, String nombreGrado,
           int idEntidad, Entidad Entidad){
       this.idGrado = idGrado;
       this.nombreGrado = nombreGrado;
       this.idEntidad = idEntidad;
       this.entidad = Entidad;
   }

    private Grado(Integer id, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Grado(int i, String name, int idGrado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   public int getIdGrado(){
        return idGrado;
    }
    
   public String getNombreGrado(){
        return nombreGrado;
    }
   
   public int getIdEntidad(){
        return idEntidad;
    }
    
   public Entidad getEntidad(){
        return entidad;
    }
   
   public interface GradoDAO{
       
       public void insert(Grado grado) throws SQLException;
       public void update(Grado grado) throws SQLException;
       public void delete(Integer id) throws SQLException;
       public Grado read (Integer id) throws SQLException;
       public List<Grado> readAll() throws SQLException;
   }
   
   public class GradoDAOImp implements GradoDAO{
    private String TABLE_NAME;
    private ConexionMySQL conMySQL;
    private Connection conn;
            
            public GradoDAOImp(){
        TABLE_NAME = "grado";
        conMySQL = new ConexionMySQL();
    }

        @Override
        public void insert(Grado grado) throws SQLException {
             //abrir la conexion
            conn = conMySQL.getConnection();
            String insertQuery = "INSERT INTO"+TABLE_NAME+"(nombreGrado, idGrado"
                    + "VALUES(?,?)";
            try(PreparedStatement ps = conn.prepareStatement(insertQuery)){
                //enviar el comando insert
                ps.setString(1, grado.getNombreGrado());
                ps.setInt(2, grado.getIdEntidad());
                ps.executeUpdate();
            }
            conn.close();
        }

        @Override
        public void update(Grado grado) throws SQLException {
            conn = conMySQL.getConnection();
            String updateQuery = "UPDATE"+TABLE_NAME
                    +"SET nombreGrado = ?"
                    +"WHERE idGrado = ?";
            try(PreparedStatement ps = conn.prepareStatement(updateQuery)){
                //agregar parámetros y ejecutar la actualización
                ps.setString(1, grado.getNombreGrado());
                ps.setInt(2, grado.getIdGrado());
                ps.executeUpdate();
            }
            conn.close();
        }

        @Override
        public void delete(Integer id) throws SQLException {
            conn = conMySQL.getConnection();
            String deleteQuery = "DELETE FROM"+TABLE_NAME
                    +"WHERE idGrado = ?";
            try(PreparedStatement ps = conn.prepareStatement(deleteQuery)){
                //agregar parámetros y ejecutar el Delete
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            conn.close();
        }

        @Override
        public Grado read(Integer id) throws SQLException {
            Grado grado = null;
            
            conn = conMySQL.getConnection();
            String Query = "SELECT idGrado, "
                    + "nombreGrado FROM "+ TABLE_NAME
                    + "WHERE idGrado = ?";
            try(PreparedStatement ps = conn.prepareStatement(Query)){
                //agregar parámetros y ejecutar el Delete
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        //obtener cada una de las columnas y mapearlas a la clase identidad
                       grado = new Grado(id, 
                               rs.getString("nombreGrado"));
                   
                    }
                }
            }
            return grado;
        }

        @Override
        public List<Grado> readAll() throws SQLException {
            List<Grado> grados = new ArrayList();
            conn = conMySQL.getConnection();
            String Query = "SELECT idGrado"
                    +"nombreGrado FROM" +TABLE_NAME;
            try(PreparedStatement ps = conn.prepareStatement(Query)){
                //agregar parámetros y ejecutar la consulta
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()){
                        //obtener cada una de las columnas y mapearlas a la clase identidad
                       Grado grado = new Grado(
                       rs.getInt("idGrado"),
                       rs.getString("nombreGrado"));
                       grados.add(grado);
                    }
                }
            }
            return grados;
        }
    }

        
}
    

