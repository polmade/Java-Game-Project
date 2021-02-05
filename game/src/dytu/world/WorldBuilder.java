package dytu.world;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dytu.game.Covid;
import dytu.game.Hero;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public abstract class WorldBuilder extends World{
    protected Hero hero = new Hero(this);
    protected Covid monster = new Covid(this, 5, 1);

    public Hero getHero() {
        return hero;
    }


    public WorldBuilder() {
        super();

    }


    public void velocityChangeHero(int a){
        hero.startWalking(a);
    }
}
