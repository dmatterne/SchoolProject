package be.david.school.Enums;

/**
 * Created by stannisbaratheon on 22/08/16.
 */
public enum InputTypes {
    TEXT("text")
    ,PASSWORD("password")
    ,RADIO("radio")
    ,CHECKBOX("checkbox")
    ,SUBMIT("submit")
    ,BUTTON("button")
    ,SEARCH("search")
    ,EMAIL("email")
    ,URL("url")
    ,TEL("tel")
    ,NUMBER("number")
    ,RANGE("range")
    ,DATE("date")
    ,MONTH("month")
    ,WEEK("week")
    ,TIME("time")
    ,DATETIME("datetime")
    ,DATETIMELOCAL("datetime-local")
    ,COLOR("color")
    ,FILE("file")
    ,HIDDEN("hidden")
    ,RESET("reset");



    private final String type;

    InputTypes(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
