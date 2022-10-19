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
public class PresupuestoDetalleFacade extends AbstractFacade<IfPresupuestoDet> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public PresupuestoDetalleFacade() {
        super(IfPresupuestoDet.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<IfPresupuestoDet> findPresupuestoDetByID(Long id) {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoDet d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
    public List<IfPresupuestoDet> listaPresupuestoDet() {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoDet d WHERE d.estadoregistro = 1 order by d.id");
        //System.out.println("Size # ingIfPresupuestoDets : "+q.getResultList().size());
        return q.getResultList();
    }
    
      public List<IfPresupuestoDet> listaPresupuestoDetByEnc(BigDecimal id) {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoDet d WHERE d.estadoregistro = 1 and d.presupuesto.id = :id order by d.id");
        q.setParameter("id", id);
        //System.out.println("Size # ingIfPresupuestoDets : "+q.getResultList().size());
        return q.getResultList();
    }
      
    public List<IfPresupuestoDet> listaPresupuestoDetByInsumo(Long id) {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoDet d WHERE d.estadoregistro = 1 and d.insumo.id=:id order by d.id");
        q.setParameter("id", id);
        //System.out.println("Size # ingIfPresupuestoDets : "+q.getResultList().size());
        return q.getResultList();
    }
      
    public List<IfPresupuestoDet> listaPresupuestoDetByCodigoUNSPSC(String codigo) {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoDet d WHERE d.estadoregistro = 1 and d.codigounspsc=:codigo order by d.id");
        q.setParameter("codigo", codigo);
        //System.out.println("Size # ingIfPresupuestoDets : "+q.getResultList().size());
        return q.getResultList();
    }
      
    public List<IfPresupuestoDet> listaPresupuestoDetByCodigoInterno(String codigo) {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoDet d WHERE d.estadoregistro = 1 and d.codigointerno=:codigo order by d.id");
        q.setParameter("codigo", codigo);
        System.out.println("Size # ingIfPresupuestoDets : "+q.getResultList().size());
        return q.getResultList();
    }

    
    public List<IfPresupuestoDet> listaPresupuestoDetLimitado() {
        Query q = em.createQuery("SELECT d FROM IfPresupuestoDet d WHERE d.estadoregistro = 1 order by d.id").setMaxResults(25);
        System.out.println("Size # ingIfPresupuestoDets : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
    
           
  
    public List<IfPresupuestoDet> listaPresupuestoDetSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM IfPresupuestoDet d WHERE d.estadoregistro = 1 and ( UPPER(d.nombre) like :txt  OR upper(d.descripcion) like :txt )");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarPresupuestoDet(IfPresupuestoDet IfPresupuestoDet) {
        em.merge(IfPresupuestoDet);
        em.flush();
        return true;
    }
       
      public boolean crearPresupuestoDet(IfPresupuestoDet IfPresupuestoDet) {
        boolean r = false;
        em.persist(IfPresupuestoDet);
        em.flush();
        r=true;
        return r;
    }
     
    public boolean crearPresupuestoDetLista(List<IfPresupuestoDet> ifPresupuestoDet) {
        boolean r = false;
        for (IfPresupuestoDet obj : ifPresupuestoDet) {
            em.persist(obj);
        }
        em.flush();
        r = true;
        return r;
    }
  
      
      
    
    @Transactional
    public boolean crearIfPresupuestoDet(IfPresupuestoDet ingIfPresupuestoDet1, IfPresupuestoDet ingIfPresupuestoDet2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingIfPresupuestoDet1);
        em.persist(ingIfPresupuestoDet2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarIfPresupuestoDet(IfPresupuestoDet IfPresupuestoDet) {
        Short a = 0;
        IfPresupuestoDet.setEstadoregistro(a);
        em.merge(IfPresupuestoDet);
        em.flush();
        return true;
    }
 

}
