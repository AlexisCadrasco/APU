package controllers;

//import entities.PgParametroDetalle;
//import entities.SeMenu;
import ejbs.UsuarioFacade;
//import ejbs.PgUsuarioRolFacade;
//import ejbs.PgParametroDetalleFacade;
import entities.PgUsuario;
import entities.PgUsuarioRol;
import entities.PgMenu;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *String
 * @author Juan Carlos Gutiérrez Meriño
 */
@ManagedBean(name = "loginCtrl")
@SessionScoped
public class LoginCtrl extends BaseController implements java.io.Serializable {
    
    @EJB
    private UsuarioFacade UsuarioFacade;
    
    //private PgParametroDetalleFacade PgParametroDetalleFacade;

    public UsuarioFacade getUsuarioFacade() {
        return UsuarioFacade;
    }

    public void setJcgUsuarioFacade(UsuarioFacade UsuarioFacade) {
        this.UsuarioFacade = UsuarioFacade;
    }

    //Model fields.
    private Long id;
    private String usuario;
    private String password;
    private PgUsuario usuarioSession;
    //private PgParametroDetalle parametro;
    private String passwordActual;
    private String passwordNuevo1;
    private String passwordNuevo2;

    @PostConstruct
    private void init(){
        usuarioSession = new PgUsuario();
        cleanForm();
        cleanFormChangePassword();
        
    }

    public String login() throws Exception
    {
        boolean loggedIn = false;
        //System.out.println("USUARIO: "+usuario+ " PASSWORD:"+password);
        if (this.usuario == null) {
            loggedIn = false;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Validación", "Debe ingresar un usuario"));
            return "index";
        }
        if (this.password == null) {
            loggedIn = false;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Validación", "Debe ingresar una contraseña"));
            return "index";
        }
        try{
            Long access = UsuarioFacade.validLogin(usuario, password);
            if (access == 1) {
                usuarioSession = UsuarioFacade.selectUser(usuario, password).get(0);
                //parametro = PgParametroDetalleFacade.listToday(usuarioSession.getEmpresa().getId()).get(0);
                loggedIn = true;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioSession);
                //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("parametro", parametro);
                this.usuario = usuarioSession.getUsuario();
                return "home?faces-redirect=true";
            }else{
                loggedIn = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validación", "Usuario o contraseña invalida"));
                return "index";
            }
        }catch(NullPointerException e){
            loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe ingresar un usuario o contraseña","Ingrese el usuario y contraseña"));
            return "index";
        }
    }

    public void cleanForm(){
        try{
            this.usuario = null;
            this.password = null;
        }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Validación", "Problemas al intentar limpiar"));
            return;
        }
    }
    
    public void cleanFormChangePassword(){
        try{
            this.passwordActual = null;
            this.passwordNuevo1 = null;
            this.passwordNuevo2 = null;
        }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Validación", "Problemas al intentar limpiar"));
            return;
        }
    }
    
    public void changePassword() throws IOException 
    {
        if(!passwordNuevo1.equals(passwordNuevo2) ){
            //System.out.println("las contrasenas son : "+passwordNuevo1+" otra: "+passwordNuevo2);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validación", "Las nuevas contraseñas no coinciden "));
            return;
        }
        
        PgUsuario us = (PgUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        //System.out.println("Nombre usuario: " + us.getUsuario() + " contraseña "+ passwordActual);        
        Long validPassword = UsuarioFacade.validPassword(us.getUsuario(), passwordActual);
        if (validPassword == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validación", "La contraseña actual está errada"));
            return;
        }else{
            PgUsuario record = us;
            //Guardo las nuevas contraseñas
            String passwordCifrado = DigestUtils.md5Hex(passwordNuevo1);
            
            record.setPassword(passwordCifrado);
            record.setUsuariomodificacion(this.usuario);
            record.setFechamodificacion(new Date());
            
            UsuarioFacade.actualizarPgUsuario(record);
            PgUsuario tus = UsuarioFacade.findRegistroUsuarioByID(id);
            
            if(!tus.getPassword().equals(passwordCifrado)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validación", "La contraseña fue cambiada con exito"));
                
            }
            
            
            cleanFormChangePassword();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validación", "La contraseña fue cambiada con exito"));
            logout();/*Colocar en mesaje de cambio de contraseña*/
            return;
        }
    }
    
    public void logout() throws IOException 
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/faces/views/index.xhtml");
    }
    
    public void manualUsuario() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/home/manual");
    }
    
    public void perfilUsuario() throws IOException{
        PgUsuario us = (PgUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.nombreUsuarioLogin = us.getNombres() + " " + us.getApellidos();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/home/perfil");
    }
    
    public void goChangePassword() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/faces/views/configuracion/cambioPassword.xhtml");
    }
    
    public void goEdit()throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/faces/views/configuracion/editarPerfil.xhtml");
    }
    
    public void verificarSesion() throws IOException{
        PgUsuario us = (PgUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        PgUsuarioRol rolUs = (PgUsuarioRol) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rolU");
        PgMenu menuUs = (PgMenu) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(us);

        if ((us == null) || (rolUs == null))
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/faces/views/home.xhtml");
        }else{
            this.nombreUsuarioLogin = us.getUsuario();
        }    
    }
    
    public void inicio() throws IOException{
        //System.out.println("INICIANDO PAGE INIT:" + usuario);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/faces/views/index.xhtml");
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PgUsuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(PgUsuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public String getNombreUsuarioLogin() {
        return nombreUsuarioLogin;
    }

    public void setNombreUsuarioLogin(String nombreUsuarioLogin) {
        this.nombreUsuarioLogin = nombreUsuarioLogin;
    }

    public String getPasswordActual() {
        return passwordActual;
    }

    public void setPasswordActual(String passwordActual) {
        this.passwordActual = passwordActual;
    }

    public String getPasswordNuevo1() {
        return passwordNuevo1;
    }

    public void setPasswordNuevo1(String passwordNuevo1) {
        this.passwordNuevo1 = passwordNuevo1;
    }

    public String getPasswordNuevo2() {
        return passwordNuevo2;
    }

    public void setPasswordNuevo2(String passwordNuevo2) {
        this.passwordNuevo2 = passwordNuevo2;
    }

    
}
