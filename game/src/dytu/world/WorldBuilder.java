package dytu.world;

import city.cs.engine.*;
import dytu.game.Covid;
import dytu.game.GameView;
import dytu.game.Hero;
import dytu.game.WorldChanger;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to create worlds
 * @author dylantuckey
 * @version 1
 * @since 2021
 */
public abstract class WorldBuilder extends World{
    //declare and/or instantiate variables for the world
    /**
     * The player's character
     */
    protected Hero hero;
    /**
     * The enemy
     */
    protected Covid monster;
    //protected BodyImage wallImage = new BodyImage("Data/wall1.png", 20);
    //protected BodyImage groundImage = new BodyImage("Data/ground4.png", 1);
    protected float[] hsbValues = new float[3];
    protected float[] hsbValues2 = new float[3];
    /**
     * List to store block coordinates
     */
    protected List<Vec2> blockPlaces = new ArrayList<>();
    /**
     * list of all Enemy characters in a world
     */
    protected List<Covid> covidList = new ArrayList<>();
    /**
     * The view
     */
    protected GameView view;
    /**
     * The backing sound
     */
    protected static SoundClip levelSound;
    protected Vec2 DEFAULT_COORDS = new Vec2(-8, -10);
    /**
     * Where to place the player character
     */
    protected Vec2 placeCharHere = DEFAULT_COORDS;
    protected double DEFAULT_SOUND_LEVEL = 0.15;
    /**
     * Backing sound volume
     */
    protected double soundLevel = DEFAULT_SOUND_LEVEL;
    /**
     * Player name
     */
    protected String name;
    /**
     * Player score
     */
    protected int score;
    /**
     * Boolean to allow controls for looking a few pixels forward
     */
    protected boolean looking;
    /**
     * Where to spawn the player character
     */
    protected Vec2 spawnPoint;

    /**
     * Returns the sound level
     * @return Sound level (volume)
     */
    public double getSoundLevel() {
        return soundLevel;
    }

    /**
     * Sets the sound level (volume)
     * @param level
     */
    public void setSoundLevel(double level){
        soundLevel = level;
    }

    /**
     * Sets where the player character should be placed
     * @param placeCharHere
     */
    public void setPlaceCharHere(Vec2 placeCharHere) {
        this.placeCharHere = placeCharHere;
    }

    static{
        try{
            levelSound = new SoundClip("Data/Sounds/push-ahead.wav"); /*please note that this sound was taken from https://opengameart.org/content/pushing-ahead at 18:13 1/3/21 */

        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }

    /**
     * Sets the view
     * @param view
     */
    public void setView(GameView view) {
        this.view = view;
    }

    /**
     * Gets the backing sound
     * @return The backing sound
     */
    public static SoundClip getLevelSound() {
        return levelSound;
    }

    /**
     * Plays the backing sound
     * @param volume
     */
    public static void playLevelSound(double volume){
        levelSound.loop();
        System.out.println(volume);
        System.out.println(volume/100);
        levelSound.setVolume(volume/100);
    }

    /**
     * Stops playing the backing sound
     */
    public static void stopLevelSound(){
        levelSound.stop();
    }

    //getters and setters

    /**
     * Get the hero
     * @return The player character
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Set the hero
     * @param hero
     */
    public void setHero(Hero hero){
        this.hero = hero;
    }

    /**
     * Get the Coovid enemy character
     * @return
     */
    public Covid getMonster() {
        return monster;
    }

    /**
     * Get list of all covid enemy characters in a world
     * @return List of all enemy characters in a world
     */
    public List<Covid> getCovidList(){ return covidList; }

    /**
     * Set the covid list
     * <p>
     *     The name is a bit misleading
     *     Removes covid character at given index of list
     * </p>
     * @param n
     */
    public void setCovidList(int n){
        covidList.remove(n);
    }

    //constructor

    /**
     * Constructor
     */
    public WorldBuilder() {
        super();

    }

    //used for motion controls

    /**
     * Allows for controls to let the character walk
     * @param a
     */
    public void velocityChangeHero(int a){
        hero.startWalking(a);
    }

    /**
     * Allows for controls to let the character jump
     * @param a
     */
    public void jumpHero(float a){
        hero.jump(a);
    }

    /**
     * Remove all bodies from the world
     */
    public void cleanLevel(){
        for(StaticBody body: this.getStaticBodies()){
            body.destroy();
        }
        for(DynamicBody body: this.getDynamicBodies()){
            body.destroy();
        }
    }

    /**
     * Abstract
     * <p>
     *     Does nothing
     *     Needs setting within levels themselves
     * </p>
     * @return
     */
    public abstract int getLevelNo();


}
