/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

/**
 *
 * @author jgutierrez
 */

import entities.GeUnidadMedida;
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
public class UnidadMedidaFacade extends AbstractFacade<GeUnidadMedida> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public UnidadMedidaFacade() {
        super(GeUnidadMedida.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Long existeUnidadCodigo(String codigo) {
        Query q = em.createQuery("SELECT d FROM GeUnidadMedida d WHERE d.estadoregistro = 1 and d.codigo = :codigo");
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
    
    public List<GeUnidadMedida> findUnidadMedidaByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeUnidadMedida d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
    public List<GeUnidadMedida> listaUnidadMedida() {
        Query q = em.createQuery("SELECT d FROM GeUnidadMedida d WHERE d.estadoregistro = 1 order by d.nombre, d.descripcion");
        //System.out.println("Size # ingGeUnidadMedidas : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeUnidadMedida> listaUnidadMedidaLimitado() {
        Query q = em.createQuery("SELECT d FROM GeUnidadMedida d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        //System.out.println("Size # ingGeUnidadMedidas : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeUnidadMedida> listaUnidadMedidaNombre(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeUnidadMedida d WHERE d.estadoregistro = 1 and  UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
    
    public GeUnidadMedida UnidadMedidaNombre(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeUnidadMedida d WHERE d.estadoregistro = 1 and  UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return (GeUnidadMedida)q.getResultList().get(0);
    }
     
    public boolean actualizarUnidadMedida(GeUnidadMedida GeUnidadMedida) {
        em.merge(GeUnidadMedida);
        em.flush();
        return true;
    }
       
      public boolean crearUnidadMedida(GeUnidadMedida GeUnidadMedida) {
        boolean r = false;
        em.persist(GeUnidadMedida);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearGeUnidadMedida(GeUnidadMedida ingGeUnidadMedida1, GeUnidadMedida ingGeUnidadMedida2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeUnidadMedida1);
        em.persist(ingGeUnidadMedida2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarGeUnidadMedida(GeUnidadMedida GeUnidadMedida) {
        Short a = 0;
        GeUnidadMedida.setEstadoregistro(a);
        em.merge(GeUnidadMedida);
        em.flush();
        return true;
    }
 

}
