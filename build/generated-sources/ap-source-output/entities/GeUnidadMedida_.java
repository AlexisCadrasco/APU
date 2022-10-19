package entities;

import entities.GeInsumo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeUnidadMedida.class)
public class GeUnidadMedida_ { 

    public static volatile SingularAttribute<GeUnidadMedida, String> descripcion;
    public static volatile SingularAttribute<GeUnidadMedida, String> codigo;
    public static volatile SingularAttribute<GeUnidadMedida, Short> estadoregistro;
    public static volatile SingularAttribute<GeUnidadMedida, Date> fechacreacion;
    public static volatile SingularAttribute<GeUnidadMedida, Date> fechamodificacion;
    public static volatile ListAttribute<GeUnidadMedida, GeInsumo> geInsumoList;
    public static volatile SingularAttribute<GeUnidadMedida, String> usuariocreacion;
    public static volatile SingularAttribute<GeUnidadMedida, BigDecimal> id;
    public static volatile SingularAttribute<GeUnidadMedida, BigInteger> empresa;
    public static volatile SingularAttribute<GeUnidadMedida, String> nombre;
    public static volatile SingularAttribute<GeUnidadMedida, String> usuariomodificacion;

}