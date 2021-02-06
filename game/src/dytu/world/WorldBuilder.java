package dytu.world;

import city.cs.engine.*;
import dytu.game.Covid;
import dytu.game.Hero;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public abstract class WorldBuilder extends World{
    protected Hero hero = new Hero(this);
    protected Covid monster;
    protected BodyImage wallImage = new BodyImage("Data/wall.png", 5);
    protected float[] hsbValues = new float[3];
    protected float[] hsbValues2 = new float[3];
    protected List<Vec2> blockPlaces;

    {
        blockPlaces = new ArrayList<> ();
    }

    public dytu.game.Hero getHero() {
        return hero;
    }

    public Covid getMonster() {
        return monster;
    }

    public WorldBuilder() {
        super();

    }


    public void velocityChangeHero(int a){
        hero.startWalking(a);
    }

    public void jumpHero(float a){
        hero.jump(a);
    }
}
