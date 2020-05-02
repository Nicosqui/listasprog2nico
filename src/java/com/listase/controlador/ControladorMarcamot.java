/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import com.listase.modelo.MarcaMotogp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas
 */

public class ControladorMarcamot {
    private List<MarcaMotogp> marcas;



    public ControladorMarcamot() {
        //llenar las ciudades y los departamentos
        llenarMarcas();
    }
          
    
    public List<MarcaMotogp> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<MarcaMotogp> marcas) {
        this.marcas = marcas;
    }
    
    private void llenarMarcas()
    {
        marcas = new ArrayList<>();
        marcas.add(new MarcaMotogp("Yamaha"));
        marcas.add(new MarcaMotogp("Honda"));
        marcas.add(new MarcaMotogp("Ducati"));
        
    }
    
}
