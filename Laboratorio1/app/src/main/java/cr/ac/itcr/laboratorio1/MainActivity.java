package cr.ac.itcr.laboratorio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ListView lstViewNations;
    private ArrayList<Nations> listNations;
    NationsAdapter adaptNations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstViewNations = (ListView)findViewById(R.id.lvNations);

        listNations = new ArrayList<>();

        listNations.add(new Nations(R.drawable.egipto, "Egipto", "86.127.000"));
        listNations.add(new Nations(R.drawable.marruecos, "Marruecos", "33.202.300"));
        listNations.add(new Nations(R.drawable.costa_rica,"Costa Rica","4.667.096"));
        listNations.add(new Nations(R.drawable.jamaica,"Jamaica","2.711.476"));
        listNations.add(new Nations(R.drawable.panama,"Panamá","3.405.813"));
        listNations.add(new Nations(R.drawable.brasil,"Brasil","201.032.714"));
        listNations.add(new Nations(R.drawable.chile,"Chile","16.341.929"));
        listNations.add(new Nations(R.drawable.colombia, "Colombia", "47.506.000"));
        listNations.add(new Nations(R.drawable.peru, "Peru", "30.475.144"));
        listNations.add(new Nations(R.drawable.uruguay,"Uruguay","3.286.314"));
        listNations.add(new Nations(R.drawable.venezuela,"Venezuela","28.946.101"));
        listNations.add(new Nations(R.drawable.china,"China","1.363.350.000"));
        listNations.add(new Nations(R.drawable.india,"India","1.241.610.000"));
        listNations.add(new Nations(R.drawable.japon,"Japon","127.180.000"));
        listNations.add(new Nations(R.drawable.malasia,"Malasia","30.041.000"));
        listNations.add(new Nations(R.drawable.nepal,"Nepal","26.494.504"));
        listNations.add(new Nations(R.drawable.nepal,"España","46.609.700"));
        listNations.add(new Nations(R.drawable.nepal,"Francia","65.844.000"));
        listNations.add(new Nations(R.drawable.nepal,"Holanda","16.842.200"));
        listNations.add(new Nations(R.drawable.nepal,"Australia","23.414.552"));
        adaptNations = new NationsAdapter(this,R.layout.fragment_nation,listNations);

        lstViewNations.setAdapter(adaptNations);
    }
}
