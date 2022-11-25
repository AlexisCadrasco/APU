/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import ejbs.InsumoFacade;
import ejbs.UnidadMedidaFacade;
import ejbs.ApuMaestroFacade;
import ejbs.GrupoFacade;
import ejbs.SubGrupoFacade;
import ejbs.SegmentoFacade;
import ejbs.ClaseFacade;
import ejbs.ProductoFacade;
import entities.GeInsumo;
import entities.GeSubGrupo;
import entities.GeGrupo;
import entities.GeClase;
import entities.GeProducto;
import entities.GeUnidadMedida;
import entities.GeSegmento;
import entities.GeFamilia;
import entities.IfApuMaestro;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import static java.time.Instant.now;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

@ManagedBean(name = "fileUploadCtrl")
@SessionScoped
/**
 *
 * @author Juan C
 */
public class FileUploadCtrl implements Serializable {
    @EJB
    private InsumoFacade insumoFacade;
    @EJB
    private UnidadMedidaFacade unidadMedidaFacade;
    @EJB
    private ApuMaestroFacade apuMaestroFacade;
    @EJB
    private GrupoFacade grupoFacade;
    @EJB
    private SubGrupoFacade subGrupoFacade;
    @EJB
    private SegmentoFacade segmentoFacade;
    @EJB
    private ClaseFacade claseFacade;
    @EJB
    private ProductoFacade productoFacade;
    
    
    
    private GeInsumo insumo = new GeInsumo();
    private GeSubGrupo subgrupo = new GeSubGrupo();
    private GeUnidadMedida unidadMedida = new GeUnidadMedida();
    private IfApuMaestro apu = new IfApuMaestro();
    private UploadedFile filexls;
    private String nombreArchivo;
    private String validacionPlano;
    //private transient UploadedFile uploadedFile;
    
    @PostConstruct
    public void init() {
       filexls = null;
       nombreArchivo = null;
    }
    
    public void subir(FileUploadEvent event) throws FileNotFoundException, IOException {
        
        //System.out.println("se está cargando el archivo ");
        
        filexls = event.getFile();
        String fileName = filexls.getFileName();
        String contentType = filexls.getContentType();
        byte[] contents = filexls.getContent();
        
        //System.out.println("this.archivo " + this.archivo.getFileName());
        // Do what you want with the file       
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputStream());
            FacesMessage msg = new FacesMessage("Exito! ", event.getFile().getFileName() + " ha sido subido.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage("Error!, problemas al subir el archivo.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();          
        }
      
    }
    
    public void copyFile(String fileName, InputStream in) {
    
    }
    
    public void validaPlano(Row lineArc, int noLinea){
        Long salto = 0L;
        Long valGrupo = grupoFacade.existeGrupoByID(lineArc.getCell(0).getNumericCellValue());
        if (valGrupo == 0){
            validacionPlano += "Linea "+noLinea+" grupo invalido, ";
            salto = 1L;
        }
        
        Long valSubgrupo = subGrupoFacade.existeSubgrupoByID(lineArc.getCell(1).getNumericCellValue());
        if (valSubgrupo == 0){
            validacionPlano += "Linea "+noLinea+" subgrupo invalido, ";
            salto = 1L;
        }
        
        Long valUnidad = unidadMedidaFacade.existeUnidadCodigo(lineArc.getCell(3).getStringCellValue());
        if (valUnidad == 0){
            validacionPlano += "Linea "+noLinea+" unidad de medida invalida, ";
            salto = 1L;
        }
        
        Long valSegmento = segmentoFacade.existeSegmentoByCodigo(lineArc.getCell(6).getStringCellValue());
        if (valSegmento == 0){
            validacionPlano += "Linea "+noLinea+" segmento invalido, ";
            salto = 1L;
        }
        
        Long valClase = claseFacade.existeClaseByCodigo(lineArc.getCell(7).getStringCellValue());
        if (valClase == 0){
            validacionPlano += "Linea "+noLinea+" clase invalida, ";
            salto = 1L;
        }
        
        Long valProducto = productoFacade.existeProductoCodigo(lineArc.getCell(8).getStringCellValue());
        if (valProducto == 0){
            validacionPlano += "Linea "+noLinea+" producto invalido, ";
            salto = 1L;
        }
        if (salto == 1L){
            validacionPlano += "\nun";
        }
        System.out.println("validaPlano "+noLinea+"+++ "+validacionPlano );
    }
    
    public void importarExcelInsumo()throws IOException{
        
        System.out.println("Valor de file: " + filexls);
        
        String fileName = filexls.getFileName();
        String contentType = filexls.getContentType();
        byte[] contents = filexls.getContent(); // Or getInputStream()
        if (filexls != null){
            try{
                InputStream inputStream = filexls.getInputStream();
                
                XSSFWorkbook libro = new XSSFWorkbook(inputStream);
                Sheet sheet = libro.getSheetAt(0);
                System.out.println("Hoja: "+sheet.getSheetName());
                Iterator<Row> iterator = sheet.iterator();
                int i = 0;
                System.out.println("iterator: "+iterator);
                while(iterator.hasNext()){
                    Row currentRow = iterator.next();
                    validaPlano(currentRow, i);
                    System.out.println("revisión... "+validacionPlano);
                    
                }
                
                if (validacionPlano.isEmpty()){
                    while(iterator.hasNext()){
                        Row currentRow = iterator.next();
                        if (i > 0){
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
                            if (currentRow.getCell(0)!= null && currentRow.getCell(1) != null 
                                    && currentRow.getCell(2) != null
                                    && currentRow.getCell(3) != null && currentRow.getCell(4) != null
                                    && currentRow.getCell(5) != null && currentRow.getCell(6) != null
                                    && currentRow.getCell(7) != null && currentRow.getCell(8) != null){

                                String nombreInsumo;
                                BigDecimal grupo;
                                BigDecimal subGrupo;
                                BigDecimal unidadMedida;
                                BigDecimal precio;
                                BigDecimal segmento;
                                int familia = 0;
                                BigDecimal clase;
                                BigDecimal producto;
                                grupo = new BigDecimal(currentRow.getCell(0).getNumericCellValue());
                                subGrupo = new BigDecimal(currentRow.getCell(1).getNumericCellValue());
                                nombreInsumo = currentRow.getCell(2).getStringCellValue();
                                unidadMedida = new BigDecimal(currentRow.getCell(3).getNumericCellValue());
                                precio = new BigDecimal(currentRow.getCell(5).getNumericCellValue());
                                segmento = new BigDecimal(currentRow.getCell(6).getNumericCellValue());
                                clase = new BigDecimal(currentRow.getCell(7).getNumericCellValue());
                                producto = new BigDecimal(currentRow.getCell(8).getNumericCellValue());
                                //Valido si existe
                                Integer eInsumo = insumoFacade.existeInsumoNombre(nombreInsumo);
                                if (eInsumo == 0){
                                    insumo.setGrupo(new GeGrupo (grupo));
                                    insumo.setSubgrupo(new GeSubGrupo (subGrupo));
                                    insumo.setSegmento(new GeSegmento(segmento));
                                    insumo.setFamilia(new GeFamilia(new BigDecimal(familia)));
                                    insumo.setNombre(nombreInsumo);
                                    insumo.setUnidadmedida(new GeUnidadMedida (unidadMedida));
                                    insumo.setCompuesto(currentRow.getCell(4).getStringCellValue().charAt(0));
                                    insumo.setPrecio(precio);
                                    insumo.setClase(new GeClase (clase));
                                    insumo.setProducto(new GeProducto(producto));
                                    insumo.setEmpresa(BigInteger.ONE);
                                    insumo.setFechacreacion(new Date());
                                    insumo.setUsuariocreacion("ADMIN");
                                    insumoFacade.crearInsumo(insumo);
                                }else{
                                    System.out.println("Insumo ya existe.... "+nombreInsumo );
                                }

                                //System.out.println("Cargue realizado.... ");

                            }else{
                                break;
                            }
                        }
                        i++;
                    }
                }
                filexls = null;
                nombreArchivo = null;
                //libro.close();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Se cargo el archivo exitosamente."));
                return;
            }catch(Exception e){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Error: Archivo con datos erroneos."));
                return;
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Error al cargar archivo."));
            return;
        }        
    }
    
    public void importarExcelApu()throws IOException{
        //System.out.println("Valor de file: " + filexls);
        String fileName = filexls.getFileName();
        String contentType = filexls.getContentType();
        byte[] contents = filexls.getContent(); // Or getInputStream()
        if (filexls != null){
            try{
                InputStream inputStream = filexls.getInputStream();
                
                XSSFWorkbook libro = new XSSFWorkbook(inputStream);
                Sheet sheet = libro.getSheetAt(0);
                //System.out.println("Hoja: "+sheet.getSheetName());
                Iterator<Row> iterator = sheet.iterator();
                int i = 0;
                while(iterator.hasNext()){
                    Row currentRow = iterator.next();
                    if (i > 0){
                        /*
                            Encabezado
                        
                            CONSECUTIVO
                            NOMBRE
                            DESCRIPCION
                            TIPO
                            CANTIDAD
                            UNIDADMEDIDA
                        */
                        
                        /*
                            Detalle
                        
                            id
                            apumaestro
                            insumo
                            nombreinsumo
                            codigounspsc
                            codigointerno
                            marca
                            cantidad
                            precio
                            rendimiento
                            descripcion

                        */
                        
                        if (currentRow.getCell(0)!= null && currentRow.getCell(1) != null 
                                && currentRow.getCell(2) != null
                                && currentRow.getCell(3) != null && currentRow.getCell(4) != null){

                            String consecutivo;
                            String nombre;
                            String descripcion; 
                            String tipo;
                            BigDecimal cantidad;
                            String unidadMed = "";
                            
                            consecutivo = currentRow.getCell(0).getStringCellValue();
                            nombre = currentRow.getCell(1).getStringCellValue();
                            descripcion = currentRow.getCell(2).getStringCellValue();
                            
                            cantidad = new BigDecimal(currentRow.getCell(4).getNumericCellValue());
                            
                            GeUnidadMedida unidad = unidadMedidaFacade.UnidadMedidaNombre(unidadMed);
                            
                            if ( unidad == null){
                                //Unidad del APU no está creada
                            }
                            
                            Integer exisApu = apuMaestroFacade.existeApuNombre(nombre);
                            
                            if (exisApu == 0){
                                apu.setConsecutivo(consecutivo);
                                apu.setNombre(nombre);
                                apu.setDescripcion(descripcion);
                                //apu.setTipo(tipo);
                                apu.setCantidad(Long.MIN_VALUE);
                                apu.setUnidadmedida(unidad);
                                insumo.setEmpresa(BigInteger.ONE);
                                insumo.setFechacreacion(new Date());
                                insumo.setUsuariocreacion("ADMIN");
                                insumoFacade.crearInsumo(insumo);
                            }else{
                                System.out.println("Insumo ya existe.... "+nombre );
                            }
                            
                            //System.out.println("Cargue realizado.... ");
                            
                        }else{
                            break;
                        }
                    }
                    i++;
                }
                filexls = null;
                nombreArchivo = null;
                //libro.close();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Se cargo el archivo exitosamente."));
                return;
            }catch(Exception e){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de APU", "Error: Archivo con datos erroneos."));
                return;
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Administrador de APU", "Error al cargar archivo."));
            return;
        }        
    }

    public InsumoFacade getInsumoFacade() {
        return insumoFacade;
    }

    public void setInsumoFacade(InsumoFacade insumoFacade) {
        this.insumoFacade = insumoFacade;
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

    public GeUnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(GeUnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public UploadedFile getFilexls() {
        return filexls;
    }

    public void setFilexls(UploadedFile filexls) {
        this.filexls = filexls;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getValidacionPlano() {
        return validacionPlano;
    }

    public void setValidacionPlano(String validacionPlano) {
        this.validacionPlano = validacionPlano;
    }

        
}
