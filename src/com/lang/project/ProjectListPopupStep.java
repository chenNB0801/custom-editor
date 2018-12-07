package com.lang.project;


import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.SpeedSearchFilter;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.openapi.wm.WindowManager;

import java.awt.Component;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;

import org.jetbrains.annotations.NotNull;

/**
 * @author guoliang
 * @description:
 * @company: https://www.huobi.com
 * @email guoliang@huobi.com
 * @date 7:28 PM 2018/12/5
 */
public class ProjectListPopupStep extends BaseListPopupStep<Project> {

    public ProjectListPopupStep(String title, List<Project> projects) {
        super(title, projects);
    }

    @NotNull
    @Override
    public String getTextFor(Project value) {
        return value.getName();
    }

    @Override
    public SpeedSearchFilter<Project> getSpeedSearchFilter() {
        return new SpeedSearchFilter<Project>() {
            @Override
            public boolean canBeHidden(Project value) {
                return true;
            }

            @Override
            public String getIndexedString(Project value) {
                return value.getName();
            }
        };
    }


    @Override
    public boolean isSpeedSearchEnabled() {
        return true;
    }

    @Override
    public PopupStep onChosen(Project project, boolean finalChoice) {
        if (null == project || project.getBasePath() == null) {
            return FINAL_CHOICE;
        }
        switchSpecifyProjectWindow(project);
        return FINAL_CHOICE;
    }

    public static void switchSpecifyProjectWindow(Project project) {
        if (null == project || null == project.getBasePath()) return;

        ProjectManagerEx projectManagerEx = ProjectManagerEx.getInstanceEx();
        Project p = null;
        try {
            p = projectManagerEx.loadProject(project.getBasePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame projectFrame = WindowManager.getInstance().getFrame(project);

        if (null == projectFrame || null == p) return;

        projectManagerEx.openProject(p);
        IdeFocusManager.getGlobalInstance().doWhenFocusSettlesDown(() -> {
            Component mostRecentFocusOwner = projectFrame.getMostRecentFocusOwner();
            if (mostRecentFocusOwner != null) {
                IdeFocusManager.getGlobalInstance().requestFocus(mostRecentFocusOwner, true);
            }
        });
    }
}
