package entities;

import entities.GeFamilia;
import entities.GeInsumo;
import entities.GeProducto;
import entities.GeSegmento;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeClase.class)
public class GeClase_ { 

    public static volatile SingularAttribute<GeClase, String> descripcion;
    public static volatile SingularAttribute<GeClase, String> codigo;
    public static volatile SingularAttribute<GeClase, Short> estadoregistro;
    public static volatile ListAttribute<GeClase, GeInsumo> geInsumoList;
    public static volatile SingularAttribute<GeClase, String> usuariocreacion;
    public static volatile SingularAttribute<GeClase, String> nombre;
    public static volatile SingularAttribute<GeClase, String> usuariomodificacion;
    public static volatile SingularAttribute<GeClase, GeSegmento> segmento;
    public static volatile SingularAttribute<GeClase, Date> fechacreacion;
    public static volatile SingularAttribute<GeClase, Date> fechamodificacion;
    public static volatile SingularAttribute<GeClase, BigDecimal> id;
    public static volatile SingularAttribute<GeClase, BigInteger> empresa;
    public static volatile ListAttribute<GeClase, GeProducto> geProductoList;
    public static volatile SingularAttribute<GeClase, GeFamilia> familia;

}