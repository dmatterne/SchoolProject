package be.david.school.Enums;

/**
 * Created by David on 21/08/2016.
 */
public enum Encryption {
    URL_ENC("application/x-www-form-urlencoded"),
    FORM_DATA("multipart/form-data"),
    PLAIN("text/plain");

    private final String value;

    Encryption(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
