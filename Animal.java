// TODO: extend Entity
// TODO: make Animal abstract

import java.util.ArrayList;

public abstract class Animal extends Entity{
    protected int hunger;
    protected boolean isSick;
    // TODO: instance variables
    public Animal(String name, int x, int y) {
        super("Animal", x, y);
        this.hunger=0;
        this.isSick=false;
    }
    // TODO: add constructor

    public abstract void eat(Food food);
    public abstract void move(Zoo zoo);
    // TODO: add abstract method eat(Food food)
    // TODO: add abstract method move(Zoo zoo)
    
    // TODO: add non-abstact methods as necessary

    public ArrayList<Entity> neighbor(Zoo zoo) {
        ArrayList<Entity> neighbors = new ArrayList<Entity>();
        for (int i=y-1; i<y+2; i++) {
            for (int j=x-1; j<x+2; j++) {
                if (i>=0 && i < zoo.getHeight()||j>=0&&j<zoo.getWidth()) {
                    neighbors.addAll(zoo.at(j,i));
                }
            }
        }
        return neighbors;
    }
}
