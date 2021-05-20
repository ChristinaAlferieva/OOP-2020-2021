package ie.tudublin;

import processing.core.PApplet;

public class Player extends GameObject {

    int health = 10;
    int ammo = 10;

    public Player(YASC yasc, float x, float y)
    {
        super(yasc, x, y, 0);
    }

    public void render() //translation to positition and rotate and turn 
    {
        yasc.pushMatrix();
        yasc.translate(x, y);
        yasc.text("Health: " + health, 50,  - 10);
        yasc.text("Ammo: " + ammo, 50, 10);
        yasc.rotate(rotation);
        // Write this!!
        yasc.line(- halfW, halfW, 0, - halfW);
        yasc.line(0, - halfW, halfW, halfW);
        yasc.line(halfW, halfW, 0, 0);
        yasc.line(0, 0, - halfW, halfW);
        yasc.popMatrix(); //player independent 
        yasc.textSize(14);        
    }

    void shoot()
    {
        if (yasc.checkKey(' '))
        {
            float dist = 30;
            
            Bullet b = new Bullet(yasc, x + (dx * dist), y + (dy * dist), rotation);
            
            yasc.bullets.add(b);
        }
    }

    public void update()
    {
        //unit circle - dx is sine of rotation and dy is cosine of rotation 
        dx = PApplet.sin(rotation);
        dy =  - PApplet.cos(rotation);
        
        if (yasc.checkKey(PApplet.UP)) //if keys pressed
        {
            x += dx;
            y += dy;
        }
        if (yasc.checkKey(PApplet.DOWN))
        {
            x -= dx;
            y -= dy;
        }
        if (yasc.checkKey(PApplet.LEFT))
        {
            rotation -= 0.1f;
        }
        if (yasc.checkKey(PApplet.RIGHT))
        {
            rotation += 0.1f;
        }  
        
        shoot();
    }

    
}