package entities;

import entities.GeInsumo;
import entities.GeSubGrupo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(GeGrupo.class)
public class GeGrupo_ { 

    public static volatile SingularAttribute<GeGrupo, String> descripcion;
    public static volatile SingularAttribute<GeGrupo, Short> estadoregistro;
    public static volatile SingularAttribute<GeGrupo, Date> fechacreacion;
    public static volatile SingularAttribute<GeGrupo, Date> fechamodificacion;
    public static volatile ListAttribute<GeGrupo, GeSubGrupo> geSubGrupoList;
    public static volatile ListAttribute<GeGrupo, GeInsumo> geInsumoList;
    public static volatile SingularAttribute<GeGrupo, String> usuariocreacion;
    public static volatile SingularAttribute<GeGrupo, BigDecimal> id;
    public static volatile SingularAttribute<GeGrupo, BigInteger> empresa;
    public static volatile SingularAttribute<GeGrupo, String> nombre;
    public static volatile SingularAttribute<GeGrupo, String> usuariomodificacion;

}