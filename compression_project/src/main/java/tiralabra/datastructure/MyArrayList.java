
package tiralabra.datastructure;


/**
 * The manually created array list
 * based on the simple array data structure.
 */
public class MyArrayList extends Object {
    Object[] lista;
    
    public MyArrayList () {
        this.lista = new Object[10];
    }
    
    public Object get(int index) {
        return this.lista[index];
    }
    
    /**
     * Adds a new string element at the end 
     * of the array list.
     * If the list is full, double the length.
     * @param elem the string to be added in
     *             the array.
     */
    public void add(Object elem) {
        for (int i = 0; i < this.lista.length; i++) {
            if (this.lista[i] == null) {
                this.lista[i] = elem;
                return;
            }
        }
        
        Object[] newArray = new String[this.lista.length * 2];
        int lastIndex = this.lista.length;
        
        for (int ind = 0; ind < this.lista.length; ind++) {
            newArray[ind] = this.lista[ind];
        }
        
        newArray[lastIndex] = elem;
        
        this.lista = newArray;
    }
    
    public int length() {
        return this.lista.length;
    }
    
    public Object[] getLines() {
        return this.lista;
    }
}
