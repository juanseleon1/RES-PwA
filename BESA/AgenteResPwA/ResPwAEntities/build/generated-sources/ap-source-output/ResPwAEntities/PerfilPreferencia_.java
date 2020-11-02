package ResPwAEntities;

import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Perfilpwa;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:34:42")
@StaticMetamodel(PerfilPreferencia.class)
public class PerfilPreferencia_ { 

    public static volatile ListAttribute<PerfilPreferencia, Cuento> cuentoList;
    public static volatile SingularAttribute<PerfilPreferencia, String> perfilpwaCedula;
    public static volatile SingularAttribute<PerfilPreferencia, Double> gustokaraoke;
    public static volatile ListAttribute<PerfilPreferencia, Actxpreferencia> actxpreferenciaList;
    public static volatile SingularAttribute<PerfilPreferencia, String> nombrepreferido;
    public static volatile SingularAttribute<PerfilPreferencia, BigInteger> volpreferido;
    public static volatile ListAttribute<PerfilPreferencia, Cancion> cancionList;
    public static volatile SingularAttribute<PerfilPreferencia, Perfilpwa> perfilpwa;
    public static volatile SingularAttribute<PerfilPreferencia, Double> gustomusica;
    public static volatile SingularAttribute<PerfilPreferencia, Double> gustobaile;

}