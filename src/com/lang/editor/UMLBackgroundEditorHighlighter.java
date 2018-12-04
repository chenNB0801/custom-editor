package com.lang.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.codeHighlighting.HighlightingPass;
import org.jetbrains.annotations.NotNull;

public class UMLBackgroundEditorHighlighter implements BackgroundEditorHighlighter {
    private final HighlightingPass[] myPasses;

    public UMLBackgroundEditorHighlighter(final UMLEditor umlEditor) {
        this.myPasses = new HighlightingPass[] {new UMLHighlightingPass(umlEditor)};
    }

    @NotNull
    @Override
    public HighlightingPass[] createPassesForEditor() {
        return new HighlightingPass[0];
    }

    @NotNull
    @Override
    public HighlightingPass[] createPassesForVisibleArea() {
        return HighlightingPass.EMPTY_ARRAY;
    }
}
