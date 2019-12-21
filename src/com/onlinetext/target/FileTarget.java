package com.onlinetext.target;

import java.io.*;

public class FileTarget implements Target {
    private String filename;

    public FileTarget(String filename) {
        this.filename = filename;
    }

//    public static void main(String args[]) {
//        FileTarget rw = new FileTarget();
////        rw.appendToFile("sanjay.txt","hello there");
//          rw.writeToFile("sanjay.txt","abcd");
//          rw.appendToFile("sanjay.txt"," ABCD");
//          System.out.println(rw.readFile("sanjay.txt"));
//    }

    @Override
    public String getText() throws IOException {
        String contents = "", temp;
        try {
            File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            temp = reader.readLine();
            while (temp != null) {
                contents = temp;
                temp = reader.readLine();
            }
            return contents;
        } catch (Exception io) {
            return io.getMessage();
        }
    }

    @Override
    public boolean putText(String text) {
        try {
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            writer.close();
            return true;
        } catch (Exception io) {
            System.out.println(io.getMessage());
            return false;
        }
    }

    @Override
    public boolean appendText(String text) {
        try{
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            writer.write(text);
            writer.close();
            return true;
        } catch(Exception io){
            System.out.println(io.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "File: " + this.filename;
    }
}