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
@Table(name = "GE_INSUMO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeInsumo.findAll", query = "SELECT g FROM GeInsumo g")})
public class GeInsumo implements Serializable {

    @Column(name = "CANTIDAD")
    private Long cantidad;
    @OneToMany(mappedBy = "insumo")
    private List<IfApuMateriales> ifApuMaterialesList;
    @OneToMany(mappedBy = "insumo")
    private List<IfApuManoObra> ifApuManoObraList;
    @OneToMany(mappedBy = "insumo")
    private List<IfApuTransporte> ifApuTransporteList;
    @OneToMany(mappedBy = "insumo")
    private List<IfApuEquipo> ifApuEquipoList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "INSUMO_GENERATOR", sequenceName = "SQ_GE_INSUMO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSUMO_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "CODIGOINTERNO")
    private String codigointerno;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "COMPUESTO")
    private Character compuesto;
    @Column(name = "RENDIMIENTO")
    private BigDecimal rendimiento;
    @Column(name = "PRECIO")
    private BigDecimal precio;
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
    @JoinColumn(name = "CLASE", referencedColumnName = "ID")
    @ManyToOne
    private GeClase clase;
    @JoinColumn(name = "FAMILIA", referencedColumnName = "ID")
    @ManyToOne
    private GeFamilia familia;
    @JoinColumn(name = "GRUPO", referencedColumnName = "ID")
    @ManyToOne
    private GeGrupo grupo;
    @JoinColumn(name = "MARCA", referencedColumnName = "ID")
    @ManyToOne
    private GeMarca marca;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID")
    @ManyToOne
    private GeProducto producto;
    @JoinColumn(name = "SEGMENTO", referencedColumnName = "ID")
    @ManyToOne
    private GeSegmento segmento;
    @JoinColumn(name = "SUBGRUPO", referencedColumnName = "ID")
    @ManyToOne
    private GeSubGrupo subgrupo;
    @JoinColumn(name = "UNIDADMEDIDA", referencedColumnName = "ID")
    @ManyToOne
    private GeUnidadMedida unidadmedida;
    @JoinColumn(name = "ETAPA", referencedColumnName = "ID")
    @ManyToOne
    private PgEtapa etapa;
    @OneToMany(mappedBy = "insumo")
    private List<GeInsumoDetalle> geInsumoDetalleList;
    
    @Transient
    private BigDecimal subtotal;
    
    public GeInsumo() {
    }

    public GeInsumo(BigDecimal id) {
        this.id = id;
    }

    public GeInsumo(BigDecimal id, String nombre, BigInteger empresa, short estadoregistro, String usuariocreacion, Date fechacreacion) {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigointerno() {
        return codigointerno;
    }

    public void setCodigointerno(String codigointerno) {
        this.codigointerno = codigointerno;
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

    public Character getCompuesto() {
        return compuesto;
    }

    public void setCompuesto(Character compuesto) {
        this.compuesto = compuesto;
    }

    public BigDecimal getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(BigDecimal rendimiento) {
        this.rendimiento = rendimiento;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
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


    public GeClase getClase() {
        return clase;
    }

    public void setClase(GeClase clase) {
        this.clase = clase;
    }

    public GeFamilia getFamilia() {
        return familia;
    }

    public void setFamilia(GeFamilia familia) {
        this.familia = familia;
    }

    public GeGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(GeGrupo grupo) {
        this.grupo = grupo;
    }

    public GeMarca getMarca() {
        return marca;
    }

    public void setMarca(GeMarca marca) {
        this.marca = marca;
    }

    public GeProducto getProducto() {
        return producto;
    }

    public void setProducto(GeProducto producto) {
        this.producto = producto;
    }

    public GeSegmento getSegmento() {
        return segmento;
    }

    public void setSegmento(GeSegmento segmento) {
        this.segmento = segmento;
    }

    public GeSubGrupo getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(GeSubGrupo subgrupo) {
        this.subgrupo = subgrupo;
    }

    public GeUnidadMedida getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(GeUnidadMedida unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public PgEtapa getEtapa() {
        return etapa;
    }

    public void setEtapa(PgEtapa etapa) {
        this.etapa = etapa;
    }

    @XmlTransient
    public List<GeInsumoDetalle> getGeInsumoDetalleList() {
        return geInsumoDetalleList;
    }

    public void setGeInsumoDetalleList(List<GeInsumoDetalle> geInsumoDetalleList) {
        this.geInsumoDetalleList = geInsumoDetalleList;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
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
        if (!(object instanceof GeInsumo)) {
            return false;
        }
        GeInsumo other = (GeInsumo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GeInsumo[ id=" + id + " ]";
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    @XmlTransient
    public List<IfApuMateriales> getIfApuMaterialesList() {
        return ifApuMaterialesList;
    }

    public void setIfApuMaterialesList(List<IfApuMateriales> ifApuMaterialesList) {
        this.ifApuMaterialesList = ifApuMaterialesList;
    }

    @XmlTransient
    public List<IfApuManoObra> getIfApuManoObraList() {
        return ifApuManoObraList;
    }

    public void setIfApuManoObraList(List<IfApuManoObra> ifApuManoObraList) {
        this.ifApuManoObraList = ifApuManoObraList;
    }

    @XmlTransient
    public List<IfApuTransporte> getIfApuTransporteList() {
        return ifApuTransporteList;
    }

    public void setIfApuTransporteList(List<IfApuTransporte> ifApuTransporteList) {
        this.ifApuTransporteList = ifApuTransporteList;
    }

    @XmlTransient
    public List<IfApuEquipo> getIfApuEquipoList() {
        return ifApuEquipoList;
    }

    public void setIfApuEquipoList(List<IfApuEquipo> ifApuEquipoList) {
        this.ifApuEquipoList = ifApuEquipoList;
    }
    
}
