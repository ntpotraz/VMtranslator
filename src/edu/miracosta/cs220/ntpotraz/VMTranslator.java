package edu.miracosta.cs220.ntpotraz;

import java.util.Scanner;

public class VMTranslator {
    public static void main(String[] args) {
        //Gather the name of the file from the user
        String fileName;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        fileName = keyboard.nextLine();

        //Parses through the vm file and writes Assembly code based on it to an asm file
	    Parser p = new Parser(fileName);
	    CodeWriter cw = new CodeWriter(fileName);

	    //Checks to see if the vm file has more lines to parse
	    while(p.hasMoreCommands()) {
	        p.advance(); //Advances to the next line, or the first line on first instance
	        switch(p.commandType()) {
	            //Writes for Push and Pop commands
                case C_PUSH:
                case C_POP:
                    cw.writePushPop(p.commandType(), p.getArg1(), p.getArg2());
                    break;
                //Writes for Arithmetic commands
                case C_ARITHMETIC:
                    cw.writeArithmetic(p.getArg1());
                    break;
                default:
                    break;
            }
        }
        cw.close(); //Closes and saves the asm file
    }
}
