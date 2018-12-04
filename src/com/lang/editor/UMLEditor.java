package com.lang.editor;

import com.intellij.designer.DesignerEditorPanelFacade;
import com.intellij.designer.ModuleProvider;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ThreeComponentsSplitter;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class UMLEditor extends JPanel implements DesignerEditorPanelFacade, DataProvider, ModuleProvider {
    private final UMLFileEditor fileEditor;
    private final Project project;
    private final Module module;
    private final VirtualFile file;
    private final ThreeComponentsSplitter myContentSplitter = new ThreeComponentsSplitter();
    @NonNls
    private static final String ourHelpID = "umlDesigner.uiTour.workspace";

    public UMLEditor(UMLFileEditor fileEditor, Project project, Module module, VirtualFile file) {
        this.fileEditor = fileEditor;
        this.project = project;
        this.module = module;
        this.file = file;
    }

    @Override
    public ThreeComponentsSplitter getContentSplitter() {
        return this.myContentSplitter;
    }

    @Nullable
    @Override
    public Object getData(String dataId) {
        if (PlatformDataKeys.HELP_ID.is(dataId)) {
            return ourHelpID;
        }
        return null;
    }

    @Override
    public Module getModule() {
        return this.module;
    }

    @Override
    public Project getProject() {
        return this.project;
    }

    public JComponent getPreferredFocusedComponent() {
        return null;
    }

    public void dispose() {

    }
}
