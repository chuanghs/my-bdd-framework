package tw.idv.zmb.bdd;

/**
 * A very simple BDD DSL
 */
public class Behavior {
    private Behavior() {}

    public static Behavior Scenario() {
        return new Behavior();
    }

    public Behavior Given(String annotation, Runnable runnable) {
        return execute(annotation, runnable);
    }

    public Behavior When(String annotation, Runnable runnable) {
        return execute(annotation, runnable);
    }

    public Behavior Then(String  annotation, Runnable runnable) {
        return execute(annotation, runnable);
    }

    public Behavior ThenSuccess(Runnable runnable) {
        return execute("result", runnable);
    }

    public Behavior ThenFailure(String annotation, Runnable runnable) {
        return execute(annotation, runnable);
    }

    public Behavior ThenFailure(Runnable runnable) {
        return execute("result", runnable);
    }

    private Behavior execute(String annotation, Runnable runnable) {
        try {
            runnable.run();
        }catch (Exception e){
            throw new BehaviorException(annotation, e);
        }
        return this;
    }
}
