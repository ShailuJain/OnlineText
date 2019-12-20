package com.onlinetext.client;
import java.io.*;

public class ReadWrite {

    public String readFile(String filename) {
        String contents = "", temp;
        try {
            File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            temp = reader.readLine();
            while (temp != null) {
                contents = temp;
                temp = reader.readLine();
            }

        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }

    public void writeToFile(String filename, String contents) {
        try {
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(contents);
            writer.close();

        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendToFile(String filename, String contents){
        try{
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));

            writer.write(contents);
            writer.close();
        }
        catch(IOException io){
            io.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ReadWrite rw = new ReadWrite();
//        rw.appendToFile("sanjay.txt","hello there");
          rw.writeToFile("sanjay.txt","abcd");
          rw.appendToFile("sanjay.txt"," ABCD");
          System.out.println(rw.readFile("sanjay.txt"));
    }
}