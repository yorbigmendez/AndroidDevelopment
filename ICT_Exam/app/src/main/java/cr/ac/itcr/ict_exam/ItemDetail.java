package cr.ac.itcr.ict_exam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import access_data.BirdRepository;
import class_structures.Bird;

/**
 * Created by Mendez Soto on 4/11/2016.
 */
public class ItemDetail extends Activity {
    //Used to show the birds detail
    public static Bird b;
    //Used for the widgets
    private Button btnDelete;
    private TextView txtID;
    private TextView txtName;
    private TextView txtColor;
    private TextView txtSize;
    private TextView txtFootStructure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
        //Capture widgets
        btnDelete = (Button)findViewById(R.id.btnDelete);
        txtID = (TextView)findViewById(R.id.txt);
        txtName = (TextView)findViewById(R.id.txtName);
        txtColor = (TextView)findViewById(R.id.txtColor);
        txtSize = (TextView)findViewById(R.id.txtSize);
        txtFootStructure = (TextView)findViewById(R.id.txtFootStructure);
        //Fill the data
        txtID.setText(String.valueOf(b.getId()));
        txtName.setText(b.getName());
        txtColor.setText(b.getColor());
        txtSize.setText(b.getSize());
        txtFootStructure.setText(b.getFootStructure());

        //Delete button action Action
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BirdRepository br = new BirdRepository(getApplicationContext());
                br.Delete(b);
                Toast.makeText(ItemDetail.this, "Bird Successfully deleted from database, you must log off and log back in to refresh listview", Toast.LENGTH_LONG).show();
                onBackPressed();
                //You must log out to see the listView with the updated items
            }
        });
    }

}
