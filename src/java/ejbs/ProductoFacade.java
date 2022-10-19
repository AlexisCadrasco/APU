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


import entities.GeProducto;
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
public class ProductoFacade extends AbstractFacade<GeProducto> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public ProductoFacade() {
        super(GeProducto.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Long existeProductoCodigo(String codigo) {
        Query q = em.createQuery("SELECT d FROM GeProducto d WHERE d.estadoregistro = 1 and d.codigo = :codigo");
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
    
    public List<GeProducto> findProductoByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeProducto d WHERE d.estadoregistro = 1 and d.id = :id");
        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
    public List<GeProducto> findProductoByCodigo(Long codigo) {
        Query q = em.createQuery("SELECT d FROM GeProducto d WHERE d.estadoregistro = 1 and d.codigo = :codigo");
        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("codigo", codigo);
        return q.getResultList();
    }
       
       
    public List<GeProducto> listaProducto() {
        Query q = em.createQuery("SELECT d FROM GeProducto d WHERE d.estadoregistro = 1 order by d.nombre");
        //System.out.println("Size # ingGeProductos : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeProducto> listaProductoClase(BigDecimal claseId) {
        Query q = em.createQuery("SELECT d FROM GeProducto d WHERE d.estadoregistro = 1 AND d.clase.id = :claseId ORDER BY d.nombre");
        //System.out.println("Size # ingGeProductos : "+q.getResultList().size());
        q.setParameter("claseId", claseId);
        return q.getResultList();
    }
    
    public List<GeProducto> listaProductoLimitado() {
        Query q = em.createQuery("SELECT d FROM GeProducto d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        //System.out.println("Size # ingGeProductos : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeProducto> listaProductoSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeProducto d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarProducto(GeProducto GeProducto) {
        em.merge(GeProducto);
        em.flush();
        return true;
    }
       
      public boolean crearProducto(GeProducto GeProducto) {
        boolean r = false;
        em.persist(GeProducto);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearGeProducto(GeProducto ingGeProducto1, GeProducto ingGeProducto2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeProducto1);
        em.persist(ingGeProducto2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarGeProducto(GeProducto GeProducto) {
        Short a = 0;
        GeProducto.setEstadoregistro(a);
        em.merge(GeProducto);
        em.flush();
        return true;
    }
       
    public List<GeProducto> findProductoByClase(Long clase) {
        Query q = em.createQuery("SELECT d FROM GeProducto d WHERE d.estadoregistro = 1 and d.clase.id :clase");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("clase", clase);
        return q.getResultList();
    }
     
    public List<GeProducto> findProductoByClaseFamiliaSegmento(Long clase, Long familia, Long segmento) {
        Query q = em.createQuery("SELECT d FROM GeProducto d WHERE d.estadoregistro = 1 and d.familia.id=:familia and d.segmento.id=:segmento and d.clase.id=:clase");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("familia", familia);
        q.setParameter("segmento", segmento);
        q.setParameter("clase", clase);        
        return q.getResultList();
    }
      
 

}
