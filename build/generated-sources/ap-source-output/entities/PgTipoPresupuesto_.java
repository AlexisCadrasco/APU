package entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PgTipoPresupuesto.class)
public class PgTipoPresupuesto_ { 

    public static volatile SingularAttribute<PgTipoPresupuesto, String> descripcion;
    public static volatile SingularAttribute<PgTipoPresupuesto, Short> estadoregistro;
    public static volatile SingularAttribute<PgTipoPresupuesto, Date> fechacreacion;
    public static volatile SingularAttribute<PgTipoPresupuesto, Date> fechamodificacion;
    public static volatile SingularAttribute<PgTipoPresupuesto, String> usuariocreacion;
    public static volatile SingularAttribute<PgTipoPresupuesto, BigDecimal> id;
    public static volatile SingularAttribute<PgTipoPresupuesto, BigInteger> empresa;
    public static volatile SingularAttribute<PgTipoPresupuesto, String> nombre;
    public static volatile SingularAttribute<PgTipoPresupuesto, String> usuariomodificacion;

}