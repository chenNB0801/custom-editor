package com.lang.editor;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class UMLEditorProvider implements FileEditorProvider {

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return virtualFile.getFileType() == UMLFileType.UML_DESIGNER_FILE_TYPE
                && ModuleUtilCore.findModuleForFile(virtualFile, project) != null;
    }

    @NotNull
    @Override
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return new UMLFileEditor(project, virtualFile);
    }

    @NotNull
    @Override
    public String getEditorTypeId() {
        return "uml-designer";
    }

    @NotNull
    @Override
    public FileEditorPolicy getPolicy() {
        // return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
        return FileEditorPolicy.PLACE_AFTER_DEFAULT_EDITOR;
    }
}
