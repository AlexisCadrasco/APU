/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.atlantico.apu.exceptions;

import javax.faces.application.FacesMessage;

/**
 *
 * @author HP
 */
public class CustomException extends Exception {

private int nivel;

    /**
     * Constructor
     */
    public CustomException() {

    }

    /**
     * Constructor donde se establece el mensaje de error y el nivel de error.
     *
     * @param message Mensaje de error
     * @param nivel Nivel de error
     */
    public CustomException(String message, int nivel) {
        super(message);
        this.nivel = nivel;
    }

    /**
     * Returna nivel de Severidad de mensajes de Faces
     *
     * @return Nivel del mensaje Severity
     */
    public FacesMessage.Severity getNivelFacesMessage() {
        FacesMessage.Severity sev;
        switch (this.getNivel()) {
            case 1:
                sev = FacesMessage.SEVERITY_INFO;
                break;
            case 2:
                sev = FacesMessage.SEVERITY_WARN;
                break;
            default:
                sev = FacesMessage.SEVERITY_ERROR;
                break;
        }
        return sev;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String getMessage() {
        switch (this.getNivel()) {
            case 1:
            case 2:
                return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
            default:
                return "Error: "+super.getMessage(); //To change body of generated methods, choose Tools | Templates.
        }
    }
    

}
