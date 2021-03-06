package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet {

    //array list of objects, can change amount of elements
    ArrayList<Star> stars = new ArrayList<Star>();

    int startStar = -1;
    int endStar = -1;


    //draw grid and render stars to grid
    void drawGrid()
    {
        float border = 0.1f * width; // 10% of border
        textAlign(CENTER, CENTER); //alligns text around x and y coordinates
        //float drawable = width - (border * 2.0f);
        // /float gap = drawable / 10.0f; -devide by amount of boxes, so gap betwwen lines
        for(int i = -5 ; i <=5 ; i ++) //generate numbers from -5 to 5
        {
            //mapping range from 5 to -5, mapped onto screen coordinate border to left and to the right
            float x = map(i, -5, 5, border, width - border);
            float y = map(i, -5, 5, border, height - border);
            //float x = border + ((i + 5) * gap);
            //float y = border + ((i + 5) * gap);
            stroke(0, 0, 255);
            line(x, border,x, height - border);//horixontal line
            line(border, y, width - border, y); //vertical line
            fill(255);
            text(i, x, border / 2);
            text(i, border / 2, y); //draws grid
        }
    }

    //print all elements in array list
    void printStars()
    {
        for(Star s: stars) //enhanced forloop to iterate over list
        {
            println(s);
        }
    }

    //loading stars, load table object into array list and enstantuate star class
    void loadStars()
    {
        Table table = loadTable("HabHYG15ly.csv", "header"); //table object one of processing objects, header tells csv file has names headers
        for(TableRow row:table.rows()) //enhanced forloop, iterates over collection
        {
            Star s = new Star(row);  //println(row) would just print each row
            stars.add(s); //add star to arraylist
        }
    }

    public void settings() {
        size(800, 800);
    }

    float border;

    public void mouseClicked()
    {
        
        for(int i = 0 ; i < stars.size() ; i ++)
        {
            Star s = stars.get(i);
            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);
            if (dist(mouseX, mouseY, x, y ) < s.getAbsMag() / 2)
            {
                if (startStar == -1)
                {
                    startStar = i;
                    break;
                }
                else if (endStar == -1)
                {
                    endStar = i;
                    break;
                }
                else 
                {
                    startStar = i;
                    endStar = -1;
                }                
            }
        }
    }

    public void setup() {
        colorMode(RGB);
        loadStars();
        printStars();
        border = width * 0.1f;
    }

    public void drawStars()
    {
        for(Star s: stars)
        {
            s.render(this);
        }
    }

    public void draw() {
        background(0);
        drawGrid();
        drawStars();
        if (startStar != -1 && endStar == -1)
        {
            Star s = stars.get(startStar);
            stroke(255, 255, 0);
            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);
            line(x, y, mouseX, mouseY);
        }
        else if (startStar != -1 && endStar != -1)
        {
            Star s = stars.get(startStar);
            stroke(255, 255, 0);
            float x1 = map(s.getxG(), -5, 5, border, width - border);
            float y1 = map(s.getyG(), -5, 5, border, height - border);

            Star s1 = stars.get(endStar);            
            float x2 = map(s1.getxG(), -5, 5, border, width - border);
            float y2 = map(s1.getyG(), -5, 5, border, height - border);
            line(x1, y1, x2, y2);
            float dist = dist(s.getxG(), s.getyG(), s.getzG(), s1.getxG(), s1.getyG(), s1.getzG());
            stroke(255);
            textAlign(CENTER, CENTER);
            text("Distance between " + s.getDisplayName() + " and " + s1.getDisplayName() + " is " + dist + " parsecs", width /2 , height - (border / 2));
        }
    }
}