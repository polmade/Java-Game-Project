package dytu.world;

import city.cs.engine.*;
import dytu.game.covid;
import dytu.game.hero;

public abstract class WorldBuilder extends World{
    protected hero Hero = new hero(this);
    protected covid monster = new covid(this, 5, 1);
    protected BodyImage wallImage = new BodyImage("Data/wall.png", 5);
    protected float[] hsbValues = new float[3];
    protected float[] hsbValues2 = new float[3];

    public hero getHero() {
        return Hero;
    }


    public WorldBuilder() {
        super();

    }


    public void velocityChangeHero(int a){
        Hero.startWalking(a);
    }

    public void jumpHero(float a){
        Hero.jump(a);
    }
}
