package helper.cast;

/**
  * The class <code>ClassCast</code> cast object
  * @version 1.0
  * @author Dorian Terbah 
**/

public class ClassCast {

    /**
      * Return the object with the cast of T
      * @param clazz The class
      * @param object The object to cast 
    **/
    public static final <T> T castObject(Class<T> clazz, Object object) {
        return (T)object;
    }
}