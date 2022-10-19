package entities;

import entities.GeClase;
import entities.GeFamilia;
import entities.GeInsumo;
import entities.GeProducto;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeSegmento.class)
public class GeSegmento_ { 

    public static volatile SingularAttribute<GeSegmento, String> descripcion;
    public static volatile SingularAttribute<GeSegmento, String> codigo;
    public static volatile SingularAttribute<GeSegmento, Short> estadoregistro;
    public static volatile ListAttribute<GeSegmento, GeInsumo> geInsumoList;
    public static volatile SingularAttribute<GeSegmento, String> usuariocreacion;
    public static volatile SingularAttribute<GeSegmento, String> nombre;
    public static volatile SingularAttribute<GeSegmento, String> usuariomodificacion;
    public static volatile SingularAttribute<GeSegmento, Date> fechacreacion;
    public static volatile SingularAttribute<GeSegmento, Date> fechamodificacion;
    public static volatile ListAttribute<GeSegmento, GeClase> geClaseList;
    public static volatile SingularAttribute<GeSegmento, BigDecimal> id;
    public static volatile SingularAttribute<GeSegmento, BigInteger> empresa;
    public static volatile ListAttribute<GeSegmento, GeProducto> geProductoList;
    public static volatile ListAttribute<GeSegmento, GeFamilia> geFamiliaList;

}