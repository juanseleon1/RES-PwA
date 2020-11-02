package ResPwAEntities;

import ResPwAEntities.Cuidador;
import ResPwAEntities.Estadocivil;
import ResPwAEntities.Familiar;
import ResPwAEntities.NivelEducativo;
import ResPwAEntities.PerfilMedico;
import ResPwAEntities.PerfilPreferencia;
import ResPwAEntities.Registroactividad;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:58:32")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:34:42")
>>>>>>> ac8eb44b9d2fabe4c4b72ab8c27dcb8fe067e199
@StaticMetamodel(Perfilpwa.class)
public class Perfilpwa_ { 

    public static volatile SingularAttribute<Perfilpwa, BigInteger> idrobot;
    public static volatile SingularAttribute<Perfilpwa, Cuidador> cuidadorNombreusuario;
    public static volatile SingularAttribute<Perfilpwa, String> cedula;
    public static volatile ListAttribute<Perfilpwa, Familiar> familiarList;
    public static volatile SingularAttribute<Perfilpwa, String> paisnacimiento;
    public static volatile SingularAttribute<Perfilpwa, String> nombre;
    public static volatile SingularAttribute<Perfilpwa, BigInteger> edad;
    public static volatile SingularAttribute<Perfilpwa, NivelEducativo> nivelEducativoTipone;
    public static volatile ListAttribute<Perfilpwa, Registroactividad> registroactividadList;
    public static volatile SingularAttribute<Perfilpwa, PerfilPreferencia> perfilPreferencia;
    public static volatile SingularAttribute<Perfilpwa, Date> fechanacimiento;
    public static volatile SingularAttribute<Perfilpwa, String> apellido;
    public static volatile SingularAttribute<Perfilpwa, String> profesion;
    public static volatile SingularAttribute<Perfilpwa, Estadocivil> estadocivilTipoec;
    public static volatile SingularAttribute<Perfilpwa, PerfilMedico> perfilMedico;

}