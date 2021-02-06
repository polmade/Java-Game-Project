package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class CovidCollision implements CollisionListener {
    private Covid covid;

    public CovidCollision(Covid covid){
        this.covid = covid;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Hero){
            System.out.println("Collision detected with: "+collisionEvent.getOtherBody());
        } if (collisionEvent.getOtherBody() instanceof Syringe){
            System.out.println(((Syringe) collisionEvent.getOtherBody()).getCollisionWithCovid());
            ((Syringe) collisionEvent.getOtherBody()).setCollisionWithCovid(true);
            System.out.println(((Syringe) collisionEvent.getOtherBody()).getCollisionWithCovid());
            covid.setInfectiousness(Syringe.getDamage());
            System.out.println(covid.getInfectiousness());
            //System.out.println("Collision detected");
        }
    }
}
