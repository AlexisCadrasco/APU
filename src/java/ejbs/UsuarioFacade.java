/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;


import entities.PgUsuario;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.ApplicationException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author admin
 */
@Stateless
@ApplicationException(rollback = true)
public class UsuarioFacade extends AbstractFacade<PgUsuario> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;

  public UsuarioFacade() {
        super(PgUsuario.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    

    public List<PgUsuario> findUsuarioByUsuarioLogin(String usuario) {
        Query q = em.createQuery("SELECT d FROM PgUsuario d WHERE d.usuario = :usuario");
        q.setParameter("usuario", usuario);
        return q.getResultList();
    }
    
    public Long validLogin(String usuario, String password) {
            String passwordCifrado = DigestUtils.md5Hex(password);
            Query q = em.createQuery("SELECT j FROM PgUsuario j WHERE j.estadoregistro = 1 AND j.usuario = :usuario AND j.password = :password");
            q.setParameter("usuario", usuario);
            q.setParameter("password", passwordCifrado);
            if (q.getResultList().size() == 1)
            {
                return 1L;
            }
            else
            {
                return 0L;
            }
    }
    
    public List<PgUsuario> selectUser(String usuario, String password) {
        String passwordCifrado = DigestUtils.md5Hex(password);
        //String passCifrado = c.cifrar(SeUsuario.getPassword());
        Query q = em.createQuery("SELECT j FROM PgUsuario j WHERE j.estadoregistro = 1 and j.usuario = :usuario and j.password = :password ");
        q.setParameter("usuario", usuario);
        q.setParameter("password", passwordCifrado);
        //System.out.println("password cifrado: "+passwordCifrado);
        return q.getResultList();
    }
    
    public Long validPassword(String nombreUsuario, String passwordActual) {
        String passwordCifrado = DigestUtils.md5Hex(passwordActual);
        Query q = em.createQuery("SELECT j FROM PgUsuario j WHERE j.estadoregistro = 1 and j.usuario = :nombreUsuario AND j.password = :passwordCifrado ORDER BY j.id");
        q.setParameter("nombreUsuario", nombreUsuario);
        q.setParameter("passwordCifrado", passwordCifrado);
        if (q.getResultList().size() == 1)
            {
                return 1L;
            }
            else
            {
                return 0L;
            }
    }
    
    public List<PgUsuario> findUsuarioByID(Long id) {
        Query q = em.createQuery("SELECT d FROM PgUsuario d WHERE d.estadoregistro = 1 and d.id = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }
    
     public PgUsuario findRegistroUsuarioByID(Long id) {
        Query q = em.createQuery("SELECT d FROM PgUsuario d WHERE d.estadoregistro = 1 and d.id = :id");
        q.setParameter("id", id);
        PgUsuario reg = (PgUsuario) q.getResultList();
        return reg;
    }
       
    public List<PgUsuario> listaUsuario() {
        Query q = em.createQuery("SELECT d FROM PgUsuario d WHERE d.estadoregistro = 1 order by d.usuario");
        //System.out.println("Size usuario: "+q.getResultList().size());
        ////System.out.println("Contenido dimensiones: "+q.getResultList());
        return q.getResultList();
    }    
    
    public List<PgUsuario> listaUsuarioSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM PgUsuario d WHERE d.estadoregistro = 1 and UPPER(d.usuario) like :txt");
        q.setParameter("txt", "%"+txtUpper+"%");
        ////System.out.println("Size dimensiones search: "+q.getResultList().size());
        ////System.out.println("Contenido dimensiones search: "+q.getResultList());
        return q.getResultList();
    }
    
     public void actualizarPgUsuario(PgUsuario PgUsuario) {
        em.merge(PgUsuario);
        em.flush();
    }
       
    public void crearPgUsuario(PgUsuario PgUsuario) {
        em.persist(PgUsuario);
        em.flush();
    }

    public void eliminarPgUsuario(PgUsuario PgUsuario) {
        Short a = 0;
        PgUsuario.setEstadoregistro(a);
        em.merge(PgUsuario);
        em.flush();
    }
       
    public List<PgUsuario> validarPgUsuario(PgUsuario PgUsuario) throws Exception {
        System.out.println("Into facade login objeto : "+ PgUsuario.getUsuario());
        String passCifrado = DigestUtils.md5Hex(PgUsuario.getPassword());
        Query q = em.createQuery("SELECT d FROM PgUsuario d WHERE d.usuario = :usuario and d.password = :clave");
        q.setParameter("usuario", PgUsuario.getUsuario());
        q.setParameter("clave", passCifrado);
        if (q.getResultList().size() == 1)
        {
            return q.getResultList();
        }
        else
        {
            return null;
        }
    }
    
    public Long validarUsuario(PgUsuario PgUsuario) throws Exception {
        System.out.println("Into facade login Long : "+ PgUsuario.getUsuario());
        System.out.println("Into facade login : "+ PgUsuario.getPassword());
        String passCifrado = DigestUtils.md5Hex(PgUsuario.getPassword());
        //System.out.println("password cifrado : "+ passCifrado);
        Query q = em.createQuery("SELECT d FROM PgUsuario d WHERE d.usuario = ?1 and d.password = ?2");
        q.setParameter(1, PgUsuario.getUsuario());
        q.setParameter(2, passCifrado);
        if (q.getResultList().size() == 1)
        {
            return 1L;
        }
        else
        {
            return 0L;
        }
    }

}
