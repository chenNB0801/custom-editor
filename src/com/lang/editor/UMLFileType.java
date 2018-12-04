package com.lang.editor;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import com.lang.util.UMLIconLoader;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class UMLFileType implements FileType {
    public static final UMLFileType UML_DESIGNER_FILE_TYPE = new UMLFileType();
    @NonNls public static final String DEFAULT_EXTENSION = "uml";
    @NonNls public static final String DOT_DEFAULT_EXTENSION = "." + DEFAULT_EXTENSION;

    @NotNull
    @Override
    public String getName() {
        return "LANG_UML";
    }

    @NotNull
    @Override
    public String getDescription() {
        return UMLBundle.message("filetype.description.uml");
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return UMLIconLoader.UML_ICON;
    }

    @Override
    public boolean isBinary() {
        return false;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Nullable
    @Override
    public String getCharset(@NotNull VirtualFile virtualFile, @NotNull byte[] bytes) {
        return CharsetToolkit.UTF8;
    }
}
