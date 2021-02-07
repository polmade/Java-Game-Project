package dytu.game;

import city.cs.engine.StepEvent;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class StepListener implements city.cs.engine.StepListener {
    //declare variables
    private GameView view;
    private Hero hero;
    private Covid covid;
    private World world;


    public StepListener(GameView view, Hero hero, Covid covid, World world) {
        this.view = view;
        this.hero = hero;
        this.covid = covid;
        this.world = world;
    }


    @Override
    public void preStep(StepEvent e) {
    }
    @Override
    public void postStep(StepEvent e) {
        //set view centre for the world, based on position of hero character and if it should be looking forward
        if(hero.isLookingForward() == false){
            this.view.setCentre(new Vec2(hero.getPosition().x,(hero.getPosition().y)+8f));
        } else{
            this.view.setCentre(new Vec2(hero.getPosition().x+10,(hero.getPosition().y)+8f));
        }
        //System.out.println(this.hero.getPosition().y);
        //check health of both character and covid instances, and if <=0 destroy them
        if (hero.getHealth() <= 0){
            //Hero.setHealth(0);
            hero.destroy();
        } if (covid.getInfectiousness() <= 0){
            covid.destroy();
        }
    }


}
