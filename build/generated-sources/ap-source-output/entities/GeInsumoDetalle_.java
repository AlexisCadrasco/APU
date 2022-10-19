package entities;

import entities.GeInsumo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeInsumoDetalle.class)
public class GeInsumoDetalle_ { 

    public static volatile SingularAttribute<GeInsumoDetalle, Short> estadoregistro;
    public static volatile SingularAttribute<GeInsumoDetalle, Date> fechacreacion;
    public static volatile SingularAttribute<GeInsumoDetalle, Date> fechamodificacion;
    public static volatile SingularAttribute<GeInsumoDetalle, String> usuariocreacion;
    public static volatile SingularAttribute<GeInsumoDetalle, GeInsumo> insumo;
    public static volatile SingularAttribute<GeInsumoDetalle, BigDecimal> id;
    public static volatile SingularAttribute<GeInsumoDetalle, BigInteger> empresa;
    public static volatile SingularAttribute<GeInsumoDetalle, String> usuariomodificacion;

}