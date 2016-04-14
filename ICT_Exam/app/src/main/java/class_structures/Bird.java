package class_structures;

/**
 * Created by Mendez Soto on 4/7/2016.
 */
public class Bird {
    private int id;
    private String name;
    private String color;
    private String size;
    private String footStructure;

    public Bird(){}

    public Bird(int id,String name, String color, String size, String fStructure){
        this.id=id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.footStructure = fStructure;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFootStructure() {
        return footStructure;
    }

    public void setFootStructure(String footStructure) {
        this.footStructure = footStructure;
    }
}
