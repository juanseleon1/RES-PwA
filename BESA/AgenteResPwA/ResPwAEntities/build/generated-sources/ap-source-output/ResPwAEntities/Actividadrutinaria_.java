package ResPwAEntities;

import ResPwAEntities.PerfilMedico;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:34:42")
@StaticMetamodel(Actividadrutinaria.class)
public class Actividadrutinaria_ { 

    public static volatile SingularAttribute<Actividadrutinaria, Date> hora;
    public static volatile SingularAttribute<Actividadrutinaria, BigInteger> duracion;
    public static volatile SingularAttribute<Actividadrutinaria, BigDecimal> id;
    public static volatile SingularAttribute<Actividadrutinaria, String> nombre;
    public static volatile SingularAttribute<Actividadrutinaria, PerfilMedico> perfilMedicoPerfilpwaCedula;

}