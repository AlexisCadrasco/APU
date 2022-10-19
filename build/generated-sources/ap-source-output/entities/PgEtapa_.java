package entities;

import entities.IfPresupuestoEnc;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PgEtapa.class)
public class PgEtapa_ { 

    public static volatile SingularAttribute<PgEtapa, String> descripcion;
    public static volatile SingularAttribute<PgEtapa, Short> estadoregistro;
    public static volatile SingularAttribute<PgEtapa, Date> fechacreacion;
    public static volatile SingularAttribute<PgEtapa, Date> fechamodificacion;
    public static volatile ListAttribute<PgEtapa, IfPresupuestoEnc> ifPresupuestoEncList;
    public static volatile SingularAttribute<PgEtapa, String> usuariocreacion;
    public static volatile SingularAttribute<PgEtapa, BigDecimal> id;
    public static volatile SingularAttribute<PgEtapa, BigInteger> empresa;
    public static volatile SingularAttribute<PgEtapa, String> nombre;
    public static volatile SingularAttribute<PgEtapa, String> usuariomodificacion;

}