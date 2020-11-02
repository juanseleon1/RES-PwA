package ResPwAEntities;

import ResPwAEntities.Frases;
import ResPwAEntities.Genero;
import ResPwAEntities.PerfilPreferencia;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:58:33")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:34:42")
>>>>>>> ac8eb44b9d2fabe4c4b72ab8c27dcb8fe067e199
@StaticMetamodel(Cuento.class)
public class Cuento_ { 

    public static volatile SingularAttribute<Cuento, Double> gusto;
    public static volatile SingularAttribute<Cuento, Genero> generoGenero;
    public static volatile ListAttribute<Cuento, Frases> frasesList;
    public static volatile SingularAttribute<Cuento, String> nombre;
    public static volatile ListAttribute<Cuento, PerfilPreferencia> perfilPreferenciaList;
    public static volatile SingularAttribute<Cuento, String> autor;

}