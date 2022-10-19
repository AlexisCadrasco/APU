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
@StaticMetamodel(GeMarca.class)
public class GeMarca_ { 

    public static volatile SingularAttribute<GeMarca, String> descripcion;
    public static volatile SingularAttribute<GeMarca, Short> estadoregistro;
    public static volatile SingularAttribute<GeMarca, Date> fechacreacion;
    public static volatile SingularAttribute<GeMarca, Date> fechamodificacion;
    public static volatile ListAttribute<GeMarca, GeInsumo> geInsumoList;
    public static volatile SingularAttribute<GeMarca, String> usuariocreacion;
    public static volatile SingularAttribute<GeMarca, BigDecimal> id;
    public static volatile SingularAttribute<GeMarca, BigInteger> empresa;
    public static volatile SingularAttribute<GeMarca, String> nombre;
    public static volatile SingularAttribute<GeMarca, String> usuariomodificacion;

}