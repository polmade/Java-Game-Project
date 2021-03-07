package dytu.world;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import dytu.game.Covid;
import dytu.game.CovidCollision;
import dytu.game.HeroCollision;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.util.Random;

public class Level_Three extends WorldBuilder {
    //a float array created using the java.awt.Color class, to set the colour of the floor block
    private float[] groundValues = Color.RGBtoHSB(60, 114, 16, hsbValues);

    //for later use as a world oriented point system
    private int blockNo;

    //an int to display level numbers in the view
    private int levelNo = 3;

    //get levelNo
    @Override
    public int getLevelNo(){
        return levelNo;
    }

    //private float[] wallValues = Color.RGBtoHSB(170, 67, 72, hsbValues2);
    public Level_Three(){
        super();
        levelSound.setVolume(0.15);
        levelSound.loop();
        // make the ground
        Shape groundShape = new BoxShape(85, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(70, -11.5f));
        ground.setFillColor(new Color(groundValues[0], groundValues[1], groundValues[2]));

        // make the left & right boundary walls
        Shape wallShape = new BoxShape(2, 20);
        Body leftWall = new StaticBody(this, wallShape);
        Body rightWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-13, 8));
        leftWall.setFillColor(new Color(203,65,84));
        rightWall.setPosition(new Vec2(148, 8));

        //create level
        Shape blockShape = new BoxShape(4, 0.5f);
        Shape blockShape2 = new BoxShape(7, 0.5f);


        //add to blockPlaces list of coords for generation of blocks
        blockPlaces.add(0, new Vec2(4, -2f));
        blockPlaces.add(1, new Vec2(15, 5f));
        blockPlaces.add(2, new Vec2(29, 11f));
        blockPlaces.add(3, new Vec2(50, 16));
        blockPlaces.add(4, new Vec2(70, 20));
        blockPlaces.add(5, new Vec2(89, 10));
        //blockPlaces.add(6, new Vec2(99, 10));
        //blockPlaces.add(7, new Vec2(112, 20));

        //generate blocks for level
        for(int i=0; i<blockPlaces.size(); i++){
            Body block;
            switch(i){
                case (0), (1), (2) -> {
                    block = new StaticBody(this, blockShape);
                    block.setFillColor(new Color(13, 19, 33));
                }
                case (3), (4), (5) -> {
                    block = new StaticBody(this, blockShape2);
                    block.setFillColor(new Color(13, 19, 33));
                }
                default -> throw new IllegalStateException("Unexpected value: " + i);
            }

            block.setPosition(blockPlaces.get(i));
            if(blockPlaces.get(i) == blockPlaces.get(blockPlaces.size()-1)){
                block.setName("finishLine");
            }
        }

        //generate more places for Covids to be spawned
        Random random = new Random();
        int randomGen = 6;
        while(randomGen < 11){
            blockPlaces.add(randomGen, new Vec2(random.nextInt(100), -8));
            ++randomGen;
        }

        // make the characters
        hero.setPosition(new Vec2(-8, -10));
        HeroCollision colliderHero = new HeroCollision(hero, this);
        hero.addCollisionListener(colliderHero);
        monster = new Covid(this, 5, 2);
        monster.setPosition(new Vec2(0, -10));
        CovidCollision initCollide = new CovidCollision(monster);
        monster.addCollisionListener(initCollide);
        for(int i=0; i<blockPlaces.size()-1; i++){
            monster = new Covid(this, 4,1);
            CovidCollision colliderCovid = new CovidCollision(monster);
            monster.setPosition(new Vec2(blockPlaces.get(i+1).x, blockPlaces.get(i+1).y + 2));
            monster.addCollisionListener(colliderCovid);
            monster.setIndex(i);
            covidList.add(i, monster);
            //System.out.println();
        }



    }
}
