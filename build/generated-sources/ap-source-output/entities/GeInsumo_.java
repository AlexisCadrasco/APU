package entities;

import entities.GeClase;
import entities.GeFamilia;
import entities.GeGrupo;
import entities.GeInsumoDetalle;
import entities.GeMarca;
import entities.GeProducto;
import entities.GeSegmento;
import entities.GeSubGrupo;
import entities.GeUnidadMedida;
import entities.PgEtapa;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeInsumo.class)
public class GeInsumo_ { 

    public static volatile SingularAttribute<GeInsumo, String> descripcion;
    public static volatile SingularAttribute<GeInsumo, PgEtapa> etapa;
    public static volatile SingularAttribute<GeInsumo, GeGrupo> grupo;
    public static volatile SingularAttribute<GeInsumo, String> usuariocreacion;
    public static volatile SingularAttribute<GeInsumo, GeProducto> producto;
    public static volatile SingularAttribute<GeInsumo, String> nombre;
    public static volatile SingularAttribute<GeInsumo, String> usuariomodificacion;
    public static volatile SingularAttribute<GeInsumo, GeClase> clase;
    public static volatile SingularAttribute<GeInsumo, GeMarca> marca;
    public static volatile SingularAttribute<GeInsumo, BigDecimal> precio;
    public static volatile SingularAttribute<GeInsumo, GeSubGrupo> subgrupo;
    public static volatile SingularAttribute<GeInsumo, BigDecimal> id;
    public static volatile SingularAttribute<GeInsumo, String> codigo;
    public static volatile SingularAttribute<GeInsumo, Short> estadoregistro;
    public static volatile ListAttribute<GeInsumo, GeInsumoDetalle> geInsumoDetalleList;
    public static volatile SingularAttribute<GeInsumo, String> codigointerno;
    public static volatile SingularAttribute<GeInsumo, Character> compuesto;
    public static volatile SingularAttribute<GeInsumo, GeSegmento> segmento;
    public static volatile SingularAttribute<GeInsumo, BigDecimal> rendimiento;
    public static volatile SingularAttribute<GeInsumo, Date> fechacreacion;
    public static volatile SingularAttribute<GeInsumo, Date> fechamodificacion;
    public static volatile SingularAttribute<GeInsumo, Long> cantidad;
    public static volatile SingularAttribute<GeInsumo, BigInteger> empresa;
    public static volatile SingularAttribute<GeInsumo, GeFamilia> familia;
    public static volatile SingularAttribute<GeInsumo, GeUnidadMedida> unidadmedida;

}