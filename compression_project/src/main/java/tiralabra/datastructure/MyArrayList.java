package tiralabra.datastructure;

// See below why this is used
import java.lang.reflect.Array;

/**
 * The manually created array list based on the simple array data structure.
 *
 * @param <T> The class of the variable type used, e.g. String, Integer.
 */
public class MyArrayList<T> {

    private T[] lista;
    private int items;
    /**
     * Variable needed for making the list bigger. When the bound is met, the
     * list has to be doubled.
     */
    private int bound;
    /**
     * The type of variable to be used for the array list.
     */
    private final Class<T> typeClass;

    public MyArrayList(Class<T> c) {
        this.typeClass = c;
        this.bound = 100;
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(c, this.bound);
        this.lista = a;
        this.items = 0;

    }

    /**
     * Return toString version of this array list.
     * @return toString, which includes the list, the item count in the list,
     * and the bound at the moment.
     */
    @Override
    public String toString() {
        return "MyArrayList{" + "lista=" + this.lista + ", items=" + this.items
                + ", bound=" + this.bound + '}';
    }

    /**
     * Return the element in the given index.
     *
     * @param index The index of the element as an integer.
     * @return The element at the given index.
     */
    public T get(int index) {
        if (index > this.items) {
            return null;
        }
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

        // if array list too small
        if (this.items >= this.bound) {
            this.bound *= 2;

            // I wasn't able to make the array list accept any data type
            // (strings, integers, etc.), which is the reason why this Array
            // method is used.
            // Other solution would have been to make this array list only for
            // strings. Then there wouldn't have been any need for this method,
            // but I needed this for integers as well.
            @SuppressWarnings("unchecked")
            final T[] a = (T[]) Array.newInstance(this.typeClass, this.bound);

            // copy
            for (int i = 0; i < this.items; i++) {
                a[i] = this.lista[i];
            }
            this.lista = a;
        }
    }

    /**
     * Returns the number of items in the list.
     *
     * @return The number of items as an integer.
     */
    public int length() {
        return this.items;
    }

    /**
     * Return the list as an array. As the array list contains objects of any
     * variable type, the list has to be copied to an array.
     *
     * @return The array version of the array list.
     */
    public T[] toArray() {
        final T[] a = (T[]) Array.newInstance(this.typeClass, this.items);
        for (int i = 0; i < this.items; i++) {
            a[i] = this.lista[i];
        }
        return a;
    }

}
