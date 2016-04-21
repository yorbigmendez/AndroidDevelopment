package cr.ac.itcr.gpstrackerapp;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TrackingActivity extends AppCompatActivity {
    private Button btnStartLocation;
    private static TextView txtLongitude;
    private static TextView txtLatitude;

    // GPSTracker class
    private GPSTracking gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tacking);
        btnStartLocation = (Button) findViewById(R.id.btnLocate);
        txtLatitude = (TextView)findViewById(R.id.txtLatitude);
        txtLongitude= (TextView) findViewById(R.id.txtLongitude);
        // show location button click event
        btnStartLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Instantiate class object
                gps = new GPSTracking(TrackingActivity.this);

                // check if GPS enabled
                if (gps.canGetLocation()) {
                    //Update the texts
                    txtLatitude.setText(String.valueOf(gps.getLatitude()));
                    txtLongitude.setText(String.valueOf(gps.getLongitude()));
                 }else {
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
    }

    //Method used to update the text on a gps location change
    public static void onChange(Location x){
        txtLongitude.setText(String.valueOf(x.getLongitude()));
        txtLatitude.setText(String.valueOf(x.getLatitude()));
    }

}
