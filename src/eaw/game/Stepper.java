package eaw.game;

public class Stepper {
    public enum Step {WAIT, BEGIN, HOLD, END} // Chain of enums.
    private Step actualStep = Step.WAIT; // Initial step.
    private boolean changeBoolean = false;

    /** Check if step event was changed.
     * @return change test
     */
    public boolean isChanged() {
        return changeBoolean;
    }

    /** Change step.
     * @param step chosen step
     */
    private void changeTo(Step step) {
        actualStep = step;
        changeBoolean = true;
    }

    /** Update step event based on source (which is true/false, like mouse button for example).
     * You should use this in each frame when source can be checked.
     * Chain order of appearance is WAIT -> PRESS -> HOLD -> RELEASE.
     * @param source event source
     */
    public void update(boolean source) {
        changeBoolean = false;
        if (source) {
            if (actualStep == Step.BEGIN) {
                changeTo(Step.HOLD);
            }
            if (actualStep == Step.WAIT) {
                changeTo(Step.BEGIN);
            }
        } else {
            if (actualStep == Step.END) {
                changeTo(Step.WAIT);
            }
            if (actualStep == Step.HOLD) {
                changeTo(Step.END);
            }
        }
    }

    /** Get actual step enum of this stepper.
     * @return step
     */
    public Step getStep() {
        return actualStep;
    }

    /** Compare actual step to source. If it is the same, return true.
     * @param sourceStep comparable step
     * @return correctness between actual and source step
     */
    public boolean stepIsEqual(Step sourceStep) {
        return (this.actualStep == sourceStep);
    }

    /** Utility that returns actual step as a string.
     * @return step as string
     */
    public String getStepString() {
        String str = "";
        switch (actualStep) {
            case WAIT: { str = "WAIT"; break; }
            case BEGIN: { str = "PRESS"; break; }
            case HOLD: { str = "HOLD"; break; }
            case END: { str = "RELEASE"; break; }
        }
        return "<" + str + ">";
    }
}
