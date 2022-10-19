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


import entities.GeEntidad;
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
public class EntidadFacade extends AbstractFacade<GeEntidad> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public EntidadFacade() {
        super(GeEntidad.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<GeEntidad> findEntidadByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeEntidad d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    public List<GeEntidad> findEntidadByNIT(String nit) {
        Query q = em.createQuery("SELECT d FROM GeEntidad d WHERE d.estadoregistro = 1 and d.nit = :nit");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("nit ", nit);
        return q.getResultList();
    }
 
       
       
    public List<GeEntidad> listaEntidad() {
        Query q = em.createQuery("SELECT d FROM GeEntidad d WHERE d.estadoregistro = 1 order by d.nombre");
        System.out.println("Size # ingGeEntidads : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeEntidad> listaEntidadLimitado() {
        Query q = em.createQuery("SELECT d FROM GeEntidad d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        System.out.println("Size # ingGeEntidads : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeEntidad> listaEntidadSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeEntidad d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarEntidad(GeEntidad GeEntidad) {
        em.merge(GeEntidad);
        em.flush();
        return true;
    }
       
      public boolean crearEntidad(GeEntidad GeEntidad) {
        boolean r = false;
        em.persist(GeEntidad);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearGeEntidad(GeEntidad ingGeEntidad1, GeEntidad ingGeEntidad2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeEntidad1);
        em.persist(ingGeEntidad2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarGeEntidad(GeEntidad GeEntidad) {
        Short a = 0;
        GeEntidad.setEstadoregistro(a);
        em.merge(GeEntidad);
        em.flush();
        return true;
    }
 

}
