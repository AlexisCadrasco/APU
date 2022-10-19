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


import entities.GeMarca;
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
public class MarcaFacade extends AbstractFacade<GeMarca> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public MarcaFacade() {
        super(GeMarca.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<GeMarca> findMarcaByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeMarca d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
       
       
    public List<GeMarca> listaMarca() {
        Query q = em.createQuery("SELECT d FROM GeMarca d WHERE d.estadoregistro = 1 order by d.nombre, d.descripcion");
        System.out.println("Size # ingGeMarcas : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeMarca> listaMarcaLimitado() {
        Query q = em.createQuery("SELECT d FROM GeMarca d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        System.out.println("Size # ingGeMarcas : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeMarca> listaMarcaSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeMarca d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarMarca(GeMarca GeMarca) {
        em.merge(GeMarca);
        em.flush();
        return true;
    }
       
      public boolean crearMarca(GeMarca GeMarca) {
        boolean r = false;
        em.persist(GeMarca);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearGeMarca(GeMarca ingGeMarca1, GeMarca ingGeMarca2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeMarca1);
        em.persist(ingGeMarca2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarGeMarca(GeMarca GeMarca) {
        Short a = 0;
        GeMarca.setEstadoregistro(a);
        em.merge(GeMarca);
        em.flush();
        return true;
    }
 

}
