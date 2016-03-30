package cr.ac.itcr.laboratorio2;

import android.app.Dialog;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Mendez Soto on 3/17/2016.
 */
class MyAsyncTask extends AsyncTask<Void,Void,ArrayList<Person>> {


    @Override
    protected ArrayList<Person> doInBackground(Void... params) {
        // everything in here gets executed in a separate thread
        ArrayList<Person> temp;
        try {

            temp = (new ArrayList<Person>(MainActivity.myList.subList(0, 4)));
            MainActivity.myList.remove(0);
            MainActivity.myList.remove(0);
            MainActivity.myList.remove(0);
            MainActivity.myList.remove(0);
            MainActivity.myList.remove(0);
            return temp;
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }



    @Override
    protected void onPostExecute(ArrayList<Person> items) {
        // stop the loading animation or something
        MainActivity.myAdapter.addAll(items);
    }


}
