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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan C
 */
@Entity
@Table(name = "IF_PRESUPUESTO_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfPresupuestoDet.findAll", query = "SELECT i FROM IfPresupuestoDet i")})
public class IfPresupuestoDet implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PPTODET_GENERATOR", sequenceName = "SQ_IF_PRESUPUESTO_DET", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PPTODET_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "EMPRESA")
    private BigInteger empresa;
    @Basic(optional = false)
    @Column(name = "ESTADOREGISTRO")
    private short estadoregistro;
    @Basic(optional = false)
    @Column(name = "USUARIOCREACION")
    private String usuariocreacion;
    @Column(name = "USUARIOMODIFICACION")
    private String usuariomodificacion;
    @Basic(optional = false)
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @Column(name = "CONSECUTIVO")
    private BigInteger consecutivo;
    @Column(name = "CAPITULO")
    private String capitulo;
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @JoinColumn(name = "APU", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private IfApuMaestro apumaestro;
    
    @JoinColumn(name = "INSUMO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private GeInsumo insumo;
    @JoinColumn(name = "PRESUPUESTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private IfPresupuestoEnc presupuesto;
    @JoinColumn(name = "ETIQUETA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PrEtiqueta etiqueta;

    public IfPresupuestoDet() {
    }

    public IfPresupuestoDet(BigDecimal id) {
        this.id = id;
    }

    public IfPresupuestoDet(BigDecimal id, BigInteger empresa, short estadoregistro, String usuariocreacion, Date fechacreacion) {
        this.id = id;
        this.empresa = empresa;
        this.estadoregistro = estadoregistro;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public IfApuMaestro getApumaestro() {
        return apumaestro;
    }

    public void setApumaestro(IfApuMaestro apumaestro) {
        this.apumaestro = apumaestro;
    }


    public BigInteger getEmpresa() {
        return empresa;
    }

    public void setEmpresa(BigInteger empresa) {
        this.empresa = empresa;
    }

    public short getEstadoregistro() {
        return estadoregistro;
    }

    public void setEstadoregistro(short estadoregistro) {
        this.estadoregistro = estadoregistro;
    }

    public String getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    public String getUsuariomodificacion() {
        return usuariomodificacion;
    }

    public void setUsuariomodificacion(String usuariomodificacion) {
        this.usuariomodificacion = usuariomodificacion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public BigInteger getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(BigInteger consecutivo) {
        this.consecutivo = consecutivo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public GeInsumo getInsumo() {
        return insumo;
    }

    public void setInsumo(GeInsumo insumo) {
        this.insumo = insumo;
    }

    public IfPresupuestoEnc getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(IfPresupuestoEnc presupuesto) {
        this.presupuesto = presupuesto;
    }

    public PrEtiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(PrEtiqueta etiqueta) {
        this.etiqueta = etiqueta;
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
        if (!(object instanceof IfPresupuestoDet)) {
            return false;
        }
        IfPresupuestoDet other = (IfPresupuestoDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IfPresupuestoDet[ id=" + id + " ]";
    }
    
}
