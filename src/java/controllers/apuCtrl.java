/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import co.gov.atlantico.apu.exceptions.ApuException;
import ejbs.ApuMaestroFacade;
import ejbs.ApuDetalleFacade;
import ejbs.InsumoFacade;
import ejbs.TipoApuFacade;
import ejbs.UnidadMedidaFacade;
import entities.GeClase;
import entities.GeFamilia;
import entities.IfApuMaestro;
import entities.IfApuDetalle;
import entities.GeInsumo;
import entities.GeProducto;
import entities.GeSegmento;
import entities.IfTipoApu;
import entities.GeUnidadMedida;
import entities.PgEtapa;
import entities.PgUsuario;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import static java.time.Instant.now;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jguerrero
 */
@ManagedBean(name = "apuCtrl")
@SessionScoped
public class apuCtrl implements java.io.Serializable {

    @EJB
    private ApuMaestroFacade apuMaestroFacade;
    @EJB
    private ApuDetalleFacade apuDetalleFacade;
    @EJB
    private TipoApuFacade tipoApuFacade;
    @EJB
    private InsumoFacade insumoFacade;
    @EJB
    private UnidadMedidaFacade unidadMedidaFacade;

    @Resource(lookup = "jdbc/apu")
    private DataSource dataSource;

    //Variables Locales
    private String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    private String unspsc;
    private String descripcion;
    private List<GeInsumo> listaInsumoSearch = new ArrayList<GeInsumo>();
    private List<GeInsumo> listaInsumoApu = new ArrayList<GeInsumo>();
    private List<IfApuDetalle> listaApuSearch = new ArrayList<IfApuDetalle>();
    private List<IfApuMaestro> listIfApuMaestro;
    private List<IfApuDetalle> listaApuDetalle;
    private GeInsumo insumo = new GeInsumo();
    private IfApuMaestro apuMaestro = new IfApuMaestro();
    private IfApuDetalle apuDetalle = new IfApuDetalle();
    private IfTipoApu tipoApu;
    private Long tipoID;
    private Long cantidad = 0L;
    private Long valorTotal = 0L;
    private String txtSearch;
    private ArrayList<SelectItem> listaTipoApuItem;
    private Long IdAPU;
    private ArrayList<SelectItem> listaUnidadMedidaItem;
    private BigDecimal unidadMedidaID;
    private String nameArchive;

    @PostConstruct
    public void init() {
        this.listIfApuMaestro = apuMaestroFacade.listaApuMaestro();
        nameArchive = "Apus-" + now();
    }

    public void findApuMaestro() {
        List<IfApuMaestro> obj = apuMaestroFacade.findApuMaestroByID(this.IdAPU);
        System.out.println("Change event documento objeto : " + obj.size());
        if (obj.size() != 0) {
            System.out.println("Find APU : " + obj.get(0).getId() + " / " + obj.get(0).getNombre());
            //buscar detalle de ese APU y cargarlo en el datatable de detalle de APU
        } else {
            return;
        }
    }

    public void listarInsumoUNSPSC() {
        //System.out.println("listaInsumoSearch "+  this.unspsc);
        //if (this.listaInsumoSearch.size()!=0)
        this.listaInsumoSearch.clear();
        this.descripcion = null;
        this.listaInsumoSearch = insumoFacade.listaInsumoSearch(this.unspsc);
    }

    public void limpiarSearch() {

        this.listaInsumoSearch.clear();
        this.unspsc = "";
    }

    public void listarInsumoDescripcion() {
        //System.out.println("listaInsumoSearch "+  this.descripcion);
        this.listaInsumoSearch.clear();
        this.unspsc = null;
        this.listaInsumoSearch = insumoFacade.listaInsumoSearch(this.descripcion);
    }

    public void add(GeInsumo record) {
        //insumoFacade.findInsumoByID(record.getId());
        //record.setCantidad(this.cantidad);
        //valorTotal = record.getPrecio() * record.getPrecio();
        //this.listaApuDetalle.add(e);
        this.listaInsumoApu.add(record);
        //this.listaInsumoApu.remove(0);

    }

    public void deleteInsumo(GeInsumo record) {
        this.listaInsumoApu.remove(record);
    }

    public void approveApu(IfApuMaestro record) {
        IfApuMaestro obj = record;
        obj.setEtapa(new PgEtapa(BigDecimal.valueOf(2L)));
        apuMaestroFacade.actualizarApuMaestro(obj);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se autorizo el APU " + record.getConsecutivo()));
        this.listIfApuMaestro = apuMaestroFacade.listaApuMaestro();
    }

    public void cancelApu(IfApuMaestro record) {
        IfApuMaestro obj = record;
        obj.setEtapa(new PgEtapa(BigDecimal.valueOf(4L)));
        apuMaestroFacade.actualizarApuMaestro(obj);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se rechazo el APU " + record.getConsecutivo()));
        this.listIfApuMaestro = apuMaestroFacade.listaApuMaestro();
    }

    public void delete(IfApuMaestro record) {
        System.out.println("Eliminar: " + record.getId());
        //insumoFacade.findInsumoByID(record.getId());
        apuMaestroFacade.eliminarIfApuMaestro(record);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se elimino el APU."));
        this.listIfApuMaestro = apuMaestroFacade.listaApuMaestro();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void crear() throws ApuException {

        try {
            if (listaInsumoApu.isEmpty()) {
                throw new ApuException("Se debe escoger al menos un insumo para adicionar", 2);
            }

            FacesContext contextFaces = FacesContext.getCurrentInstance();
            PgUsuario us = (PgUsuario) contextFaces.getExternalContext().getSessionMap().get("usuario");
            CallableStatement cstmt = null;
            Connection connection = dataSource.getConnection();
            oracle.jdbc.OracleConnection oraCon = connection.unwrap(oracle.jdbc.OracleConnection.class);
            StructDescriptor recApuDetalle = StructDescriptor.createDescriptor("T_APU_DETALLE", oraCon);
            ArrayDescriptor arrayDetalleApu = ArrayDescriptor.createDescriptor("ARRAY_APU_DETALLE", oraCon);
            //detalle
            //listaInsumoApu
            Object[] arrayDetalle = new Object[this.listaInsumoApu.size()];
            Object[] objDetalle;
            int idx = 0;

            //int consecutivo = 1;
            //for (IfApuDetalle o : apuMaestro.getIfApuDetalleList()) {
            for (int i = 0; i < this.listaInsumoApu.size(); i++) {
                //ID                  NUMBER,
                //APUMAESTRO          NUMBER,
                //INSUMO              NUMBER,
                //NOMBREINSUMO        VARCHAR2(512),
                //CODIGOUNSPSC        VARCHAR2(10),
                //CODIGOINTERNO       VARCHAR2(10),
                //MARCA               NUMBER,
                //CANTIDAD            NUMBER(10),
                //PRECIO              NUMBER(15,5),
                //RENDIMIENTO         NUMBER(5,3),
                //DESCRIPCION         VARCHAR2(2048),
                //EMPRESA             NUMBER

                objDetalle = new Object[12];

                objDetalle[0] = apuMaestro.getId();
                objDetalle[1] = listaInsumoApu.get(i).getId();
                objDetalle[2] = listaInsumoApu.get(i).getId();
                objDetalle[3] = listaInsumoApu.get(i).getNombre();
                objDetalle[4] = listaInsumoApu.get(i).getCodigo();
                objDetalle[5] = listaInsumoApu.get(i).getCodigo();
                objDetalle[6] = listaInsumoApu.get(i).getMarca();
                objDetalle[7] = listaInsumoApu.get(i).getCantidad();
                objDetalle[8] = listaInsumoApu.get(i).getPrecio();
                objDetalle[9] = listaInsumoApu.get(i).getRendimiento();
                objDetalle[10] = listaInsumoApu.get(i).getDescripcion();
                objDetalle[11] = BigInteger.ONE;

                STRUCT oracle_record = new STRUCT(recApuDetalle, oraCon, objDetalle);
                arrayDetalle[idx] = oracle_record;
                idx++;
            }

            ARRAY oracleArrayDetalle = new ARRAY(arrayDetalleApu, oraCon, arrayDetalle);
            oraCon = connection.unwrap(oracle.jdbc.OracleConnection.class);

            cstmt = oraCon.prepareCall("{call PQ_APU.PR_INSERTA_APU(?,?,?,?,?,?,?,?,?,?,?)}");

            //P_NOMBRE
            //P_DESCRIPCION
            //P_EMPRESA
            //P_USUARIOCREACION
            //P_TIPO
            //P_CANTIDAD
            //P_DETALLE
            //P_CONSECUTIVO
            //Error_Num
            //Error_Msg
            cstmt.setString(1, apuMaestro.getNombre());
            cstmt.setString(2, apuMaestro.getDescripcion());
            cstmt.setInt(3, 1);
            cstmt.setString(4, us.getUsuario());
            cstmt.setLong(5, tipoID);
            cstmt.setBigDecimal(6, unidadMedidaID);
            cstmt.setLong(7, 1);//cantidad);
            cstmt.setArray(8, oracleArrayDetalle);
            cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(10, OracleTypes.BIGINT);
            cstmt.registerOutParameter(11, OracleTypes.VARCHAR);

            cstmt.executeQuery();

            String codigoResult = cstmt.getString(9);
            BigDecimal num_error = cstmt.getBigDecimal(10);
            String msgResult = cstmt.getString(11);

            if (msgResult != null) {
                if (!msgResult.isEmpty()) {
                    throw new ApuException("Algo no fue bien: " + msgResult, 3);
                }
            }

            this.listIfApuMaestro = apuMaestroFacade.listaApuMaestro();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se creo el APU con código " + codigoResult));
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/faces/views/apu/listar.xhtml");
            return;
        } catch (SQLException ex) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_FATAL,
                            "Alerta SQL.",
                            ex.getMessage()
                    ));
        } catch (ApuException ie) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ie);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            ie.getNivelFacesMessage(),
                            ie.getTitle(),
                            ie.getMessage()
                    ));
        } catch (IOException e) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, e);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Alerta",
                            "Se aproducido un error inesperado. <br> intente nuevamente."
                    ));
        }
    }

    public void onCellEdit(CellEditEvent event) {

    }

    public void onRowEdit(RowEditEvent event) {
        System.out.println("Insumo nombre " + ((GeInsumo) event.getObject()).getNombre());
        System.out.println("Insumo ID " + ((GeInsumo) event.getObject()).getId());
        System.out.println("Cantidad" + this.getCantidad());
        //this.valorTotal = ((GeInsumo) event.getObject()).getCantidad() * ((GeInsumo) event.getObject()).getPrecio();
    }

    public void onRowCancel(RowEditEvent event) {

        //FacesMessage msg = new FacesMessage("Edición cancelada", ((Cita) event.getObject()).getCitacumplida());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public apuCtrl() {
        System.out.println("listaInsumoSearch " + this.unspsc);

    }

    public void search(String txt) {
        this.listIfApuMaestro.clear();
        for (IfApuMaestro obj : apuMaestroFacade.listaApuMaestroSearchLista(txt)) {
            listIfApuMaestro.add(obj);
        }
        this.listIfApuMaestro = listIfApuMaestro;
    }

    public void totalizador(Long cant, Long prec) {
        valorTotal = 0L;
        valorTotal = cant * prec;
        System.out.println("el valor total es: " + valorTotal);

    }

    public String goCreate() throws IOException {
        //System.out.println("ingresando a ***goCreate***");
        apuMaestro = new IfApuMaestro();
        //FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/faces/views/motivo/crear.xhtml");
        return "crearApu.xhtml?faces-redirect=true";
    }

    public String edit(IfApuMaestro record) throws IOException {
        try {
            this.apuMaestro = record;
            //idMotivoEdicion = record.getId();
            //System.out.println("ingresando a ***edit***"+idMotivoEdicion);
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/faces/views/apu/editarApu.xhtml");
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Error al intentar editar el apu."));
        }
        return "editarApu.xhtml";
    }

    public String viewer(IfApuMaestro record) throws IOException {
        try {
            this.apuMaestro = record;
            this.listaApuDetalle = apuDetalleFacade.listaApuDetalleByMaestro(record.getId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/faces/views/apu/verApu.xhtml");
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Error al intentar editar el apu."));
        }
        return "verApu.xhtml";
    }

    public String searchAdvanced() throws IOException {
        return "buscarApu.xhtml?faces-redirect=true";
    }

    public ArrayList<SelectItem> getListaTipoApuItem() {
        listaTipoApuItem = new ArrayList<SelectItem>();
        List a = tipoApuFacade.listaTipoApu();
        //System.out.println("Listar Meta Producto" + a);
        for (IfTipoApu obj : tipoApuFacade.listaTipoApu()) {

            listaTipoApuItem.add(new SelectItem(obj.getId(), obj.getCodigo() + " - " + obj.getNombre()));
        }
        return listaTipoApuItem;

    }

    public TipoApuFacade getTipoApuFacade() {
        return tipoApuFacade;
    }

    public void setTipoApuFacade(TipoApuFacade tipoApuFacade) {
        this.tipoApuFacade = tipoApuFacade;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUnspsc() {
        return unspsc;
    }

    public void setUnspsc(String unspsc) {
        this.unspsc = unspsc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<GeInsumo> getListaInsumoSearch() {
        return listaInsumoSearch;
    }

    public void setListaInsumoSearch(List<GeInsumo> listaInsumoSearch) {
        this.listaInsumoSearch = listaInsumoSearch;
    }

    public List<GeInsumo> getListaInsumoApu() {
        return listaInsumoApu;
    }

    public void setListaInsumoApu(List<GeInsumo> listaInsumoApu) {
        this.listaInsumoApu = listaInsumoApu;
    }

    public List<IfApuMaestro> getListIfApuMaestro() {
        return listIfApuMaestro;
    }

    public void setListIfApuMaestro(List<IfApuMaestro> listIfApuMaestro) {
        this.listIfApuMaestro = listIfApuMaestro;
    }

    public IfApuDetalle getApuDetalle() {
        return apuDetalle;
    }

    public void setApuDetalle(IfApuDetalle apuDetalle) {
        this.apuDetalle = apuDetalle;
    }

    public GeInsumo getInsumo() {
        return insumo;
    }

    public void setInsumo(GeInsumo insumo) {
        this.insumo = insumo;
    }

    public List<IfApuDetalle> getListaApuDetalle() {
        return listaApuDetalle;
    }

    public void setListaApuDetalle(List<IfApuDetalle> listaApuDetalle) {
        this.listaApuDetalle = listaApuDetalle;
    }

    public IfApuMaestro getApuMaestro() {
        return apuMaestro;
    }

    public void setApuMaestro(IfApuMaestro apuMaestro) {
        this.apuMaestro = apuMaestro;
    }

    public Long getTipoID() {
        return tipoID;
    }

    public void setTipoID(Long tipoID) {
        this.tipoID = tipoID;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public Long getIdAPU() {
        return IdAPU;
    }

    public void setIdAPU(Long IdAPU) {
        this.IdAPU = IdAPU;
    }

    public void setListaTipoApuItem(ArrayList<SelectItem> listaTipoApuItem) {
        this.listaTipoApuItem = listaTipoApuItem;
    }

    public ArrayList<SelectItem> getListaTipoItem() {
        listaTipoApuItem = new ArrayList<SelectItem>();
        //System.out.println("Listar Meta Producto" + a);
        for (IfTipoApu obj : tipoApuFacade.listaTipoApu()) {

            listaTipoApuItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaTipoApuItem;
    }

    public ArrayList<SelectItem> getListaUnidadMedidaItem() {
        listaUnidadMedidaItem = new ArrayList<SelectItem>();
        for (GeUnidadMedida obj : unidadMedidaFacade.listaUnidadMedida()) {

            listaUnidadMedidaItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaUnidadMedidaItem;
    }

    public void actualizar() {

        try {
            FacesContext contextFaces = FacesContext.getCurrentInstance();
            PgUsuario us = (PgUsuario) contextFaces.getExternalContext().getSessionMap().get("usuario");
            this.apuMaestro.setUsuariomodificacion(us.getUsuario());
            this.apuMaestro.setFechamodificacion(new Date());
            apuMaestroFacade.actualizarApuMaestro(apuMaestro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se actualizo el APU con Consecutivo 8" + apuMaestro.getConsecutivo()));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/home/apu");

            return;
        } catch (IOException ex) {
            Logger.getLogger(apuCtrl.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador de APU - IO", "Error en el proceso: <br/> " + ex.getCause().getMessage()));
        } catch (Exception ex) {
            Logger.getLogger(apuCtrl.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador de APU - EX", "Error en el proceso: <br/> " + ex.getCause().getMessage()));
        }
    }

    public void setListaUnidadMedidaItem(ArrayList<SelectItem> listaUnidadMedidaItem) {
        this.listaUnidadMedidaItem = listaUnidadMedidaItem;
    }

    public List<IfApuDetalle> getListaApuSearch() {
        return listaApuSearch;
    }

    public void setListaApuSearch(List<IfApuDetalle> listaApuSearch) {
        this.listaApuSearch = listaApuSearch;
    }

    public BigDecimal getUnidadMedidaID() {
        return unidadMedidaID;
    }

    public void setUnidadMedidaID(BigDecimal unidadMedidaID) {
        this.unidadMedidaID = unidadMedidaID;
    }

    public String getNameArchive() {
        return nameArchive;
    }

    public void setNameArchive(String nameArchive) {
        this.nameArchive = nameArchive;
    }

}
