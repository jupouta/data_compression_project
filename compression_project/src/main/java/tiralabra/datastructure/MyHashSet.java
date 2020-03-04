package tiralabra.datastructure;

/**
 * The manually created HashSet.
 */
public class MyHashSet {

    /**
     * The list of elements as nodes in a linked list. Every node points to
     * another in a key (index) of the list.
     */
    public HashNode<String>[] theList;
    int items;
    int bound;

    public MyHashSet() {
        this.bound = 10;
        this.theList = new HashNode[this.bound];
        this.items = 0;
    }

    public void add(String value) {

        // make the list bigger if bound is met
        if (this.items == this.bound) {
            this.bound *= 2;

            HashNode<String>[] prev = this.theList;
            this.theList = new HashNode[this.bound];

            // copy
            for (HashNode<String> node : prev) {
                while (node != null) {
                    add(node.value);
                    node = node.next;
                }
            }
        }

        HashNode<String> entry = new HashNode<>(value, null);

        int hCode = this.hashCode(value);
        int placing = hCode % this.bound;

        HashNode<String> givenNode = theList[placing];

        if (givenNode == null) {
            theList[placing] = entry;
            items++;
        } else {
            while (givenNode.next != null) {
                if (givenNode.value.equals(value)) {
                    return; // if already exists, nothing to do
                }

                givenNode = givenNode.next;
            }
            //if (givenNode.key.equals(key)) {
            //    givenNode.value = value;
            //} else {
            givenNode.next = entry;
            items++;
            //}
        }
    }

    /**
     * Count the hash code of the string. Use the string's hash code for
     * counting the hash code.
     *
     * @param elem The string for which to count the hash code.
     * @return The hash code as an integer value.
     */
    public int hashCode(String elem) {
        int hash = 7;
        hash = 37 * hash + (elem == null ? 0 : hashCodeForString(elem));
        return hash;
    }

    /**
     * Count the hash code for a specific string. Represents the internal hash
     * code.
     *
     * @param elem The string element for which to count the hash code.
     * @return The hash code as an integer value.
     */
    public int hashCodeForString(String elem) {
        int number = 0;
        int elemLength = elem.length();

        for (int i = 0; i < elemLength; i++) {
            int ascii = (int) elem.charAt(i);

            number += (ascii * (Math.pow(2.0, elemLength - 1 + i)));
        }

        return number % this.bound;

    }

    /**
     * Returns true or false depending if the given string is in the hash set.
     *
     * @param value The string that we want to check.
     * @return True or false if the string is included in the array.
     */
    /*
    public boolean contains(String elem) {
        if (this.theList[(int) this.hashCode(elem) % this.bound] != null) {
            return true;
        }
        return false;
    }*/
    public boolean contains(String value) {
        HashNode<String> bucket = this.theList[hashCode(value) % this.bound];

        while (bucket != null) {
            if (value.equals(bucket.value)) {
                return true;
            }
            bucket = bucket.next;
        }
        return false;
    }

}
