package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import class_structures.Bird;
import cr.ac.itcr.ict_exam.R;

/**
 * Created by Mendez Soto on 4/7/2016.
 */
public class AdapterBird extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    protected Activity activity;
    protected ArrayList<Bird> items;

    public AdapterBird (Context context, ArrayList<Bird> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int arg0)
    {
        return true;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Bird> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }


    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView txtName,txtID,txtColor,txtSize,txtFeetStructure;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.item_bird, parent, false);

        // Locate the TextViews in listview_item.xml
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtColor = (TextView) itemView.findViewById(R.id.txtColor);

        // Capture position and set to the TextViews
        txtName.setText(items.get(position).getName());
        txtColor.setText(items.get(position).getColor());
        //txtName.setText(items.get(position).getId());


        return itemView;
    }


}
