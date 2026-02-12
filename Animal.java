
// TODO: extend Entity
// TODO: make Animal abstract
public abstract class Animal extends Entity{
    protected int hunger;
    protected boolean isSick;
    // TODO: instance variables
    public Animal(String name, int x, int y) {
        super("Animal", 0, 0);
        this.hunger=0;
        this.isSick=false;
    }
    // TODO: add constructor

    public abstract void eat(Food food);
    public abstract void move(Zoo zoo);
    // TODO: add abstract method eat(Food food)
    // TODO: add abstract method move(Zoo zoo)
    
    // TODO: add non-abstact methods as necessary
}
