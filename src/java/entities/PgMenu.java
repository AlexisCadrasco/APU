/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PG_MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PgMenu.findAll", query = "SELECT p FROM PgMenu p")})
public class PgMenu implements Serializable {

    @OneToMany(mappedBy = "idMenu")
    private List<PgRolMenu> pgRolMenuList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "UBICACION")
    private String ubicacion;
    @Column(name = "IMAGEN")
    private String imagen;
    @Column(name = "USUARIOCREACION")
    private String usuariocreacion;
    @Column(name = "USUARIOMODIFICACION")
    private String usuariomodificacion;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @Column(name = "ESTADOREGISTRO")
    private Long estadoregistro;
    @Column(name = "ORDEN")
    private BigInteger orden;
    @JoinColumn(name = "EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private GeEmpresa empresa;
    @OneToMany(mappedBy = "padre")
    private List<PgMenu> pgMenuList;
    @JoinColumn(name = "PADRE", referencedColumnName = "ID")
    @ManyToOne
    private PgMenu padre;

    public PgMenu() {
    }

    public PgMenu(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    public Long getEstadoregistro() {
        return estadoregistro;
    }

    public void setEstadoregistro(Long estadoregistro) {
        this.estadoregistro = estadoregistro;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public GeEmpresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(GeEmpresa empresa) {
        this.empresa = empresa;
    }

    @XmlTransient
    public List<PgMenu> getPgMenuList() {
        return pgMenuList;
    }

    public void setPgMenuList(List<PgMenu> pgMenuList) {
        this.pgMenuList = pgMenuList;
    }

    public PgMenu getPadre() {
        return padre;
    }

    public void setPadre(PgMenu padre) {
        this.padre = padre;
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
        if (!(object instanceof PgMenu)) {
            return false;
        }
        PgMenu other = (PgMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PgMenu[ id=" + id + " ]";
    }

    @XmlTransient
    public List<PgRolMenu> getPgRolMenuList() {
        return pgRolMenuList;
    }

    public void setPgRolMenuList(List<PgRolMenu> pgRolMenuList) {
        this.pgRolMenuList = pgRolMenuList;
    }
    
}
