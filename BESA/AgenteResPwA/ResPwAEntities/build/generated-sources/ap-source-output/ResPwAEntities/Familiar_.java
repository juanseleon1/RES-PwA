package ResPwAEntities;

import ResPwAEntities.Perfilpwa;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:58:33")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:34:42")
>>>>>>> ac8eb44b9d2fabe4c4b72ab8c27dcb8fe067e199
@StaticMetamodel(Familiar.class)
public class Familiar_ { 

    public static volatile SingularAttribute<Familiar, BigInteger> estavivo;
    public static volatile ListAttribute<Familiar, Perfilpwa> perfilpwaList;
    public static volatile SingularAttribute<Familiar, String> parentesco;
    public static volatile SingularAttribute<Familiar, BigDecimal> id;
    public static volatile SingularAttribute<Familiar, String> nombre;
    public static volatile SingularAttribute<Familiar, Double> interes;
    public static volatile SingularAttribute<Familiar, Date> nacimiento;

}