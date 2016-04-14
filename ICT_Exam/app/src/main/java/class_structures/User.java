package class_structures;

/**
 * Created by Mendez Soto on 4/11/2016.
 */
public class User {
    private int id;
    private String name;
    private String pass;

    public User(String name, String pass){
        this.name = name;
        this.pass = pass;
    }

    public User(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
