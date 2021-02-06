package dytu.world;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dytu.game.*;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level_One extends WorldBuilder {
    private float[] groundValues = Color.RGBtoHSB(60, 114, 16, hsbValues);
    private int blockNo;

    //private float[] wallValues = Color.RGBtoHSB(170, 67, 72, hsbValues2);
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
        leftWall.setFillColor(new Color(203,65,84));

        //create level
        Shape blockShape = new BoxShape(4, 0.5f);
        StaticBody block1 = new StaticBody(this, blockShape);
        block1.setPosition(new Vec2(5, -2.5f));
        blockPlaces.add(0, block1.getPosition());

        StaticBody block2 = new StaticBody(this, blockShape);
        block2.setPosition(new Vec2(15, 7.5f));
        blockPlaces.add(1, block2.getPosition());

        StaticBody block3 = new StaticBody(this, blockShape);
        block3.setPosition(new Vec2(28, 0f));
        blockPlaces.add(2, block3.getPosition());

        StaticBody block4 = new StaticBody(this, blockShape);
        block4.setPosition(new Vec2(40, 11));
        blockPlaces.add(3, block4.getPosition());

        //update blockNo, to allow for creation of covids


        // make the character
        hero.setPosition(new Vec2(-8, -10));
        HeroCollision colliderHero = new HeroCollision(hero);
        hero.addCollisionListener(colliderHero);
        monster = new Covid(this, 5, 2);
        monster.setPosition(new Vec2(0, -10));
        CovidCollision initCollide = new CovidCollision(monster);
        monster.addCollisionListener(initCollide);
        //Syringe Syringe = new Syringe(this);
        //Syringe.setAlwaysOutline(true);
        //Syringe.setPosition(new Vec2(5, -5));
        for(int i=0; i<blockPlaces.size()-1; i++){
            monster = new Covid(this, 4,1);
            CovidCollision colliderCovid = new CovidCollision(monster);
            monster.setPosition(new Vec2(blockPlaces.get(i+1).x, blockPlaces.get(i+1).y + 2));
            monster.addCollisionListener(colliderCovid);
            //stepListener listen = new stepListener(, monster);

            //System.out.println();
        }



    }
}
