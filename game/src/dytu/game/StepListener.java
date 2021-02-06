package dytu.game;

import city.cs.engine.StepEvent;
import org.jbox2d.common.Vec2;

public class StepListener implements city.cs.engine.StepListener {
    private GameView view;
    private dytu.game.Hero Hero;
    private dytu.game.Covid Covid;
    //private heroCollision herocollide;
    public StepListener(GameView view, dytu.game.Hero Hero, dytu.game.Covid Covid) {
        this.view = view;
        this.Hero = Hero;
        this.Covid = Covid;
    }

    @Override
    public void preStep(StepEvent e) {}
    @Override
    public void postStep(StepEvent e) {
        //set view centre for the world, based on position of hero character
        this.view.setCentre(new Vec2(Hero.getPosition().x,(Hero.getPosition().y)+8f));
        //System.out.println(this.hero.getPosition().y);
        if (Hero.getHealth() <= 0){
            Hero.destroy();
        } if (Covid.getInfectiousness() <= 0){
            Covid.destroy();
        }

    }


}
