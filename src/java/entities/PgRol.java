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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "PG_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PgRol.findAll", query = "SELECT p FROM PgRol p")})
public class PgRol implements Serializable {

    @OneToMany(mappedBy = "idRol")
    private List<PgRolMenu> pgRolMenuList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
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
    @OneToMany(mappedBy = "idRol")
    private List<PgUsuarioRol> pgUsuarioRolList;

    public PgRol() {
    }

    public PgRol(BigDecimal id) {
        this.id = id;
    }

    public PgRol(BigDecimal id, BigInteger empresa, short estadoregistro, String usuariocreacion, Date fechacreacion) {
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

    @XmlTransient
    public List<PgUsuarioRol> getPgUsuarioRolList() {
        return pgUsuarioRolList;
    }

    public void setPgUsuarioRolList(List<PgUsuarioRol> pgUsuarioRolList) {
        this.pgUsuarioRolList = pgUsuarioRolList;
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
        if (!(object instanceof PgRol)) {
            return false;
        }
        PgRol other = (PgRol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PgRol[ id=" + id + " ]";
    }

    @XmlTransient
    public List<PgRolMenu> getPgRolMenuList() {
        return pgRolMenuList;
    }

    public void setPgRolMenuList(List<PgRolMenu> pgRolMenuList) {
        this.pgRolMenuList = pgRolMenuList;
    }
    
}
