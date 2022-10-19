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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan C
 */
@Entity
@Table(name = "PG_USUARIO_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PgUsuarioRol.findAll", query = "SELECT p FROM PgUsuarioRol p")})
public class PgUsuarioRol implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
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
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID")
    @ManyToOne
    private PgRol idRol;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private PgUsuario idUsuario;

    public PgUsuarioRol() {
    }

    public PgUsuarioRol(BigDecimal id) {
        this.id = id;
    }

    public PgUsuarioRol(BigDecimal id, BigInteger empresa, short estadoregistro, String usuariocreacion, Date fechacreacion) {
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

    public PgRol getIdRol() {
        return idRol;
    }

    public void setIdRol(PgRol idRol) {
        this.idRol = idRol;
    }

    public PgUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(PgUsuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof PgUsuarioRol)) {
            return false;
        }
        PgUsuarioRol other = (PgUsuarioRol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PgUsuarioRol[ id=" + id + " ]";
    }
    
}
