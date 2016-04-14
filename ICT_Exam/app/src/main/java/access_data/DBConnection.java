package access_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mendez Soto on 4/7/2016.
 */
public class DBConnection extends SQLiteOpenHelper {
    private static final int VERSION_BOD = 1;
    private static final String NAME_BOD = "db_birds";


    public DBConnection(Context context){
        super(context,NAME_BOD,null,VERSION_BOD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();
            //Create
            String createTables = "CREATE TABLE IF NOT EXISTS bird (id INTEGER PRIMARY KEY AUTOINCREMENT, name CHAR(200), " +
                    "color CHAR(200), size CHAR(200), foot_structure CHAR(200))";
            String createUser = "CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, username CHAR(200)," +
                    "password CHAR(200));";
            sql.append(createTables);
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(createUser);
            db.execSQL(sql.toString());
        }catch(Exception error){
            Log.d("Error", error.getMessage().toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sql = new StringBuilder();

        for(int indiceVersion = oldVersion; indiceVersion < newVersion; indiceVersion++){
            int nextVersion = indiceVersion +1;
            switch (nextVersion){
                case 1:
                    String sqlDropPlace = "DROP TABLE IF EXISTS bird";
                    sql.append(sqlDropPlace);
                    break;
                case 2:
                    String sqlCreateUser= "CREATE TABLE IF NOT EXISTS user"
                            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,username CHAR(200),password CHAR(200))";
                    sql.append(sqlCreateUser);
                    break;
                case 3:
                    String sqlCreateAdvisor = "DROP TABLE IF NOT EXISTS advisor"
                            +"(id INTEGER PRIMAR KEY AUTOINCREMENT, name CHAR(100))";
                    sql.append(sqlCreateAdvisor);
                    break;
            }
        }
    }
}
