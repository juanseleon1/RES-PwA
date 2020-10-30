package ResPwAEntities;

import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-29T18:35:21")
@StaticMetamodel(Genero.class)
public class Genero_ { 

    public static volatile ListAttribute<Genero, Cuento> cuentoList;
    public static volatile SingularAttribute<Genero, Double> gusto;
    public static volatile ListAttribute<Genero, Cancion> cancionList;
    public static volatile SingularAttribute<Genero, String> nombregenero;

}