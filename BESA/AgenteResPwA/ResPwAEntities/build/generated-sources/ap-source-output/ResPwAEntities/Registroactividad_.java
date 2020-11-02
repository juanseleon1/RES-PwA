package ResPwAEntities;

import ResPwAEntities.Actividadpwa;
import ResPwAEntities.Perfilpwa;
import ResPwAEntities.RegistroactividadPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T17:03:29")
@StaticMetamodel(Registroactividad.class)
public class Registroactividad_ { 

    public static volatile SingularAttribute<Registroactividad, Perfilpwa> perfilpwaCedula;
    public static volatile SingularAttribute<Registroactividad, String> estadoinicial;
    public static volatile SingularAttribute<Registroactividad, String> estadofinal;
    public static volatile SingularAttribute<Registroactividad, RegistroactividadPK> registroactividadPK;
    public static volatile SingularAttribute<Registroactividad, Actividadpwa> actividadpwaId;

}