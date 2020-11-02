package ResPwAEntities;

import ResPwAEntities.Cuento;
import ResPwAEntities.Enriq;
import ResPwAEntities.FrasesPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:34:42")
@StaticMetamodel(Frases.class)
public class Frases_ { 

    public static volatile SingularAttribute<Frases, String> contenido;
    public static volatile SingularAttribute<Frases, Cuento> cuento;
    public static volatile SingularAttribute<Frases, FrasesPK> frasesPK;
    public static volatile ListAttribute<Frases, Enriq> enriqList;

}