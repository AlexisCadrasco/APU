/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import ejbs.EmpresaFacade;
import entities.GeEmpresa;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author sis
 */
@ManagedBean(name = "pgEmpresaControllers")
@SessionScoped
public class EmpresaCtrl {
    @EJB
    private EmpresaFacade EmpresaFacade;


//Model fields.
    private Long id;
    private String nombre;
    private Long nit;
    private Short digitoVerificacion;
    private String direccion;
    private String telefono;
    private String paginaweb;
    private String logo;
    private BigDecimal pg_empresa_id;
    private BigDecimal ge_ruta_cobro_id;
    

    
    @PostConstruct
    private void init(){
        
    }
    
    public void process(){
        System.out.println("valor stateProcess= " + stateProcess);
        if(this.stateProcess == 0L){
            create();
        }else{
            System.out.println("valor id= " + this.id);
            update(this.id);
        }
        cleanForm();
    }
    
    public void create()
    {
        
        if (this.nombre == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Validación", "Debe ingresar un nombre"));
            return;
        }
        if (this.direccion==null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Validación", "Debe ingresar una dirección"));
            return;
        }
        if (this.telefono==null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Validación", "Debe ingresar un teléfono"));
            return;
        }
         
        GeEmpresa obj = new GeEmpresa();
        obj.setNombre(nombre);
        obj.setNit(nit);
        obj.setDigitoverificacion(this.digitoVerificacion);
        obj.setTelefono(telefono);
        obj.setDireccion(direccion);
        obj.setLogo(logo);
        obj.setEstadoregistro(Short.parseShort("1"));
        obj.setFechacreacion(new Date());
        //obj.setUsuariocreacion(us.getUsuario());
        //obj.setGeEmpresaId(new GeEmpresa(1L));
         
        
        EmpresaFacade.crearEmpresa(obj);
        this.listGeEmpresa = EmpresaFacade.listaEmpresa();
        this.nombre = "";
        this.nit = null;
        this.digitoVerificacion = null;
        this.direccion = null;
        this.telefono = null;
        this.paginaweb = null;
        this.logo = null;
        
    }
    
    public void delete(GeEmpresa record)
    {
        //System.out.println("Registro eliminar..."+ registro);
        EmpresaFacade.eliminarEmpresa(record);
        this.listGeEmpresa = EmpresaFacade.listaEmpresa();

    }
     
    public void update(Long id)
    {
        GeEmpresa obj = new GeEmpresa();
        obj.setNombre(nombre);
        obj.setNit(nit);
        obj.setDigitoverificacion(digitoVerificacion);
        obj.setDireccion(direccion);
        obj.setTelefono(telefono);
        obj.setLogo(logo);
        EmpresaFacade.actualizarEmpresa(obj);
        this.listGeEmpresa = EmpresaFacade.listaEmpresa();
    }
     
    public void edit(GeEmpresa record)
    {
        GeEmpresa obj = EmpresaFacade.find(record.getId());
        this.nombre = obj.getNombre();
        this.nit = obj.getNit();
        this.digitoVerificacion = obj.getDigitoverificacion();
        this.direccion = obj.getDireccion();
        this.telefono = obj.getTelefono();
        this.logo = obj.getLogo();
        this.stateProcess = 1L;
    }
    
    public void cleanForm(){
        try{
            this.nombre = null;
            this.nit = null;
            this.digitoVerificacion = null;
            this.direccion = null;
            this.telefono = null;
            this.paginaweb = null;
            this.logo = null;
            this.stateProcess = 0L;
        }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Validación", "Problemas al intentar limpiar"));
            return;
        }
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public Short getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(Short digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getPaginaweb() {
        return paginaweb;
    }

    public void setPaginaweb(String paginaweb) {
        this.paginaweb = paginaweb;
    }
    
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    public BigDecimal getPg_empresa_id() {
        return pg_empresa_id;
    }

    public void setPg_empresa_id(BigDecimal pg_empresa_id) {
        this.pg_empresa_id = pg_empresa_id;
    }

    public BigDecimal getGe_ruta_cobro_id() {
        return ge_ruta_cobro_id;
    }

    public void setGe_ruta_cobro_id(BigDecimal ge_ruta_cobro_id) {
        this.ge_ruta_cobro_id = ge_ruta_cobro_id;
    }

    private List<GeEmpresa> listGeEmpresa;
    private Long stateProcess = 0L;

    
    public List<GeEmpresa> getListGeEmpresa() {
        return listGeEmpresa;
    }

    public void setListGeEmpresa(List<GeEmpresa> listGeEmpresa) {
        this.listGeEmpresa = listGeEmpresa;
    }

    public EmpresaFacade getGeEmpresaFacade() {
        return EmpresaFacade;
    }

    public void setGeEmpresaFacade(EmpresaFacade GeEmpresaFacade) {
        this.EmpresaFacade = GeEmpresaFacade;
    }

    public Long getStateProcess() {
        return stateProcess;
    }

    public void setStateProcess(Long stateProcess) {
        this.stateProcess = stateProcess;
    }

    
}
