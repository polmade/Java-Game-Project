package dytu.game;

import dytu.world.WorldBuilder;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlForm {
    private JPanel MainPanel;
    private JButton restart;
    private JButton pause;
    private JButton Play;
    private JButton Quit;
    private JTextField nameInput;
    private JButton submitName;
    private static WorldBuilder world;
    private static GameView view;
    private static int levelNo;

    //initialise level selector & view selector class
    private WorldChanger wc = new WorldChanger((WorldBuilder) world);
    private ViewChanger vc = new ViewChanger((WorldBuilder) world, view);

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public static void setViewAndWorld(WorldBuilder w, GameView v){
        world = w;
        view = v;
        levelNo = world.getLevelNo();
    }

    public void setLevelNo(){
        levelNo = this.world.getLevelNo();
    }

    public ControlForm(){
        //checks for input in the JTextField nameInput, once activated, and sets the name to be the input in the JTextField
        submitName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Build.setPlayerName(nameInput.getText());
                view.requestFocusInWindow();
            }
        });

        //checks for activation of the restart JButton, and then resets the world
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world = wc.levelSelect(levelNo - 1);
                vc.setWorld(world);
                vc.viewSelect(levelNo - 1);
                view.requestFocusInWindow();
            }
        });
        //pauses the world
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.stop();
                System.out.println("World paused");
                view.requestFocusInWindow();
            }
        });
        //restarts the world
        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.start();
                System.out.println("World restarted");
                view.requestFocusInWindow();
            }
        });
        //quits the game
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(2);
            }
        });
    }
}
