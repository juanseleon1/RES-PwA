package ResPwAEntities;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-02T17:03:29")
@StaticMetamodel(Books.class)
public class Books_ { 

    public static volatile SingularAttribute<Books, String> authorLastName;
    public static volatile SingularAttribute<Books, String> authorFirstName;
    public static volatile SingularAttribute<Books, BigInteger> rating;
    public static volatile SingularAttribute<Books, String> title;
    public static volatile SingularAttribute<Books, String> bookId;

}