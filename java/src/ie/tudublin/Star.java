package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Star {
    private boolean hab;
    private String displayName;
    private float distance;
    private float xG,yG,zG;
    private float absMag;

    //default contsructor - takes no parameters, any default values
    public Star()
    {
        
    }

    public void render(PApplet pa)
    {
        float border = pa.width * 0.1f;
        float x = PApplet.map(xG, -5, 5, border, pa.width - border);
        float y = PApplet.map(yG, -5, 5, border, pa.width - border);
        pa.stroke(255, 255, 0);
        pa.line(x - 5, y, x + 5, y);
        pa.line(x, y - 5, x, y + 5);
        pa.stroke(255, 0, 0);
        pa.noFill();
        pa.circle(x, y, absMag);
        pa.fill(255);
        pa.textAlign(PApplet.LEFT, PApplet.CENTER);
        pa.text(displayName, x + 10, y);
    }

    public Star(TableRow row) //takes single table row and creates star from it
    {
        // Constructor chaining - lots of diff contructors and dont want to duplicate them, so call some contructors from other contructor
        this(
            //returns column with name hab
            row.getInt("Hab?") == 1 ? true : false,//either 1 or empty, use inline if, 
            row.getString("Display Name"), //returns column as string
            row.getFloat("Distance"),
            row.getFloat("Xg"),
            row.getFloat("Yg"),
            row.getFloat("Zg"),
            row.getFloat("AbsMag")
        );
    }

    public Star(boolean hab, String displayName, float distance, float xG, float yG, float zG, float absMag)
    {
        //allows to disanbiguate so no two habs
        this.hab = hab;
        this.displayName = displayName;
        this.distance = distance;
        this.xG = xG;
        this.yG = yG;
        this.zG = zG;
        this.absMag = absMag;
    }
    
    public boolean isHab() {
        //allows get private fields
        return hab;
    }

    //accessor methods
    public void setHab(boolean hab) {
        //pass boolean hab, assigning private field from parameter
        this.hab = hab;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getxG() {
        return xG;
    }

    public void setxG(float xG) {
        this.xG = xG;
    }

    public float getyG() {
        return yG;
    }

    public void setyG(float yG) {
        this.yG = yG;
    }

    public float getzG() {
        return zG;
    }

    public void setzG(float zG) {
        this.zG = zG;
    }

    public float getAbsMag() {
        return absMag;
    }

    public void setAbsMag(float absMag) {
        this.absMag = absMag;
    }

    //method called automatically when object passed to something that expects a string
    @Override //indicates overrides methods in superclass
    public String toString() {
        return "Star [absMag=" + absMag + ", displayName=" + displayName + ", distance=" + distance + ", hab=" + hab
                + ", xG=" + xG + ", yG=" + yG + ", zG=" + zG + "]";
    }
}