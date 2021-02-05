package dytu.game;

import dytu.world.WorldBuilder;

import java.awt.*;

public class drawStuff {
    private dytu.game.hero hero;
    public WorldBuilder world;

    drawStuff(dytu.game.hero hero){
        this.hero = hero;
    }

    public void displayHealth(Graphics g){
        g.drawString(String.valueOf(hero.getHealth()), 20, 20);
    }

}
