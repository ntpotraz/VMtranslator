package edu.miracosta.cs220.ntpotraz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private String arg1;
    private int arg2;
    private Scanner inputFile;
    private String currentLine;

    public Parser(String fileName) {
        try {
            if(!fileName.contains(".vm")) {
                throw new FileNotFoundException();
            }
            inputFile = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.err.println("File Does not Exist!");
        }
    }

    public boolean hasMoreCommands() {
        return inputFile.hasNextLine();
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public CommandType commandType() {
        if(currentLine == null)
            return CommandType.C_NOCOMMAND;

        if(currentLine.contains("pop"))
            return CommandType.C_POP;
        else if(currentLine.contains("push"))
            return  CommandType.C_PUSH;
        else if(!currentLine.contains(" "))
            return CommandType.C_ARITHMETIC;
        return CommandType.C_NOCOMMAND;
    }

    public void advance() {
        if(!hasMoreCommands()) {
            System.out.println("End of the line!");
            return;
        }

        currentLine = inputFile.nextLine();
        //System.out.println(currentLine);

        if(currentLine.equals("") || currentLine.charAt(0) == '/') {
            advance();
            return;
        }

        if(currentLine.contains("//")) {
            int index = currentLine.indexOf("//");
            currentLine = currentLine.substring(0, index);
        }

        //Setting arg1 and arg2 for push and pop commands
        if(commandType() == CommandType.C_POP || commandType() == CommandType.C_PUSH)
           setPushPop();


        if(commandType() == CommandType.C_ARITHMETIC) {
            arg1 = currentLine;

            //Testing stuff
            System.out.println("---------------------\nARITH COMMAND!");
            System.out.println("arg1: " + arg1);
            System.out.println("vvvvvvvvvvvvvvvvvvvvv");
        }
    }

    //Helper method for setting the arg1 and arg2
    private void setPushPop() {
        //Gets rid of the 'pop ' from the string
        String tempArg = currentLine.substring(currentLine.indexOf(" ")+1);
        arg1 = tempArg.substring(0, tempArg.indexOf(" "));

        //Gets rid of arg1 from the remainder of the string and sets the number to arg2
        tempArg = tempArg.substring(tempArg.indexOf(" ")+1);
        arg2 = Integer.parseInt(tempArg);

        //Testing stuff
        if(commandType() == CommandType.C_POP)
            System.out.println("---------------------\nPOP COMMAND!");
        else
            System.out.println("---------------------\nPUSH COMMAND!");
        System.out.println("arg1: " + arg1);
        System.out.println("arg2: " + arg2);
        System.out.println("vvvvvvvvvvvvvvvvvvvvv");
    }

    public String getArg1() {
        return arg1;
    }

    public int getArg2() {
        return arg2;
    }
}
