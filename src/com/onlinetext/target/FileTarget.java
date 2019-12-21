package com.onlinetext.target;

import com.onlinetext.exception.TargetException;

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
    public String getText() throws TargetException {
        StringBuilder contents = new StringBuilder();
        String temp;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ((temp = reader.readLine()) != null) {
                contents.append(temp).append("\n");
            }
            return contents.toString();
        } catch (Exception io) {
            System.out.println("Something is bugging me in FileTarget" + io.getMessage());
            throw new TargetException("Could not get text from file");
        }
    }

    @Override
    public boolean putText(String text) throws TargetException {
        try {
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            writer.close();
            return true;
        } catch (Exception io) {
            System.out.println("Something is bugging me in FileTarget" + io.getMessage());
            throw new TargetException("Could not put text to file");
        }
    }

    public boolean appendText(String text) {
        try{
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            writer.write(text);
            writer.close();
            return true;
        } catch(Exception io){
            System.out.println("Something is bugging me in FileTarget" + io.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "File: " + this.filename;
    }
}