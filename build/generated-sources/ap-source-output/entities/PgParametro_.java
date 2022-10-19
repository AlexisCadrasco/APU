package entities;

import entities.PgParametroValor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PgParametro.class)
public class PgParametro_ { 

    public static volatile SingularAttribute<PgParametro, String> descripcion;
    public static volatile SingularAttribute<PgParametro, Short> estadoregistro;
    public static volatile SingularAttribute<PgParametro, Date> fechacreacion;
    public static volatile SingularAttribute<PgParametro, Date> fechamodificacion;
    public static volatile SingularAttribute<PgParametro, String> usuariocreacion;
    public static volatile ListAttribute<PgParametro, PgParametroValor> pgParametroValorList;
    public static volatile SingularAttribute<PgParametro, BigDecimal> id;
    public static volatile SingularAttribute<PgParametro, BigInteger> empresa;
    public static volatile SingularAttribute<PgParametro, String> nombre;
    public static volatile SingularAttribute<PgParametro, String> usuariomodificacion;

}