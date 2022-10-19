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


import entities.PgTipoPresupuesto;
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
public class TipoPresupuestoFacade extends AbstractFacade<PgTipoPresupuesto> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public TipoPresupuestoFacade() {
        super(PgTipoPresupuesto.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<PgTipoPresupuesto> findEntidadByID(Long id) {
        Query q = em.createQuery("SELECT d FROM PgTipoPresupuesto d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
       
    public List<PgTipoPresupuesto> listaTipoPresupuesto() {
        Query q = em.createQuery("SELECT d FROM PgTipoPresupuesto d WHERE d.estadoregistro = 1 order by d.nombre");
        System.out.println("Size # ingPgTipoPresupuestos : "+q.getResultList().size());
        return q.getResultList();
    }
      
    public List<PgTipoPresupuesto> listaTipoPresupuestoSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM PgTipoPresupuesto d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarEntidad(PgTipoPresupuesto PgTipoPresupuesto) {
        em.merge(PgTipoPresupuesto);
        em.flush();
        return true;
    }
       
      public boolean crearTipoPresupuesto(PgTipoPresupuesto PgTipoPresupuesto) {
        boolean r = false;
        em.persist(PgTipoPresupuesto);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearPgTipoPresupuesto(PgTipoPresupuesto ingPgTipoPresupuesto1, PgTipoPresupuesto ingPgTipoPresupuesto2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingPgTipoPresupuesto1);
        em.persist(ingPgTipoPresupuesto2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

    public boolean eliminarPgTipoPresupuesto(PgTipoPresupuesto PgTipoPresupuesto) {
        Short a = 0;
        PgTipoPresupuesto.setEstadoregistro(a);
        em.merge(PgTipoPresupuesto);
        em.flush();
        return true;
    }
 

}
