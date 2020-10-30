package ResPwAEntities;

import ResPwAEntities.Perfilpwa;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-29T18:35:21")
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