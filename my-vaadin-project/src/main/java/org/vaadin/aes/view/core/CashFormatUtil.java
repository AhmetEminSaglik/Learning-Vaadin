package org.vaadin.aes.view.core;

import java.text.NumberFormat;
import java.util.Locale;

public class CashFormatUtil {
    public static String convertTL(double val) {
        Locale trLocale = new Locale("tr", "TR");
        NumberFormat cashFormat = NumberFormat.getCurrencyInstance(trLocale);
        return cashFormat.format(val);
    }
}
