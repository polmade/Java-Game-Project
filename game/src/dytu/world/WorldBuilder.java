package dytu.world;

import city.cs.engine.*;
import dytu.game.Covid;
import dytu.game.Hero;
import org.jbox2d.common.Vec2;

import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public abstract class WorldBuilder extends World{
    //declare and/or instantiate variables for the world
    protected Hero hero = new Hero(this);
    protected Covid monster;
    protected BodyImage wallImage = new BodyImage("Data/wall.png", 5);
    protected float[] hsbValues = new float[3];
    protected float[] hsbValues2 = new float[3];
    protected List<Vec2> blockPlaces = new ArrayList<> ();


    //getters and setters
    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero){
        this.hero = hero;
    }

    public Covid getMonster() {
        return monster;
    }

    //constructor
    public WorldBuilder() {
        super();

    }

    //used for motion controls
    public void velocityChangeHero(int a){
        hero.startWalking(a);
    }

    public void jumpHero(float a){
        hero.jump(a);
    }
}
