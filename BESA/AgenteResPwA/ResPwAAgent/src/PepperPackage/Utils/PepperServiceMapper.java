/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.Utils;

import Adapter.ServiceMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author juans
 */
public class PepperServiceMapper extends ServiceMapper<String>{

    private static final String CONFIG_FILE="ResPwAServMap.txt";
    public PepperServiceMapper(){
        super();
    }
    @Override
    protected void uploadServiceRelation() {
        try {
            try (Scanner myReader = new Scanner(new File(CONFIG_FILE))) {
                String data=null;
                while (myReader.hasNextLine()) {
                    String helper[];
                    data = myReader.nextLine();
                    helper= data.split(",");
//                    System.out.println("D: "+data);
                    if(keyvals.contains(helper[0]))
                      mapper.put(helper[0], helper[1]);
                }
            }
//            System.out.println("ServiceMapperLoaded");
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
    }
    
    @Override
    public String getServiceTranslation(String object) {
        return object;
    }
    


    
}
