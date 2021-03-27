package dytu.game;

import org.jbox2d.common.Vec2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Writes scores to a file
 * @author dylantuckey
 * @version 1
 * @since 2021
 */
public class WriteFile {
    /**
     * A String array that contains the scores and player names
     */
    private String[] lineContent;
    /*
    writes the hero's score to a results file
     */

    /**
     * Writes results to results file
     * @param n
     * @param name
     */
    public void WriteFile(int n, String name){
        try{
            FileWriter fW = new FileWriter("./Data/results.txt", true);
            BufferedWriter bW = new BufferedWriter(fW);
            bW.write(n+", ");
            bW.write(name+"/");
            System.out.println(n+" written to file");
            bW.close();
        }catch(Exception e){
            return;
        }
    }

    /**
     * Reads all lines from a file
     * @return Null - if unsuccessful, All lines - if successful
     */
    public String[] readFile(){
        try{
            //FileReader fR = new FileReader("./Data/State.txt");
            //BufferedReader bR = Files.newBufferedReader(Path.of("./Data/State.txt"));
            //String line = bR.readAllLines();
            List<String> lines = Files.readAllLines(Path.of("./Data/results.txt"));
            System.out.println(lines);
            //List<String> lineContent = new ArrayList<>();
            for(String line: lines){
                lineContent = line.split("/");
                System.out.println(lineContent);
                //System.out.println(lineContent.get(0));
            }
            return lineContent;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
