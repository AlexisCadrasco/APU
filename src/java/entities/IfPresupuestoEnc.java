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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan C
 */
@Entity
@Table(name = "IF_PRESUPUESTO_ENC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfPresupuestoEnc.findAll", query = "SELECT i FROM IfPresupuestoEnc i")})
public class IfPresupuestoEnc implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PPTO_MAESTRO_GENERATOR", sequenceName = "SQ_IF_PRESUPUESTO_ENC", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PPTO_MAESTRO_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ADMINISTRACION")
    private Long administracion;
    @Column(name = "IMPREVISTO")
    private Long imprevisto;
    @Column(name = "UTILIDAD")
    private Long utilidad;
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "POSICIONX")
    private BigDecimal posicionx;
    @Column(name = "POSICIONY")
    private BigDecimal posiciony;
    @Column(name = "MULTIPLICADOR")
    private BigDecimal multiplicador;
    @Column(name = "CDP")
    private Long cdp;
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
    @JoinColumn(name = "TIPO_PRESUPUESTO", referencedColumnName = "ID")
    @ManyToOne
    private PgTipoPresupuesto tipopresupuesto;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presupuesto")
    private List<IfPresupuestoDet> ifPresupuestoDetList;
    @JoinColumn(name = "ENTIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private GeEntidad entidad;
    @JoinColumn(name = "ETAPA", referencedColumnName = "ID")
    @ManyToOne
    private PgEtapa etapa;

    public IfPresupuestoEnc() {
    }

    public IfPresupuestoEnc(BigDecimal id) {
        this.id = id;
    }

    public IfPresupuestoEnc(BigDecimal id, BigInteger empresa, short estadoregistro, String usuariocreacion, Date fechacreacion) {
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

    public Long getAdministracion() {
        return administracion;
    }

    public void setAdministracion(Long administracion) {
        this.administracion = administracion;
    }

    public Long getImprevisto() {
        return imprevisto;
    }

    public void setImprevisto(Long imprevisto) {
        this.imprevisto = imprevisto;
    }

    public Long getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(Long utilidad) {
        this.utilidad = utilidad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPosicionx() {
        return posicionx;
    }

    public void setPosicionx(BigDecimal posicionx) {
        this.posicionx = posicionx;
    }

    public BigDecimal getPosiciony() {
        return posiciony;
    }

    public void setPosiciony(BigDecimal posiciony) {
        this.posiciony = posiciony;
    }

    public BigDecimal getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(BigDecimal multiplicador) {
        this.multiplicador = multiplicador;
    }

    public Long getCdp() {
        return cdp;
    }

    public void setCdp(Long cdp) {
        this.cdp = cdp;
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

    public PgTipoPresupuesto getTipopresupuesto() {
        return tipopresupuesto;
    }

    public void setTipopresupuesto(PgTipoPresupuesto tipopresupuesto) {
        this.tipopresupuesto = tipopresupuesto;
    }


    @XmlTransient
    public List<IfPresupuestoDet> getIfPresupuestoDetList() {
        return ifPresupuestoDetList;
    }

    public void setIfPresupuestoDetList(List<IfPresupuestoDet> ifPresupuestoDetList) {
        this.ifPresupuestoDetList = ifPresupuestoDetList;
    }

    public GeEntidad getEntidad() {
        return entidad;
    }

    public void setEntidad(GeEntidad entidad) {
        this.entidad = entidad;
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
        if (!(object instanceof IfPresupuestoEnc)) {
            return false;
        }
        IfPresupuestoEnc other = (IfPresupuestoEnc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IfPresupuestoEnc[ id=" + id + " ]";
    }
    
}
