package entities;

import entities.PgRol;
import entities.PgUsuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PgUsuarioRol.class)
public class PgUsuarioRol_ { 

    public static volatile SingularAttribute<PgUsuarioRol, String> descripcion;
    public static volatile SingularAttribute<PgUsuarioRol, Short> estadoregistro;
    public static volatile SingularAttribute<PgUsuarioRol, PgRol> idRol;
    public static volatile SingularAttribute<PgUsuarioRol, Date> fechacreacion;
    public static volatile SingularAttribute<PgUsuarioRol, Date> fechamodificacion;
    public static volatile SingularAttribute<PgUsuarioRol, PgUsuario> idUsuario;
    public static volatile SingularAttribute<PgUsuarioRol, String> usuariocreacion;
    public static volatile SingularAttribute<PgUsuarioRol, BigDecimal> id;
    public static volatile SingularAttribute<PgUsuarioRol, BigInteger> empresa;
    public static volatile SingularAttribute<PgUsuarioRol, String> usuariomodificacion;

}