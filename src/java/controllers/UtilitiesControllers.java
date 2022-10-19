/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
//import org.primefaces.PrimeFaces;


/**
 *
 * @author sis
 */
@ManagedBean(name = "utilitiesControllers")
@RequestScoped
public class UtilitiesControllers {
    String nameArchive;
    //public HttpServletRequest request;
    
    public static String windowModal(String name, String title, String text){
        String textCode;
        
        textCode = "<div class=\"modal\" tabindex=\"-1\" role=\"dialog\">\n" +
            "  <div class=\"modal-dialog\" role=\"document\">\n" +
            "    <div class=\"modal-content\">\n" +
            "      <div class=\"modal-header\">\n" +
            "        <h5 class=\"modal-title\">"+title+"</h5>\n" +
            "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
            "          <span aria-hidden=\"true\">&times;</span>\n" +
            "        </button>\n" +
            "      </div>\n" +
            "      <div class=\"modal-body\">\n" +
            "        <p>"+text+"</p>\n" +
            "      </div>\n" +
            "      <div class=\"modal-footer\">\n" +
            "        <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</div>";
        
        return textCode;
    } 
        
    public String urlLoad(String uri){
        /*Encuentra la ubicación .xhtml abierta*/
        //String url = request.getRequestURL().toString();
        //String uri = request.getRequestURI();
        return uri;
    }
    
      
    public void showMessage(String text) {
        System.out.println("Creando mensaje");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "PQRSD", text);        
        //PrimeFaces.current().dialog().showMessageDynamic(message);
    }
    
    public void postProcessXLS(Object document) {
        
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
        
        HSSFCellStyle cellStyle = wb.createCellStyle();
        
        /*cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont fontHeader = (HSSFFont) wb.createFont();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        fontHeader.setFontName("Arial Black");
        cellStyle.setFont(fontHeader);
        
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
            cell.setCellStyle(cellStyle);
            sheet.autoSizeColumn((short)i);
        }  
       */
    }

    public String getNameArchive() {
        return nameArchive;
    }

    public void setNameArchive(String nameArchive) {
        this.nameArchive = nameArchive;
    }
    
    
}
