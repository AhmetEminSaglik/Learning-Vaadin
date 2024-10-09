package org.vaadin.aes.i18n;

import com.vaadin.flow.i18n.I18NProvider;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CustomI18NProvider implements I18NProvider {
    private static final List<Locale> LOCALES = List.of(new Locale("en"), new Locale("tr"));

    @Override
    public List<Locale> getProvidedLocales() {
        return LOCALES;
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... objects) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        return bundle.getString(key);
    }
}
