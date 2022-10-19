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


import entities.GeFamilia;
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
public class FamiliaFacade extends AbstractFacade<GeFamilia> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public FamiliaFacade() {
        super(GeFamilia.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<GeFamilia> findFamiliaByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeFamilia d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
      public List<GeFamilia> findFamiliaByCodigo(Long codigo) {
        Query q = em.createQuery("SELECT d FROM GeFamilia d WHERE d.estadoregistro = 1 and d.codigo = :codigo");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("codigo", codigo);
        return q.getResultList();
    }
       
       
    public List<GeFamilia> listaFamilia() {
        Query q = em.createQuery("SELECT d FROM GeFamilia d WHERE d.estadoregistro = 1 order by d.nombre, d.descripcion");
        //System.out.println("Size # ingGeFamilias : "+q.getResultList().size());
        return q.getResultList();
    }
    
    public List<GeFamilia> listaFamiliaSegmento(BigDecimal segmentoId) {
        Query q = em.createQuery("SELECT d FROM GeFamilia d WHERE d.estadoregistro = 1 AND d.segmento.id = :segmentoId ORDER BY d.nombre, d.descripcion");
        q.setParameter("segmentoId", segmentoId);
        //System.out.println("Size # ingGeFamilias : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeFamilia> listaFamiliaLimitado() {
        Query q = em.createQuery("SELECT d FROM GeFamilia d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        //System.out.println("Size # ingGeFamilias : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeFamilia> listaFamiliaSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeFamilia d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarFamilia(GeFamilia GeFamilia) {
        em.merge(GeFamilia);
        em.flush();
        return true;
    }
       
      public boolean crearFamilia(GeFamilia GeFamilia) {
        boolean r = false;
        em.persist(GeFamilia);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearGeFamilia(GeFamilia ingGeFamilia1, GeFamilia ingGeFamilia2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeFamilia1);
        em.persist(ingGeFamilia2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarGeFamilia(GeFamilia GeFamilia) {
        Short a = 0;
        GeFamilia.setEstadoregistro(a);
        em.merge(GeFamilia);
        em.flush();
        return true;
    }
       
      public List<GeFamilia> findFamiliaBySegmento(Long segmento) {
        Query q = em.createQuery("SELECT d FROM GeFamilia d WHERE d.estadoregistro = 1 and d.segmento.id = :segmento");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("segmento", segmento);
        return q.getResultList();
    }
 

}
