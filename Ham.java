import java.awt.*;


public class Ham extends Food {
    private boolean eaten = false;
    private boolean expired = false;

    public Ham(String name, int x, int y, boolean isAnimalProduct, boolean isVegetableProduct, int nutritionValue) {
        super(name, x, y, isAnimalProduct, isVegetableProduct, nutritionValue);
    }

    @Override
    public void tick(Zoo z) {
        age++;
        if (age == 200) {
            expired = true;
            System.out.println(name + " has expired.");
        }
        if (alive) {
            for (Entity e : z.at(x, y)) {
                if (e instanceof Animal) {
                    beEaten((Animal) e);
                    break;
                }
            }
        }
    }

    @Override
    public void beEaten(Animal eater) {
        if (eaten) return;
        eaten = true;
        if (expired) {
            eater.hunger += 5;
            eater.isSick = true;
            System.out.println(eater.getClass().getSimpleName() + " " + eater.name + " ate expired " + name + ", increasing hunger by 5 and got sick!");
        } else {
            int before = eater.hunger;
            try {
                eater.getClass().getMethod("changeHunger", int.class).invoke(eater, 15);
            } catch (Exception ex) {
                eater.hunger += 15;
            }
            int after = eater.hunger;
            System.out.println(eater.getClass().getSimpleName() + " " + eater.name + " ate " + name + ", increasing hunger by 15! Hunger: " + before + " -> " + after);
        }
        alive = false;
    }

    @Override
    public boolean isAlive() {
        return alive && !eaten;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🍖", Zoo.wrap(x, Zoo.ZOO_COLS) * Zoo.SCALE, Zoo.wrap(y, Zoo.ZOO_ROWS) * Zoo.SCALE + 25);
    }
}
