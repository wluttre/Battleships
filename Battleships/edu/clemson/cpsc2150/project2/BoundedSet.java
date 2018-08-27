package edu.clemson.cpsc2150.project2;


/**
 * Created by Owner on 9/20/2016.
 */
public class BoundedSet {

    //edited from the lab version

    Coordinate [] contents;
    int count;

    public BoundedSet(int length)
    {
        count = 0;
        contents = new Coordinate[length];
    }


    /**
     * @param element Element to be inserted into the set.
     * @requires
     * element != null and [element is not in the set.] and
     * [The set is not full.]
     * @ensures <pre>
     * [element is unchanged.] and [element is inserted into the set.]
     * </pre>
     */
    public void insert(Coordinate element) {
        if(contents[0] == null){

            contents[0] = element;
            ++count;
            return;
        }
        else{

            for(int i = 0; i < contents.length; ++i)
            {
                if(contents[i] == null) {
                    contents[i] = element;
                    ++count;
                    return;
                }

            }
        }
    }


    /**
     * @param element Element to find in the set.
     * @return Indicator of element presence in the set.
     * @requires
     * element != null
     * @ensures <pre>
     * [element is unchanged.] and
     * [The return is true if element was found in the set; otherwise
     * the return is false.]
     * </pre>
     */
    public boolean contains(Coordinate element) {
        //if(contents[0] == null)
            //return false;
        for(int i = 0; i < count ; ++i) {
            if(contents[i] != null) {
                if (contents[i].equals(element))
                    return true;
            }
        }
        return false;
    }



    /**
     * @param element Element to remove from the set.
     * @requires
     * element != null and [element is in the set.]
     * @ensures <pre>
     * [element is unchanged.] and [element is removed from the set.]
     * </pre>
     */
    public void remove(Coordinate element) {
        for(int i = 0; i < count; ++i){
            if(contents[i].equals(element))
            {
                contents[i] = contents[count-1];
                contents[count-1] = null;
                --count;
                return;
            }
        }
    }


    /**
     * @return The size of the set.
     * @requires true
     * @ensures <pre>
     * [The contents of the set are unchanged.] and
     * [The return equals the number of elements in the set.]
     * </pre>
     */
    public int sizeOfSet() {

        return count;
    }


    /**
     * @return The element removed from the set.
     * @requires [The set is not empty.]
     * @ensures <pre>
     * [An arbitrarily chosen element is removed from the set; this element
     * is returned to the caller.]
     * </pre>
     */
    public Coordinate removeAny() {

        Coordinate temp = contents[count-1];
        contents[count-1] = null;
        --count;
        return temp;
    }


    /**
     * @return The stringified contents of the set.
     * @requires true
     * @ensures <pre>
     * [The contents of the set are unchanged.] and
     * [The contents of the set are returned as a string.]
     * </pre>
     */
    public String toString() {
        String set = "{";
        for(int i = 0; i < count; ++i)
        {
            if(contents[i] != null)
                set += contents[i] + "),";
        }
        set += "}";
        return set;
    }



    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BoundedSet)
        {
            BoundedSet other = (BoundedSet) obj;
            if(this.sizeOfSet() == 0 && other.sizeOfSet() == 0)
                return true;
            else if(this.sizeOfSet() != other.sizeOfSet())
                return false;

            for(int j = 0; j < count; ++j)
            {
                if(!(other.contains(contents[j]))) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }
}

