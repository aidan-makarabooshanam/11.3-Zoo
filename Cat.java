import java.util.*;
import java.awt.*;

// TODO: extend Animal
public abstract class Cat extends Animal{

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
    }

    @Override
    public void draw(Graphics g) {
        // two optional examples of a way to draw a cat follow!

        //g.setColor(Color.DARK_GRAY);
        //g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        //g.drawString("🐈", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+25);

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
        int randomNumX = (int) (Math.random() * 3)-1;     
        int randomNumY=(int) (Math.random() * 3)-1;
        double directionx = 0.0;
        double directiony = 0.0;
        if (age % 10 ==0){
            directionx+=randomNumX;
            directiony+=randomNumY;
        }

    }
}
