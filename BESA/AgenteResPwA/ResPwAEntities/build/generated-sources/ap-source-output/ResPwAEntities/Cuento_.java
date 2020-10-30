package ResPwAEntities;

import ResPwAEntities.Frases;
import ResPwAEntities.Genero;
import ResPwAEntities.PerfilPreferencia;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-29T18:35:21")
@StaticMetamodel(Cuento.class)
public class Cuento_ { 

    public static volatile SingularAttribute<Cuento, Double> gusto;
    public static volatile ListAttribute<Cuento, Frases> frasesList;
    public static volatile SingularAttribute<Cuento, String> nombreautor;
    public static volatile SingularAttribute<Cuento, Genero> nombregenero;
    public static volatile ListAttribute<Cuento, PerfilPreferencia> perfilPreferenciaList;
    public static volatile SingularAttribute<Cuento, String> nombrecuento;

}