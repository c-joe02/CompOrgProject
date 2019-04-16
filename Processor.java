/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processor;

import java.util.*;
import java.io.*;

/**
 *
 * @author Benson
 */
public class Processor {

    /**
     * Main memory of the simulated computer
     */
    private Memory memory;

    /**
     *
     * @param mem
     */
    public Processor(int mem) {
        memory = new Memory(cap);
    }

    private int[] reg = new int[8];

    private int PC;

    /**
     *
     * @return
     */
    public int getPC() {
        return PC;
    }

    /**
     *
     * @param PC
     */
    public void setPC(int PC) {
        this.PC = PC;
    }

    private int IR = PC++;

    /**
     *
     * @return
     */
    public int getIR() {
        return IR;
    }

    /**
     *
     * @param IR
     */
    public void setIR(int IR) {
        this.IR = IR;
    }

    /**
     *
     * @return
     */
    public boolean step() {
        if (IR == 0) {
            return false;
        } else {
            while (IR != 0) {
                PC++;
            }
        }
        return true;
    }

    /**
     *
     */
    public void dump() {
        
    }

    public static void main(String[] args) {

    }
}
