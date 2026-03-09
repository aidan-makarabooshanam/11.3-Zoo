import java.util.*;
import java.awt.*;

// TODO: extend Animal
public class Rat extends Animal {
    private int directionX = (int)(Math.random() * 4);
    private int directionY = (int)(Math.random() * 4);
    private int ticksSinceDirectionChange = 0;

    public Rat(String name, int x, int y) {
        super(name, x, y);
    }

    public void beEaten(Animal eater) {
        if (!alive) return;
        eater.hunger = Math.max(0, eater.hunger - 10);
        alive = false;
        System.out.println("Rat " + name + " was eaten, decreasing hunger by 10!");
    }

    @Override
    public void tick(Zoo z) {
        if (age > 500 && Zoo.percentChance(1.5)) {
            alive = false;
            System.out.println("Rat " + name + " has died.");
        }
        if (age > 500 && Zoo.percentChance(20) && isSick == true) {
            alive = false;
            System.out.println("Rat " + name + " has died.");
        }
        if (isSick == true && Zoo.percentChance(1)) {
            alive = false;
            System.out.println("Rat " + name + " has died.");
        }

        for (Entity e : z.at(x, y)) {
            if (e != this && (e instanceof Dog || e instanceof Cat)) {
                if (alive) {
                    alive = false;
                    if (e instanceof Animal) {
                        ((Animal)e).hunger = Math.max(0, ((Animal)e).hunger - 10);
                        System.out.println("Rat " + name + " was eaten by " + e.getClass().getSimpleName() + ", decreasing hunger by 10!");
                    }
                }
            }
        }

        if (age % 50 == 0 && Zoo.percentChance(10.0)) {
            int[] dx = {0, 0, 1, -1};
            int[] dy = {-1, 1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                boolean empty = true;
                for (Entity e : z.at(nx, ny)) {
                    if (e instanceof Animal) {
                        empty = false;
                        break;
                    }
                }
                if (empty && age > 0) {
                    z.add(new Rat("Ratling", nx, ny));
                    System.out.println("A new rat has spawned at (" + nx + ", " + ny + ")!");
                    break;
                }
            }
        }

        this.move(z);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🐀", Zoo.wrap(x, Zoo.ZOO_COLS) * Zoo.SCALE, Zoo.wrap(y, Zoo.ZOO_ROWS) * Zoo.SCALE + 25);
    }

    @Override
    public void eat(Food food) {
        if (food.name.toLowerCase().contains("cheese")) {
            food.beEaten(this);
            if (hunger < 20) hunger = 20;
        } else if (hunger > 50) {
            food.beEaten(this);
            if (hunger < 20) hunger = 20;
        }
    }

    @Override
    public void move(Zoo z) {
        if (age % 5 != 0) return;
        ticksSinceDirectionChange++;
        if (ticksSinceDirectionChange >= 40 + (int)(Math.random() * 21)) {
            directionX = (int)(Math.random() * 4) - 1;
            directionY = (int)(Math.random() * 4) - 1;
            ticksSinceDirectionChange = 0;
        }
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            for (Entity e : z.at(nx, ny)) {
                if (e instanceof Food && e.name.toLowerCase().contains("cheese")) {
                    x = nx;
                    y = ny;
                    return;
                }
            }
        }
        x += directionX;
        y += directionY;
            if (x == 0) x++;
            if (y == 0) x++;
            if (x == 800) y--;
            if (y == 600) y--;
        
    }
}
