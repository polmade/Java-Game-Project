package dytu.game;

import city.cs.engine.StepEvent;
import city.cs.engine.World;
import dytu.world.WorldBuilder;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private GameView view;
    private WorldBuilder world;
    //this allows for the instance to have its creation frame attached to it
    private long eventInitialTime;

    public MouseHandler(GameView view, WorldBuilder world){
        this.view = view;
        this.world = world;
    }

    public void mousePressed(MouseEvent e){
        //set the time of creation of the instance
        eventInitialTime = world.getSimulationSettings().getFrameCount();
        System.out.println(eventInitialTime);
        /*
        create the instance, including its creation frame
        with a collision listener and other settings to allow for better physics interactions
         */
        Syringe syringe = new Syringe(world, eventInitialTime);
        syringe.addCollisionListener(new SyringeCollision(syringe));
        syringe.setGravityScale(0.1f);
        syringe.setBullet(true);
        syringe.setPosition(world.getHero().getPosition());
        //based upon the orientation of the character's body, set the velocity of the syringe projectile
        switch (this.world.getHero().getImage()){
            case "rightImage" -> {
                syringe.setLinearVelocity(new Vec2(25, 0));
            }
            case "leftImage" -> {
                syringe.setLinearVelocity(new Vec2(-25, 0));
            }
        }



    }
}
