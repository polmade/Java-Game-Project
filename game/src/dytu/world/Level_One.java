package dytu.world;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dytu.game.*;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level_One extends WorldBuilder {
    //a float array created using the java.awt.Color class, to set the colour of the floor block
    private float[] groundValues = Color.RGBtoHSB(60, 114, 16, hsbValues);

    //for later use as a world oriented point system
    private int blockNo;

    //an int to display level numbers in the view
    private int levelNo = 1;

    //get levelNo
    @Override
    public int getLevelNo(){
        return levelNo;
    }

    //private float[] wallValues = Color.RGBtoHSB(170, 67, 72, hsbValues2);
    public Level_One(){
        super();
        //levelSound.setVolume(0.15);
        //levelSound.loop();
        // make the ground
        Shape groundShape = new BoxShape(25, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(10, -11.5f));
        ground.setFillColor(new Color(groundValues[0], groundValues[1], groundValues[2]));

        // make the left boundary wall
        Shape wallShape = new BoxShape(2, 9);
        Body leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-13, -3));
        leftWall.setFillColor(new Color(203,65,84));

        //create level
        Shape blockShape = new BoxShape(4, 0.5f);
        Body block1 = new StaticBody(this, blockShape);
        block1.setPosition(new Vec2(5, -2.5f));
        blockPlaces.add(0, block1.getPosition());

        Body block2 = new StaticBody(this, blockShape);
        block2.setPosition(new Vec2(15, 7.5f));
        blockPlaces.add(1, block2.getPosition());

        Body block3 = new StaticBody(this, blockShape);
        block3.setPosition(new Vec2(28, 0f));
        blockPlaces.add(2, block3.getPosition());

        Body block4 = new StaticBody(this, blockShape);
        block4.setPosition(new Vec2(40, 11));
        blockPlaces.add(3, block4.getPosition());

        Shape blockShape2 = new BoxShape(7, 0.5f);
        Body block5 = new StaticBody(this, blockShape2);
        block5.setPosition(new Vec2(58, 6));
        blockPlaces.add(4, block5.getPosition());

        Body block6 = new StaticBody(this, blockShape);
        block6.setPosition(new Vec2(45, -11.5f));
        blockPlaces.add(5, block6.getPosition());

        Body ground2 = new StaticBody(this, groundShape);
        ground2.setPosition(new Vec2(85, -11.5f));
        ground2.setFillColor(new Color(groundValues[0], groundValues[1], groundValues[2]));

        Body block7 = new StaticBody(this, blockShape2);
        block7.setPosition(new Vec2(85, 2));
        blockPlaces.add(6, block7.getPosition());

        Body block8 = new StaticBody(this, blockShape);
        block8.setPosition(new Vec2(99, 10));
        blockPlaces.add(7, block8.getPosition());

        Body block9 = new StaticBody(this, blockShape);
        block9.setPosition(new Vec2(105, 5));
        blockPlaces.add(8, block9.getPosition());

        Body block10 = new StaticBody(this, blockShape2);
        block10.setPosition(new Vec2(130, -11.5f));
        //allows HeroCollision class to exit the game
        block10.setName("finishLine");
        blockPlaces.add(9, block10.getPosition());

        // make the characters
        hero.setPosition(new Vec2(-8, -8));
        HeroCollision colliderHero = new HeroCollision(hero, this);
        hero.addCollisionListener(colliderHero);
        monster = new Covid(this, 5, 2);
        monster.setPosition(new Vec2(0, -10));
        CovidCollision initCollide = new CovidCollision(monster);
        monster.addCollisionListener(initCollide);
        for(int i=0; i<blockPlaces.size()-1; i++){
            monster = new Covid(this, 4,1);
            CovidCollision colliderCovid = new CovidCollision(monster);
            monster.addCollisionListener(colliderCovid);
            monster.setPosition(new Vec2(blockPlaces.get(i+1).x, blockPlaces.get(i+1).y + 4));

            System.out.println(monster.getFixtureList());
            covidList.add(i, monster);
            //System.out.println();
        }



    }
}
