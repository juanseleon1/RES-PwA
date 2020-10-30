package ResPwAEntities;

import ResPwAEntities.PerfilMedico;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-29T18:35:21")
@StaticMetamodel(Cdr.class)
public class Cdr_ { 

    public static volatile SingularAttribute<Cdr, String> perfilMedicoCedula;
    public static volatile SingularAttribute<Cdr, BigInteger> memoria;
    public static volatile SingularAttribute<Cdr, BigInteger> vidaSocial;
    public static volatile SingularAttribute<Cdr, BigInteger> juicio;
    public static volatile SingularAttribute<Cdr, BigInteger> cuidadopersonal;
    public static volatile SingularAttribute<Cdr, BigInteger> orientacion;
    public static volatile SingularAttribute<Cdr, BigInteger> hogar;
    public static volatile SingularAttribute<Cdr, PerfilMedico> perfilMedico;

}