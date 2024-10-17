package org.vaadin.aes.view.core.cssdesign;

import com.vaadin.flow.component.HtmlComponent;

public class CssDesign {

    @Deprecated
    public static void setMargin(HtmlComponent component) {
        component.getStyle().set("margin", "0px 0px 0px 15px");
    }

    @Deprecated
    public static void setMargin(HtmlComponent component, int top, int rightleft, int bottom) {
        String param = getPxText(top) + " " + getPxText(rightleft) + " " + getPxText(bottom);
        component.getStyle().set("margin", param);
    }

    @Deprecated
    public static void setMargin(HtmlComponent component, int top, int right, int bottom, int left) {
        String param = getPxText(top) + " " + getPxText(right) + " " + getPxText(bottom) + " " + getPxText(left);
        component.getStyle().set("margin", param);
    }

    @Deprecated
    public static void setPadding(HtmlComponent component, int top, int right, int bottom, int left) {
        String param = getPxText(top) + " " + getPxText(right) + " " + getPxText(bottom) + " " + getPxText(left);
        component.getStyle().set("padding", param);
    }

    @Deprecated
    public static void setPadding(HtmlComponent component) {
        component.getStyle().set("padding", "0px 0px 0px 15px");
    }

    @Deprecated
    private static String getPxText(int val) {
        return val + "px";
    }
}
