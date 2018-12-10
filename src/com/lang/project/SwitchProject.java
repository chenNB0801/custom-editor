package com.lang.project;

import com.intellij.ide.RecentProjectsManager;
import com.intellij.ide.ReopenProjectAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.util.text.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * @author guoliang
 * @description:
 * @company: https://www.huobi.com
 * @email guoliang@huobi.com
 * @date 6:30 PM 2018/12/5
 */
public class SwitchProject extends AnAction {

	@Override
	public void update(@NotNull AnActionEvent e) {
		e.getPresentation().setEnabledAndVisible(ProjectManager.getInstance().getOpenProjects().length > 0);
	}

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		Project project = e.getData(CommonDataKeys.PROJECT);
		if (null == project) return;
		Project[] projects = ProjectManager.getInstance().getOpenProjects();
		List<Project> allProject = new ArrayList<>(Arrays.asList(projects));
		allProject.remove(project);

		List<Project> recentProjects = new ArrayList<>();
		AnAction[] actions = RecentProjectsManager.getInstance().getRecentProjectsActions(false);
		for (AnAction action : actions) {
			if (!(action instanceof ReopenProjectAction)) {
				continue;
			}
			ReopenProjectAction reopenProjectAction = (ReopenProjectAction) action;
			if (StringUtil.equals(reopenProjectAction.getProjectPath(), project.getBasePath())) {
				continue;
			}

			boolean contained = false;
			for (Project openedProject : allProject) {
				if (StringUtil.equals(reopenProjectAction.getProjectPath(), openedProject.getBasePath())) {
					contained = true;
					break;
				}
			}
			try {
				if (!contained) {
					recentProjects.add(ProjectManagerEx.getInstanceEx().loadProject(reopenProjectAction.getProjectPath()));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		allProject.addAll(recentProjects);
		if (allProject.size() == 1) {
			ProjectListPopupStep.switchSpecifyProjectWindow(allProject.get(0));
			return;
		}
		ListPopup listPopup = JBPopupFactory.getInstance()
				.createListPopup(new ProjectListPopupStep("", allProject, recentProjects.isEmpty() ? null : recentProjects.get(0)));
		listPopup.showInFocusCenter();
	}


}
