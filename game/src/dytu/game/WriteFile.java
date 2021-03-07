package dytu.game;

import java.io.*;

public class WriteFile {
    /*
    writes the hero's score to a results file
     */
    public void WriteFile(int n, String name){
        try{
            FileWriter fW = new FileWriter("./Data/results.txt", true);
            BufferedWriter bW = new BufferedWriter(fW);
            bW.write(n+", ");
            bW.write(name+"\n");
            System.out.println(n+" written to file");
            bW.close();
        }catch(Exception e){
            return;
        }
    }
    /*public static void main(String[] args){
        WriteFile wf = new WriteFile();
        wf.WriteFile(6, "Cranjis McBasketball");
    }

     */
}
