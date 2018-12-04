package com.lang.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.designer.DesignerEditorPanelFacade;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.PossiblyDumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.LightVirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class UMLFileEditor extends UserDataHolderBase implements FileEditor, PossiblyDumbAware {

    private final VirtualFile file;
    private final UMLEditor umlEditor;
    private BackgroundEditorHighlighter backgroundEditorHighlighter;

    public UMLFileEditor(final Project project, final VirtualFile file) {
        this.file = file instanceof LightVirtualFile ? ((LightVirtualFile) file).getOriginalFile() : file;
        Module module = ModuleUtilCore.findModuleForFile(this.file, project);
        if (null == module) {
            throw new IllegalArgumentException("no module for " + this.file + " in project " + project);
        }
        this.umlEditor = new UMLEditor(this, project, module, this.file);
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return this.umlEditor;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return this.umlEditor.getPreferredFocusedComponent();
    }

    @NotNull
    @Override
    public String getName() {
        return UMLBundle.message("title.uml.designer");
    }

    @Override
    public void setState(@NotNull FileEditorState fileEditorState) {

    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return FileDocumentManager.getInstance().getDocument(this.file) != null
                && this.file.getFileType() == UMLFileType.UML_DESIGNER_FILE_TYPE;
    }

    @Override
    public void selectNotify() {

    }

    @Override
    public void deselectNotify() {

    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener propertyChangeListener) {

    }

    @Override
    public void dispose() {
        this.umlEditor.dispose();
    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener propertyChangeListener) {

    }

    @Nullable
    @Override
    public BackgroundEditorHighlighter getBackgroundHighlighter() {
        if (null == this.backgroundEditorHighlighter) {
            this.backgroundEditorHighlighter = new UMLBackgroundEditorHighlighter(this.umlEditor);
        }
        return this.backgroundEditorHighlighter;
    }

    @Nullable
    @Override
    public FileEditorLocation getCurrentLocation() {
        return null;
    }

    @NotNull
    @Override
    public FileEditorState getState(@NotNull FileEditorStateLevel level) {
        return FileEditorState.INSTANCE;
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }

    public UMLEditor getEditor() {
        return this.umlEditor;
    }
}
