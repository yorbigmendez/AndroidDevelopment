package access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import class_structures.User;

/**
 * Created by Mendez Soto on 4/11/2016.
 */
public class UserRepository implements IRepository<User> {
    DBConnection connexion;

    public UserRepository(Context context){
        connexion = new DBConnection(context);
    }

    @Override
    public boolean Save(User user) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("username",user.getName());
                newData.put("password",user.getPass());
                db.insert("user",null, newData);
                connexion.close();
                return false;
            }
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Update(User user) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                ContentValues updateData = new ContentValues();
                updateData.put("name",user.getName());

                db.update("user", updateData, "id=?", new String[]{String.valueOf(user.getId())});
                connexion.close();
                return false;//Sin errores
            }
        }catch(Exception error){
            Log.d("Error",error.getMessage());
        }
        return true;
    }

    @Override
    public boolean Delete(User user) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                String[] args = new String[]{String.valueOf(user.getId())};
                db.delete("user","id=?",args);

                connexion.close();
                return false;//Sin errores
            }
        }catch(Exception error){
            Log.d("Error",error.getMessage());
        }
        return true;
    }

    @Override
    public ArrayList<User> GetAll() {
        ArrayList<User> listUser = new ArrayList<User>();
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db!=null){
                Cursor c = db.query("user",new String[]{"id","username","password"},
                        null,null,null,null, "id desc",null);
                if(c.moveToFirst()){
                    do{
                        int id = c.getInt(0);
                        String name =  c.getString(1);
                        String pass = c.getString(2);

                        User user1 = new User();
                        user1.setId(id);
                        user1.setName(name);
                        user1.setPass(pass);
                        listUser.add(user1);
                    }while(c.moveToNext());
                }
                connexion.close();
                return listUser;
            }
        }catch (Exception error){
            Log.d("Error",error.getMessage());
        }
        return listUser;
    }

    @Override
    public ArrayList<User> GetBy(User user) {
        ArrayList<User> listUser = new ArrayList<User>();
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(user.getName())};
                Cursor c = db.query("user",new String[]{"id","username","password"},
                        "username=?",args,null,null, null,null);
                if(c.moveToFirst()){
                    do{
                        int id = c.getInt(0);
                        String name = c.getString(1);
                        String pass = c.getString(2);

                        User tempUser= new User();
                        tempUser.setId(id);
                        tempUser.setName(name);
                        tempUser.setPass(pass);
                        listUser.add(tempUser);
                    }while(c.moveToNext());
                }
                connexion.close();
                return listUser;
            }
        }catch (Exception error){
            Log.d("Error",error.getMessage());
        }
        return listUser;
    }
}
