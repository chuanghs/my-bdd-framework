package tw.idv.zmb.bdd;

public class BehaviorException extends RuntimeException{
    public BehaviorException(String message, Exception ex) {
        super(message, ex);
    }
}
