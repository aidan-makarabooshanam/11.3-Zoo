import java.util.*;
import java.awt.*;

public class Cheese extends Food {
    private int timesEaten = 0;
    private boolean expired = false;

    public Cheese(String name, int x, int y, boolean isAnimalProduct, boolean isVegetableProduct, int nutritionValue) {
        super(name, x, y, isAnimalProduct, isVegetableProduct, nutritionValue);
    }

    @Override
    public void tick(Zoo z) {
        age++;
        if (!expired && age > 400 && Zoo.percentChance(1.0)) {
            expired = true;
            System.out.println(name + " got old and rotted away.");
        }
        // Only allow cheese to be eaten if it is alive and there is an animal present
        if (alive) {
            for (Entity e : z.at(x, y)) {
                if (e instanceof Animal) {
                    beEaten((Animal) e);
                    // After being eaten, break so animal can move away next tick
                    break;
                }
            }
        }
        // Remove cheese if eaten 3 times
        if (timesEaten >= 3) {
            alive = false;
        }
    }

    @Override
    public void beEaten(Animal eater) {
        if (!alive) return; // Prevent further eating if cheese is already gone
        if (expired) {
            if (!(eater instanceof Rat)) {
                eater.isSick = true;
                System.out.println(eater.getClass().getSimpleName() + " " + eater.name + " got food poisoning from eating " + name + ".");
            }
            // Eating expired cheese does not change hunger
            return;
        }
        timesEaten++;
        int nutrition = 0;
        String part = "";
        if (timesEaten == 1) {
            nutrition = 10;
            part = "first";
        } else if (timesEaten == 2) {
            nutrition = 8;
            part = "second";
        } else if (timesEaten == 3) {
            nutrition = 5;
            part = "third";
        }
        if (timesEaten <= 3) {
            int before = eater.hunger;
            // Use changeHunger if available, otherwise update directly
            try {
                eater.getClass().getMethod("changeHunger", int.class).invoke(eater, nutrition);
            } catch (Exception ex) {
                eater.hunger += nutrition;
            }
            int after = eater.hunger;
            System.out.println(eater.getClass().getSimpleName() + " " + eater.name + " ate the " + part + " part of " + name + ", gaining " + nutrition + " nutrition! Hunger: " + before + " -> " + after);
        }
        if (timesEaten >= 3) {
            alive = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🧀", Zoo.wrap(x, Zoo.ZOO_COLS) * Zoo.SCALE, Zoo.wrap(y, Zoo.ZOO_ROWS) * Zoo.SCALE + 25);
    }

    @Override
    public boolean isAlive() {
        // Cheese is alive only if it hasn't been eaten 3 times and isn't expired
        return alive && timesEaten < 3;
    }
}
