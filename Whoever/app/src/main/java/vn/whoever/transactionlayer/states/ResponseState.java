package vn.whoever.transactionlayer.states;

/**
 * Created by spider man on 4/5/2016.
 */
public enum ResponseState {

    SUCCESS("success"),
    NOT_FOUND("not_found"),
    CONNECT_SERVER_FAIL("connect_fail");

    private String state;

    private ResponseState(final String state) {
        this.state = state;
    }

    public String getResponseState() {
        return this.state;
    }

    @Override
    public String toString() {
        return this.state;
    }

    public String getName() {
        return this.getName();
    }
}
