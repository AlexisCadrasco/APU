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


import entities.GeClase;
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
public class ClaseFacade extends AbstractFacade<GeClase> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public ClaseFacade() {
        super(GeClase.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Long existeClaseByCodigo(String codigo) {
        Query q = em.createQuery("SELECT d FROM GeClase d WHERE d.estadoregistro = 1 and d.codigo = :codigo");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("codigo", codigo);
        if (q.getResultList().size() == 1)
            {
                return 1L;
            }
            else
            {
                return 0L;
            }
    }
    
    public List<GeClase> findClaseByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeClase d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
     public List<GeClase> findClaseByCodigo(Long codigo) {
        Query q = em.createQuery("SELECT d FROM GeClase d WHERE d.estadoregistro = 1 and d.codigo = :codigo");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("codigo", codigo);
        return q.getResultList();
    }
       
       
    public List<GeClase> listaClase() {
        Query q = em.createQuery("SELECT d FROM GeClase d WHERE d.estadoregistro = 1 order by d.nombre, d.descripcion");
        //System.out.println("Size # ingGeClases : "+q.getResultList().size());
        return q.getResultList();
    }
    
    public List<GeClase> listaClaseFamilia(BigDecimal familiaId) {
        Query q = em.createQuery("SELECT d FROM GeClase d WHERE d.estadoregistro = 1 AND d.familia.id = :familiaId ORDER BY d.nombre, d.descripcion");
        //System.out.println("Size # ingGeClases : "+q.getResultList().size());
        q.setParameter("familiaId", familiaId);
        return q.getResultList();
    }

    public List<GeClase> listaClaseLimitado() {
        Query q = em.createQuery("SELECT d FROM GeClase d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        //System.out.println("Size # ingGeClases : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeClase> listaClaseSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeClase d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarClase(GeClase GeClase) {
        em.merge(GeClase);
        em.flush();
        return true;
    }
       
      public boolean crearClase(GeClase GeClase) {
        boolean r = false;
        em.persist(GeClase);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearGeClase(GeClase ingGeClase1, GeClase ingGeClase2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeClase1);
        em.persist(ingGeClase2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarGeClase(GeClase GeClase) {
        Short a = 0;
        GeClase.setEstadoregistro(a);
        em.merge(GeClase);
        em.flush();
        return true;
    }
       
    public List<GeClase> findClaseByFamilia(Long familia) {
        Query q = em.createQuery("SELECT d FROM GeClase d WHERE d.estadoregistro = 1 and d.familia.id :familia");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("familia", familia);
        return q.getResultList();
    }
     
    public List<GeClase> findClaseByFamiliaSegmento(Long familia, Long segmento) {
        Query q = em.createQuery("SELECT d FROM GeClase d WHERE d.estadoregistro = 1 and d.familia.id=:familia and d.segmento.id=:segmento");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("familia", familia);
        q.setParameter("segmento", segmento);
        return q.getResultList();
    }
      
 

}
