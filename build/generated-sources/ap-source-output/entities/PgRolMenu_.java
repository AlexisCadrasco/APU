package entities;

import entities.PgMenu;
import entities.PgRol;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PgRolMenu.class)
public class PgRolMenu_ { 

    public static volatile SingularAttribute<PgRolMenu, Short> estadoregistro;
    public static volatile SingularAttribute<PgRolMenu, PgRol> idRol;
    public static volatile SingularAttribute<PgRolMenu, Date> fechacreacion;
    public static volatile SingularAttribute<PgRolMenu, Date> fechamodificacion;
    public static volatile SingularAttribute<PgRolMenu, PgMenu> idMenu;
    public static volatile SingularAttribute<PgRolMenu, String> usuariocreacion;
    public static volatile SingularAttribute<PgRolMenu, BigDecimal> id;
    public static volatile SingularAttribute<PgRolMenu, BigInteger> empresa;
    public static volatile SingularAttribute<PgRolMenu, String> usuariomodificacion;

}