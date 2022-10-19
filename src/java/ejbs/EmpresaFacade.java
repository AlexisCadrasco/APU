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


import entities.GeEmpresa;
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
public class EmpresaFacade extends AbstractFacade<GeEmpresa> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

  public EmpresaFacade() {
        super(GeEmpresa.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<GeEmpresa> findEmpresaByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeEmpresa d WHERE d.estadoregistro = 1 and d.id = :id");
//        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
       
       
    public List<GeEmpresa> listaEmpresa() {
        Query q = em.createQuery("SELECT d FROM GeEmpresa d WHERE d.estadoregistro = 1 order by d.nombre, d.descripcion");
        //System.out.println("Size # ingGeEmpresas : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeEmpresa> listaEmpresaLimitado() {
        Query q = em.createQuery("SELECT d FROM GeEmpresa d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        System.out.println("Size # ingGeEmpresas : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
      
    public List<GeEmpresa> listaEmpresaSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeEmpresa d WHERE d.estadoregistro = 1 and UPPER(d.nombre) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarEmpresa(GeEmpresa GeEmpresa) {
        em.merge(GeEmpresa);
        em.flush();
        return true;
    }
       
      public boolean crearEmpresa(GeEmpresa GeEmpresa) {
        boolean r = false;
        em.persist(GeEmpresa);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearEmpresa(GeEmpresa ingGeEmpresa1, GeEmpresa ingGeEmpresa2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeEmpresa1);
        em.persist(ingGeEmpresa2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

       public boolean eliminarEmpresa(GeEmpresa GeEmpresa) {
        Short a = 0;
        GeEmpresa.setEstadoregistro(a);
        em.merge(GeEmpresa);
        em.flush();
        return true;
    }
 

}
