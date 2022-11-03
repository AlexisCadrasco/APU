/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file                                                                                                , choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

/**
 *
 * @author jguerrero
 */


import entities.GeGrupo;
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
public class GrupoFacade extends AbstractFacade<GeGrupo> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public GrupoFacade() {
        super(GeGrupo.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Long existeGrupoByID(Double id) {
        Query q = em.createQuery("SELECT d FROM GeGrupo d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        if (q.getResultList().size() == 1)
            {
                return 1L;
            }
            else
            {
                return 0L;
            }
    }
    
    public List<GeGrupo> findGrupoByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeGrupo d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
      
    public List<GeGrupo> listaGrupo() {
        Query q = em.createQuery("SELECT d FROM GeGrupo d WHERE d.estadoregistro = 1 order by d.nombre, d.descripcion");
        //System.out.println("Size # ingGeGrupos : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeGrupo> listaGrupoLimitado() {
        Query q = em.createQuery("SELECT d FROM GeGrupo d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        System.out.println("Size # ingGeGrupos : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeGrupo> listaGrupoSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeGrupo d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarGrupo(GeGrupo GeGrupo) {
        em.merge(GeGrupo);
        em.flush();
        return true;
    }
       
      public boolean crearGrupo(GeGrupo GeGrupo) {
        boolean r = false;
        em.persist(GeGrupo);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearGeGrupo(GeGrupo ingGeGrupo1, GeGrupo ingGeGrupo2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeGrupo1);
        em.persist(ingGeGrupo2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarGeGrupo(GeGrupo GeGrupo) {
        Short a = 0;
        GeGrupo.setEstadoregistro(a);
        em.merge(GeGrupo);
        em.flush();
        return true;
    }
 

}
