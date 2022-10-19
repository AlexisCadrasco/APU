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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan C
 */
@Entity
@Table(name = "IF_APU_MAESTRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfApuMaestro.findAll", query = "SELECT i FROM IfApuMaestro i")})
public class IfApuMaestro implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "APU_MAESTRO_GENERATOR", sequenceName = "SQ_IF_APU_MAESTRO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APU_MAESTRO_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "CONSECUTIVO")
    private String consecutivo;
    @Column(name = "NOMBRE")
    private String nombre;
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
    @Basic(optional = false)
    @Column(name = "FECHAAPROBACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaaprobacion;
    @Column(name = "USUARIOAPROBACION")
    private String usuarioaprobacion;
    @Column(name = "CANTIDAD")
    private Long cantidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apumaestro")
    private List<IfApuDetalle> ifApuDetalleList;
    @JoinColumn(name = "UNIDADMEDIDA", referencedColumnName = "ID")
    @ManyToOne
    private GeUnidadMedida unidadmedida;
    @JoinColumn(name = "TIPO", referencedColumnName = "ID")
    @ManyToOne
    private IfTipoApu tipo;
    @JoinColumn(name = "ETAPA", referencedColumnName = "ID")
    @ManyToOne
    private PgEtapa etapa;
    @Transient
    private String capitulo;
    
    
    public IfApuMaestro() {
    }

    public IfApuMaestro(BigDecimal id) {
        this.id = id;
    }

    public IfApuMaestro(BigDecimal id, BigInteger empresa, short estadoregistro, String usuariocreacion, Date fechacreacion, Date fechaaprobacion) {
        this.id = id;
        this.empresa = empresa;
        this.estadoregistro = estadoregistro;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.fechaaprobacion = fechaaprobacion;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Date getFechaaprobacion() {
        return fechaaprobacion;
    }

    public void setFechaaprobacion(Date fechaaprobacion) {
        this.fechaaprobacion = fechaaprobacion;
    }

    public String getUsuarioaprobacion() {
        return usuarioaprobacion;
    }

    public void setUsuarioaprobacion(String usuarioaprobacion) {
        this.usuarioaprobacion = usuarioaprobacion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    @XmlTransient
    public List<IfApuDetalle> getIfApuDetalleList() {
        return ifApuDetalleList;
    }

    public void setIfApuDetalleList(List<IfApuDetalle> ifApuDetalleList) {
        this.ifApuDetalleList = ifApuDetalleList;
    }

    public GeUnidadMedida getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(GeUnidadMedida unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public IfTipoApu getTipo() {
        return tipo;
    }

    public void setTipo(IfTipoApu tipo) {
        this.tipo = tipo;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public PgEtapa getEtapa() {
        return etapa;
    }

    public void setEtapa(PgEtapa etapa) {
        this.etapa = etapa;
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
        if (!(object instanceof IfApuMaestro)) {
            return false;
        }
        IfApuMaestro other = (IfApuMaestro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IfApuMaestro[ id=" + id + " ]";
    }
    
}
