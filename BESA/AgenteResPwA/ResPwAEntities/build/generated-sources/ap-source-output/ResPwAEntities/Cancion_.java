package ResPwAEntities;

import ResPwAEntities.Genero;
import ResPwAEntities.PerfilPreferencia;
import ResPwAEntities.Tags;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:58:32")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:34:42")
>>>>>>> ac8eb44b9d2fabe4c4b72ab8c27dcb8fe067e199
@StaticMetamodel(Cancion.class)
public class Cancion_ { 

    public static volatile SingularAttribute<Cancion, Double> gusto;
    public static volatile SingularAttribute<Cancion, Genero> generoGenero;
    public static volatile ListAttribute<Cancion, Tags> tagsList;
    public static volatile SingularAttribute<Cancion, String> nombre;
    public static volatile ListAttribute<Cancion, PerfilPreferencia> perfilPreferenciaList;

}