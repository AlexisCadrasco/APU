/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.atlantico.apu.validator.view.insumos;

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
@FacesValidator("validator.view.insumos.precio")
public class PrecioValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            FacesMessage msg = new FacesMessage("Dato Obligatorio", "El campo Precio es Obligatorio.");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }

        String val = value.toString().trim();

        if (val.isEmpty()) {
            FacesMessage msg = new FacesMessage("Dato Obligatorio", "El campo Precio es Obligatorio.");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        } else {
            long rs = Long.parseLong(val);
            if (rs <= 0) {
                FacesMessage msg = new FacesMessage("Dato Obligatorio", "El valor del Campo precio debe ser mayor a 0 (Cero).");
                msg.setSeverity(FacesMessage.SEVERITY_WARN);
                throw new ValidatorException(msg);
            }
        }
    }

}
