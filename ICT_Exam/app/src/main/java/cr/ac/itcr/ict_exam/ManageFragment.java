package cr.ac.itcr.ict_exam;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import access_data.BirdRepository;
import class_structures.Bird;

/**
 * Created by Mendez Soto on 4/7/2016.
 */
public class ManageFragment extends Fragment {
    //Variables for use of widgets
    private EditText txtName;
    private Spinner spinColor;
    private Spinner spinFoot;
    private Spinner spinSize;
    private Button add;
    private Button update;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = (View)inflater.inflate(R.layout.fragment_manage,container,false);
        txtName = (EditText)view.findViewById(R.id.txtName);
        spinColor = (Spinner)view.findViewById(R.id.spinColor);
        spinFoot = (Spinner)view.findViewById(R.id.spinFootStructure);
        spinSize = (Spinner)view.findViewById(R.id.spinSize);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.size, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinSize.setAdapter(sizeAdapter);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Color, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinColor.setAdapter(colorAdapter);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> footAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.foot_structure, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        footAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinFoot.setAdapter(footAdapter);

        add = (Button)view.findViewById(R.id.btnAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClick(v);
            }
        });
        update = (Button)view.findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClick(v);
            }
        });

        return view;
    }

    //Add bird button action
    public void onAddClick(View v) {
        //Check for empty spaces
        if(txtName.getText().toString().equals("Bird Name") || txtName.getText().toString().equals("")){
            new AlertDialog.Builder(getContext()).setTitle("Empty Name").setMessage("Bird name is empty").setIcon(android.R.drawable.ic_dialog_alert).show();
        }else{
            BirdRepository br = new BirdRepository(getContext().getApplicationContext());
            Bird b = captureData();
            br.Save(b);
            //You must log out to see the listView with the updated items
            //Problem issue with ViewPager
            new AlertDialog.Builder(getContext()).setTitle("Success").setMessage("Bird has been created, you must log out and log back in to " +
                    "refresh the listView").setIcon(android.R.drawable.ic_dialog_alert).show();
        }
    }

    //You must log out to see the listView with the updated items
    //On Update action click
    public void onUpdateClick(View v) {
        BirdRepository br = new BirdRepository(getContext().getApplicationContext());
        Bird b = captureData();//Get the data
        //Invokes the update message from database
        br.Update(b);//You must log out to see the listView with the updated items
    }

    //Used to capture the data,get all the text and return a bird object
    public Bird captureData(){
        String name = txtName.getText().toString();
        String color = spinColor.getSelectedItem().toString();
        String size = spinSize.getSelectedItem().toString();
        String footStructure = spinFoot.getSelectedItem().toString();
        Bird b = new Bird();
        b.setName(name);
        b.setColor(color);
        b.setSize(size);
        b.setFootStructure(footStructure);
        return b;
    }

}
