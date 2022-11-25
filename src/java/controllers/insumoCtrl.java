/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import co.gov.atlantico.apu.exceptions.InsumosException;
import ejbs.InsumoFacade;
import ejbs.SegmentoFacade;
import ejbs.FamiliaFacade;
import ejbs.ClaseFacade;
import ejbs.GrupoFacade;
import ejbs.ProductoFacade;
import ejbs.SubGrupoFacade;
import ejbs.UnidadMedidaFacade;
import entities.GeClase;
import entities.GeFamilia;
import entities.GeGrupo;
import entities.GeInsumo;
import entities.GeProducto;
import entities.GeSegmento;
import entities.GeSubGrupo;
import entities.GeUnidadMedida;
import entities.PgEtapa;
import entities.PgUsuario;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import static java.time.Instant.now;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author jgutierrez
 */
@ManagedBean(name = "insumoCtrl")
//@SessionScoped
@ViewScoped
public class insumoCtrl extends BaseController implements java.io.Serializable {

    @EJB
    private InsumoFacade insumoFacade;

    @EJB
    private SegmentoFacade segmentoFacade;

    @EJB
    private FamiliaFacade familiaFacade;

    @EJB
    private ClaseFacade claseFacade;

    @EJB
    private GrupoFacade grupoFacade;

    @EJB
    private SubGrupoFacade subgrupoFacade;

    @EJB
    private ProductoFacade productoFacade;

    @EJB
    private UnidadMedidaFacade unidadMedidaFacade;

    @Resource(lookup = "jdbc/apu")
    private DataSource dataSource;

    private GeInsumo insumo = new GeInsumo();
    private GeSubGrupo subgrupo = new GeSubGrupo();
    private GeUnidadMedida unidadMedida = new GeUnidadMedida();

    //Variables Locales
    private BigDecimal segmentoId = null;
    private BigDecimal familiaId = null;
    private BigDecimal claseId = null;
    private BigDecimal productoId = null;
    private String txtSearch;
    private final String PATH = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    private String nameFile;
    private List<GeInsumo> listaInsumo = new ArrayList<>();
    private List<GeInsumo> listaInsumoSearch = new ArrayList<>();
    private List<GeInsumo> listaInsumoSearch2 = new ArrayList<>();
    private List<GeInsumo> listaInsumoDetalle = new ArrayList<>();
    private ArrayList<SelectItem> listaSegmentoItem = new ArrayList<>();
    private ArrayList<SelectItem> listaFamiliaItem = new ArrayList<>();
    private ArrayList<SelectItem> listaClaseItem = new ArrayList<>();
    private ArrayList<SelectItem> listaProductoItem = new ArrayList<>();
    private ArrayList<SelectItem> listaGrupoItem = new ArrayList<>();
    private ArrayList<SelectItem> listaSubgrupoItem = new ArrayList<>();
    private ArrayList<SelectItem> listaUnidadMedidaItem = new ArrayList<>();
    private UploadedFile filexls;
    private BigDecimal grupoId;
    private BigDecimal subgrupoId;
    private BigDecimal unidadmedidaId;
    private Character compuestoId;
    private UploadedFile archivo;
    private String nombreArchivo;

    /**
     * @param txt String, texto a buscar.
     */
    public void search(String txt) {
        try {
            this.listaInsumo.clear();
            setListaInsumo(insumoFacade.listaInsumoSearchLista(txt));

            if (getListaInsumo() != null) {
                if (getListaInsumo().isEmpty()) {
                    throw new InsumosException("No se han encontrado datos.", 2);
                }
            }

            /*for (GeInsumo obj : getListaInsumo()) {
                listaInsumo.add(obj);
            }*/
            //    this.listaInsumo = listaInsumo;
        } catch (javax.ejb.EJBException ex) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Alerta.",
                            ex.getCausedByException().getCause().getMessage()
                    ));
        } catch (InsumosException ie) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.INFO, null, ie);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            ie.getNivelFacesMessage(),
                            ie.getTitle(),
                            ie.getMessage()
                    ));
        } catch (Exception x) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, x);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Alerta.",
                            x.getCause() != null ? x.getCause().getMessage() : x.getMessage()
                    ));
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void crearDb() {
        try {
            if (getListaInsumoDetalle().isEmpty() && getCompuestoId().equals('S')) {
                throw new InsumosException("Se debe escoger al menos un insumo para adicionar", 2);
            }

            FacesContext contextFaces = FacesContext.getCurrentInstance();
            PgUsuario us = (PgUsuario) contextFaces.getExternalContext().getSessionMap().get("usuario");
            CallableStatement cstmt = null;
            Connection connection = dataSource.getConnection();
            oracle.jdbc.OracleConnection oraCon = connection.unwrap(oracle.jdbc.OracleConnection.class);
            StructDescriptor recApuDetalle = StructDescriptor.createDescriptor("T_DETALLE_GENERICO", oraCon);
            ArrayDescriptor arrayDetalleApu = ArrayDescriptor.createDescriptor("ARRAY_DETALLE_GENERICO", oraCon);

            Object[] arrayDetalle = new Object[this.listaInsumoDetalle.size()];
            Object[] objDetalle;
            int idx = 0;
            /////////////////////////////
            for (int i = 0; i < this.listaInsumoDetalle.size(); i++) {
                /*
                ID     NUMBER
                NOMBRE VARCHAR2(500)
                CANTIDAD NUMBER
                 */
                objDetalle = new Object[3];

                objDetalle[0] = listaInsumoDetalle.get(i).getId();
                objDetalle[1] = listaInsumoDetalle.get(i).getNombre();
                objDetalle[2] = listaInsumoDetalle.get(i).getCantidad();

                STRUCT oracle_record = new STRUCT(recApuDetalle, oraCon, objDetalle);
                arrayDetalle[idx] = oracle_record;
                idx++;
            }
            /////////////////////////////
            ARRAY oracleArrayDetalle = new ARRAY(arrayDetalleApu, oraCon, arrayDetalle);
            oraCon = connection.unwrap(oracle.jdbc.OracleConnection.class);

            cstmt = oraCon.prepareCall("{call PQ_APU.PR_INSERTA_INSUMO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            /*
            p_Grupo           IN VARCHAR2,
            p_Subgrupo        IN VARCHAR2,
            p_Nombre          IN VARCHAR2,
            p_Descripcion     IN VARCHAR2,
            p_Unidad_Medida   IN NUMBER,
            p_Marca           IN NUMBER,
            p_Compuesto       IN CHAR,
            p_Rendimiento     IN NUMBER,
            p_Cantidad        IN NUMBER,
            p_Precio          IN NUMBER,
            p_Segmento        IN NUMBER,
            p_Clase           IN NUMBER,
            p_Familia         IN NUMBER,
            p_Producto        IN NUMBER,
            p_Empresa         IN NUMBER,
            p_Usuariocreacion IN VARCHAR2,
            p_Detalle         IN Array_Detalle_Generico,
            p_Codigo          OUT VARCHAR2,
            Error_Num         OUT NUMBER,
            Error_Msg         OUT VARCHAR2
             */
            cstmt.setBigDecimal(1, grupoId);
            cstmt.setBigDecimal(2, subgrupoId);
            cstmt.setString(3, insumo.getNombre());
            cstmt.setString(4, insumo.getDescripcion());
            cstmt.setBigDecimal(5, unidadmedidaId);
            cstmt.setBigDecimal(6, null);
            cstmt.setString(7, compuestoId.toString());
            cstmt.setString(8, null);//rendimiento
            cstmt.setBigDecimal(9, BigDecimal.ONE);//Cantidad
            cstmt.setBigDecimal(10, insumo.getPrecio());
            cstmt.setBigDecimal(11, segmentoId);
            cstmt.setBigDecimal(12, claseId);
            cstmt.setBigDecimal(13, familiaId);
            cstmt.setBigDecimal(14, productoId);
            cstmt.setBigDecimal(15, BigDecimal.ONE);//empresa
            cstmt.setString(16, us.getUsuario());
            cstmt.setArray(17, oracleArrayDetalle);
            cstmt.registerOutParameter(18, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(19, OracleTypes.BIGINT);
            cstmt.registerOutParameter(20, OracleTypes.VARCHAR);

            cstmt.executeQuery();
            String v_consecutivo = cstmt.getString(18);
            String v_msgError = cstmt.getString(20);

            if (v_msgError != null) {
                if (!v_msgError.isEmpty()) {
                    throw new InsumosException("Algo no fue bien: " + v_msgError, 3);
                }
            }

            this.listaInsumo = insumoFacade.listaInsumo();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se creo el insumo con código " + v_consecutivo));

            FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/home/insumos");

        } catch (SQLException ex) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_FATAL,
                            "Alerta SQL.",
                            ex.getMessage()
                    ));
        } catch (InsumosException ie) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ie);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            ie.getNivelFacesMessage(),
                            ie.getTitle(),
                            ie.getMessage()
                    ));
        } catch (IOException e) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, e);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Alerta",
                            "Se aproducido un error inesperado. <br> intente nuevamente."
                    ));
        }
    }

    public long getRandom() {
        return new Random().nextLong();
    }

    public void actualizar() {

        try {
            FacesContext contextFaces = FacesContext.getCurrentInstance();
            PgUsuario us = (PgUsuario) contextFaces.getExternalContext().getSessionMap().get("usuario");
            this.insumo.setUsuariomodificacion(us.getUsuario());
            this.insumo.setFechamodificacion(new Date());
            this.insumo.setSegmento(new GeSegmento(segmentoId));
            this.insumo.setFamilia(new GeFamilia(familiaId));
            this.insumo.setClase(new GeClase(claseId));
            this.insumo.setProducto(new GeProducto(productoId));
            this.insumo.setUnidadmedida(new GeUnidadMedida(unidadmedidaId));
            insumoFacade.actualizarInsumo(insumo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se actualizo el insumo con código " + insumo.getCodigo()));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/home/insumos");

            return;
        } catch (IOException ex) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador de APU - IO", "Error en el proceso: <br/> " + ex.getCause().getMessage()));
        } catch (Exception ex) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador de APU - EX", "Error en el proceso: <br/> " + ex.getCause().getMessage()));
        }
    }

    public void crear() throws IOException {
        try {
            this.insumo.setEmpresa(BigInteger.ONE);//us.getEmpresa().getId());
            FacesContext contextFaces = FacesContext.getCurrentInstance();
            PgUsuario us = (PgUsuario) contextFaces.getExternalContext().getSessionMap().get("usuario");
            this.insumo.setUsuariocreacion(us.getUsuario());
            this.insumo.setFechacreacion(new Date());
            this.insumo.setEstadoregistro(Short.parseShort("1"));
            this.insumo.setGrupo(new GeGrupo(grupoId));
            this.insumo.setSubgrupo(new GeSubGrupo(subgrupoId));
            this.insumo.setUnidadmedida(new GeUnidadMedida(unidadmedidaId));
            this.insumo.setSegmento(new GeSegmento(segmentoId));
            this.insumo.setCompuesto(compuestoId);
            this.insumo.setFamilia(new GeFamilia(familiaId));
            this.insumo.setClase(new GeClase(claseId));
            this.insumo.setProducto(new GeProducto(productoId));

            insumoFacade.crearInsumo(insumo);
            this.listaInsumo = insumoFacade.listaInsumo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se creo el insumo general: " + insumo.getNombre()));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/APUGobAtl/home/insumos");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Error al guardar el insumo."));
            return;
        }
    }

    public void delete(GeInsumo record) {
        try {
            //System.out.println("ingresando a ***delete***");
            record.setUsuariomodificacion(nombreUsuarioLogin);
            record.setFechamodificacion(new Date());
            record.setEstadoregistro(Short.parseShort("0"));
            insumoFacade.eliminarGeInsumo(record);
            FacesContext.getCurrentInstance()
                    .addMessage(
                             null,
                             new FacesMessage(
                                    FacesMessage.SEVERITY_INFO,
                                     "Administrador de PQRSD",
                                     "Se borro el insumo: " + record.getNombre()));
            //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de PQRSD", "Se borro el motivo general: "+ record.getNombre()));
            //cleanForm();
            this.listaInsumo = insumoFacade.listaInsumo();
        } catch (Exception ex) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSegmento() {
        for (GeFamilia obj : familiaFacade.listaFamiliaSegmento(segmentoId)) {
            listaFamiliaItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
    }

    public void updateFamilia() {
        for (GeClase obj : claseFacade.listaClaseFamilia(familiaId)) {
            listaClaseItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
    }

    public void updateClase() {
        System.out.println("Actualizando producto... " + claseId);
        for (GeProducto obj : productoFacade.listaProductoClase(claseId)) {
            listaProductoItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
    }

    public void updateClaseProducto(BigDecimal clase) {
        for (GeProducto obj : productoFacade.listaProductoClase(clase)) {
            listaProductoItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
    }

    public String edit(GeInsumo record) throws IOException {
        try {
            this.insumo = record;

            segmentoId = record.getSegmento() != null ? record.getSegmento().getId() : BigDecimal.valueOf(-1);
            familiaId = record.getFamilia() != null ? record.getFamilia().getId() : BigDecimal.valueOf(-1);
            claseId = record.getClase() != null ? record.getClase().getId() : BigDecimal.valueOf(-1);
            productoId = record.getProducto() != null ? record.getProducto().getId() : BigDecimal.valueOf(-1);
            updateSegmento();
            updateFamilia();
            updateClase();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("p_insumo", insumo);
            FacesContext.getCurrentInstance().getExternalContext().redirect(PATH + "/faces/views/insumos/editarInsumo.xhtml");
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de PQRSD", "Error al intentar editar el insumo."));
        }
        return "";
    }

    public String viewer(GeInsumo record) throws IOException {
        try {
            //idMotivoEdicion = record.getId();
            //System.out.println("ingresando a ***edit***"+idMotivoEdicion);
            this.insumo = record;

            segmentoId = record.getSegmento() != null ? record.getSegmento().getId() : BigDecimal.valueOf(-1);
            familiaId = record.getFamilia() != null ? record.getFamilia().getId() : BigDecimal.valueOf(-1);
            claseId = record.getClase() != null ? record.getClase().getId() : BigDecimal.valueOf(-1);
            productoId = record.getProducto() != null ? record.getProducto().getId() : BigDecimal.valueOf(-1);
            updateSegmento();
            updateFamilia();
            updateClase();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("p_insumo", insumo);
            FacesContext.getCurrentInstance().getExternalContext().redirect(PATH + "/faces/views/insumos/verInsumo.xhtml");
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de PQRSD", "Error al intentar editar el insumo."));
            return "listar.xhtml";
        }
        return "verInsumo.xhtml";
    }

    public void approveInsumo(GeInsumo record) {
        try {
            GeInsumo obj = record;
            obj.setEtapa(new PgEtapa(BigDecimal.valueOf(2L)));
            insumoFacade.actualizarInsumo(obj);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se autorizo el insumo " + record.getCodigo()));
            this.listaInsumo = insumoFacade.listaInsumo();
        } catch (Exception ex) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cancelInsumo(GeInsumo record) {
        try {
            GeInsumo obj = record;
            obj.setEtapa(new PgEtapa(BigDecimal.valueOf(4L)));
            insumoFacade.actualizarInsumo(obj);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Se rechazo el insumo " + record.getCodigo()));
            this.listaInsumo = insumoFacade.listaInsumo();
        } catch (Exception ex) {
            Logger.getLogger(insumoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void subir(FileUploadEvent event) {
        this.filexls = event.getFile();
        this.nombreArchivo = event.getFile().getFileName();
        System.out.println("archivo que se está cargando " + this.filexls.getFileName());
        FacesMessage msg = new FacesMessage("Exito! ", event.getFile().getFileName() + " ha sido subido.");
        /*
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            FacesMessage msg = new FacesMessage("Exito! ", event.getFile().getFileName() + " ha sido subido.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage("Error!, problemas al subir el archivo.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();
        }
         */
    }

    /*
    public void copyFile(String fileName, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            //fileName=this.procesoBean.getRadicado();
            FacesContext contextFaces = FacesContext.getCurrentInstance();
            PgParametroDetalle par = (PgParametroDetalle) contextFaces.getExternalContext().getSessionMap().get("pathServer");
            
            //this.ruta = par.getValorparametro();
            
            this.ruta = "/home/glassfish/ptsalud/";

            OutputStream out = new FileOutputStream(new File(ruta + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            //System.out.println("New file created!");
            this.nombreArchivo = fileName;
            System.out.println("la ruta es: "+this.nombreArchivo);
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        }
    }

    
    private static void readExcel(FileUploadEvent event) throws Exception {
        archivo = event.getFile();
        String nombreArchivo = event.getFile().getFileName();
        
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("data.xls"));
        HSSFSheet sheet = wb.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        for (int i = 1; i < rows; ++i) {
            HSSFRow row = sheet.getRow(i);

            HSSFCell productCell = row.getCell(0);
            HSSFCell priceCell = row.getCell(1);
            HSSFCell linkCell = row.getCell(2);

            String product = productCell.getStringCellValue();
            BigDecimal price = new BigDecimal(priceCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
            String link = linkCell.getStringCellValue();

            System.out.printf("%s, %s, %s%n", product, price.toString(), link);
        }
    }
     */
    public void importarExcel() {
        System.out.println("Valor de file: ");
        if (filexls != null) {

            try {
                /*
                InputStream input = getFile().getInputStream();            
                JavaPoiUtils javaPoiUtils = new JavaPoiUtils();
                javaPoiUtils.readExcelFile(input);
                FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                 */
                InputStream inputStream = filexls.getInputStream();
                //JavaPoiUtils javaPoiUtils = new JavaPoiUtils();
                //javaPoiUtils.readExcelFile(inputStream);

                XSSFWorkbook libro = new XSSFWorkbook(inputStream);
                Sheet sheet = libro.getSheetAt(0);
                Iterator<Row> iterator = sheet.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    Row currentRow = iterator.next();
                    if (i > 0) {
                        /*
                            GRUPO
                            SUBGRUPO
                            NOMBRE
                            UNIDADMEDIDA
                            COMPUESTO
                            PRECIO

                            -SEGMENTO
                            --FAMILIA
                            CLASE
                            PRODUCTO
                         */
                        if (currentRow.getCell(0) == null && currentRow.getCell(1) != null
                                && currentRow.getCell(1) != null && currentRow.getCell(2) != null
                                && currentRow.getCell(3) != null && currentRow.getCell(4) != null
                                && currentRow.getCell(5) != null && currentRow.getCell(6) != null
                                && currentRow.getCell(7) != null && currentRow.getCell(8) != null) {

                            BigDecimal grupo;
                            BigDecimal subGrupo;
                            BigDecimal unidadMedida;
                            BigDecimal precio;
                            int segmento;
                            int familia;
                            BigDecimal clase;
                            BigDecimal producto;

                            grupo = new BigDecimal(currentRow.getCell(0).getNumericCellValue());
                            subGrupo = new BigDecimal(currentRow.getCell(1).getNumericCellValue());
                            unidadMedida = new BigDecimal(currentRow.getCell(3).getNumericCellValue());
                            precio = new BigDecimal(currentRow.getCell(5).getNumericCellValue());
                            clase = new BigDecimal(currentRow.getCell(6).getStringCellValue());
                            producto = new BigDecimal(currentRow.getCell(7).getNumericCellValue());

                            insumo.setGrupo(new GeGrupo(grupo));
                            insumo.setSubgrupo(new GeSubGrupo(subGrupo));
                            insumo.setNombre(currentRow.getCell(2).getStringCellValue());
                            insumo.setUnidadmedida(new GeUnidadMedida(unidadMedida));
                            insumo.setCompuesto(currentRow.getCell(4).getStringCellValue().charAt(0));
                            insumo.setPrecio(precio);
                            insumo.setClase(new GeClase(clase));
                            insumo.setProducto(new GeProducto(producto));
                            insumoFacade.crearInsumo(insumo);
                        } else {
                            break;
                        }
                    }
                    i++;
                }

                //libro.close();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Se cargo el archivo exitosamente."));
                return;
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Error:Arhivo con datos erroneos."));
                return;
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Error al cargar archivo."));
            return;

        }

    }

    public insumoCtrl() {

    }

    @PostConstruct
    public void init() {
        Object temp = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_insumo");
        insumo = new GeInsumo();
        if (temp != null) {
            insumo = (GeInsumo) temp;
            segmentoId = insumo.getSegmento() != null ? insumo.getSegmento().getId() : BigDecimal.valueOf(-1);
            familiaId = insumo.getFamilia() != null ? insumo.getFamilia().getId() : BigDecimal.valueOf(-1);
            claseId = insumo.getClase() != null ? insumo.getClase().getId() : BigDecimal.valueOf(-1);
            productoId = insumo.getProducto() != null ? insumo.getProducto().getId() : BigDecimal.valueOf(-1);
            unidadmedidaId = insumo.getUnidadmedida() != null ? insumo.getUnidadmedida().getId() : BigDecimal.valueOf(-1);
            updateSegmento();
            updateFamilia();
            updateClase();
            listaInsumo = insumoFacade.listaInsumo();
            nameFile = "Insumo" + now();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("p_insumo");
        } else {
            listaInsumo = insumoFacade.listaInsumo();
            nameFile = "Insumo" + now();
        }

    }

    public String goCreate() throws IOException {
        //System.out.println("ingresando a ***goCreate***");
        insumo = new GeInsumo();
        subgrupo = new GeSubGrupo();
        grupoId = null;
        subgrupoId = null;
        unidadmedidaId = null;
        compuestoId = null;
        //FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/faces/views/motivo/crear.xhtml");
        listaInsumoDetalle.clear();
        return "crearInsumo.xhtml?faces-redirect=true";
    }

    public String goImport() throws IOException {
        //System.out.println("ingresando a ***goCreate***");
        insumo = new GeInsumo();
        subgrupo = new GeSubGrupo();
        //FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/faces/views/motivo/crear.xhtml");
        return "importarInsumo.xhtml?faces-redirect=true";
    }

    public void listarInsumoUNSPSC() {
        //System.out.println("listaInsumoSearch "+  this.unspsc);
        //if (this.listaInsumoSearch.size()!=0)
        this.listaInsumoSearch2.clear();
        this.listaInsumoSearch2 = insumoFacade.listaInsumoSearch(this.txtSearch);
    }

    public void limpiarSearch() {

        this.listaInsumoSearch2.clear();
        this.txtSearch = "";
    }

    public void addDetalle(GeInsumo record) {
        this.listaInsumoDetalle.add(record);
    }

    public void deleteDetalle(GeInsumo record) {
        this.listaInsumoDetalle.remove(record);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (this.listaInsumoDetalle.get(event.getRowIndex()).getCantidad() != null) {
            this.listaInsumoDetalle.get(event.getRowIndex())
                    .setSubtotal(this.listaInsumoDetalle.get(event.getRowIndex()).getPrecio()
                            .multiply(BigDecimal.valueOf(this.listaInsumoDetalle.get(event.getRowIndex()).getCantidad())));
        } else {
            this.listaInsumoDetalle.get(event.getRowIndex()).setSubtotal(BigDecimal.ZERO);
        }
        System.out.println("valor subtotal " + this.listaInsumoDetalle.get(event.getRowIndex()).getSubtotal());

    }

    public BigDecimal getSegmentoId() {
        return segmentoId;
    }

    public void setSegmentoId(BigDecimal segmentoId) {
        this.segmentoId = segmentoId;
    }

    public BigDecimal getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(BigDecimal familiaId) {
        this.familiaId = familiaId;
    }

    public BigDecimal getClaseId() {
        return claseId;
    }

    public void setClaseId(BigDecimal claseId) {
        this.claseId = claseId;
    }

    public BigDecimal getProductoId() {
        return productoId;
    }

    public void setProductoId(BigDecimal productoId) {
        this.productoId = productoId;
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public String getPath() {
        return PATH;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public List<GeInsumo> getListaInsumo() {
        return listaInsumo;
    }

    public void setListaInsumo(List<GeInsumo> listaInsumo) {
        this.listaInsumo = listaInsumo;
    }

    public List<GeInsumo> getListaInsumoSearch() {
        return listaInsumoSearch;
    }

    public void setListaInsumoSearch(List<GeInsumo> listaInsumoSearch) {
        this.listaInsumoSearch = listaInsumoSearch;
    }

    public List<GeInsumo> getListaInsumoSearch2() {
        return listaInsumoSearch2;
    }

    public List<GeInsumo> getListaInsumoDetalle() {
        return listaInsumoDetalle;
    }

    public void setListaInsumoDetalle(List<GeInsumo> listaInsumoDetalle) {
        this.listaInsumoDetalle = listaInsumoDetalle;
    }

    public void setListaInsumoSearch2(List<GeInsumo> listaInsumoSearch2) {
        this.listaInsumoSearch2 = listaInsumoSearch2;
    }

    public ArrayList<SelectItem> getListaSegmentoItem() {
        listaSegmentoItem = new ArrayList<SelectItem>();
        List a = segmentoFacade.listaSegmento();
        for (GeSegmento obj : segmentoFacade.listaSegmento()) {
            listaSegmentoItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaSegmentoItem;

    }

    public void setListaSegmentoItem(ArrayList<SelectItem> listaSegmentoItem) {
        this.listaSegmentoItem = listaSegmentoItem;
    }

    public ArrayList<SelectItem> getListaFamiliaItem() {
        listaFamiliaItem = new ArrayList<SelectItem>();
        List a = familiaFacade.listaFamilia();
        for (GeFamilia obj : familiaFacade.listaFamiliaSegmento(segmentoId)) {
            listaFamiliaItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }

        return listaFamiliaItem;
    }

    public void setListaFamiliaItem(ArrayList<SelectItem> listaFamiliaItem) {
        this.listaFamiliaItem = listaFamiliaItem;
    }

    public ArrayList<SelectItem> getListaClaseItem() {
        listaClaseItem = new ArrayList<SelectItem>();
        List a = claseFacade.listaClase();
        for (GeClase obj : claseFacade.listaClaseFamilia(familiaId)) {
            listaClaseItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaClaseItem;
    }

    public void setListaClaseItem(ArrayList<SelectItem> listaClaseItem) {
        this.listaClaseItem = listaClaseItem;
    }

    public ArrayList<SelectItem> getListaProductoItem() {
        listaProductoItem = new ArrayList<SelectItem>();
        List a = productoFacade.listaProducto();
        for (GeProducto obj : productoFacade.listaProductoClase(claseId)) {
            listaProductoItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaProductoItem;
    }

    public void setListaProductoItem(ArrayList<SelectItem> listaProductoItem) {
        this.listaProductoItem = listaProductoItem;
    }

    public ArrayList<SelectItem> getListaGrupoItem() {
        listaGrupoItem = new ArrayList<SelectItem>();
        List a = grupoFacade.listaGrupo();
        for (GeGrupo obj : grupoFacade.listaGrupo()) {
            listaGrupoItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaGrupoItem;
    }

    public void setListaGrupoItem(ArrayList<SelectItem> listaGrupoItem) {
        this.listaGrupoItem = listaGrupoItem;
    }

    public ArrayList<SelectItem> getListaSubgrupoItem() {
        listaSubgrupoItem = new ArrayList<SelectItem>();
        List a = subgrupoFacade.listaSubGrupo();
        for (GeSubGrupo obj : subgrupoFacade.listaSubGrupo()) {
            listaSubgrupoItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaSubgrupoItem;
    }

    public void setListaSubgrupoItem(ArrayList<SelectItem> listaSubgrupoItem) {
        this.listaSubgrupoItem = listaSubgrupoItem;
    }

    public ArrayList<SelectItem> getListaUnidadMedidaItem() {
        listaUnidadMedidaItem = new ArrayList<SelectItem>();
        //List a = unidadMedidaFacade.listaUnidadMedida();
        for (GeUnidadMedida obj : unidadMedidaFacade.listaUnidadMedida()) {
            listaUnidadMedidaItem.add(new SelectItem(obj.getId(), obj.getNombre()));
        }
        return listaUnidadMedidaItem;
    }

    public void setListaUnidadMedidaItem(ArrayList<SelectItem> listaUnidadMedidaItem) {
        this.listaUnidadMedidaItem = listaUnidadMedidaItem;
    }

    public GeInsumo getInsumo() {
        return insumo;
    }

    public void setInsumo(GeInsumo insumo) {
        this.insumo = insumo;
    }

    public GeSubGrupo getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(GeSubGrupo subgrupo) {
        this.subgrupo = subgrupo;
    }

    public UploadedFile getFilexls() {
        return filexls;
    }

    public void setFile(UploadedFile filexls) {
        this.filexls = filexls;
    }

    public BigDecimal getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(BigDecimal grupoId) {
        this.grupoId = grupoId;
    }

    public BigDecimal getSubgrupoId() {
        return subgrupoId;
    }

    public void setSubgrupoId(BigDecimal subgrupoId) {
        this.subgrupoId = subgrupoId;
    }

    public BigDecimal getUnidadmedidaId() {
        return unidadmedidaId;
    }

    public void setUnidadmedidaId(BigDecimal unidadmedidaId) {
        this.unidadmedidaId = unidadmedidaId;
    }

    public Character getCompuestoId() {
        return compuestoId;
    }

    public void setCompuestoId(Character compuestoId) {
        this.compuestoId = compuestoId;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

}
