package dytu.world;

import city.cs.engine.*;
import dytu.game.Covid;
import dytu.game.GameView;
import dytu.game.Hero;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class WorldBuilder extends World{
    //declare and/or instantiate variables for the world
    protected Hero hero = new Hero(this);
    protected Covid monster;
    protected BodyImage wallImage = new BodyImage("Data/wall1.png", 20);
    protected BodyImage groundImage = new BodyImage("Data/ground4.png", 1);
    protected float[] hsbValues = new float[3];
    protected float[] hsbValues2 = new float[3];
    protected List<Vec2> blockPlaces = new ArrayList<>();
    protected List<Covid> covidList = new ArrayList<>();
    protected GameView view;
    protected static SoundClip levelSound;

    static{
        try{
            levelSound = new SoundClip("Data/Sounds/push-ahead.wav"); /*please note that this sound was taken from https://opengameart.org/content/pushing-ahead at 18:13 1/3/21 */

        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }

    public void setView(GameView view) {
        this.view = view;
    }


    //getters and setters
    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero){
        this.hero = hero;
    }
    public Covid getMonster() {
        return monster;
    }
    public List<Covid> getCovidList(){ return covidList; }

    public void setCovidList(int n){
        covidList.remove(n);
    }

    //constructor
    public WorldBuilder() {
        super();

    }

    //used for motion controls
    public void velocityChangeHero(int a){
        hero.startWalking(a);
    }

    public void jumpHero(float a){
        hero.jump(a);
    }

    public void cleanLevel(){
        for(StaticBody body: this.getStaticBodies()){
            body.destroy();
        }
        for(DynamicBody body: this.getDynamicBodies()){
            body.destroy();
        }
    }

    public abstract int getLevelNo();
}
