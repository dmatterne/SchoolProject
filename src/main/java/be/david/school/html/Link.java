package be.david.school.html;

import be.david.school.Enums.HtmlLanguages;
import be.david.school.Enums.Targets;
import be.david.school.interfaces.FormElement;

/**
 * Created by David on 21/08/2016.
 */
public class Link implements FormElement{

    private String href;
    private Targets target;
    private String label;
    private HtmlLanguages hreflang;
    private boolean download;

    public Link(String href, String label) {
        this.href = href;
        this.label = label;
        this.target = Targets._blank;
        this.download = false;
    }

    public Link(String href, String label, Targets target) {
        this.href = href;
        this.target = target;
        this.label = label;
        this.download = false;
    }

    public Link(String href, String label, boolean download) {
        this.href = href;
        this.label = label;
        this.download = download;
        this.target = Targets._blank;
    }

    public Link(String href, String label, Targets target, HtmlLanguages hreflang, boolean download) {
        this.href = href;
        this.target = target;
        this.label = label;
        this.hreflang = hreflang;
        this.download = download;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Targets getTarget() {
        return target;
    }

    public void setTarget(Targets target) {
        this.target = target;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public HtmlLanguages getHreflang() {
        return hreflang;
    }

    public void setHreflang(HtmlLanguages hreflang) {
        this.hreflang = hreflang;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    @Override
    public String getHtml() {

        String url = "<a " +
                "href='" + this.href + "' " +
                "target='" + this.target + "' ";
        if(this.hreflang != null ) {
           url +=  "hreflang='" + this.hreflang + "' ";
        }
        if (this.download) {
            url += "download ";
        }
        url += "> " + this.label +
                "</a> ";

        return url;
    }
}
