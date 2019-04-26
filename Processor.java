package console;

import java.util.*;
import java.io.*;

public class Processor {

    /**
     * Main memory of the simulated computer
     */
    private int PC = 0;// program counter, or instruction address register
    private int IR = 0;// the instruction register, stores current instruction
    public int[] reg = new int[8]; //8 registers
    private Memory memory;

    private int p;
    private int a;
    private int b;

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

    public void getPAB(int n) {
        int srP = n / ((int) Math.pow(2, 8)); //removes the bits on the right we don't need
        int srA = n / ((int) Math.pow(2, 4));
        int srB = n / ((int) Math.pow(2, 0));

        this.p = (srP & 15);    //keeps the bits we want
        this.a = (srA & 15);
        this.b = (srB & 15);

    }

    public void decode(int n) {
        getPAB(n);
        //1. load a b // reg[a] = cell[reg[b]]
        switch (p) {
            case 1:
                reg[a] = memory.cell[reg[b]];
                break;
            //2. loadc a // reg[a] = cell[PC++]
            case 2:
                reg[a] = memory.cell[PC++];
                break;
            //3. store a b // cell[reg[a]] = reg[b]
            case 3:
                memory.cell[reg[a]] = reg[b];
                break;
                
            //4. add a b // reg[a] = reg[a] + reg[b]        
            case 4:
                reg[a] = reg[a] + reg[b];
                break;
                
            //5. mul a b // reg[a] = reg[a] * reg[b]
            case 5:
                reg[a] = reg[a] * reg[b];
                break;
                
            //6. sub a b // reg[a] = reg[a] - reg[b]
            case 6:
                reg[a] = reg[a] - reg[b];
                break;
                
            //7. div a b // reg[a] = reg[a] / reg[b], error if reg[b] == 0
            case 7:
                reg[a] = reg[a] / reg[b];
                break;
                
            //8. and a b // if (reg[a]!= 0&&reg[b]!=0) reg[a]=1 else reg[a]=0
            case 8:
                if (reg[a]!= 0&&reg[b]!=0){
                    reg[a]=1;
                }
                else{
                    reg[a]=0;
                }
                break;
                
            //9. or a b // if (reg[a]!=0||reg[b]!=0) reg[a]=1 else reg[a]=0
            case 9:
                if (reg[a] != 0 || reg[b] != 0) {
                    reg[a] = 1;
                } else {
                    reg[a] = 0;
                }
                break;
                
            //10. not a b // if (reg[b]!=0) reg[a]=0 else reg[a]=1
            case 10:
                if (reg[b] != 0) {
                    reg[a] = 0;
                } else {
                    reg[a] = 1;
                }
                break;

            //11. lshift a b // reg[a] = reg[b] << 1
            case 11:
                reg[a] = reg[b] * 2;
                break;
                
            //12. rshift a b // reg[a] = reg[b] >> 1
            case 12:
                reg[a] = reg[b] / 2;
                break;

            //13. bwc a b // reg[a] = reg[a] & reg[b]
            case 13:
                reg[a] = reg[a] & reg[b];
                break;

            //14. bwd a b // reg[a] = reg[a] | reg[b]
            case 14:
                reg[a] = (reg[a] | reg[b]);
                break;
                
            //15. if a b // if (reg[a] != 0) pc = reg[b]
            case 15:
                if (reg[a] != 0) {
                    PC = reg[b];
                }
                break;
            
            case 0:
                
            //0. halt // stop fetch-execute cycle
                
            default:

        }

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
        int n = 0b101011010110;
        System.out.println(Integer.toBinaryString(n));
        p.getPAB(n);
        System.out.println("p: " + Integer.toBinaryString(p.p) + "\ta: " + Integer.toBinaryString(p.a) + "\tb: " + Integer.toBinaryString(p.b));
    }
}

