/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import co.gov.atlantico.pqrsd.entities.;
//import co.gov.atlantico.pqrsd.entities.SeUsuarioRol;
import entities.PgUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author jgutierrez
 */
public class BaseController implements Serializable{
   
    protected String nombreUsuarioLogin;
    
    public String getNombreUsuarioLogin() {
        return nombreUsuarioLogin;
    }

    public void setNombreUsuarioLogin(String nombreUsuarioLogin) {
        this.nombreUsuarioLogin = nombreUsuarioLogin;
    }
    
    public void verificarSesion() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        PgUsuario us = (PgUsuario) context.getExternalContext().getSessionMap().get("usuario");
        //SeUsuarioRol rolUs = (SeUsuarioRol) context.getExternalContext().getSessionMap().get("rolU");
        //SeMenu menuUs = (SeMenu) context.getExternalContext().getSessionMap().get(us);

        System.out.println("valor de usuario: "+ us.getNombres());
        if ((us == null))// || (rolUs == null))
        {
            context.getExternalContext().redirect("/faces/views/index.xhtml");
        }else{
            this.nombreUsuarioLogin = us.getUsuario();
            ////System.out.println("MI USUARIO ES: " + this.nombreUsuarioLogin);
        }    
    }
    
    public BaseController() {

        FacesContext context = FacesContext.getCurrentInstance();


    }
    
}

