package console;

import java.util.*;
import java.io.*;

public class Processor {

    /**
     * Main memory of the simulated computer
     */
    
    private int PC = 0;// program counter, or instruction address register
    private int IR = 0;// the instruction register, stores current instruction
    private int[] reg = new int[8]; //8 registers
    private Memory memory;

    /**
     *
     * @param mem
     */
    public Processor() {
        int cap = 256;
    }
    
   
    
    public boolean step() { //steps through each instruction address
        IR = memory.read(PC++);
        if (IR == 0) {
            return false;    //halt program
        }
        return true;   //continue to execute next IR
    }

    public void dump() {// essentially displays all registers
        for (int i = 0; i < reg.length; i++) {
            System.out.println("reg[" + Integer.toString(i) + "] = " + Integer.toHexString(reg[i]));
        }
        System.out.println("PC = " + Integer.toHexString(PC));
        System.out.println("IR = " + Integer.toHexString(IR));
    }
    
    public int pab(int n, int removeR, int keep){
//        int shiftRight = n/((int)Math.pow(2, removeR));
        int shiftLeft = n * (int) Math.pow(2, (32-keep));
        int shiftRight = shiftLeft/((int)Math.pow(2, 32-keep));
        return shiftLeft;//see how to turn off bits and keep the same
    } 
    
    
    public void decode(){
        
    }
    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getIR() {
        return IR;
    }

    public void setIR(int IR) {
        this.IR = IR;
    }

    public int[] getReg() {
        return reg;
    }

    public void setReg(int[] reg) {
        this.reg = reg;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }
    public static void main(String[] args) {
        Processor p = new Processor();
        int n = 13;
        System.out.println(Integer.toBinaryString(n));
        int ans = p.pab(n, 2,3);
        System.out.println(Integer.toBinaryString(ans));
    }
    
    //decoder goes here -Benson
}

