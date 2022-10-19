/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import ejbs.ApuMaestroFacade;
import ejbs.ApuDetalleFacade;
import ejbs.InsumoFacade;
import ejbs.TipoApuFacade;
import entities.IfApuMaestro;
import entities.IfApuDetalle;
import entities.GeInsumo;
import entities.IfTipoApu;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 *
 * @author jguerrero
 */
@ManagedBean(name = "ingresoCtrl")
@ViewScoped
public class IngresoCtrl {
    @EJB
    private ApuMaestroFacade apuMaestroFacade;   
    @EJB
    private ApuDetalleFacade apuDetalleFacade;   
    @EJB
    private TipoApuFacade tipoApuFacade;   
    @EJB
    private InsumoFacade insumoFacade;   
    
  
    
    private List<IfApuDetalle> listaApuDetalle;
    private IfApuMaestro apuMaestro;

    public IfApuMaestro getApuMaestro() {
        return apuMaestro;
    }

    public void setApuMaestro(IfApuMaestro apuMaestro) {
        this.apuMaestro = apuMaestro;
    }
    
    

    public List<IfApuDetalle> getListaApuDetalle() {
        return listaApuDetalle;
    }

    public void setListaApuDetalle(List<IfApuDetalle> listaApuDetalle) {
        this.listaApuDetalle = listaApuDetalle;
    }

    
    public ArrayList<SelectItem> getListaTipoApuItem() {
        return listaTipoApuItem;
    }

    public void setListaTipoApuItem(ArrayList<SelectItem> listaTipoApuItem) {
        this.listaTipoApuItem = listaTipoApuItem;
    }
  
    private ArrayList<SelectItem> listaTipoApuItem;

    private Long IdAPU;

    public Long getIdAPU() {
        return IdAPU;
    }

    public void setIdAPU(Long IdAPU) {
        this.IdAPU = IdAPU;
    }
    
    
    

    public ArrayList<SelectItem> getListaTipoVisitanteItem() {
          listaTipoApuItem = new ArrayList<SelectItem>();
        //DependenciaI = new DependenciaServicioImpl();
        //ArrayList a = (ArrayList) DependenciaI.listarDependencia();
        List a = tipoApuFacade.listaTipoApu();
        //System.out.println("Listar Meta Producto" + a);
        for (IfTipoApu obj : tipoApuFacade.listaTipoApu()) {
            
            listaTipoApuItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaTipoApuItem;
    }

   public void findApuMaestro()
   {
       List<IfApuMaestro> obj = apuMaestroFacade.findApuMaestroByID(this.IdAPU);
       System.out.println("Change event documento objeto : "+obj.size());
       if ( obj.size() != 0)
       {
           System.out.println("Find APU : "+obj.get(0).getId()+" / "+obj.get(0).getNombre());
           //buscar detalle de ese APU y cargarlo en el datatable de detalle de APU
       }
       else
       {
           return;
       }
       
       
   }      
   
   public void crear() throws IOException
   {
        /*if ((this.visitante.getIdentificacion() == null))
        {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Validación", "Es necesario suministrar una IDENTIFICACIÓN.."));
                return ;
        }*/
      
       if (this.apuMaestro != null && this.listaApuDetalle != null) {
            boolean rst = apuMaestroFacade.crearApuMaestroTransaccion(this.apuMaestro, this.listaApuDetalle);
       }

       
       return;
   }        
   
   

    
    public IngresoCtrl() {

    }

    
    @PostConstruct
    public void init() {
       
    }

}
