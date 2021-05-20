package ie.tudublin;

// abstract means the class cannot be instantiated
public abstract class GameObject {
    float x, y;
    float dx, dy;
    float rotation = 0;
    float speed = 5;
    YASC yasc; //so we can draw object
    float w = 50;
    float halfW = w / 2;

    public GameObject(YASC yasc, float x, float y, float rotation)
    {
        this.yasc = yasc;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

//acessor methods
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    // subclasses must implement these abstract methods, otherwise they will be abstract
    public abstract void render();

    public abstract void update();
    //abstract classes is class that can only have subclasses and must be implemented in subclass or become
    //abstarct class
    
}
