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
import entities.GeInsumo;
import java.sql.SQLException;
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
import org.eclipse.persistence.exceptions.JPQLException;

/**
 *
 * @author admin
 */
@Stateless
@ApplicationException(rollback = true)
public class InsumoFacade extends AbstractFacade<GeInsumo> {

    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;

    public InsumoFacade() {
        super(GeInsumo.class);
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public List<GeInsumo> findInsumoByID(Long id) {
        Query q = em.createQuery("SELECT d FROM GeInsumo d WHERE d.estadoregistro = 1 and d.id = :id");
        //        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }

    public List<GeInsumo> listaInsumo() {
        Query q = em.createQuery("SELECT d FROM GeInsumo d WHERE d.estadoregistro = 1 order by d.nombre");
        //System.out.println("Size # ingGeInsumos : "+q.getResultList().size());
        return q.getResultList();
    }

    public List<GeInsumo> listaInsumoLimitado() {
        Query q = em.createQuery("SELECT d FROM GeInsumo d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        System.out.println("Size # ingGeInsumos : " + q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }

    public List<GeInsumo> listaInsumoCodigoInterno(String codigo) {

        Query q = em.createQuery("SELECT d FROM GeInsumo d WHERE d.estadoregistro = 1 and d.codigointerno = :codigo");
        q.setParameter("codigo", codigo);
        return q.getResultList();
    }

    public List<GeInsumo> listaInsumoCodigoUNSPSC(String codigo) {
        Query q = em.createQuery("SELECT d FROM GeInsumo d WHERE d.estadoregistro = 1 and d.codigo = :codigo");
        q.setParameter("codigo", codigo);
        return q.getResultList();
    }

    public Integer existeInsumoNombre(String nombre) {
        String txtUpper = nombre.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeInsumo d WHERE d.estadoregistro = 1 and d.nombre = :txtUpper");
        q.setParameter("txtUpper", txtUpper);
        return q.getResultList().size();
    }

    public List<GeInsumo> listaInsumoSearchLista(String txt) throws javax.ejb.EJBException{
        //System.out.println("listaInsumoSearch "+ txt);
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM GeInsumo d WHERE d.estadoregistro = 1 and (UPPER(d.nombre) like :txt OR upper(d.descripcion) like :txt OR upper(d.codigo) like :txt) ORDER BY d.nombre");
        q.setParameter("txt", "%" + txtUpper + "%");
        return q.getResultList();
    }

    public List<GeInsumo> listaInsumoSearch(String txt) {
        //System.out.println("listaInsumoSearch "+ txt);
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery(
                "SELECT d FROM GeInsumo d WHERE d.estadoregistro = 1 "
                        + "and (UPPER(d.nombre) like :txt OR upper(d.descripcion) like :txt OR upper(d.codigo) like :txt) "
                        + "AND d.etapa.id = 2 ORDER BY d.nombre");
        q.setParameter("txt", "%" + txtUpper + "%");
        return q.getResultList();
    }

    public boolean actualizarInsumo(GeInsumo GeInsumo) throws Exception{
        em.merge(GeInsumo);
        em.flush();
        return true;
    }

    public boolean crearInsumo(GeInsumo GeInsumo) {
        boolean r = false;
        em.persist(GeInsumo);
        em.flush();
        r = true;
        return r;

    }

    @Transactional
    public boolean crearGeInsumo(GeInsumo ingGeInsumo1, GeInsumo ingGeInsumo2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingGeInsumo1);
        em.persist(ingGeInsumo2);
        em.flush();
        r = true;
        return r;
        //em.getTransaction().commit();
    }

    public boolean eliminarGeInsumo(GeInsumo GeInsumo)throws Exception{
        Short a = 0;
        GeInsumo.setEstadoregistro(a);
        em.merge(GeInsumo);
        em.flush();
        return true;
    }

}
