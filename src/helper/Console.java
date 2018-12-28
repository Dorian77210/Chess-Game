package helper;

/**
  * The class <code>Console</code> is an helper to display messages to console
  * @version 1.0
  * @author Dorian Terbah
**/

public class Console {

    /**
      * Display a message to the console
      * @param element The element to display
    **/
    public static <T> void log(T element) {
        System.out.println(element);
    }
}