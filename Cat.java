import java.util.*;
import java.awt.*;

// TODO: extend Animal
public class Cat extends Animal{

    // TODO: add instance variables
    protected int lives;

    // TODO: add constructor
    public Cat(String name, int x, int y) {
        super(name, x, y);
        this.lives=9;
    }
    // TODO: override the tick method

    @Override
    public void tick(Zoo z) {
        age++;
        if (age>500){
            if (Zoo.percentChance(1.0)){
                lives--;
            }
            if (Zoo.percentChance(10.0) && isSick==true){
                lives--;
            }
        }
        if (Zoo.percentChance(0.1) && isSick==true){
            lives--;
        }
        if (lives<=0){
            alive=false;
            System.out.println("Cat "+name+" has died.");
        }
        this.move(z);
    }

    @Override
    public void draw(Graphics g) {
        // two optional examples of a way to draw a cat follow!

        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🐈", Zoo.wrap(x,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(y,Zoo.ZOO_ROWS)*Zoo.SCALE+25);

        //g.setColor(Color.DARK_GRAY);
        //g.setFont(new Font("Consolas", Font.PLAIN, 10));
        //g.drawString(" ^-^ ", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+5);
        //g.drawString("/. .\\", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+15);
        //g.drawString("\\_o_/", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+25);
    }

    // TODO: override the eat method
    @Override
    public void eat(Food food) {
        if (hunger > 25 && food.isAnimalProduct) {
            if (Zoo.percentChance(99.0)) {
                int before = hunger;
                food.beEaten(this);
                System.out.println("Cat " + name + " ate " + food.name + ", gaining " + food.nutritionValue + " nutrition! Hunger: " + before + " -> " + hunger);
            }
        }
    }

    public void changeHunger(int delta) {
        int before = hunger;
        hunger += delta;
        System.out.println("Cat " + name + " hunger changed: " + before + " -> " + hunger);
    }

    // TODO: override the move method
    @Override
    public void move(Zoo zoo) {
        // Only move every 10 ticks
        if (age % 10 != 0) return;
        // Move towards adjacent food (cheese/rats) if present
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            for (Entity e : zoo.at(nx, ny)) {
                if (e instanceof Food && e.isAlive()) {
                    // Move to food, eat it, then return
                    x = nx;
                    y = ny;
                    eat((Food) e);
                    return;
                }
            }
        }
        // Otherwise, try to move randomly, but avoid other animals
        int dir = Zoo.rand.nextInt(4);
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        boolean animalInCell = false;
        for (Entity e : zoo.at(nx, ny)) {
            if (e instanceof Animal) {
                animalInCell = true;
                break;
            }
        }
        if (animalInCell) {
            // Move in the opposite direction
            nx = x - dx[dir];
            ny = y - dy[dir];
        }
        x = nx;
        y = ny;
    }
}
