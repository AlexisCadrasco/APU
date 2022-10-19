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


import entities.GeSegmento;
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
public class SegmentoFacade extends AbstractFacade<GeSegmento> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public SegmentoFacade() {
        super(GeSegmento.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Long existeSegmentoByCodigo(String codigo) {
        Query q = em.createQuery("SELECT d FROM GeSegmento d WHERE d.estadoregistro = 1 and d.codigo = :codigo");
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
    
    public List<GeSegmento> findSegmentoByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeSegmento d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    public List<GeSegmento> findSegmentoByCodigo(Long codigo) {
        Query q = em.createQuery("SELECT d FROM GeSegmento d WHERE d.estadoregistro = 1 and d.codigo = :codigo");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("codigo", codigo);
        return q.getResultList();
    }
 
       
       
    public List<GeSegmento> listaSegmento() {
        Query q = em.createQuery("SELECT d FROM GeSegmento d WHERE d.estadoregistro = 1 order by d.nombre, d.descripcion");
        //System.out.println("Size # ingGeSegmentos : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeSegmento> listaSegmentoLimitado() {
        Query q = em.createQuery("SELECT d FROM GeSegmento d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        //System.out.println("Size # ingGeSegmentos : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeSegmento> listaSegmentoSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeSegmento d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarSegmento(GeSegmento GeSegmento) {
        em.merge(GeSegmento);
        em.flush();
        return true;
    }
       
      public boolean crearSegmento(GeSegmento GeSegmento) {
        boolean r = false;
        em.persist(GeSegmento);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearGeSegmento(GeSegmento ingGeSegmento1, GeSegmento ingGeSegmento2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeSegmento1);
        em.persist(ingGeSegmento2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarGeSegmento(GeSegmento GeSegmento) {
        Short a = 0;
        GeSegmento.setEstadoregistro(a);
        em.merge(GeSegmento);
        em.flush();
        return true;
    }
 

}
