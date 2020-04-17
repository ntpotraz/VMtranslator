package edu.miracosta.cs220.ntpotraz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
    private BufferedWriter outputFile;
    private String file;
    private int eqNumber = 1;
    private int gtNumber = 1;
    private int ltNumber = 1;

    public CodeWriter(String fileName) {
        file = fileName.substring(0, fileName.indexOf("."));

        String asmName = fileName;
        asmName = asmName.substring(0, asmName.indexOf("."));
        asmName = asmName + ".asm";

        try {
            outputFile = new BufferedWriter(new FileWriter(asmName));
        } catch(IOException e) {
            e.getMessage();
        }
    }

    public void close() {
        try {
            outputFile.close();
        } catch(IOException e) {
            e.getMessage();
        }
    }

    public void writeArithmetic(String command) {

        try {
            switch (command) {
                case "add":
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("A=M-1");
                    outputFile.newLine();
                    outputFile.write("D=M");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=D+M");
                    outputFile.newLine();
                    outputFile.write("AD=A+1");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("M=D");
                    outputFile.newLine();
                    break;
                case "sub":
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("A=M-1");
                    outputFile.newLine();
                    outputFile.write("D=M");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=M-D");
                    outputFile.newLine();
                    outputFile.write("AD=A+1");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("M=D");
                    outputFile.newLine();

                    break;
                case "neg":
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("A=M-1");
                    outputFile.newLine();
                    outputFile.write("M=-M");
                    outputFile.newLine();

                    break;
                case "eq":
                    String equalLabel = "EQUAL" + eqNumber;
                    String equalEndLabel = "EQUALEND" + eqNumber;
                    eqNumber++;

                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("A=M-1");
                    outputFile.newLine();
                    outputFile.write("D=M");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("D=D-M");
                    outputFile.newLine();
                    outputFile.write("@" + equalLabel);
                    outputFile.newLine();
                    outputFile.write("D;JEQ");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("AM=M-1");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=0");
                    outputFile.newLine();
                    outputFile.write("@" + equalEndLabel);
                    outputFile.newLine();
                    outputFile.write("0;JMP");
                    outputFile.newLine();
                    outputFile.write("(" + equalLabel + ")");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("AM=M-1");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=-1");
                    outputFile.newLine();
                    outputFile.write("(" +equalEndLabel + ")");
                    outputFile.newLine();

                    break;
                case "gt":
                    String greaterThanLabel = "GREATERTHAN" + gtNumber;
                    String greaterThanEndLabel = "GREATERTHANEND" + gtNumber;
                    gtNumber++;

                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("A=M-1");
                    outputFile.newLine();
                    outputFile.write("D=M");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("D=D-M");
                    outputFile.newLine();
                    outputFile.write("@" + greaterThanLabel);
                    outputFile.newLine();
                    outputFile.write("D;JLT");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("AM=M-1");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=0");
                    outputFile.newLine();
                    outputFile.write("@" + greaterThanEndLabel);
                    outputFile.newLine();
                    outputFile.write("0;JMP");
                    outputFile.newLine();
                    outputFile.write("(" + greaterThanLabel + ")");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("AM=M-1");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=-1");
                    outputFile.newLine();
                    outputFile.write("(" + greaterThanEndLabel + ")");
                    outputFile.newLine();

                    break;
                case "lt":
                    String lessThanLabel = "LESSTHAN" + ltNumber;
                    String lessThanEndLabel = "LESSTHANEND" + ltNumber;
                    ltNumber++;

                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("A=M-1");
                    outputFile.newLine();
                    outputFile.write("D=M");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("D=D-M");
                    outputFile.newLine();
                    outputFile.write("@" + lessThanLabel);
                    outputFile.newLine();
                    outputFile.write("D;JGT");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("AM=M-1");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=0");
                    outputFile.newLine();
                    outputFile.write("@" + lessThanEndLabel);
                    outputFile.newLine();
                    outputFile.write("0;JMP");
                    outputFile.newLine();
                    outputFile.write("(" + lessThanLabel + ")");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("AM=M-1");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=-1");
                    outputFile.newLine();
                    outputFile.write("(" + lessThanEndLabel + ")");
                    outputFile.newLine();

                    break;
                case "and":
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("AM=M-1");
                    outputFile.newLine();
                    outputFile.write("D=M");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=D&M");
                    outputFile.newLine();

                    break;
                case "or":
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("AM=M-1");
                    outputFile.newLine();
                    outputFile.write("D=M");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=D|M");
                    outputFile.newLine();

                    break;
                case "not":
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("A=M-1");
                    outputFile.newLine();
                    outputFile.write("M=!M");
                    outputFile.newLine();

                    break;

                default:
                    break;
            }
            outputFile.write("//----------------");
            outputFile.newLine();
        } catch(IOException e) {
            e.getMessage();
        }
    }

    public void writePushPop(CommandType commandType, String segment, int index) {
        try {
            switch (commandType) {
                case C_POP:

                    switch (segment) {
                        case "local":
                            String address = "@" + index;
                            outputFile.write(address);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@LCL");
                            outputFile.newLine();
                            outputFile.write("A=M+D");
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("AM=M-1");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();

                            break;
                        case "argument":
                            outputFile.write("@" + index);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@ARG");
                            outputFile.newLine();
                            outputFile.write("D=M+D");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("AM=M-1");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();

                            break;

                        case "this":
                            outputFile.write("@" + index);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@THIS");
                            outputFile.newLine();
                            outputFile.write("D=M+D");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("AM=M-1");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();

                            break;
                        case "that":
                            outputFile.write("@" + index);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@THAT");
                            outputFile.newLine();
                            outputFile.write("D=M+D");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("AM=M-1");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();

                            break;
                        case "temp":
                            int addr = 5 + index;
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("AM=M-1");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@" + addr);
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();

                            break;
                        case "pointer":
                            if(index == 0) {
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("AM=M-1");
                                outputFile.newLine();
                                outputFile.write("D=M");
                                outputFile.newLine();
                                outputFile.write("@THIS");
                                outputFile.newLine();
                                outputFile.write("M=D");
                                outputFile.newLine();
                            } else if(index == 1) {
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("AM=M-1");
                                outputFile.newLine();
                                outputFile.write("D=M");
                                outputFile.newLine();
                                outputFile.write("@THAT");
                                outputFile.newLine();
                                outputFile.write("M=D");
                                outputFile.newLine();
                            }

                            break;
                        case "static":
                            String fileName = "@" + file + "." + index;
                            /*outputFile.write(fileName);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("AM=M-1");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine(); */
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("AM=M-1");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write(fileName);
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();

                            break;

                    }
                    break;
                case C_PUSH:

                    switch (segment) {
                        case "constant":
                            String address = "@" + index;
                            if (index == 0 || index == 1 || index == -1) {
                                String binaryIndex = "M=" + index;
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("A=M");
                                outputFile.newLine();
                                outputFile.write(binaryIndex);
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("M=M+1");
                                outputFile.newLine();
                            } else {
                                outputFile.write(address);
                                outputFile.newLine();
                                outputFile.write("D=A");
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("A=M");
                                outputFile.newLine();
                                outputFile.write("M=D");
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("M=M+1");
                                outputFile.newLine();
                            }
                            break;
                        case "static":
                            String fileName = "@" + file + "." + index;
                            outputFile.write(fileName);
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("M=M+1");
                            outputFile.newLine();

                            break;
                        case "pointer":
                            if(index == 0) {
                                outputFile.write("@THIS");
                                outputFile.newLine();
                                outputFile.write("D=M");
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("A=M");
                                outputFile.newLine();
                                outputFile.write("M=D");
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("M=M+1");
                            } else if(index == 1) {
                                outputFile.write("@THAT");
                                outputFile.newLine();
                                outputFile.write("D=M");
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("A=M");
                                outputFile.newLine();
                                outputFile.write("M=D");
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("M=M+1");
                            }

                            break;
                        case "this":
                            outputFile.write("@" + index);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@THIS");
                            outputFile.newLine();
                            outputFile.write("A=M+D");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("M=M+1");
                            outputFile.newLine();

                            break;
                        case "that":
                            outputFile.write("@" + index);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@THAT");
                            outputFile.newLine();
                            outputFile.write("A=M+D");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("M=M+1");
                            outputFile.newLine();

                            break;
                        case "local":
                            outputFile.write("@" + index);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@LCL");
                            outputFile.newLine();
                            outputFile.write("A=M+D");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("M=M+1");
                            outputFile.newLine();

                            break;
                        case "argument":
                            outputFile.write("@" + index);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@ARG");
                            outputFile.newLine();
                            outputFile.write("A=M+D");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("M=M+1");
                            outputFile.newLine();

                            break;
                        case "temp":
                            int addr = 5 + index;
                            outputFile.write("@" + addr);
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("M=M+1");

                            break;
                        default:
                            break;

                    }

                    break;
                default:
                    break;
            }
            outputFile.write("//----------------");
            outputFile.newLine();
        } catch(IOException e) {
            e.getMessage();
        }
    }

}
