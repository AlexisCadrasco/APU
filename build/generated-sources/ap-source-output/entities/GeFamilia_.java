package entities;

import entities.GeClase;
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
@StaticMetamodel(GeFamilia.class)
public class GeFamilia_ { 

    public static volatile SingularAttribute<GeFamilia, String> descripcion;
    public static volatile SingularAttribute<GeFamilia, String> codigo;
    public static volatile SingularAttribute<GeFamilia, Short> estadoregistro;
    public static volatile ListAttribute<GeFamilia, GeInsumo> geInsumoList;
    public static volatile SingularAttribute<GeFamilia, String> usuariocreacion;
    public static volatile SingularAttribute<GeFamilia, String> nombre;
    public static volatile SingularAttribute<GeFamilia, String> usuariomodificacion;
    public static volatile SingularAttribute<GeFamilia, GeSegmento> segmento;
    public static volatile SingularAttribute<GeFamilia, Date> fechacreacion;
    public static volatile SingularAttribute<GeFamilia, Date> fechamodificacion;
    public static volatile ListAttribute<GeFamilia, GeClase> geClaseList;
    public static volatile SingularAttribute<GeFamilia, BigDecimal> id;
    public static volatile SingularAttribute<GeFamilia, BigInteger> empresa;
    public static volatile ListAttribute<GeFamilia, GeProducto> geProductoList;

}