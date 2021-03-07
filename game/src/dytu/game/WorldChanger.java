package dytu.game;

import city.cs.engine.DebugViewer;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import dytu.world.Level_One;
import dytu.world.Level_Three;
import dytu.world.Level_Two;
import dytu.world.WorldBuilder;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class WorldChanger {
    /*
    This class changes/creates the world that the view will be looking at
     */
    private WorldBuilder world;
    //private GameView view;
    private String heroName;

    public WorldBuilder levelSelect(int selection){
        System.out.println(selection);
        /*
        uses a switch statement to generate the world, based upon the integer passed to it
         */
        switch(selection){
            case (0) -> {
                if (world != null){
                    heroName = world.getHero().getName();
                    world.getHero().setPosition(new Vec2(-8, -8));
                    world.getHero().setHealthMin((int) (world.getHero().getHealth() - world.getHero().getHealth()));
                    world.cleanLevel();
                    System.out.println(world.getHero().getHealth());
                    world.stop();
                    world = new Level_One();
                    //world.getHero().setName(heroName);
                } else{
                    world = new Level_One();
                }
                world.start();
                world.getHero().setName(heroName);
                System.out.println(world.getHero().getName());
                return world;
            }
            case (1) -> {
                if (world != null){
                    heroName = world.getHero().getName();
                    world.getHero().setPosition(new Vec2(-8, -8));
                    world.getHero().setHealthMin((int) (world.getHero().getHealth() - world.getHero().getHealth()));
                    world.cleanLevel();
                    System.out.println(world.getHero().getHealth());
                    world.getCovidList().clear();
                    world.stop();
                    world = new Level_Two();

                }else{
                    world = new Level_Two();
                }
                world.start();
                world.getHero().setName(heroName);
                System.out.println(world.getHero().getName());
                return world;
            }
            case (2) -> {
                if (world != null){
                    heroName = world.getHero().getName();
                    world.getHero().setPosition(new Vec2(-8, -8));
                    world.getHero().setHealthMin((int) (world.getHero().getHealth() - world.getHero().getHealth()));
                    world.cleanLevel();
                    System.out.println(world.getHero().getHealth());
                    world.getCovidList().clear();
                    world.stop();
                    world = new Level_Three();
                    //world.getHero().setName(heroName);
                }else{
                    world = new Level_Three();
                }
                world.start();
                world.getHero().setName(heroName);
                System.out.println(world.getHero().getName());
                return world;
            }
        }
        return null;
    }

    public WorldChanger(WorldBuilder world/*, GameView view*/){
        this.world = world;
        //this.view = view;

    }

}
