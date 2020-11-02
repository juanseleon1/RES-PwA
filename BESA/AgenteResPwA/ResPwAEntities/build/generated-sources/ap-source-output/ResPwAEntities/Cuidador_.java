package ResPwAEntities;

import ResPwAEntities.Perfilpwa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:58:32")
@StaticMetamodel(Cuidador.class)
public class Cuidador_ { 

    public static volatile SingularAttribute<Cuidador, String> nombreusuario;
    public static volatile ListAttribute<Cuidador, Perfilpwa> perfilpwaList;
    public static volatile SingularAttribute<Cuidador, String> correo;
    public static volatile SingularAttribute<Cuidador, String> celular;
    public static volatile SingularAttribute<Cuidador, String> nombre;
    public static volatile SingularAttribute<Cuidador, String> contraseña;

}