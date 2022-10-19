package entities;

import entities.GeEntidad;
import entities.IfPresupuestoDet;
import entities.PgEtapa;
import entities.PgTipoPresupuesto;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T18:51:22")
@StaticMetamodel(IfPresupuestoEnc.class)
public class IfPresupuestoEnc_ { 

    public static volatile SingularAttribute<IfPresupuestoEnc, String> descripcion;
    public static volatile SingularAttribute<IfPresupuestoEnc, PgTipoPresupuesto> tipopresupuesto;
    public static volatile SingularAttribute<IfPresupuestoEnc, Short> estadoregistro;
    public static volatile SingularAttribute<IfPresupuestoEnc, PgEtapa> etapa;
    public static volatile SingularAttribute<IfPresupuestoEnc, BigDecimal> valor;
    public static volatile SingularAttribute<IfPresupuestoEnc, String> usuariocreacion;
    public static volatile SingularAttribute<IfPresupuestoEnc, Long> imprevisto;
    public static volatile SingularAttribute<IfPresupuestoEnc, Long> utilidad;
    public static volatile SingularAttribute<IfPresupuestoEnc, BigDecimal> posicionx;
    public static volatile SingularAttribute<IfPresupuestoEnc, Long> cdp;
    public static volatile SingularAttribute<IfPresupuestoEnc, String> usuariomodificacion;
    public static volatile SingularAttribute<IfPresupuestoEnc, BigDecimal> posiciony;
    public static volatile SingularAttribute<IfPresupuestoEnc, Date> fechacreacion;
    public static volatile SingularAttribute<IfPresupuestoEnc, Date> fechamodificacion;
    public static volatile SingularAttribute<IfPresupuestoEnc, BigDecimal> multiplicador;
    public static volatile SingularAttribute<IfPresupuestoEnc, GeEntidad> entidad;
    public static volatile ListAttribute<IfPresupuestoEnc, IfPresupuestoDet> ifPresupuestoDetList;
    public static volatile SingularAttribute<IfPresupuestoEnc, BigDecimal> id;
    public static volatile SingularAttribute<IfPresupuestoEnc, BigInteger> empresa;
    public static volatile SingularAttribute<IfPresupuestoEnc, Long> administracion;

}