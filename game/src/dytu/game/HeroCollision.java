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
    private WriteFile wF;
    private boolean hasFinished = false;

    //initialise level selector & view selector class
    private WorldChanger wc = new WorldChanger((WorldBuilder) world);
    private ViewChanger vc = new ViewChanger((WorldBuilder) world);


    public HeroCollision(Hero hero, WorldBuilder world){
        this.hero = hero;
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        //allows for comparison to inform user that they have completed the game
        int levelNo = hero.getWorld().getLevelNo();
        if(collisionEvent.getOtherBody() instanceof Covid){
            //use the radius of the instance that the character has collided with to reduce the characters health
            hero.setHealthMin(3* (((Covid) collisionEvent.getOtherBody()).getRadiusInt()));
            GameView.healthWidth = (int) hero.getHealth();
            hero.getImpactSound().setVolume(0.5);
            hero.getImpactSound().play();
            //THIS NEEDS DOING
            hero.move(new Vec2(-4, 0));
            //destroy the hero character, and play sound
            if (hero.getHealth() <= 0){
                //Hero.setHealth(0);
                hero.getDeathSound().play();
                hero.destroy();
            }
        }
        //checks if the static body that the character is in contact with is the finish line, and if it is ends the game
        if(collisionEvent.getOtherBody() instanceof StaticBody){
            if(collisionEvent.getOtherBody().getName() == "finishLine" && hero.getWorld().getLevelNo() != 3){
                System.out.println("Well Done! You completed the level");
                world = wc.levelSelect(levelNo);
                vc.setWorld(world);
                vc.viewSelect(levelNo);
                ControlForm.setViewAndWorld(world, vc.viewSelect(levelNo));
                }
            if(collisionEvent.getOtherBody().getName() == "finishLine" && hero.getWorld().getLevelNo() == 3){
                System.out.println("Well Done! You completed the level");
                vc.setFinished(true);

                if(hasFinished == false){
                    wF = new WriteFile();
                    wF.WriteFile(hero.getPoints(), Build.getPlayerName());
                }
                hasFinished = true;
                //ControlForm.setViewAndWorld(world, vc.viewSelect(1));



            }
        }
        if(collisionEvent.getOtherBody() instanceof CovidProjectile){
            //System.out.println(((CovidProjectile) collisionEvent.getOtherBody()).getRadCeiling());
            hero.setHealthMin(3*(((CovidProjectile) collisionEvent.getOtherBody()).getRadCeiling()));
            GameView.healthWidth = (int) hero.getHealth();
            if (hero.getHealth() <= 0){
                //Hero.setHealth(0);
                hero.getDeathSound().play();
                hero.destroy();
            }
        }
    }
}
