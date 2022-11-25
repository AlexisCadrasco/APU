/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import ejbs.ApuMaestroFacade;
import ejbs.EntidadFacade;
import ejbs.PresupuestoMaestroFacade;
import ejbs.PresupuestoDetalleFacade;
import ejbs.TipoPresupuestoFacade;
import entities.GeEntidad;
import entities.IfApuMaestro;
import entities.IfPresupuestoEnc;
import entities.IfPresupuestoDet;
import entities.IfTipoApu;
import entities.PgEtapa;
import entities.PgTipoPresupuesto;
import entities.PgUsuario;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import static java.time.Instant.now;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jgutierrez
 */
@ManagedBean(name = "presupuestoCtrl")
@ViewScoped
public class PresupuestoCtrl implements java.io.Serializable {

    @EJB
    private PresupuestoMaestroFacade presupuestoEncFacade;
    @EJB
    private PresupuestoDetalleFacade presupuestoDetFacade;
    @EJB
    private ApuMaestroFacade apuMaestroFacade;
    @EJB
    private EntidadFacade entidadFacade;
    @EJB
    private TipoPresupuestoFacade tipoPresupuestoFacade;

    //Variables Locales
    private List<IfApuMaestro> listaApuSearch = new ArrayList<IfApuMaestro>();
    private List<IfPresupuestoEnc> listaPresupuestoEnc = new ArrayList<IfPresupuestoEnc>();
    private List<IfPresupuestoDet> listaPresupuestoDet;
    private List<IfApuMaestro> listaApuPresupuesto;
    private IfPresupuestoEnc pptoEnc = new IfPresupuestoEnc();
    private IfPresupuestoEnc presupuestoEnc = new IfPresupuestoEnc();
    private IfPresupuestoEnc presEnc = new IfPresupuestoEnc();
    private String descripcion;
    private String codigoApu;
    private Long entidadID;
    private String txtSearch;
    private Long tipoPresupuestoID;
    private ArrayList<SelectItem> listaTipoPresupuestoItem;
    private Long IdAPU;
    private Boolean renderedMultiplicador = true;
    private ArrayList<SelectItem> listaEntidadItem;
    private String nameFile;
    private String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    @PostConstruct
    public void init() {
        this.presupuesto.setAdministracion(23L);
        this.presupuesto.setImprevisto(2L);
        this.presupuesto.setUtilidad(5L);
        listaApuPresupuesto = new ArrayList<IfApuMaestro>();
        this.listaPresupuestoEnc = presupuestoEncFacade.listaPresupuestoEnc();
        nameFile = "Presupuesto" + now();
    }

    public void search(String txt) {
        this.listaPresupuestoEnc.clear();
        for (IfPresupuestoEnc obj : presupuestoEncFacade.listaPresupuestoEncSearch(txt)) {
            listaPresupuestoEnc.add(obj);
        }
        this.listaPresupuestoEnc = listaPresupuestoEnc;
    }

    public void updateTipoPresupuesto() {
        if (tipoPresupuestoID == 1L) {
            renderedMultiplicador = true;
        } else {
            renderedMultiplicador = false;
            presupuesto.setMultiplicador(null);
        }

    }

    public String goCreate() throws IOException {
        //System.out.println("ingresando a ***goCreate***");
        pptoEnc = new IfPresupuestoEnc();
        //FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/faces/views/motivo/crear.xhtml");
        return "crearPresupuesto.xhtml?faces-redirect=true";
    }

    public void listarApuDescripcion() {
        System.out.println("listaApuSearch " + this.descripcion);
        this.listaApuSearch.clear();

        this.listaApuSearch = apuMaestroFacade.listaApuMaestroSearch(this.descripcion);
    }

    public void add(IfPresupuestoEnc record) {
        System.out.println("Adicionar: " + record.getId());
        //insumoFacade.findApuByID(record.getId());
        this.listaPresupuestoEnc.add(record);

    }

    public void delete(IfPresupuestoEnc record) {
        System.out.println("Eliminar: " + record.getId());
        presupuestoEncFacade.eliminarIfPresupuestoEnc(record);
        this.listaPresupuestoEnc = presupuestoEncFacade.listaPresupuestoEnc();
    }

    public void addApu(IfApuMaestro record) {
        //System.out.println("Adicionar: "+record.getId());
        this.listaApuPresupuesto.add(record);
    }

    public void deleteApu(IfApuMaestro record) {
        this.listaApuPresupuesto.remove(record);
    }

    public void crear() throws IOException {
        FacesContext contextFaces = FacesContext.getCurrentInstance();
        PgUsuario us = (PgUsuario) contextFaces.getExternalContext().getSessionMap().get("usuario");
        this.presupuesto.setEmpresa(BigInteger.ONE);
        this.presupuesto.setTipopresupuesto(new PgTipoPresupuesto(BigDecimal.valueOf(tipoPresupuestoID)));
        this.presupuesto.setEntidad(new GeEntidad(entidadID));
        this.presupuesto.setEtapa(new PgEtapa(BigDecimal.ONE));
        this.presupuesto.setFechacreacion(new Date());
        this.presupuesto.setEstadoregistro(Short.parseShort("1"));
        this.presupuesto.setUsuariocreacion(us.getUsuario());

        presupuestoEncFacade.crearPresupuestoEnc(presupuesto);

        //Presupuesto Detalle
        for (int i = 0; i < listaApuPresupuesto.size(); i++) {
            IfPresupuestoDet obj1 = new IfPresupuestoDet();

            obj1.setCantidad(BigDecimal.valueOf(listaApuPresupuesto.get(i).getCantidad()));
            obj1.setCapitulo(listaApuPresupuesto.get(i).getCapitulo());
            obj1.setApumaestro(new IfApuMaestro(listaApuPresupuesto.get(i).getId()));
            obj1.setPresupuesto(presupuesto);
            obj1.setUsuariocreacion(us.getUsuario());
            obj1.setFechacreacion(new Date());
            obj1.setEstadoregistro(Short.parseShort("1"));
            obj1.setEmpresa(BigInteger.ONE);
            presupuestoDetFacade.crearPresupuestoDet(obj1);
        }

        this.listaPresupuestoEnc = presupuestoEncFacade.listaPresupuestoEnc();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se creo el Presupuesto : " + presupuesto.getDescripcion()));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/home/ppto");

        //tipoPresupuestoID;
        /*if ((this.visitante.getIdentificacion() == null))
        {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Validación", "Es necesario suministrar una IDENTIFICACIÓN.."));
                return ;
        }*/
        if (this.presupuestoEnc != null && this.listaPresupuestoDet != null) {
            boolean rst = presupuestoEncFacade.crearPresupuestoEncTransaccion(this.presupuestoEnc, this.listaPresupuestoDet);
        }

        return;
    }

    public String viewer(IfPresupuestoEnc record) throws IOException {
        try {
            this.presupuestoEnc = record;
            System.out.println("valor presupuestoEnc " + record.getId());
            listaPresupuestoDet = presupuestoDetFacade.listaPresupuestoDetByEnc(record.getId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/home/ppto/ver");
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Error al intentar ver el Presupuesto."));
        }
        return "verPresupuesto.xhtml";
    }

    public void onRowEdit(RowEditEvent event) {
        System.out.println("Apu nombre " + ((IfApuMaestro) event.getObject()).getNombre());
        System.out.println("Apu ID " + ((IfApuMaestro) event.getObject()).getId());

        //this.valorTotal = ((GeApu) event.getObject()).getCantidad() * ((GeApu) event.getObject()).getPrecio();
    }

    public void onRowCancel(RowEditEvent event) {

        //FacesMessage msg = new FacesMessage("Edición cancelada", ((Cita) event.getObject()).getCitacumplida());
        //FacesContext.getCurrentInstance().addMessage(null, msg); 
    }

    public void limpiarSearch() {

        this.listaApuSearch.clear();
    }

    public void listarCodigoAPU() {
        //System.out.println("listaApuSearch "+  this.codigoApu);
        //if (this.listaApuSearch.size()!=0)
        this.listaApuSearch.clear();
        this.descripcion = null;
        this.listaApuSearch = apuMaestroFacade.listaApuMaestroSearch(this.codigoApu);
    }

    public PresupuestoCtrl() {

    }

    public IfPresupuestoEnc getPptoEnc() {
        return pptoEnc;
    }

    public void setPptoEnc(IfPresupuestoEnc pptoEnc) {
        this.pptoEnc = pptoEnc;
    }

    public List<IfApuMaestro> getListaApuSearch() {
        return listaApuSearch;
    }

    public void setListaApuSearch(List<IfApuMaestro> listaApuSearch) {
        this.listaApuSearch = listaApuSearch;
    }

    public ArrayList<SelectItem> getListaEntidadItem() {
        listaEntidadItem = new ArrayList<SelectItem>();
        //DependenciaI = new DependenciaServicioImpl();
        //ArrayList a = (ArrayList) DependenciaI.listarDependencia();
        List a = entidadFacade.listaEntidad();
        //System.out.println("Listar Meta Producto" + a);
        for (GeEntidad obj : entidadFacade.listaEntidad()) {

            listaEntidadItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaEntidadItem;
    }

    public void setListaEntidadItem(ArrayList<SelectItem> listaEntidadItem) {
        this.listaEntidadItem = listaEntidadItem;
    }

    public Long getEntidadID() {
        return entidadID;
    }

    public void setEntidadID(Long entidadID) {
        this.entidadID = entidadID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoApu() {
        return codigoApu;
    }

    public void setCodigoApu(String codigoApu) {
        this.codigoApu = codigoApu;
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public Long getTipoPresupuestoID() {
        return tipoPresupuestoID;
    }

    public void setTipoPresupuestoID(Long tipoPresupuestoID) {
        this.tipoPresupuestoID = tipoPresupuestoID;
    }

    private IfPresupuestoEnc presupuesto = new IfPresupuestoEnc();

    public IfPresupuestoEnc getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(IfPresupuestoEnc presupuesto) {
        this.presupuesto = presupuesto;
    }

    public IfPresupuestoEnc getPresupuestoEnc() {
        return presupuestoEnc;
    }

    public void setPresupuestoEnc(IfPresupuestoEnc presupuestoEnc) {
        this.presupuestoEnc = presupuestoEnc;
    }

    public List<IfPresupuestoDet> getListaPresupuestoDet() {
        return listaPresupuestoDet;
    }

    public ArrayList<SelectItem> getListaTipoPresupuestoItem() {
        listaTipoPresupuestoItem = new ArrayList<SelectItem>();
        //System.out.println("Listar Meta Producto" + a);
        for (PgTipoPresupuesto obj : tipoPresupuestoFacade.listaTipoPresupuesto()) {

            listaTipoPresupuestoItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaTipoPresupuestoItem;
    }

    public void setListaTipoPresupuestoItem(ArrayList<SelectItem> listaTipoPresupuestoItem) {
        this.listaTipoPresupuestoItem = listaTipoPresupuestoItem;
    }

    public Long getIdAPU() {
        return IdAPU;
    }

    public void setIdAPU(Long IdAPU) {
        this.IdAPU = IdAPU;
    }

    public IfPresupuestoEnc getPresEnc() {
        return presEnc;
    }

    public void setPresEnc(IfPresupuestoEnc presEnc) {
        this.presEnc = presEnc;
    }

    public List<IfPresupuestoEnc> getListaPresupuestoEnc() {
        return listaPresupuestoEnc;
    }

    public void setListaPresupuestoEnc(List<IfPresupuestoEnc> listaPresupuestoEnc) {
        this.listaPresupuestoEnc = listaPresupuestoEnc;
    }

    public List<IfApuMaestro> getListaApuPresupuesto() {
        return listaApuPresupuesto;
    }

    public void setListaApuPresupuesto(List<IfApuMaestro> listaApuPresupuesto) {
        this.listaApuPresupuesto = listaApuPresupuesto;
    }

    public Boolean getRenderedMultiplicador() {
        return renderedMultiplicador;
    }

    public void setRenderedMultiplicador(Boolean renderedMultiplicador) {
        this.renderedMultiplicador = renderedMultiplicador;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
