/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import java.util.ArrayList;
import java.util.List;
import com.listase.modelo.TipoUsuario;
import com.listase.modelo.Usuario;

/**
 *
 * @author carloaiza
 */
public class ControladorUsuarios {
    private List<TipoUsuario> tiposUsuario;
    private List<Usuario> usuarios;

    public ControladorUsuarios() {
        this.iniciarListados();
    }

    public List<TipoUsuario> getTiposUsuario() {
        return tiposUsuario;
    }

    public void setTiposUsuario(List<TipoUsuario> tiposUsuario) {
        this.tiposUsuario = tiposUsuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
    private void iniciarListados()
    {
        //Simula la conexion a bds o archivos planos
        tiposUsuario = new ArrayList<>();
        tiposUsuario.add(new TipoUsuario(1,"Administrador"));
        tiposUsuario.add(new TipoUsuario(2,"Consulta"));
        tiposUsuario.add(new TipoUsuario(3,"Secretaria"));
        
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("nico50829@gmail.com","123456","Nicolas Trujillo",
         tiposUsuario.get(0)));
        
        usuarios.add(new Usuario("usuario@gmail.com","654321","Consulta Consulta",
        tiposUsuario.get(1)));
    }
    
    
    public Usuario encontrarUsuarioxCorreo(String correo)
    {
        Usuario usuarioEncontrado = null;
        //Recorre la lista de principio a fin 
        for(Usuario usu: this.usuarios)
        {
            if(usu.getCorreo().equals(correo))
            {
                return usu;
            }
        }
        
        return usuarioEncontrado;
    }
    
    
}
