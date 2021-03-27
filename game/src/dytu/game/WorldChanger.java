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

/**
 * Allows the changing/setting of worlds
 * @author dylantuckey
 * @version 1
 * @since 2021
 */
public class WorldChanger {
    /*
    This class changes/creates the world that the view will be looking at
     */
    /**
     * The world to be changed
     */
    private WorldBuilder world;
    /**
     * The name of the player
     */
    private String heroName;
    /**
     * Boolean to set the direction of the character
     */
    private static boolean looking = true;
    private static String name;
    private static int score;
    private static Vec2 spawnPoint;
    private static boolean beenReset = false;
    private static int toGoTo;
    /**
     * Boolean to change behaviour, based on if this is a state reload
     */
    public static boolean reload;

    /**
     * Sets the vars for character respawn
     * <p>
     *     Takes parameters passed to it, and allows this to set variables for reload from file
     * </p>
     * @param nameIn
     * @param scoreIn
     * @param lookingIn
     * @param coordsIn
     * @param level
     */
    public void setRespawn(String nameIn, int scoreIn, boolean lookingIn, Vec2 coordsIn, int level){
        name = nameIn;
        world.getHero().setName(nameIn);
        score = scoreIn;
        world.getHero().setPoints(scoreIn);
        looking = lookingIn;
        WorldChanger.setLooking(lookingIn);
        spawnPoint = coordsIn;
        toGoTo = level;
    }

    /**
     * Sets the looking variable
     * @param look
     */
    public static void setLooking(boolean look){
        looking = look;
    }

    /**
     * Sets the world
     * <p>
     *     Sets the world, based on a given integer
     * </p>
     * @param selection
     * @return The world to be viewed
     * @see WorldBuilder
     */
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
                    world = new Level_One(looking, reload);
                    //world.getHero().setName(heroName);
                } else{
                    world = new Level_One(looking, reload);
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
                    world = new Level_Two(looking, reload);

                }else{
                    world = new Level_Two(looking, reload);
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
                    world = new Level_Three(looking, reload);
                    //world.getHero().setName(heroName);
                }else{
                    world = new Level_Three(looking, reload);
                }
                world.start();
                world.getHero().setName(heroName);
                System.out.println(world.getHero().getName());
                return world;
            }
        }
        return null;
    }

    /**
     * Constructor
     * @param world
     */
    public WorldChanger(WorldBuilder world){
        this.world = world;
        //this.view = view;

    }
    
}
