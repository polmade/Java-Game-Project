package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;
import city.cs.engine.StepEvent;

public class HeroCollision implements CollisionListener {
    private Hero hero;


    public HeroCollision(Hero hero){
        this.hero = hero;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Covid){
            //use the radius of the instance that the character has collided with to reduce the characters health
            hero.setHealth(3* (((Covid) collisionEvent.getOtherBody()).getRadiusInt()));
            //THIS NEEDS DOING
            hero.move(new Vec2(-4, 0));
        }
        //checks if the static body that the character is in contact with is the finish line, and if it is ends the game
        if(collisionEvent.getOtherBody() instanceof StaticBody){
            if(collisionEvent.getOtherBody().getName() == "finishLine"){
                System.out.println("Well Done! You completed the game");
                System.exit(0);
            }
        }
    }
}
