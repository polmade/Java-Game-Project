package dytu.world;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dytu.game.heroCollision;
import dytu.game.covidCollision;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level_One extends WorldBuilder {
    private float[] groundValues = Color.RGBtoHSB(60, 114, 16, hsbValues);
    private float[] wallValues = Color.RGBtoHSB(170, 67, 72, hsbValues2);
    public Level_One(){
        super();
        // make the ground
        Shape groundShape = new BoxShape(200, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(185, -11.5f));
        ground.setFillColor(new Color(groundValues[0], groundValues[1], groundValues[2]));

        // make the left boundary wall
        Shape wallShape = new BoxShape(2, 9);
        StaticBody leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-13, -3));
        leftWall.setFillColor(new Color(wallValues[0], wallValues[1], wallValues[2]));

        //create level
        Shape blockShape = new BoxShape(4, 0.5f);
        StaticBody block1 = new StaticBody(this, blockShape);
        block1.setPosition(new Vec2(0, -2.5f));


        // make the character
        Hero.setPosition(new Vec2(-8, -10));
        heroCollision colliderHero = new heroCollision(Hero);
        Hero.addCollisionListener(colliderHero);
        covidCollision colliderCovid = new covidCollision(monster);
        monster.setPosition(new Vec2(0, -10));
        monster.addCollisionListener(colliderCovid);
    }
}
