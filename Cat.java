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
                food.beEaten(this);
                System.out.println("Cat " + name + " ate " + food.name + ", gaining " + food.nutritionValue + " nutrition!");
            }
        }
    }
    // TODO: override the move method
    @Override
    public void move(Zoo zoo) {
        ArrayList<Entity> neighbors = neighbor(zoo);
        int randomNumX = (int) (Math.random() * 3) - 1;
        int randomNumY = (int) (Math.random() * 3) - 1;
        double directionx = 0.0;
        double directiony = 0.0;
        boolean animalInCell = false;
        if (age % 10 == 0) {
            directionx += randomNumX;
            directiony += randomNumY;
            if (x == 0) directiony++;
            if (y == 0) directionx++;
            if (x == 800) directiony--;
            if (y == 600) directionx--;

            for (Entity n : neighbors){
                if (n instanceof Food){
                    this.x=n.getX();
                    this.y=n.getY();
                    this.eat((Food)n);
                }
            }

            for (Entity e : zoo.at(x + (int) directionx, y + (int) directiony)) {
                if (e instanceof Animal) {
                    animalInCell = true;
                    break;
                }
            }
            if (animalInCell || !neighbors.isEmpty()) {
                directionx -= randomNumX;
                directiony -= randomNumY;
            }

            if (neighbors.size()>0&&Zoo.percentChance(10.0)){
                Cat catspawn = new Cat(name+" Jr.", x, y);
                zoo.add(catspawn);
                System.out.println("Baby Soren Benson has been born");
            }

            x += (int) directionx;
            y += (int) directiony;
        }
    }
}
