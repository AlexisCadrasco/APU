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


import entities.GeSubGrupo;
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
public class SubGrupoFacade extends AbstractFacade<GeSubGrupo> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public SubGrupoFacade() {
        super(GeSubGrupo.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Long existeSubgrupoByID(Double id) {
        Query q = em.createQuery("SELECT d FROM GeSubGrupo d WHERE d.estadoregistro = 1 and d.id = :id");
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
    
    public List<GeSubGrupo> findSubGrupoByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeSubGrupo d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
       
       
    public List<GeSubGrupo> listaSubGrupo() {
        Query q = em.createQuery("SELECT d FROM GeSubGrupo d WHERE d.estadoregistro = 1 order by d.nombre");
        //System.out.println("Size # ingGeSubGrupos : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeSubGrupo> listaSubGrupoLimitado() {
        Query q = em.createQuery("SELECT d FROM GeSubGrupo d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        //System.out.println("Size # ingGeSubGrupos : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeSubGrupo> listaSubGrupoSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeSubGrupo d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarSubGrupo(GeSubGrupo GeSubGrupo) {
        em.merge(GeSubGrupo);
        em.flush();
        return true;
    }
       
      public boolean crearSubGrupo(GeSubGrupo GeSubGrupo) {
        boolean r = false;
        em.persist(GeSubGrupo);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearGeSubGrupo(GeSubGrupo ingGeSubGrupo1, GeSubGrupo ingGeSubGrupo2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeSubGrupo1);
        em.persist(ingGeSubGrupo2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarGeSubGrupo(GeSubGrupo GeSubGrupo) {
        Short a = 0;
        GeSubGrupo.setEstadoregistro(a);
        em.merge(GeSubGrupo);
        em.flush();
        return true;
    }
       
      public List<GeSubGrupo> findSubGrupoByGrupo(Long grupo) {
        Query q = em.createQuery("SELECT d FROM GeSubGrupo d WHERE d.estadoregistro = 1 and d.grupo.id = :grupo");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("grupo", grupo);
        return q.getResultList();
    }
 

}
