package vn.whoever.models.supports;

/**
 * Created by spider man on 4/4/2016.
 */
public enum Genders {
    unknown("unknown"),
    male("male"),
    female("female");

    private String sex;

    private Genders(final String sex) {
        this.sex = sex;
    }

    public String getGenders() {
        return this.sex;
    }

    @Override
    public String toString() {
        return this.sex;
    }

    public String getName() {
        return this.getName();
    }
}
