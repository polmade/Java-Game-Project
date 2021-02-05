package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class heroCollision implements CollisionListener {
    private dytu.game.hero hero;

    public heroCollision(dytu.game.hero hero){
        this.hero = hero;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof covid){
            hero.setHealth(3* (((covid) collisionEvent.getOtherBody()).getRadiusInt()));
            //System.out.println(hero.getHealth());
            //collide(collisionEvent);
            //hero.setPosition(new Vec2(hero.getPosition().x-3, hero.getPosition().y));


            //THIS NEEDS DOING
            hero.setLinearVelocity(new Vec2(-150, 5));
        }
    }
}
