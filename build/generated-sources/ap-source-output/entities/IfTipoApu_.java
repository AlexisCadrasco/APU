package entities;

import entities.IfApuMaestro;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(IfTipoApu.class)
public class IfTipoApu_ { 

    public static volatile SingularAttribute<IfTipoApu, String> descripcion;
    public static volatile ListAttribute<IfTipoApu, IfApuMaestro> ifApuMaestroList;
    public static volatile SingularAttribute<IfTipoApu, Short> estadoregistro;
    public static volatile SingularAttribute<IfTipoApu, String> codigo;
    public static volatile SingularAttribute<IfTipoApu, Date> fechacreacion;
    public static volatile SingularAttribute<IfTipoApu, Date> fechamodificacion;
    public static volatile SingularAttribute<IfTipoApu, String> usuariocreacion;
    public static volatile SingularAttribute<IfTipoApu, Long> id;
    public static volatile SingularAttribute<IfTipoApu, BigInteger> empresa;
    public static volatile SingularAttribute<IfTipoApu, String> nombre;
    public static volatile SingularAttribute<IfTipoApu, String> usuariomodificacion;

}