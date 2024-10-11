package org.vaadin.aes.view.home.core;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class CustomHtmlComponents {
    public static class DivUtil {
        private static final int maxSizeForPercent = 95;

        public Div getDivPx(String color, int width, int height) {
            Div div = getDivWithStyle(color);

            div.setHeight(height + "px");
            div.setWidth(width + "px");

            return div;
        }

        public static Div getDivPercent(String color, int width, int height) {
            Div div = getDivWithStyle(color);
            div.setHeight(height + "%");
            div.setWidth(width + "%");

            return div;
        }

        public static Div getDivAutoSize(String color) {
            return getDivPercent(color, maxSizeForPercent, maxSizeForPercent);
        }

        public static Div getDivAutoSize() {
            return getDivPercent("black", maxSizeForPercent, maxSizeForPercent);
        }

        private static Div getDivWithStyle(String color) {
            Div div = new Div();
            div.getStyle().set("border", "5px solid " + color);
//            div.getStyle().set("border-radius", "15px");
            return div;
        }
    }

    public static class PaddingUtil {
        public static Div getForWidth(int pxVal) {
            Div paddingDiv = new Div();
            paddingDiv.setWidth(pxVal + "px");
            return paddingDiv;
        }

        public static Div getForHeight(int pxVal) {
            Div paddingDiv = new Div();
            paddingDiv.setWidth(pxVal + "px");
            return paddingDiv;
        }
    }
}
