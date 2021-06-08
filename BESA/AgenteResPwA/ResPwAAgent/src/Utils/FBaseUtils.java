/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class FBaseUtils {

    private static final String FBPATH = "respwafirebase.json";
    private static final String FBSTORAG = "https://firebasestorage.googleapis.com/v0/b/respwa.appspot.com/o/imagenes%2F[imgname].jpeg?alt=media";
    private static final String CUENTOS="/cuentos";

    public static void initResPwa(RobotAgentBelieves rab){

        try {
            FileInputStream serviceAccount = new FileInputStream(FBPATH);
            FirebaseOptions options =  FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://respwa.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException ex) {
            Logger.getLogger(FBaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(CUENTOS);
        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot snap :  dataSnapshot.getChildren())
                {
                    if(snap.exists())
                    {
                        String stg = snap.getValue( String.class );
                        rab.getImgCuentos().put(stg, new ArrayList());
                        for(DataSnapshot snap2 :  snap.getChildren())
                        {
                            String imgc=snap2.getValue(String.class);
                            rab.getImgCuentos().get(stg).add(imgc);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError de) {
            }

        });
    }

    public static void loadImg(RobotAgentBelieves rab) {
        try {
            FileInputStream serviceAccount = new FileInputStream(FBPATH);
            FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://respwa.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException ex) {
            Logger.getLogger(FBaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/"+rab.getbPerfilPwA().getPerfil().getCedula());
        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot snap :  dataSnapshot.getChildren())
                {
                    if(snap.exists())
                    {
                        Imagen imgc=snap.getValue(Imagen.class);
                        rab.getImgsPerfil().add(imgc);

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError de) {
            }

        });

    }
    public static String getStorageUrl(String name)
    {
        String s=new String();
       s= s.concat(FBSTORAG);
       s =s.replace("[imgname]", name);
        return s;
    }

}
