package cr.ac.itcr.circlearea;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText txtResult;
    EditText txtRadius;
    Button btnSubmit;
    Resources res;
    ImageView imgCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();
        imgCircle = (ImageView)findViewById(R.id.imgCircle);

        imgCircle.setImageDrawable(res.getDrawable(R.drawable.circle_1));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtResult = (EditText)findViewById(R.id.txtCalculationResult);
        btnSubmit = (Button)findViewById(R.id.btnCalculate);
        txtRadius = (EditText)findViewById(R.id.txtRadius);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtRadius.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),res.getString(R.string.invalidRadius),Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        int radius = Integer.parseInt(txtRadius.getText().toString());
                        double result = 3.14 * Math.pow(radius,2);
                        txtResult.setText(String.valueOf(result));
                        Toast.makeText(getApplicationContext(),"Calculation Successfull",Toast.LENGTH_SHORT).show();
                    }catch(NumberFormatException e){
                        Toast.makeText(getApplicationContext(),res.getString(R.string.invalidRadius),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
