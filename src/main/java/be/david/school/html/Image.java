package be.david.school.html;

import be.david.school.interfaces.FormElement;

/**
 * Created by David on 21/08/2016.
 */
public class Image implements FormElement{

    private String src;
    private String height;
    private String width;
    private String alt_text;

    private boolean ismap;
//    private String longdesc;
//    private String sizes;
//    private String srcset;
    private String usemap;

    public Image(String src, String height, String width, String alt_text) {
        this.src = src;
        this.height = height;
        this.width = width;
        this.alt_text = alt_text;
    }




    @Override
    public String getHtml() {

        String img = "<img src='" + this.src +
                "' height='" + this.height +
                "' width='" + this. width +
                "' alt='" + this.alt_text +
                "'>";


        return img;
    }
}
