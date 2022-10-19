package entities;

import entities.GeEmpresa;
import entities.PgMenu;
import entities.PgRolMenu;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PgMenu.class)
public class PgMenu_ { 

    public static volatile SingularAttribute<PgMenu, String> descripcion;
    public static volatile SingularAttribute<PgMenu, String> ubicacion;
    public static volatile SingularAttribute<PgMenu, Long> estadoregistro;
    public static volatile SingularAttribute<PgMenu, String> imagen;
    public static volatile SingularAttribute<PgMenu, String> usuariocreacion;
    public static volatile ListAttribute<PgMenu, PgRolMenu> pgRolMenuList;
    public static volatile SingularAttribute<PgMenu, String> nombre;
    public static volatile SingularAttribute<PgMenu, String> usuariomodificacion;
    public static volatile SingularAttribute<PgMenu, PgMenu> padre;
    public static volatile SingularAttribute<PgMenu, Date> fechacreacion;
    public static volatile SingularAttribute<PgMenu, Date> fechamodificacion;
    public static volatile ListAttribute<PgMenu, PgMenu> pgMenuList;
    public static volatile SingularAttribute<PgMenu, Long> id;
    public static volatile SingularAttribute<PgMenu, BigInteger> orden;
    public static volatile SingularAttribute<PgMenu, GeEmpresa> empresa;

}