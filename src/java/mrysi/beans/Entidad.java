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
public class Entidad implements Serializable {

    private int idEntidad;
    private String nombreEntidad;
    
    public Entidad(){}
    
    public Entidad(int idEntidad, String nombreEntidad){
        this.idEntidad = idEntidad;
        this.nombreEntidad = nombreEntidad;
    }
    
    public int getIdEntidad(){
        return idEntidad;
    }
    
    public String getNombreEntidad(){
        return nombreEntidad;
    }
    
    public void setIdEntidad(int idEntidad){
        this.idEntidad = idEntidad;
    }
    
    public void setNombreEntidad(String nombreEntidad){
        this.nombreEntidad = nombreEntidad;
    }
    
    public interface EntidadDAO {
        public void insert (Entidad entidad) throws SQLException;
        public void update (Entidad entidad) throws SQLException;
        public void delete (Integer id) throws SQLException;
        public Entidad read (Integer id) throws SQLException;
        public List<Entidad> readAll() throws SQLException;
    }
    
    public class EntidadDAOImp implements EntidadDAO{
        
    private String TABLE_NAME;
    private ConexionMySQL conMySQL;
    private Connection conn;
    
    public EntidadDAOImp(){
        TABLE_NAME = "Entidades";
        conMySQL = new ConexionMySQL();
    }

        @Override
        public void insert(Entidad entidad) throws SQLException {
            //abrir la conexion
            conn = conMySQL.getConnection();
            String insertQuery = "INSERT INTO"+TABLE_NAME+"(nombreEntidad)"+"VALUES(?)";
            try(PreparedStatement ps = conn.prepareStatement(insertQuery)){
                //enviar el comando insert
                ps.setString(1, entidad.getNombreEntidad());
                ps.executeUpdate();
            }
            conn.close();
        }

        @Override
        public void update(Entidad entidad) throws SQLException {
           conn = conMySQL.getConnection();
            String updateQuery = "UPDATE"+TABLE_NAME
                    +"SET nombreEntidad = ?"
                    +"WHERE idEntidad = ?";
            try(PreparedStatement ps = conn.prepareStatement(updateQuery)){
                //agregar parámetros y ejecutar la actualización
                ps.setString(1, entidad.getNombreEntidad());
                ps.setInt(2, entidad.getIdEntidad());
                ps.executeUpdate();
            }
            conn.close();
        }

        @Override
        public void delete(Integer id) throws SQLException {
            conn = conMySQL.getConnection();
            String deleteQuery = "DELETE FROM"+TABLE_NAME
                    +"WHERE idEntidad = ?";
            try(PreparedStatement ps = conn.prepareStatement(deleteQuery)){
                //agregar parámetros y ejecutar el Delete
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            conn.close();

        }

        @Override
        public Entidad read(Integer id) throws SQLException {
            Entidad entidad = null;
            conn = conMySQL.getConnection();
            String Query = "SELECT idEntidad"+TABLE_NAME
                    +"WHERE idEntidad = ?";
            try(PreparedStatement ps = conn.prepareStatement(Query)){
                //agregar parámetros y ejecutar el Delete
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        //obtener cada una de las columnas y mapearlas a la clase identidad
                        entidad = new Entidad(id, rs.getString("nombreEntidad"));
                    }
                }
            }
            return entidad;
        }

        @Override
        public List<Entidad> readAll() throws SQLException {
            List<Entidad> entidades = new ArrayList();
            conn = conMySQL.getConnection();
            String Query = "SELECT idEntidad"
                    +"nombreEntidad FROM" +TABLE_NAME;
            try(PreparedStatement ps = conn.prepareStatement(Query)){
                //agregar parámetros y ejecutar la consulta
                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        //obtener cada una de las columnas y mapearlas a la clase identidad
                       Entidad entidad = new Entidad(
                       rs.getInt("idEntidad"),
                       rs.getString("nombreEntidad"));
                       entidades.add(entidad);
                    }
                }
            }
            return entidades;
        }
    }
    
}
