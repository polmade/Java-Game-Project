package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import dytu.world.WorldBuilder;
import org.jbox2d.common.Vec2;
import city.cs.engine.StepEvent;

public class HeroCollision implements CollisionListener {
    private Hero hero;
    private GameView view;
    private WorldBuilder world;
    //initialise level selector & view selector class
    private WorldChanger wc = new WorldChanger((WorldBuilder) world);
    private ViewChanger vc = new ViewChanger((WorldBuilder) world);


    public HeroCollision(Hero hero, WorldBuilder world){
        this.hero = hero;
        this.world = world;

    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Covid){
            //use the radius of the instance that the character has collided with to reduce the characters health
            hero.setHealthMin(3* (((Covid) collisionEvent.getOtherBody()).getRadiusInt()));
            GameView.healthWidth = (int) hero.getHealth();
            //THIS NEEDS DOING
            hero.move(new Vec2(-4, 0));
        }
        //checks if the static body that the character is in contact with is the finish line, and if it is ends the game
        if(collisionEvent.getOtherBody() instanceof StaticBody){
            if(collisionEvent.getOtherBody().getName() == "finishLine"){
                System.out.println("Well Done! You completed the level");
                world = wc.levelSelect(1);
                vc.setWorld(world);
                vc.viewSelect(1);
                ControlForm.setViewAndWorld(world, vc.viewSelect(1));
            }
        }
        if(collisionEvent.getOtherBody() instanceof CovidProjectile){
            //System.out.println(((CovidProjectile) collisionEvent.getOtherBody()).getRadCeiling());
            hero.setHealthMin(3*(((CovidProjectile) collisionEvent.getOtherBody()).getRadCeiling()));
            GameView.healthWidth = (int) hero.getHealth();
        }
    }
}
