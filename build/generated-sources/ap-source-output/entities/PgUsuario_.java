package entities;

import entities.PgRol;
import entities.PgUsuarioRol;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(PgUsuario.class)
public class PgUsuario_ { 

    public static volatile SingularAttribute<PgUsuario, String> apellidos;
    public static volatile SingularAttribute<PgUsuario, Short> estadoregistro;
    public static volatile SingularAttribute<PgUsuario, String> direccion;
    public static volatile SingularAttribute<PgUsuario, Long> numerodocumento;
    public static volatile SingularAttribute<PgUsuario, String> segundoApellido;
    public static volatile SingularAttribute<PgUsuario, String> usuariocreacion;
    public static volatile SingularAttribute<PgUsuario, String> usuariomodificacion;
    public static volatile SingularAttribute<PgUsuario, PgRol> rol;
    public static volatile ListAttribute<PgUsuario, PgUsuarioRol> pgUsuarioRolList;
    public static volatile SingularAttribute<PgUsuario, String> nombres;
    public static volatile SingularAttribute<PgUsuario, String> password;
    public static volatile SingularAttribute<PgUsuario, Date> fechacreacion;
    public static volatile SingularAttribute<PgUsuario, Date> fechamodificacion;
    public static volatile SingularAttribute<PgUsuario, String> usuario;
    public static volatile SingularAttribute<PgUsuario, Integer> id;
    public static volatile SingularAttribute<PgUsuario, String> telefono;
    public static volatile SingularAttribute<PgUsuario, BigInteger> empresa;
    public static volatile SingularAttribute<PgUsuario, String> correoElectronico;

}