package cr.ac.itcr.laboratorio1;

/**
 * Created by Mendez Soto on 3/9/2016.
 */
public class Nations {
    private int icon;
    private String name;
    private String pop;

    public Nations(int ic, String n, String p){
        this.icon = ic;
        this.name = n;
        this.pop = p;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

}
