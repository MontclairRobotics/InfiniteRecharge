public class FiniteStateMachine {
    public interface State {
        public void inState();

        public State nextState();

        public boolean isDone();

        public boolean isAcceptState();
    }
}