package dytu.game;

import java.awt.*;

public class drawStuff {
    private Hero hero;
    public WorldBuilder world;

    drawStuff(Hero hero){
        this.hero = hero;
    }

    public void displayHealth(Graphics g){
        g.drawString(String.valueOf(hero.getHealth()), 20, 20);
    }

}
