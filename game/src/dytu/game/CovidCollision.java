package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class CovidCollision implements CollisionListener {
    private Covid covid;
    private static SoundClip deathSound;

    //load the sound to be played when the Covid object is destroyed
    static {
        try{
            deathSound = new SoundClip("Data/Sounds/Coin01.wav"); /*please note that this was taken from https://opengameart.org/content/level-up-power-up-coin-get-13-sounds at 20:47 1/3/21, or pleas see wobbleboxx.com for more */
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public CovidCollision(Covid covid){
        this.covid = covid;
    }

    /*
    defines what happens upon collision with hero objects, and syringe objects
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Hero){
            System.out.println("Collision detected with: "+collisionEvent.getOtherBody().getName());
        } if (collisionEvent.getOtherBody() instanceof Syringe){
            //change infectiousness of covid instance, and check if covid instance has health of <=0 and then destroy if true
            covid.setInfectiousness(Syringe.getDamage());
            collisionEvent.getOtherBody().destroy();
            System.out.println(covid.getInfectiousness());
            //check if the infectiousness of the Covid object is less than 0
            if(covid.getInfectiousness()<=0){
                //set the index of the Covid object within its List
                for(int i=0; i<covid.getWorld().getCovidList().size(); i++){
                    covid.setIndex(i);
                }
                covid.getWorld().setCovidList(covid.getIndex());
                covid.getWorld().getHero().setPoints((int) (5*covid.getRadius()));
                deathSound.play();
                covid.destroy();
            }
            //System.out.println("Collision detected");
        }
    }
}
