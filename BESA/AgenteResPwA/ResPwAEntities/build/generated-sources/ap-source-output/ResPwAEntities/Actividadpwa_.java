package ResPwAEntities;

import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.Registroactividad;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:58:33")
@StaticMetamodel(Actividadpwa.class)
public class Actividadpwa_ { 

    public static volatile SingularAttribute<Actividadpwa, String> tipo;
    public static volatile ListAttribute<Actividadpwa, Registroactividad> registroactividadList;
    public static volatile ListAttribute<Actividadpwa, Actxpreferencia> actxpreferenciaList;
    public static volatile SingularAttribute<Actividadpwa, BigInteger> duracion;
    public static volatile SingularAttribute<Actividadpwa, BigInteger> id;
    public static volatile SingularAttribute<Actividadpwa, String> nombre;

}