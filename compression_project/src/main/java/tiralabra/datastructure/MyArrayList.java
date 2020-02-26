package tiralabra.datastructure;

import java.lang.reflect.Array;

/**
 * The manually created array list based on the simple array data structure.
 */
public class MyArrayList<T> {

    T[] lista;
    int items;
    int bound;
    Class<T> typeClass;
    

    public MyArrayList(Class<T> c) {
        this.typeClass = c;
        this.bound = 10;
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(c, this.bound);
        this.lista = a;
        this.items = 0;
        
    }

    @Override
    public String toString() {
        return "MyArrayList{" + "lista=" + this.lista + ", items=" + this.items
                + ", bound=" + this.bound + '}';
    }

    public T get(int index) {
        if (index > this.items) return null;
        return this.lista[index];
    }

    /**
     * Adds a new string element at the end of the array list. If the list is
     * full, double the length.
     *
     * @param elem the string to be added in the array.
     */
    public void add(T elem) {
        this.lista[this.items] = elem;
        this.items++;

        if (this.items >= this.bound) {
            this.bound *= 2;
            @SuppressWarnings("unchecked")
            final T[] a = (T[]) Array.newInstance(this.typeClass, this.bound);

            // copy
            for (int i = 0; i < this.items; i++) {
                a[i] = this.lista[i];
            }
            this.lista = a;
        }
    }

    public int length() {
        return this.items;
    }

    public T[] toArray() {
        final T[] a = (T[]) Array.newInstance(this.typeClass, this.items);
        for (int i = 0; i < this.items; i++) {
            a[i] = this.lista[i];
        }
        return a;
    }
    
}
