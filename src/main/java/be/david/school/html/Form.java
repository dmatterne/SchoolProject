package be.david.school.html;

import be.david.school.Enums.Encryption;
import be.david.school.Enums.FormMethods;
import be.david.school.Enums.OnOff;
import be.david.school.Enums.Targets;
import be.david.school.interfaces.FormElement;

/**
 * Created by David on 21/08/2016.
 */
public class Form {
    private String innerContent = "";
    private FormMethods method;
    private String action;
    private Targets target;
    private OnOff autoComplete;
    private Encryption enctype;

    public Form(FormMethods method, String action, Targets target, OnOff autoComplete, Encryption enctype) {
        this.method = method;
        this.action = action;
        this.target = target;
        this.autoComplete = autoComplete;
        this.enctype = enctype;
        startForm();

    }

    public void addItem(FormElement item) {
        innerContent += item.getHtml();
    }

    public void startForm() {
        innerContent += "<form method = '" + this.method +
                "' action='" + this.action +
                "' target='" + this.target +
                "' autocomplete='" + this.autoComplete +
                "' enctype='" + this.enctype +
                "'>";

    }

    public String getForm() {
        return innerContent += innerContent + "</form>";
    }
}
