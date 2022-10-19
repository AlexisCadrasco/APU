package entities;

import entities.GeClase;
import entities.GeFamilia;
import entities.GeInsumo;
import entities.GeSegmento;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeProducto.class)
public class GeProducto_ { 

    public static volatile SingularAttribute<GeProducto, String> descripcion;
    public static volatile SingularAttribute<GeProducto, String> codigo;
    public static volatile SingularAttribute<GeProducto, Short> estadoregistro;
    public static volatile ListAttribute<GeProducto, GeInsumo> geInsumoList;
    public static volatile SingularAttribute<GeProducto, String> usuariocreacion;
    public static volatile SingularAttribute<GeProducto, String> nombre;
    public static volatile SingularAttribute<GeProducto, String> usuariomodificacion;
    public static volatile SingularAttribute<GeProducto, GeClase> clase;
    public static volatile SingularAttribute<GeProducto, GeSegmento> segmento;
    public static volatile SingularAttribute<GeProducto, Date> fechacreacion;
    public static volatile SingularAttribute<GeProducto, Date> fechamodificacion;
    public static volatile SingularAttribute<GeProducto, BigDecimal> id;
    public static volatile SingularAttribute<GeProducto, BigInteger> empresa;
    public static volatile SingularAttribute<GeProducto, GeFamilia> familia;

}