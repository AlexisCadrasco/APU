/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.PgRolMenu;
import entities.PgRol;
import entities.PgMenu;
import ejbs.RolMenuFacade;
import entities.PgUsuario;
import java.io.IOException;
import java.math.BigDecimal;
import static java.time.Instant.now;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;


/**
 *
 * @author jgutierrez
 */
@ManagedBean(name = "rolMenuCtrl")
@SessionScoped
//@ViewScoped
public class rolMenuCtrl extends BaseController implements java.io.Serializable{
      
    @EJB
    private RolMenuFacade rolMenuFacade;
    
    @Resource(lookup = "jdbc/apu")
    private DataSource dataSource;
    
    private PgRolMenu rolMenu = new PgRolMenu();

    //Variables Locales
    private String txtSearch;
    private String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    private String nameFile;
    private List<PgRolMenu> listaRolMenu = new ArrayList<PgRolMenu>() ;
    private String nombreArchivo;
    private String largo;
   
  
    
    public void delete(PgRolMenu record){
        //System.out.println("ingresando a ***delete***");
        record.setUsuariomodificacion(nombreUsuarioLogin);
        record.setFechamodificacion(new Date());
        record.setEstadoregistro(Short.parseShort("0"));
        rolMenuFacade.eliminarPgRolMenu(record);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de PQRSD", "Se borro el motivo general: "+ record.getIdMenu().getNombre()));
        //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de PQRSD", "Se borro el motivo general: "+ record.getNombre()));
        //cleanForm();
        this.listaRolMenu = rolMenuFacade.listaRolMenu();
    }

    
    public rolMenuCtrl() {
        

    }

    
    @PostConstruct
    public void init() {
        try{
            FacesContext contextFaces = FacesContext.getCurrentInstance();
            PgUsuario us = (PgUsuario) contextFaces.getExternalContext().getSessionMap().get("usuario");
            listaRolMenu = rolMenuFacade.findRolMenuByRol(us.getRol().getId().longValue());
            nameFile = "Insumo" + now();
            this.largo = ""+listaRolMenu.size();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
   /* 
    public ArrayList<SelectItem> getListaSegmentoItem() {
        listaSegmentoItem = new ArrayList<SelectItem>();
        List a = segmentoFacade.listaSegmento();
        for (GeSegmento obj : segmentoFacade.listaSegmento()) {
            listaSegmentoItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaSegmentoItem;
        
    }

    public void setListaSegmentoItem(ArrayList<SelectItem> listaSegmentoItem) {
        this.listaSegmentoItem = listaSegmentoItem;
    }
*/

    public RolMenuFacade getRolMenuFacade() {
        return rolMenuFacade;
    }

    public void setRolMenuFacade(RolMenuFacade rolMenuFacade) {
        this.rolMenuFacade = rolMenuFacade;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public PgRolMenu getRolMenu() {
        return rolMenu;
    }

    public void setRolMenu(PgRolMenu rolMenu) {
        this.rolMenu = rolMenu;
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public List<PgRolMenu> getListaRolMenu() {
        return listaRolMenu;
    }

    public void setListaRolMenu(List<PgRolMenu> listaRolMenu) {
        this.listaRolMenu = listaRolMenu;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }
    
}
