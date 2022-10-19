/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigDecimal;

/**
 *
 * @author Leonardo Jimenez
 */
public class GenericProcedureResponse {

    private BigDecimal codigoResult;
    private String msgResult;
    private Object[] object;

    public GenericProcedureResponse() {
    }

    public BigDecimal getCodigoResult() {
        return codigoResult;
    }

    public void setCodigoResult(BigDecimal codigoResult) {
        this.codigoResult = codigoResult;
    }

    public String getMsgResult() {
        return msgResult;
    }

    public void setMsgResult(String msgResult) {
        this.msgResult = msgResult;
    }

    public Object[] getObject() {
        return object;
    }

    public void setObject(Object[] object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "codigo: " + codigoResult + ", mensaje=" + msgResult;
    }
    
    

}
