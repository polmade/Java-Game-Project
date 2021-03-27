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
    private boolean isFinished = false;
    //showStats allow for statistics, like user health and points to either be shown, or not
    private boolean showStats;

    //getter and setter for width of health bar
    public void setHealthBarWidth(int width){
        this.healthBar.width = width;
    }

    public int getHealthBarWidth(){
        return healthBar.width;
    }

    //getter and setter for isFinished
    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    //create the view
    public GameView(WorldBuilder w, int width, int height, boolean showStats) {
        super(w, width, height);
        this.world = w;
        background = new ImageIcon("data/background.png").getImage();
        this.showStats = showStats;
    }

    public void updateBackground(String location){
        Image newBack = new ImageIcon(location).getImage();
        background = newBack;
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
        //draw the stats and health bar if not finished
        if(isFinished == false && showStats == true){
            g.drawString("Health: " + String.valueOf(world.getHero().getHealth()), 20, 20);
            healthBar.width = healthWidth;
            g.draw(healthBar);
            g.fill(healthBar);
            g.drawString("Points: " + String.valueOf(world.getHero().getPoints()), 20, 60);
            g.drawString("Level "+String.valueOf(world.getLevelNo()), 450, 20);
        }
        //draw a message saying the player has finished the game
        else{
            g.drawString("WELL DONE, YOU FINISHED THE GAME", 250, 250);
        }


    }

}
