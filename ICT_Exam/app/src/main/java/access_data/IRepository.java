package access_data;

import java.util.ArrayList;

/**
 * Created by Mendez Soto on 4/7/2016.
 */
public interface IRepository<Object> {
    public boolean Save(Object object);

    public boolean Update(Object object);

    public boolean Delete(Object object);

    public ArrayList<Object> GetAll();

    public ArrayList<Object> GetBy(Object object);
}
