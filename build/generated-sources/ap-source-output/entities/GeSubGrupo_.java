package entities;

import entities.GeGrupo;
import entities.GeInsumo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeSubGrupo.class)
public class GeSubGrupo_ { 

    public static volatile SingularAttribute<GeSubGrupo, String> descripcion;
    public static volatile SingularAttribute<GeSubGrupo, Short> estadoregistro;
    public static volatile SingularAttribute<GeSubGrupo, Date> fechacreacion;
    public static volatile SingularAttribute<GeSubGrupo, Date> fechamodificacion;
    public static volatile ListAttribute<GeSubGrupo, GeInsumo> geInsumoList;
    public static volatile SingularAttribute<GeSubGrupo, GeGrupo> grupo;
    public static volatile SingularAttribute<GeSubGrupo, String> usuariocreacion;
    public static volatile SingularAttribute<GeSubGrupo, BigDecimal> id;
    public static volatile SingularAttribute<GeSubGrupo, BigInteger> empresa;
    public static volatile SingularAttribute<GeSubGrupo, String> nombre;
    public static volatile SingularAttribute<GeSubGrupo, String> usuariomodificacion;

}