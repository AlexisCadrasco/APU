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
import entities.IfApuDetalle;
import entities.IfApuMaestro;
import utils.GenericProcedureResponse;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ApplicationException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

/**
 *
 * @author admin
 */
@Stateless
@ApplicationException(rollback = true)
public class ApuMaestroFacade extends AbstractFacade<IfApuMaestro> {
    @PersistenceContext(unitName = "APUPU")
    EntityManager em;
    Query q;
    @EJB
    private ApuDetalleFacade apuDetalleFacade;   
    @Resource(lookup = "jdbc/apu")
    private DataSource dataSource;
    private IfApuMaestro apuMaestro = new IfApuMaestro();
    
    public ApuMaestroFacade() {
        super(IfApuMaestro.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    

    //crearApuMaestroTransaccion
    @Transactional
    public boolean crearApuMaestroTransaccion (IfApuMaestro apuM,  List<IfApuDetalle> apuD) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(apuM);
        for (IfApuDetalle obj : apuD) {
                apuDetalleFacade.crearApuDetalle(obj);
        }
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }
   
    public List<IfApuMaestro> findApuMaestroByID(Long id) {
        Query q = em.createQuery("SELECT d FROM IfApuMaestro d WHERE d.estadoregistro = 1 and d.id = :id");
        //System.out.println("Size actividades: "+q.getResultList().size());
        q.setParameter("id", id);
        return q.getResultList();
    }
 
    public List<IfApuMaestro> listaApuMaestro() {
        Query q = em.createQuery("SELECT d FROM IfApuMaestro d WHERE d.estadoregistro = 1 order by d.nombre");
        //System.out.println("Size # ingIfApuMaestros : "+q.getResultList().size());
        return q.getResultList();
    }
    
    public List<IfApuMaestro> listaApuMaestroByTipo(Long tipo) {
        Query q = em.createQuery("SELECT d FROM IfApuMaestro d WHERE d.estadoregistro = 1 and d.tipo.id order by d.nombre, d.descripcion");
        //System.out.println("Size # ingIfApuMaestros : "+q.getResultList().size());
        return q.getResultList();
    }
    
    public Integer existeApuNombre(String nombre) {  
        String txtUpper = nombre.toUpperCase();
        Query q = em.createQuery("SELECT d FROM IfApuMaestro d WHERE d.estadoregistro = 1 and d.nombre = :txtUpper");
        q.setParameter("txtUpper", txtUpper);
        return q.getResultList().size();
    }
      
    public void aprobarApu(BigDecimal id) {
        Query q = em.createQuery("UPDATE IfApuMaestro d SET d.etapa.id = 2 WHERE d.id = :id");
        q.setParameter("id", id);
    }
    
    public void cancelarApu(BigDecimal id) {
        Query q = em.createQuery("UPDATE IfApuMaestro d SET d.etapa.id = 4 WHERE d.id = :id");
        q.setParameter("id", id);
    }
    
    public List<IfApuMaestro> listaApuMaestroLimitado() {
        Query q = em.createQuery("SELECT d FROM IfApuMaestro d WHERE d.estadoregistro = 1 order by d.nombre").setMaxResults(25);
        //System.out.println("Size # ingIfApuMaestros : "+q.getResultList().size());
        q.setMaxResults(25);
        ////System.out.println("Contenido actividades: "+q.getResultList());
        return q.getResultList();
    }
    
    
    public List<IfApuMaestro> listaApuMaestroConsecutivo(String consecutivo) {
        
        Query q = em.createQuery("SELECT d FROM IfApuMaestro d WHERE d.estadoregistro = 1 and d.consecutivo");
        q.setParameter("consecutivo", consecutivo);
        return q.getResultList();
    }
    
    public List<IfApuMaestro> listaApuMaestroSearchLista(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM IfApuMaestro d WHERE d.estadoregistro = 1 and (UPPER(d.descripcion) like :txt) ORDER BY d.nombre");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public List<IfApuMaestro> listaApuMaestroSearch(String txt) {
        String txtUpper = txt.toUpperCase();
        Query q = em.createQuery("SELECT d FROM IfApuMaestro d WHERE d.estadoregistro = 1 and (UPPER(d.descripcion) like :txt) AND d.etapa.id = 2 ORDER BY d.nombre");
        q.setParameter("txt", "%"+txtUpper+"%");
        return q.getResultList();
    }
     
    public boolean actualizarApuMaestro(IfApuMaestro IfApuMaestro) {
        em.merge(IfApuMaestro);
        em.flush();
        return true;
    }
       
      public boolean crearApuMaestro(IfApuMaestro IfApuMaestro) {
        boolean r = false;
        em.persist(IfApuMaestro);
        em.flush();
        r=true;
        return r;
    }
    
    @Transactional
    public boolean crearIfApuMaestro(IfApuMaestro ingIfApuMaestro1, IfApuMaestro ingIfApuMaestro2) {
        //em.getTransaction().begin();
        boolean r = false;
        em.persist(ingIfApuMaestro1);
        em.persist(ingIfApuMaestro2);
        em.flush();
        r=true;
        return r;
        //em.getTransaction().commit();
    }

    public boolean eliminarIfApuMaestro(IfApuMaestro IfApuMaestro) {
        Short a = 0;
        IfApuMaestro.setEstadoregistro(a);
        em.merge(IfApuMaestro);
        em.flush();
        return true;
    }
    
       
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public GenericProcedureResponse InsertaAPU( IfApuMaestro apu, 
            Date fechaCreacion, 
            String usuario, List<IfApuDetalle> listApuDetalle) throws SQLException{
        
        CallableStatement cstmt = null;
        Connection connection;
        oracle.jdbc.OracleConnection oraCon = null;
        GenericProcedureResponse response = new GenericProcedureResponse();
        
        try {

            connection = dataSource.getConnection();
            oraCon = connection.unwrap(oracle.jdbc.OracleConnection.class);

            cstmt = oraCon.prepareCall("{call PQ_APU.PR_INSERTA_APU(?,?,?,?,?,?,?,?,?,?)}");
            /*
                P_NOMBRE          IN VARCHAR2, 1
                P_DESCRIPCION     IN VARCHAR2, 2
                P_EMPRESA         IN NUMBER, 3
                P_USUARIOCREACION IN VARCHAR2, 4
                P_TIPO            IN NUMBER, 5
                P_CANTIDAD        IN NUMBER, 6
                P_DETALLE         IN ARRAY_APU_DETALLE, 7
                P_CONSECUTIVO     Out Number, 8
                Error_Num         Out Number, 9
                Error_Msg         Out Varchar2 10
            */
            cstmt.setString(1, apu.getNombre());
            cstmt.setString(2, apu.getDescripcion());
            cstmt.setBigDecimal(3, new BigDecimal(apu.getEmpresa()));
            cstmt.setString(4, apu.getUsuariocreacion());
            cstmt.setLong(5, apu.getTipo().getId());
            cstmt.setLong(6, apu.getCantidad());
            
            //cstmt.setString(7, motivoReversion);
            cstmt.registerOutParameter(8, OracleTypes.NUMBER);
            cstmt.registerOutParameter(9, OracleTypes.NUMBER);
            cstmt.registerOutParameter(10, OracleTypes.VARCHAR);

            cstmt.executeQuery();
            
            String consecutivoResult = cstmt.getString(8);
            Long errorNumProcess = cstmt.getLong(9);
            String errorMsgProcess = cstmt.getString(10);

            /*
            GenericProcedureResonse response = new GenericProcedureResonse();
            response.setCodigoResult(codigoResult);
            response.setMsgResult(msgResult);

            Object[] object = new Object[]{idReversion};
            response.setObject(object);

            return response;
            */
        } catch (Exception ex) {
            /**/

        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (oraCon != null) {
                    oraCon.close();
                }

            } catch (Exception ex) {
                /**/
            }

        }
        return null;
        
    }

}

