
package tiralabra.datastructure;

import java.util.Arrays;



public class MyHash {
    public String[] theList;
    int items;
    long bound;
    long limit;
    
    public MyHash() {
        this.limit = 100000000l;
        this.bound = 100000000l;
        this.theList = new String[(int) this.bound];
        this.items = 0;
    }
    
    public void add(String elem) {
        long hashCode = this.hashCode(elem);
        this.theList[(int) hashCode] = elem;
        this.items++;

        if ((this.items >= this.bound)) {
            this.bound *= 2;
            String[] a = new String[(int) this.bound];
            
            // copy
            for (int i = 0; i < this.items; i++) {
                a[i] = this.theList[i];
            }
            this.theList = a;
        }

    }
    
    
    public long hashCode(String elem) {
        long number = 0l;
        
        for (int i = 0; i < elem.length(); i++) {
            int ascii = (int) elem.charAt(i);
            
            number += (ascii * (Math.pow(7, elem.length()-1+i)));
        }
        
        return (long) number % this.limit;
    }
    
    public boolean contains(String elem) {
        if (this.theList[(int) this.hashCode(elem)] != null) return true;
        return false;
    }

}
