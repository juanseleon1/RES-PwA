package rational.mapping;

import rational.data.InfoData;

public interface Believes {
    
    public boolean update(InfoData si);
    public Believes clone() throws Exception ;

}
