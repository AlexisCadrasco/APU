package entities;

import entities.IfPresupuestoEnc;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeEntidad.class)
public class GeEntidad_ { 

    public static volatile SingularAttribute<GeEntidad, Short> estadoregistro;
    public static volatile SingularAttribute<GeEntidad, String> paginaweb;
    public static volatile SingularAttribute<GeEntidad, String> direccion;
    public static volatile SingularAttribute<GeEntidad, String> usuariocreacion;
    public static volatile SingularAttribute<GeEntidad, Short> digitoverificacion;
    public static volatile SingularAttribute<GeEntidad, String> nombre;
    public static volatile SingularAttribute<GeEntidad, String> usuariomodificacion;
    public static volatile SingularAttribute<GeEntidad, Date> fechacreacion;
    public static volatile SingularAttribute<GeEntidad, Date> fechamodificacion;
    public static volatile ListAttribute<GeEntidad, IfPresupuestoEnc> ifPresupuestoEncList;
    public static volatile SingularAttribute<GeEntidad, Long> nit;
    public static volatile SingularAttribute<GeEntidad, String> logo;
    public static volatile SingularAttribute<GeEntidad, Long> id;
    public static volatile SingularAttribute<GeEntidad, String> telefono;

}