package ResPwAEntities;

import ResPwAEntities.Genero;
import ResPwAEntities.PerfilPreferencia;
import ResPwAEntities.Tags;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-29T18:35:21")
@StaticMetamodel(Cancion.class)
public class Cancion_ { 

    public static volatile SingularAttribute<Cancion, Double> gusto;
    public static volatile ListAttribute<Cancion, Tags> tagsList;
    public static volatile SingularAttribute<Cancion, Genero> nombregenero;
    public static volatile SingularAttribute<Cancion, String> nombre;
    public static volatile ListAttribute<Cancion, PerfilPreferencia> perfilPreferenciaList;

}