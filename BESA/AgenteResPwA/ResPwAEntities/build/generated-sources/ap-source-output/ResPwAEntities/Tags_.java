package ResPwAEntities;

import ResPwAEntities.Cancion;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T16:34:42")
@StaticMetamodel(Tags.class)
public class Tags_ { 

    public static volatile ListAttribute<Tags, Cancion> cancionList;
    public static volatile SingularAttribute<Tags, BigDecimal> id;
    public static volatile SingularAttribute<Tags, String> tag;

}