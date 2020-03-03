
package tiralabra.datastructure;


public class MyHash {
    public String[] theList;
    int items;
    int bound;
    int limit;
    
    public MyHash() {
        this.limit = 100000000;
        this.bound = 100000000;
        this.theList = new String[this.bound];
        this.items = 0;
    }
    
    public void add(String elem) {
        
        int hashCode = this.hashCode(elem);
        this.theList[hashCode] = elem;
        this.items++;

//        if ((this.items >= this.bound)) {
//            this.bound *= 2;
//            String[] a = new String[(int) this.bound];
//            
//            // copy
//            for (int i = 0; i < this.items; i++) {
//                a[i] = this.theList[i];
//            }
//            this.theList = a;
//        }

    }
    
    
    public int hashCode(String elem) {
        int number = 0;
        int elemLength = elem.length();
        
        for (int i = 0; i < elemLength; i++) {
            int ascii = (int) elem.charAt(i);
            
            number += (ascii * (Math.pow(2.0, elemLength-1+i)));
        }
        
        return number % this.limit;
        
    }
    
    public boolean contains(String elem) {
        if (this.theList[(int) this.hashCode(elem)] != null) return true;
        return false;
    }

    @Override
    public String toString(){
        return theList.toString();
    }
}
