package entities;

import entities.PgParametro;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PgParametroValor.class)
public class PgParametroValor_ { 

    public static volatile SingularAttribute<PgParametroValor, Short> estadoregistro;
    public static volatile SingularAttribute<PgParametroValor, Date> fechacreacion;
    public static volatile SingularAttribute<PgParametroValor, Date> fechamodificacion;
    public static volatile SingularAttribute<PgParametroValor, PgParametro> parametro;
    public static volatile SingularAttribute<PgParametroValor, String> valor;
    public static volatile SingularAttribute<PgParametroValor, String> usuariocreacion;
    public static volatile SingularAttribute<PgParametroValor, BigDecimal> id;
    public static volatile SingularAttribute<PgParametroValor, BigInteger> empresa;
    public static volatile SingularAttribute<PgParametroValor, String> usuariomodificacion;

}