package entities;

import entities.GeInsumo;
import entities.IfApuMaestro;
import entities.IfPresupuestoEnc;
import entities.PrEtiqueta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(IfPresupuestoDet.class)
public class IfPresupuestoDet_ { 

    public static volatile SingularAttribute<IfPresupuestoDet, Short> estadoregistro;
    public static volatile SingularAttribute<IfPresupuestoDet, String> usuariocreacion;
    public static volatile SingularAttribute<IfPresupuestoDet, IfApuMaestro> apumaestro;
    public static volatile SingularAttribute<IfPresupuestoDet, String> usuariomodificacion;
    public static volatile SingularAttribute<IfPresupuestoDet, BigInteger> consecutivo;
    public static volatile SingularAttribute<IfPresupuestoDet, PrEtiqueta> etiqueta;
    public static volatile SingularAttribute<IfPresupuestoDet, IfPresupuestoEnc> presupuesto;
    public static volatile SingularAttribute<IfPresupuestoDet, Date> fechacreacion;
    public static volatile SingularAttribute<IfPresupuestoDet, Date> fechamodificacion;
    public static volatile SingularAttribute<IfPresupuestoDet, String> capitulo;
    public static volatile SingularAttribute<IfPresupuestoDet, GeInsumo> insumo;
    public static volatile SingularAttribute<IfPresupuestoDet, BigDecimal> id;
    public static volatile SingularAttribute<IfPresupuestoDet, BigDecimal> cantidad;
    public static volatile SingularAttribute<IfPresupuestoDet, BigInteger> empresa;

}