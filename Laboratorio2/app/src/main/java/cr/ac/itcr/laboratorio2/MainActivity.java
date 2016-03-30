package cr.ac.itcr.laboratorio2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ListView listViewPerson;
    public static MyListAdapter myAdapter;
    public static ArrayList<Person> myList;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listViewPerson = (ListView)findViewById(R.id.listViewPersons);
        fillMyList();

        myAdapter = new MyListAdapter(this,R.layout.fragment_list,new ArrayList<Person>());
        listViewPerson.setAdapter(myAdapter);
        MyAsyncTask performBackgroundTask = new MyAsyncTask();
        // PerformBackgroundTask this class is the class that extends AsynchTask

        timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                int count=0;//manages the maount of time the task has ran
                @Override
                public void run() {
                    ++count;//Increment acount of times we ran the timer
                    if(count > 2)
                        timer.cancel();
                    new MyAsyncTask().execute();
                }

        }, 0, 5000);

        /*
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        for(int i =0; i<5<;i++)
                        {
                            new MyAsyncTask().execute();
                        }
                        ;
                    }
                });

            }
        }, 0, 10000);
        */

        /*
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        */

    }

    public void fillMyList(){
        myList = new ArrayList<Person>();
        myList.add(new Person("Yorbi Mendez","Soccer"));
        myList.add(new Person("Randy Otron","Wrestling"));
        myList.add(new Person("Anthony Rojas","Drinking"));
        myList.add(new Person("Gabriel Jimenez","Gaming"));
        myList.add(new Person("Oscar Duarte","Soccer"));
        myList.add(new Person("Bryan Ruiz","Soccer"));
        myList.add(new Person("Celso Borges","Soccer"));
        myList.add(new Person("Lionel Messi","Soccer"));
        myList.add(new Person("Benzema","Soccer"));
        myList.add(new Person("Katheryn Rodriguez","Cowgirl"));
        myList.add(new Person("Kathy Perez","Series"));
        myList.add(new Person("Alejandra Rodriguez","Soccer"));
        myList.add(new Person("Joel Campbell","Soccer"));
        myList.add(new Person("Keylor Navas","Soccer"));
        myList.add(new Person("Angeline Otarola","Babysitting"));
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

    @Override
    public void finish() {
        super.finish();
    }


}
