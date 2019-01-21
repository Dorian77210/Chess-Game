package helper.cast;

/**
  * The class <code>CastClass</code> has to cast an object to another
  * @version 1.0
  * @author Dorian Terbah 
**/

public class CastClass {

    public static final <T> T castObject(Class<T> clazz, Object object) {
        return (T) object;
      }
}