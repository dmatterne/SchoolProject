package be.david.school.html;

import be.david.school.interfaces.FormElement;

/**
 * Created by David on 21/08/2016.
 */
public class Image implements FormElement{

    private String alt_text;
    private String height;
    private boolean ismap;
    private String longdesc;
    private String src;
    private String sizes;
    private String srcset;
    private String width;
    private String usemap;

    public Image(String alt_text, String height, String src, String width) {
        this.alt_text = alt_text;
        this.height = height;
        this.src = src;
        this.width = width;
    }



    @Override
    public String getHtml() {
        return null;
    }
}
