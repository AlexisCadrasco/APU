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


import entities.PgRolMenu;
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
public class RolMenuFacade extends AbstractFacade<PgRolMenu> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public RolMenuFacade() {
        super(PgRolMenu.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<PgRolMenu> findRolMenuByID(Long id) {
        Query q = em.createQuery("SELECT d FROM PgRolMenu d WHERE d.estadoregistro = 1 and d.id = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }
 
    public List<PgRolMenu> findRolMenuByRol(Long rol) {
        Query q = em.createQuery("SELECT d FROM PgRolMenu d WHERE d.estadoregistro = 1 and d.idRol.id = :rol");
        q.setParameter("rol", rol);
        return q.getResultList();
    }   
       
    public List<PgRolMenu> listaRolMenu() {
        Query q = em.createQuery("SELECT d FROM PgRolMenu d WHERE d.estadoregistro = 1 order by d.id");
        return q.getResultList();
    }
      
    public List<PgRolMenu> listaRolMenuSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM PgRolMenu d WHERE d.estadoregistro = 1 and UPPER(d.idMenu.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarRolMenu(PgRolMenu PgRolMenu) {
        em.merge(PgRolMenu);
        em.flush();
        return true;
    }
       
      public boolean crearRolMenu(PgRolMenu PgRolMenu) {
        boolean r = false;
        em.persist(PgRolMenu);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearPgRolMenu(PgRolMenu ingPgRolMenu1, PgRolMenu ingPgRolMenu2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingPgRolMenu1);
        em.persist(ingPgRolMenu2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarPgRolMenu(PgRolMenu PgRolMenu) {
        Short a = 0;
        PgRolMenu.setEstadoregistro(a);
        em.merge(PgRolMenu);
        em.flush();
        return true;
    }
 

}
