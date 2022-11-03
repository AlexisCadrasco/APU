/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.atlantico.apu.validator.view.apu;

import co.gov.atlantico.apu.validator.view.insumos.*;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author HP
 */
@FacesValidator("validator.view.apu.nombre")
public class NombreValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String val = value.toString();
        if (val.isEmpty()) {
            FacesMessage msg
                    = new FacesMessage("Dato Obligatorio", "El campo nombre APU es Obligatorio.");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }
}