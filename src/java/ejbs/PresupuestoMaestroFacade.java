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


import entities.IfPresupuestoDet;
import entities.IfPresupuestoEnc;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.ApplicationException;
import javax.ejb.EJB;
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
public class PresupuestoMaestroFacade extends AbstractFacade<IfPresupuestoEnc> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;
    
    @EJB
    private PresupuestoDetalleFacade presupuestoDetalleFacade;   
    
  public PresupuestoMaestroFacade() {
        super(IfPresupuestoEnc.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    //crearPresupuestoEncTransaccion
    @Transactional
    public boolean crearPresupuestoEncTransaccion (IfPresupuestoEnc presupuestoM,  List<IfPresupuestoDet> presupuestoD) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(presupuestoM);
        for (IfPresupuestoDet obj : presupuestoD) {
                presupuestoDetalleFacade.crearPresupuestoDet(obj);
        }
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }
    
 
    public List<IfPresupuestoEnc> findPresupuestoEncByID(Long id) {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoEnc d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
    public List<IfPresupuestoEnc> listaPresupuestoEnc() {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoEnc d WHERE d.estadoregistro = 1 order by d.id");
        System.out.println("Size # ingIfPresupuestoEncs : "+q.getResultList().size());
        return q.getResultList();
    }
    
      public List<IfPresupuestoEnc> listaPresupuestoEncByTipo(Long tipo) {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoEnc d WHERE d.estadoregistro = 1 and d.tipo.id order by d.nombre, d.descripcion");
        System.out.println("Size # ingIfPresupuestoEncs : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<IfPresupuestoEnc> listaPresupuestoEncLimitado() {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoEnc d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        System.out.println("Size # ingIfPresupuestoEncs : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
    
  public List<IfPresupuestoEnc> listaPresupuestoEncConsecutivo(String consecutivo) {
        
        Query q = em.createQuery("SELECT d FROM IfPresupuestoEnc d WHERE d.estadoregistro = 1 and d.consecutivo");
        q.setParameter("consecutivo", consecutivo);
        return q.getResultList();
    }
     
         
  
    public List<IfPresupuestoEnc> listaPresupuestoEncSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM IfPresupuestoEnc d WHERE d.estadoregistro = 1 and ( upper(d.descripcion) like :txt ) ORDER BY d.descripcion");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarPresupuestoEnc(IfPresupuestoEnc IfPresupuestoEnc) {
        em.merge(IfPresupuestoEnc);
        em.flush();
        return true;
    }
       
      public boolean crearPresupuestoEnc(IfPresupuestoEnc IfPresupuestoEnc) {
        boolean r = false;
        em.persist(IfPresupuestoEnc);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearIfPresupuestoEnc(IfPresupuestoEnc ingIfPresupuestoEnc1, IfPresupuestoEnc ingIfPresupuestoEnc2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingIfPresupuestoEnc1);
        em.persist(ingIfPresupuestoEnc2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarIfPresupuestoEnc(IfPresupuestoEnc IfPresupuestoEnc) {
        Short a = 0;
        IfPresupuestoEnc.setEstadoregistro(a);
        em.merge(IfPresupuestoEnc);
        em.flush();
        return true;
    }
 

}
