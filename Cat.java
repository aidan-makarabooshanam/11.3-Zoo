
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
    // TODO: override the move method


}
