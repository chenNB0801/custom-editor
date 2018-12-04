package com.lang.editor;

import com.intellij.AbstractBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public class UMLBundle extends AbstractBundle {
    public static final String BUNDLE = "messages.UmlBundle";
    private static final UMLBundle INSTANCE = new UMLBundle();

    public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, @NotNull Object... params) {
        return INSTANCE.getMessage(key, params);
    }

    protected UMLBundle() {
        super(BUNDLE);
    }
}
