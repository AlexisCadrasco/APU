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
import javax.persistence.FetchType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan C
 */
@Entity
@Table(name = "GE_CLASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeClase.findAll", query = "SELECT g FROM GeClase g")
    , @NamedQuery(name = "GeClase.findById", query = "SELECT g FROM GeClase g WHERE g.id = :id")
    , @NamedQuery(name = "GeClase.findByNombre", query = "SELECT g FROM GeClase g WHERE g.nombre = :nombre")
    , @NamedQuery(name = "GeClase.findByDescripcion", query = "SELECT g FROM GeClase g WHERE g.descripcion = :descripcion")
    , @NamedQuery(name = "GeClase.findByCodigo", query = "SELECT g FROM GeClase g WHERE g.codigo = :codigo")
    , @NamedQuery(name = "GeClase.findByEmpresa", query = "SELECT g FROM GeClase g WHERE g.empresa = :empresa")
    , @NamedQuery(name = "GeClase.findByEstadoregistro", query = "SELECT g FROM GeClase g WHERE g.estadoregistro = :estadoregistro")
    , @NamedQuery(name = "GeClase.findByUsuariocreacion", query = "SELECT g FROM GeClase g WHERE g.usuariocreacion = :usuariocreacion")
    , @NamedQuery(name = "GeClase.findByUsuariomodificacion", query = "SELECT g FROM GeClase g WHERE g.usuariomodificacion = :usuariomodificacion")
    , @NamedQuery(name = "GeClase.findByFechacreacion", query = "SELECT g FROM GeClase g WHERE g.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "GeClase.findByFechamodificacion", query = "SELECT g FROM GeClase g WHERE g.fechamodificacion = :fechamodificacion")})
public class GeClase implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<GeProducto> geProductoList;
    @OneToMany(mappedBy = "clase")
    private List<GeInsumo> geInsumoList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLASE_GENERATOR", sequenceName = "SQ_GE_CLASE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASE_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CODIGO")
    private String codigo;
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
    @JoinColumn(name = "FAMILIA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GeFamilia familia;
    @JoinColumn(name = "SEGMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GeSegmento segmento;

    public GeClase() {
    }

    public GeClase(BigDecimal id) {
        this.id = id;
    }

    public GeClase(BigDecimal id, String nombre, BigInteger empresa, short estadoregistro, String usuariocreacion, Date fechacreacion) {
        this.id = id;
        this.nombre = nombre;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public GeFamilia getFamilia() {
        return familia;
    }

    public void setFamilia(GeFamilia familia) {
        this.familia = familia;
    }

    public GeSegmento getSegmento() {
        return segmento;
    }

    public void setSegmento(GeSegmento segmento) {
        this.segmento = segmento;
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
        if (!(object instanceof GeClase)) {
            return false;
        }
        GeClase other = (GeClase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GeClase[ id=" + id + " ]";
    }

    @XmlTransient
    public List<GeProducto> getGeProductoList() {
        return geProductoList;
    }

    public void setGeProductoList(List<GeProducto> geProductoList) {
        this.geProductoList = geProductoList;
    }

    @XmlTransient
    public List<GeInsumo> getGeInsumoList() {
        return geInsumoList;
    }

    public void setGeInsumoList(List<GeInsumo> geInsumoList) {
        this.geInsumoList = geInsumoList;
    }
    
}
