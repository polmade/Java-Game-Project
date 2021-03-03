package dytu.game;

import city.cs.engine.UserView;
import dytu.world.WorldBuilder;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView{
    private Image background;
    private WorldBuilder world;
    //maybe try and figure out how to get it to just be 100
    protected static int healthWidth = 200;
    private Rectangle healthBar = new Rectangle(20, 25, healthWidth, 20);

    //getter and setter for width of health bar
    public void setHealthBarWidth(int width){
        this.healthBar.width = width;
    }

    public int getHealthBarWidth(){
        return healthBar.width;
    }

    //create the view
    public GameView(WorldBuilder w, int width, int height) {
        super(w, width, height);
        this.world = w;
        background = new ImageIcon("data/background.png").getImage();
    }

    /*
    used to allow paintBackground to paint the correct statistics to the screen
     */
    public void updateWorld(WorldBuilder world){
        this.world = world;
    }



    //give world a background
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    /*
    allow drawing of statistics to foreground of the frame
    currently draws health value as a string
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.drawString("Health: " + String.valueOf(world.getHero().getHealth()), 20, 20);
        //g.drawString(String.valueOf((world.getHero().getHealth()/200)), 20, 30);
        //double newWidth = (healthBar.width * ((world.getHero().getHealth())/200));
        //g.drawString(String.valueOf((int)newWidth), 20, 30);
        //healthBar.setRect(healthBar.x, healthBar.y, healthWidth, healthBar.height);
        healthBar.width = healthWidth;
        //g.drawString(String.valueOf(healthWidth), 20, 80);
        //g.drawString(String.valueOf(healthBar.width), 20, 100);
        g.draw(healthBar);
        g.fill(healthBar);
        g.drawString("Points: " + String.valueOf(world.getHero().getPoints()), 20, 60);
        g.drawString("Level "+String.valueOf(world.getLevelNo()), 450, 20);
    }

}
