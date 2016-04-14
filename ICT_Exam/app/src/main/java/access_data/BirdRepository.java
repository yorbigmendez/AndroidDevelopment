package access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import class_structures.Bird;

/**
 * Created by Mendez Soto on 4/7/2016.
 */
public class BirdRepository implements IRepository<Bird> {
    DBConnection connexion;

    public BirdRepository(Context context){
        connexion = new DBConnection(context);
    }


    @Override
    public boolean Save(Bird bird) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("name",bird.getName());
                newData.put("size",bird.getSize());
                newData.put("foot_structure",bird.getFootStructure());
                newData.put("color",bird.getColor());
                db.insert("bird",null, newData);
                connexion.close();
                return false;
            }
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Update(Bird bird) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                ContentValues updateData = new ContentValues();
                updateData.put("color",bird.getColor());
                updateData.put("size",bird.getSize());
                updateData.put("foot_structure",bird.getFootStructure());
                db.update("bird", updateData, "name=?", new String[]{String.valueOf(bird.getName())});
                connexion.close();
                return false;//Sin errores
            }
        }catch(Exception error){
            Log.d("Error",error.getMessage());
        }
        return true;
    }

    @Override
    public boolean Delete(Bird bird) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                String[] args = new String[]{String.valueOf(bird.getId())};
                db.delete("bird","id=?",args);

                connexion.close();
                return false;//Sin errores
            }
        }catch(Exception error){
            Log.d("Error",error.getMessage());
        }
        return true;
    }

    @Override
    public ArrayList<Bird> GetAll() {
        ArrayList<Bird> birdList = new ArrayList<Bird>();
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db!=null){
                Cursor c = db.query("bird",new String[]{"id","name","color","size","foot_structure"},
                        null,null,null,null, "id desc",null);
                if(c.moveToFirst()){
                    do{
                        int id = c.getInt(0);
                        String name = c.getString(1);
                        String color = c.getString(2);
                        String size = c.getString(3);
                        String footStruct = c.getString(4);
                        Bird b = new Bird(id,name,color,size,footStruct);

                        birdList.add(b);
                    }while(c.moveToNext());
                }
                connexion.close();
                return birdList;
            }
        }catch (Exception error){
            Log.d("Error",error.getMessage());
        }
        return birdList;
    }

    @Override
    public ArrayList<Bird> GetBy(Bird bird) {
        ArrayList<Bird> birdList = new ArrayList<Bird>();
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(bird.getId())};
                Cursor c = db.query("bird",new String[]{"id","name","color","size","foot_structure"},
                        "id=?",args,null,null, "id desc",null);
                if(c.moveToFirst()){
                    do{
                        int id = c.getInt(0);
                        String name = c.getString(1);
                        String color = c.getString(2);
                        String size = c.getString(3);
                        String footStruct = c.getString(4);
                        Bird b = new Bird(id,name,color,size,footStruct);

                        birdList.add(b);
                    }while(c.moveToNext());
                }
                connexion.close();
                return birdList;
            }
        }catch (Exception error){
            Log.d("Error",error.getMessage());
        }
        return birdList;
    }
}
