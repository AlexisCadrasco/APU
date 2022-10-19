/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

/**
 *
 * @author jguerrero
 */


import entities.IfApuDetalle;
import entities.IfApuMaestro;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.ApplicationException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author admin
 */
@Stateless
@ApplicationException(rollback = true)
public class ApuDetalleFacade extends AbstractFacade<IfApuDetalle> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public ApuDetalleFacade() {
        super(IfApuDetalle.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<IfApuDetalle> findApuDetalleByID(Long id) {
        Query q = em.createQuery("SELECT d FROM IfApuDetalle d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
    public List<IfApuDetalle> listaApuDetalle() {
        Query q = em.createQuery("SELECT d FROM IfApuDetalle d WHERE d.estadoregistro = 1 order by d.nombre, d.descripcion");
        //System.out.println("Size # ingIfApuDetalles : "+q.getResultList().size());
        return q.getResultList();
    }
    
      public List<IfApuDetalle> listaApuDetalleByMaestro(BigDecimal id) {
        Query q = em.createQuery("SELECT d FROM IfApuDetalle d WHERE d.estadoregistro = 1 and d.apumaestro.id=:id order by d.id");
        q.setParameter("id", id);
        //System.out.println("Size # ingIfApuDetalles : "+q.getResultList().size());
        return q.getResultList();
    }
     
    public IfApuDetalle ApuDetalleByMaestro(BigDecimal id) {
        Query q = em.createQuery("SELECT d FROM IfApuDetalle d WHERE d.estadoregistro = 1 and d.apumaestro.id=:id order by d.id");
        q.setParameter("id", id);
        //System.out.println("Size # ingIfApuDetalles : "+q.getResultList().size());
        return (IfApuDetalle) q.getResultList();
    }
      
    public List<IfApuDetalle> listaApuDetalleByInsumo(Long id) {
        Query q = em.createQuery("SELECT d FROM IfApuDetalle d WHERE d.estadoregistro = 1 and d.insumo.id=:id order by d.nombre, d.descripcion");
        q.setParameter("id", id);
        //System.out.println("Size # ingIfApuDetalles : "+q.getResultList().size());
        return q.getResultList();
    }
      
    public List<IfApuDetalle> listaApuDetalleByCodigoUNSPSC(String codigo) {
        Query q = em.createQuery("SELECT d FROM IfApuDetalle d WHERE d.estadoregistro = 1 and d.codigounspsc=:codigo order by d.nombre, d.descripcion");
        q.setParameter("codigo", codigo);
        //System.out.println("Size # ingIfApuDetalles : "+q.getResultList().size());
        return q.getResultList();
    }
      
    public List<IfApuDetalle> listaApuDetalleByCodigoInterno(String codigo) {
        Query q = em.createQuery("SELECT d FROM IfApuDetalle d WHERE d.estadoregistro = 1 and d.codigointerno=:codigo order by d.nombre, d.descripcion");
        q.setParameter("codigo", codigo);
        //System.out.println("Size # ingIfApuDetalles : "+q.getResultList().size());
        return q.getResultList();
    }

    
    public List<IfApuDetalle> listaApuDetalleLimitado() {
        Query q = em.createQuery("SELECT d FROM IfApuDetalle d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        //System.out.println("Size # ingIfApuDetalles : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
    
           
  
    public List<IfApuDetalle> listaApuDetalleSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM IfApuDetalle d WHERE d.estadoregistro = 1 and ( UPPER(d.nombre) like :txt  OR upper(d.descripcion) like :txt )");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarApuDetalle(IfApuDetalle IfApuDetalle) {
        em.merge(IfApuDetalle);
        em.flush();
        return true;
    }
       
    public boolean crearApuDetalle(IfApuDetalle ApuDetalle) {
        boolean r = false;
        em.persist(ApuDetalle);
        em.flush();
        r = true;
        return r;
    }
     
    public boolean crearApuDetalleLista(List<IfApuDetalle> ifApuDetalle) {
        boolean r = false;
        for (IfApuDetalle obj : ifApuDetalle) {
            em.persist(obj);
        }
        em.flush();
        r = true;
        return r;
    }
  
      
      
    
    @Transactional
    public boolean crearIfApuDetalle(IfApuDetalle ingIfApuDetalle1, IfApuDetalle ingIfApuDetalle2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingIfApuDetalle1);
        em.persist(ingIfApuDetalle2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarIfApuDetalle(IfApuDetalle IfApuDetalle) {
        Short a = 0;
        IfApuDetalle.setEstadoregistro(a);
        em.merge(IfApuDetalle);
        em.flush();
        return true;
    }
 

}
