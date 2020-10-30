package ResPwAEntities;

import ResPwAEntities.Dificultad;
import ResPwAEntities.PerfilPreferencia;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-29T18:35:21")
@StaticMetamodel(Actividadpwa.class)
public class Actividadpwa_ { 

    public static volatile SingularAttribute<Actividadpwa, String> tipo;
    public static volatile SingularAttribute<Actividadpwa, Double> gusto;
    public static volatile SingularAttribute<Actividadpwa, Dificultad> dificultadDificultad;
    public static volatile SingularAttribute<Actividadpwa, BigInteger> duracion;
    public static volatile SingularAttribute<Actividadpwa, BigDecimal> id;
    public static volatile SingularAttribute<Actividadpwa, String> nombre;
    public static volatile SingularAttribute<Actividadpwa, BigInteger> enriquecimientofavorito;
    public static volatile ListAttribute<Actividadpwa, PerfilPreferencia> perfilPreferenciaList;

}