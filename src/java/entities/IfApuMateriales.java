/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "IF_APU_MATERIALES")
@XmlRootElement
public class IfApuMateriales implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Column(name = "VR_UNITARIO")
    private BigInteger vrUnitario;
    @Column(name = "FECHA_INICO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInico;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "USUARIO_CREACION", length = 255)
    private String usuarioCreacion;
    @Column(name = "USUARIO_MODIFICAION", length = 255)
    private String usuarioModificaion;
    @Column(name = "ESTADO")
    private BigInteger estado;
    @JoinColumn(name = "INSUMO", referencedColumnName = "ID")
    @ManyToOne
    private GeInsumo insumo;
    @JoinColumn(name = "APU", referencedColumnName = "ID")
    @ManyToOne
    private IfApuMaestro apu;

    public IfApuMateriales() {
    }

    public IfApuMateriales(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public BigInteger getVrUnitario() {
        return vrUnitario;
    }

    public void setVrUnitario(BigInteger vrUnitario) {
        this.vrUnitario = vrUnitario;
    }

    public Date getFechaInico() {
        return fechaInico;
    }

    public void setFechaInico(Date fechaInico) {
        this.fechaInico = fechaInico;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioModificaion() {
        return usuarioModificaion;
    }

    public void setUsuarioModificaion(String usuarioModificaion) {
        this.usuarioModificaion = usuarioModificaion;
    }

    public BigInteger getEstado() {
        return estado;
    }

    public void setEstado(BigInteger estado) {
        this.estado = estado;
    }

    public GeInsumo getInsumo() {
        return insumo;
    }

    public void setInsumo(GeInsumo insumo) {
        this.insumo = insumo;
    }

    public IfApuMaestro getApu() {
        return apu;
    }

    public void setApu(IfApuMaestro apu) {
        this.apu = apu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfApuMateriales)) {
            return false;
        }
        IfApuMateriales other = (IfApuMateriales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IfApuMateriales[ id=" + id + " ]";
    }
    
}
