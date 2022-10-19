package entities;

import entities.GeInsumo;
import entities.GeMarca;
import entities.IfApuMaestro;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(IfApuDetalle.class)
public class IfApuDetalle_ { 

    public static volatile SingularAttribute<IfApuDetalle, String> descripcion;
    public static volatile SingularAttribute<IfApuDetalle, Short> estadoregistro;
    public static volatile SingularAttribute<IfApuDetalle, String> usuariocreacion;
    public static volatile SingularAttribute<IfApuDetalle, IfApuMaestro> apumaestro;
    public static volatile SingularAttribute<IfApuDetalle, String> codigounspsc;
    public static volatile SingularAttribute<IfApuDetalle, String> codigointerno;
    public static volatile SingularAttribute<IfApuDetalle, String> usuariomodificacion;
    public static volatile SingularAttribute<IfApuDetalle, String> nombreinsumo;
    public static volatile SingularAttribute<IfApuDetalle, GeMarca> marca;
    public static volatile SingularAttribute<IfApuDetalle, BigDecimal> precio;
    public static volatile SingularAttribute<IfApuDetalle, BigDecimal> rendimiento;
    public static volatile SingularAttribute<IfApuDetalle, Date> fechacreacion;
    public static volatile SingularAttribute<IfApuDetalle, Date> fechamodificacion;
    public static volatile SingularAttribute<IfApuDetalle, GeInsumo> insumo;
    public static volatile SingularAttribute<IfApuDetalle, BigDecimal> id;
    public static volatile SingularAttribute<IfApuDetalle, Long> cantidad;
    public static volatile SingularAttribute<IfApuDetalle, BigInteger> empresa;

}