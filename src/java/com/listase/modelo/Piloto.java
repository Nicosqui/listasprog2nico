/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import java.io.Serializable;

/**
 *
 * @author nicolas
 */
public class Piloto implements  Serializable{
    private String nombre;
    private short codigo; 
    
    
    public Piloto(String nombre, short codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        
    }

    public Piloto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Piloto(String mauricio, short s, byte b, boolean b0, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getCodigo() {
        return codigo;
    }

    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }
    
    @Override
    public String toString() {
       return this.nombre; 
    }
}
