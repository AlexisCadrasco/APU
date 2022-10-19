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
@Table(name = "IF_APU_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfApuDetalle.findAll", query = "SELECT i FROM IfApuDetalle i")})
public class IfApuDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "APU_DETALLE_GENERATOR", sequenceName = "SQ_IF_APU_DETALLE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APU_DETALLE_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NOMBREINSUMO")
    private String nombreinsumo;
    @Column(name = "CODIGOUNSPSC")
    private String codigounspsc;
    @Column(name = "CODIGOINTERNO")
    private String codigointerno;
    @Column(name = "CANTIDAD")
    private Long cantidad;
    @Column(name = "PRECIO")
    private BigDecimal precio;
    @Column(name = "RENDIMIENTO")
    private BigDecimal rendimiento;
    @Column(name = "DESCRIPCION")
    private String descripcion;
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
    @JoinColumn(name = "INSUMO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private GeInsumo insumo;
    @JoinColumn(name = "MARCA", referencedColumnName = "ID")
    @ManyToOne
    private GeMarca marca;
    @JoinColumn(name = "APUMAESTRO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private IfApuMaestro apumaestro;

    public IfApuDetalle() {
    }

    public IfApuDetalle(BigDecimal id) {
        this.id = id;
    }

    public IfApuDetalle(BigDecimal id, BigInteger empresa, short estadoregistro, String usuariocreacion, Date fechacreacion) {
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

    public String getNombreinsumo() {
        return nombreinsumo;
    }

    public void setNombreinsumo(String nombreinsumo) {
        this.nombreinsumo = nombreinsumo;
    }

    public String getCodigounspsc() {
        return codigounspsc;
    }

    public void setCodigounspsc(String codigounspsc) {
        this.codigounspsc = codigounspsc;
    }

    public String getCodigointerno() {
        return codigointerno;
    }

    public void setCodigointerno(String codigointerno) {
        this.codigointerno = codigointerno;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(BigDecimal rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public GeInsumo getInsumo() {
        return insumo;
    }

    public void setInsumo(GeInsumo insumo) {
        this.insumo = insumo;
    }

    public GeMarca getMarca() {
        return marca;
    }

    public void setMarca(GeMarca marca) {
        this.marca = marca;
    }

    public IfApuMaestro getApumaestro() {
        return apumaestro;
    }

    public void setApumaestro(IfApuMaestro apumaestro) {
        this.apumaestro = apumaestro;
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
        if (!(object instanceof IfApuDetalle)) {
            return false;
        }
        IfApuDetalle other = (IfApuDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IfApuDetalle[ id=" + id + " ]";
    }
    
}
