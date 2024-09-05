package com.example.mobile_robo.moborobo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String M1_1 = "BCM17";
    private static final String M1_2 = "BCM27";
    private static final String M2_1 = "BCM5";
    private static final String M2_2 = "BCM6";

    private Gpio M11;
    private Gpio M12;
    private Gpio M21;
    private Gpio M22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PeripheralManager manager = PeripheralManager.getInstance();
        try {
            M11 = manager.openGpio(M1_1);
            M11.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            M12 = manager.openGpio(M1_2);
            M12.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            M21 = manager.openGpio(M2_1);
            M21.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            M22 = manager.openGpio(M2_2);
            M22.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
;        }catch (IOException e){
            Log.w(TAG,"Unable to access GPIO");
        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://svrmoborobo.firebaseio.com/");
        DatabaseReference ref = database.getReference("holdbutton");
        final DatabaseReference M11ref = ref.child("M11");
        final DatabaseReference M12ref = ref.child("M12");
        final DatabaseReference M21ref = ref.child("M21");
        final DatabaseReference M22ref = ref.child("M22");

        M11ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean on =(Boolean)dataSnapshot.getValue();
                if(on == true){
                    try {
                        M11.setValue(true);

                    }catch (IOException e){

                    }
                }else {
                    try {
                        M11.setValue(false);
                    }catch (IOException e){

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                try {
                    M11.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        M12ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean on = (Boolean)dataSnapshot.getValue();
                if (on == true){
                    try {
                        M12.setValue(true);
                    }catch (IOException e){

                    }
                }else {
                    try {
                        M12.setValue(false);
                    }catch (IOException e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                try {
                    M12.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        M21ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean on = (Boolean)dataSnapshot.getValue();
                if (on == true){
                    try {
                        M21.setValue(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        M21.setValue(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                try {
                    M21.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        M22ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean on = (Boolean)dataSnapshot.getValue();
                if (on==true){
                    try {
                        M22.setValue(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        M22.setValue(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                try {
                    M22.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void turnOnOff(boolean on){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
