package dytu.game;

import city.cs.engine.UserView;
import dytu.world.WorldBuilder;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView{
    private Image background;
    private WorldBuilder world;

    //public addStepListener(WorldBuilder world, Hero hero){

    //create the view
    public GameView(WorldBuilder w, int width, int height) {
        super(w, width, height);
        this.world = w;
        background = new ImageIcon("data/background.png").getImage();
    }

    //give world a background
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    //allow drawing of statistics to foreground of the frame
    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.drawString("Health: " + String.valueOf(world.getHero().getHealth()), 20, 20);
    }

}
