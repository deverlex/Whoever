package vn.whoever.models.supports;

/**
 * Created by spider man on 4/4/2016.
 */
public enum Privacies {

    secret("secret"),
    normal("normal"),
    open("open");

    private String privacy;

    private Privacies(final String privacy) {
        this.privacy = privacy;
    }

    public String getPrivacies() {
        return this.privacy;
    }

    @Override
    public String toString() {
        return this.privacy;
    }

    public String getName() {
        return this.getName();
    }
}
