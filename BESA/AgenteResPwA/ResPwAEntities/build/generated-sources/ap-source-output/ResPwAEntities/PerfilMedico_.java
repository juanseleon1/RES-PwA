package ResPwAEntities;

import ResPwAEntities.Actividadrutinaria;
import ResPwAEntities.Causademencia;
import ResPwAEntities.Cdr;
import ResPwAEntities.Perfilpwa;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:34:42")
@StaticMetamodel(PerfilMedico.class)
public class PerfilMedico_ { 

    public static volatile SingularAttribute<PerfilMedico, BigInteger> periodovigilia;
    public static volatile SingularAttribute<PerfilMedico, Cdr> cdr;
    public static volatile SingularAttribute<PerfilMedico, String> perfilpwaCedula;
    public static volatile SingularAttribute<PerfilMedico, BigInteger> discapmotora;
    public static volatile ListAttribute<PerfilMedico, Actividadrutinaria> actividadrutinariaList;
    public static volatile SingularAttribute<PerfilMedico, BigInteger> discapvisual;
    public static volatile SingularAttribute<PerfilMedico, BigInteger> estadioenfermedad;
    public static volatile SingularAttribute<PerfilMedico, Perfilpwa> perfilpwa;
    public static volatile SingularAttribute<PerfilMedico, BigInteger> discapauditiva;
    public static volatile SingularAttribute<PerfilMedico, Causademencia> causademenciaCondicion;
    public static volatile SingularAttribute<PerfilMedico, BigInteger> tomamedicamentos;

}