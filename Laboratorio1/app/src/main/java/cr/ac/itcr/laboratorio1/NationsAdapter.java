package cr.ac.itcr.laboratorio1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Mendez Soto on 3/9/2016.
 */
public class NationsAdapter extends ArrayAdapter<Nations> {
    private Context context;
    private int resource;
    private ArrayList<Nations> nations;

    public NationsAdapter(Context context, int resource, ArrayList<Nations> nations) {
        super(context, resource, nations);
        this.context = context;
        this.resource = resource;
        this.nations = nations;
    }

    @Override
    public View getView (int position,View convertView, ViewGroup parent) {
        ImageView img;
        TextView txtName;
        TextView txtPop;
        LayoutInflater inflater;
        View nationView;
        nationView = convertView;

        inflater =((Activity)context).getLayoutInflater();
        nationView = inflater.inflate(resource,parent,false);

        img = (ImageView)nationView.findViewById(R.id.imageView);
        txtName = (TextView)nationView.findViewById(R.id.txtViewNation);
        txtPop = (TextView)nationView.findViewById(R.id.txtViewPopulation);

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                nations.get(position).getIcon());

        Bitmap resized = Bitmap.createScaledBitmap(icon , 150, 125, true);

        txtName.setText(nations.get(position).getName());
        txtPop.setText(nations.get(position).getPop());
        img.setImageBitmap(resized);
        return nationView;
    }
}

