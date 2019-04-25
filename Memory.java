package memory;

public class Memory {

    private int cap = 256;  //capacity 256 by default
    private int[] cell = new int[cap];  //instruction set
    
    public Memory(int cap) {
        
    }

    public int read(int addr) {    //inputs instruction address
        return cell[addr];  //returns instruction

    }

    public int write(int addr, int data) { //address and new data to be written
        return cell[addr] = data;
    }
    
    public void dump(){
        //I'm not sure how it works here -Benson
          for(int i=0;i<cap;i++ ){
            System.out.println("cell[" + Integer.toHexString(i) + "] = " + Integer.toHexString(cell[i]));  
        }
        
    } 

}
