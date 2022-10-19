package entities;

import entities.GeUnidadMedida;
import entities.IfApuDetalle;
import entities.IfTipoApu;
import entities.PgEtapa;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(IfApuMaestro.class)
public class IfApuMaestro_ { 

    public static volatile SingularAttribute<IfApuMaestro, String> descripcion;
    public static volatile SingularAttribute<IfApuMaestro, Short> estadoregistro;
    public static volatile SingularAttribute<IfApuMaestro, IfTipoApu> tipo;
    public static volatile SingularAttribute<IfApuMaestro, Date> fechaaprobacion;
    public static volatile SingularAttribute<IfApuMaestro, PgEtapa> etapa;
    public static volatile SingularAttribute<IfApuMaestro, String> usuariocreacion;
    public static volatile SingularAttribute<IfApuMaestro, String> usuarioaprobacion;
    public static volatile SingularAttribute<IfApuMaestro, String> nombre;
    public static volatile ListAttribute<IfApuMaestro, IfApuDetalle> ifApuDetalleList;
    public static volatile SingularAttribute<IfApuMaestro, String> usuariomodificacion;
    public static volatile SingularAttribute<IfApuMaestro, String> consecutivo;
    public static volatile SingularAttribute<IfApuMaestro, Date> fechacreacion;
    public static volatile SingularAttribute<IfApuMaestro, Date> fechamodificacion;
    public static volatile SingularAttribute<IfApuMaestro, BigDecimal> id;
    public static volatile SingularAttribute<IfApuMaestro, Long> cantidad;
    public static volatile SingularAttribute<IfApuMaestro, BigInteger> empresa;
    public static volatile SingularAttribute<IfApuMaestro, GeUnidadMedida> unidadmedida;

}