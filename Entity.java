
import java.awt.Graphics;

// TODO: add the abstract keyword to the class declaration
public abstract class Entity {
    // optional code to assign a sequencial entity ID
    private static int lastID = 0;
    protected int id;
    protected boolean alive=true;
    protected int x;
    protected int y;
    protected String name;
    protected int age;

    // TODO: add the Entity instance variables

    public Entity(String name, int x, int y) {
        // optional code to assign a sequencial entity ID
        this.id = lastID;
        lastID = lastID + 1;
        this.name=name;
        this.x=x;
        this.y=y;
        this.alive=alive;
        this.age=age;

        // TODO: complete the Entity constructor
    }

    // ABSTRACT METHODS
    // tick and draw are called by the Zoo class
    
    public abstract void tick(Zoo z);

    public abstract void draw(Graphics g);

    // TODO: add abstract method tick(Zoo z)
    // TODO: add abstract method draw(Graphics g)


    // NON-ABSTRACT METHODS
    // isAlive, getX, and getY are all called by the Zoo class

    public boolean isAlive() {
        // TODO: implement the isAlive method
        return true;
    }

    public int getX() {
        // TODO: implement the getX method
        return 0;
    }

    public int getY() {
        // implement the getY method
        return 0;
    }
}
