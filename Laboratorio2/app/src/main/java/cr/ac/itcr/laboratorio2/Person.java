package cr.ac.itcr.laboratorio2;

/**
 * Created by Mendez Soto on 3/29/2016.
 */
public class Person {
    private String name;
    private String hobby;

    public Person(String pName, String pHobby){
        this.name = pName;
        this.hobby = pHobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
