package entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PrEtiqueta.class)
public class PrEtiqueta_ { 

    public static volatile SingularAttribute<PrEtiqueta, Short> estadoregistro;
    public static volatile SingularAttribute<PrEtiqueta, Date> fechacreacion;
    public static volatile SingularAttribute<PrEtiqueta, Date> fechamodificacion;
    public static volatile SingularAttribute<PrEtiqueta, String> nombreetiqueta;
    public static volatile SingularAttribute<PrEtiqueta, String> usuariocreacion;
    public static volatile SingularAttribute<PrEtiqueta, BigDecimal> id;
    public static volatile SingularAttribute<PrEtiqueta, BigInteger> empresa;
    public static volatile SingularAttribute<PrEtiqueta, String> usuariomodificacion;

}