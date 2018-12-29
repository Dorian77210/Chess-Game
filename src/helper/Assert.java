package helper;


/**
  * The class <code>Assert</code> check if a variable is null, etc
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Assert {

    /**
      * Verify if a variable is null
      * @return True if it is, else false 
    **/
    public static final <T> boolean isNull(T t) {
        return (t == null);
    }

    /**
      * Verify if a variable is set
      * @return True if it is, else false 
    **/
    public static final <T> boolean isSet(T t) {
        return !isNull(t);
    }
}