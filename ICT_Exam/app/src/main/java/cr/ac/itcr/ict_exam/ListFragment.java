package cr.ac.itcr.ict_exam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import access_data.BirdRepository;
import access_data.IRepository;
import adapter.AdapterBird;
import class_structures.Bird;

/**
 * Created by Mendez Soto on 4/7/2016.
 */
public class ListFragment extends Fragment {
    private ListView listViewBirds;
    private static AdapterBird adapter;
    private IRepository<Bird> repository;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listViewBirds = (ListView) view.findViewById(R.id.listViewBirds);
        //Creates onItemClick action for the list
        listViewBirds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Gets that item and starts the activity
                ItemDetail.b = repository.GetAll().get(position);
                Intent i = new Intent(getContext().getApplicationContext(),ItemDetail.class);
                startActivity(i);
            }
        });
        //Saves the repository
        repository = new BirdRepository(getContext().getApplicationContext());
        //Instantiates the adapter
        adapter = new AdapterBird(getActivity().getApplicationContext(),repository.GetAll());
        //sets the adapter to the list
        listViewBirds.setAdapter(adapter);
        return view;

    }

}
