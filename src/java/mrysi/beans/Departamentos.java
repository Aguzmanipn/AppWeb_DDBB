/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrysi.beans;


/**
 *
 * @author SebaxPC
 */
public class Departamentos implements java.io.Serializable {
    
    private int id;
    private String nombre;
 
    public void setIDDepartamento(int id){
    this.id = id;
    }

    public void setNombreDepartamento(String nombre){
    this.nombre = nombre;
    }
    
    public int getIDDepartamento(){
    return id;
    }

    public String getNombreDepartamento(){
    return nombre;
    }
}
