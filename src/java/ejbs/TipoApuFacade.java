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


import entities.IfTipoApu;
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
public class TipoApuFacade extends AbstractFacade<IfTipoApu> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public TipoApuFacade() {
        super(IfTipoApu.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<IfTipoApu> findTipoApuByID(Long id) {
        Query q = em.createQuery("SELECT d FROM IfTipoApu d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
    public List<IfTipoApu> listaTipoApu() {
        Query q = em.createQuery("SELECT d FROM IfTipoApu d WHERE d.estadoregistro = 1 order by d.nombre, d.descripcion");
        //System.out.println("Size # ingIfTipoApus : "+q.getResultList().size());
        return q.getResultList();
    }
    
    public IfTipoApu codigoTipoApuId(Long id) {
        Query q = em.createQuery("SELECT d FROM IfTipoApu d WHERE d.estadoregistro = 1 AND d.id = :id");
        q.setParameter("id", id);
        return (IfTipoApu) q.getResultList().get(0);
    }

    public List<IfTipoApu> listaTipoApuLimitado() {
        Query q = em.createQuery("SELECT d FROM IfTipoApu d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        //System.out.println("Size # ingIfTipoApus : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<IfTipoApu> listaTipoApuSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM IfTipoApu d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarTipoApu(IfTipoApu IfTipoApu) {
        em.merge(IfTipoApu);
        em.flush();
        return true;
    }
       
      public boolean crearTipoApu(IfTipoApu IfTipoApu) {
        boolean r = false;
        em.persist(IfTipoApu);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearIfTipoApu(IfTipoApu ingIfTipoApu1, IfTipoApu ingIfTipoApu2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingIfTipoApu1);
        em.persist(ingIfTipoApu2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarIfTipoApu(IfTipoApu IfTipoApu) {
        Short a = 0;
        IfTipoApu.setEstadoregistro(a);
        em.merge(IfTipoApu);
        em.flush();
        return true;
    }
 

}
