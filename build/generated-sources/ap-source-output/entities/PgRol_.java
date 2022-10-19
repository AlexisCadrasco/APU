package entities;

import entities.PgRolMenu;
import entities.PgUsuarioRol;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PgRol.class)
public class PgRol_ { 

    public static volatile SingularAttribute<PgRol, String> descripcion;
    public static volatile SingularAttribute<PgRol, Short> estadoregistro;
    public static volatile SingularAttribute<PgRol, Date> fechacreacion;
    public static volatile SingularAttribute<PgRol, Date> fechamodificacion;
    public static volatile SingularAttribute<PgRol, String> usuariocreacion;
    public static volatile ListAttribute<PgRol, PgRolMenu> pgRolMenuList;
    public static volatile SingularAttribute<PgRol, BigDecimal> id;
    public static volatile SingularAttribute<PgRol, BigInteger> empresa;
    public static volatile SingularAttribute<PgRol, String> nombre;
    public static volatile SingularAttribute<PgRol, String> usuariomodificacion;
    public static volatile ListAttribute<PgRol, PgUsuarioRol> pgUsuarioRolList;

}