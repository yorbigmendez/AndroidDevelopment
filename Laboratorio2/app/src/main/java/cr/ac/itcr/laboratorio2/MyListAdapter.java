package cr.ac.itcr.laboratorio2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mendez Soto on 3/29/2016.
 */
public class MyListAdapter extends ArrayAdapter<Person>{
    private Context context;
    private int resource;
    private ArrayList<Person> personLIst;

    public MyListAdapter(Context context, int resource, ArrayList<Person> pList) {
        super(context, resource, pList);
        this.context = context;
        this.resource = resource;
        this.personLIst = pList;
    }

    @Override
    public View getView (int position,View convertView, ViewGroup parent) {
        ImageView img;
        TextView txtName;
        TextView txtPop;
        LayoutInflater inflater;
        View viewItem;
        viewItem = convertView;

        inflater =((Activity)context).getLayoutInflater();
        viewItem = inflater.inflate(resource,parent,false);

        txtName = (TextView)viewItem.findViewById(R.id.txtName);
        txtPop = (TextView)viewItem.findViewById(R.id.txtHobby);

        txtName.setText(personLIst.get(position).getName());
        txtPop.setText(personLIst.get(position).getHobby());

        return viewItem;
    }
}
