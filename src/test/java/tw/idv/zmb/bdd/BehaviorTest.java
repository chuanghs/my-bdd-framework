package tw.idv.zmb.bdd;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static tw.idv.zmb.bdd.Behavior.*;

public class BehaviorTest {
    @RegisterExtension
    Mockery context = new JUnit5Mockery();
    private Runnable runnable = context.mock(Runnable.class);

    @Test
    public void shouldCreateNewScenarioInstance(){
        Behavior first = Scenario();
        Behavior second = Scenario();
        assertThat(first).isNotNull().isNotSameAs(second);
    }

    @Test
    public void shouldExecuteGivenStatements() {
        context.checking(new Expectations(){{
            oneOf(runnable).run();
        }});


        Behavior behavior = Scenario().Given("some pre-condition description", runnable);

        assertThat(behavior).isInstanceOf(Behavior.class).isNotNull();
        context.assertIsSatisfied();
    }

    @Test
    public void shouldProvideAnnotationWhenGivenStatementFail(){
        context.checking(new Expectations(){{
            oneOf(runnable).run();will(throwException(new RuntimeException()));
        }});
        BehaviorException thrown = assertThrows(BehaviorException.class, () ->
            Scenario().Given("some pre-condition description", runnable)
        );
        assertThat(thrown).hasMessage("some pre-condition description");
    }


}
