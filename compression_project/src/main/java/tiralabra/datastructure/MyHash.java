
package tiralabra.datastructure;



public class MyHash {
    boolean[] theList;
    int items;
    int bound;
    
    public MyHash(int limit) {
        this.bound = limit;
        this.theList = new boolean[this.bound];
        this.items = 0;
    }
    
    public void add(String elem) {
        int hashCode = this.hashCode(elem);
        System.out.println(hashCode);
        this.theList[hashCode] = true;
        this.items++;

        if ((this.items >= this.bound)) {
            this.bound *= 2;
            boolean[] a = new boolean[this.bound];
            
            // copy
            for (int i = 0; i < this.items; i++) {
                a[i] = this.theList[i];
            }
            this.theList = a;
        }
    }
    
    
    public int hashCode(String elem) {
        int number = 0;
        
        for (int i = 0; i < elem.length(); i++) {
            int ascii = (int) elem.charAt(i);
            
            number += ascii * (Math.pow(7, elem.length()-1+i));
        }
        
        return number;
    }

    
    
    
}
