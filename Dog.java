import java.util.*;
import java.awt.*;
import java.lang.reflect.Array;

// TODO: extend Animal
public class Dog extends Animal{

    // Preferred direction: 0=North, 1=South, 2=East, 3=West
    private int preferredDirection = (int)(Math.random() * 4);
    private int nutrition=0;
    public Dog(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public void tick(Zoo z) {
        if (age>1000 && Zoo.percentChance(0.1)){
            alive=false;
            System.out.println("Dog "+name+" has died.");
        }
        if (age>1000 && Zoo.percentChance(1) && isSick==true){
            alive=false;
            System.out.println("Dog "+name+" has died.");
        }
        this.move(z);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🐕", Zoo.wrap(x,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(y,Zoo.ZOO_ROWS)*Zoo.SCALE+25);
    }

    @Override
    public void eat(Food food){
        int before = hunger;
        if (hunger >50 && food.isAnimalProduct){
            food.beEaten(this);
            System.out.println("Dog "+name+" ate "+food.name+", gaining "+food.nutritionValue+" nutrition! Hunger: " + before + " -> " + hunger);
        }
        if (Zoo.percentChance(1) && food.isAnimalProduct){
            food.beEaten(this);
            System.out.println("Dog "+name+" ate "+food.name+", gaining "+food.nutritionValue+" nutrition! Hunger: " + before + " -> " + hunger);
        }
    }

    public void changeHunger(int delta) {
        int before = hunger;
        hunger += delta;
        System.out.println("Dog " + name + " hunger changed: " + before + " -> " + hunger);
    }

    @Override
    public void move(Zoo z) {
        if (age % 15 != 0) return;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};
        int moveDir = preferredDirection;
        if (!Zoo.percentChance(75.0)) {
            moveDir = (int)(Math.random() * 4);
        }
        int nx = x + dx[moveDir];
        int ny = y + dy[moveDir];
        for (int i = 0; i < 4; i++) {
            int adjX = x + dx[i];
            int adjY = y + dy[i];
            for (Entity e : z.at(adjX, adjY)) {
                if (e instanceof Dog && e != this) {
                    if (Zoo.percentChance(25.0)) {
                        Dog otherDog = (Dog)e;
                        this.preferredDirection = otherDog.preferredDirection;
                    }
                }
            }
        }
        if (age%15==0){
            x=nx;
            y=ny;
            if (x == 0) x++;
            if (y == 0) x++;
            if (x == 800) y--;
            if (y == 600) y--;
        }
    }
}