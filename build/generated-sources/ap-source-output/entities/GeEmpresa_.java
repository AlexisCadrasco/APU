package entities;

import entities.PgMenu;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeEmpresa.class)
public class GeEmpresa_ { 

    public static volatile SingularAttribute<GeEmpresa, Short> estadoregistro;
    public static volatile SingularAttribute<GeEmpresa, String> paginaweb;
    public static volatile SingularAttribute<GeEmpresa, String> direccion;
    public static volatile SingularAttribute<GeEmpresa, String> usuariocreacion;
    public static volatile SingularAttribute<GeEmpresa, Short> digitoverificacion;
    public static volatile SingularAttribute<GeEmpresa, String> nombre;
    public static volatile SingularAttribute<GeEmpresa, String> usuariomodificacion;
    public static volatile SingularAttribute<GeEmpresa, Date> fechacreacion;
    public static volatile SingularAttribute<GeEmpresa, Date> fechamodificacion;
    public static volatile ListAttribute<GeEmpresa, PgMenu> pgMenuList;
    public static volatile SingularAttribute<GeEmpresa, Long> nit;
    public static volatile SingularAttribute<GeEmpresa, String> logo;
    public static volatile SingularAttribute<GeEmpresa, Long> id;
    public static volatile SingularAttribute<GeEmpresa, String> telefono;

}