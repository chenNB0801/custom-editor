package com.lang.editor;

import com.intellij.codeHighlighting.HighlightingPass;
import com.intellij.openapi.progress.ProgressIndicator;
import org.jetbrains.annotations.NotNull;

public class UMLHighlightingPass implements HighlightingPass {
    private UMLEditor umlEditor;

    public UMLHighlightingPass(UMLEditor umlEditor) {
        this.umlEditor = umlEditor;
    }

    @Override
    public void collectInformation(@NotNull ProgressIndicator progress) {
    }

    @Override
    public void applyInformationToEditor() {

    }
}
