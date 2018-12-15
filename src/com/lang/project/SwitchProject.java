package com.lang.project;

import com.intellij.ide.RecentProjectsManager;
import com.intellij.ide.ReopenProjectAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.util.text.StringUtil;
import java.util.*;

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
		Set<ProjectModel> projectModelSet = new LinkedHashSet<>(ProjectModel.getProjectModels(projects));
		projectModelSet.remove(new ProjectModel(project.getName(), project.getBasePath()));

		Set<ProjectModel> recentProjectSet = new LinkedHashSet<>();
		AnAction[] actions = RecentProjectsManager.getInstance().getRecentProjectsActions(false);
		for (AnAction action : actions) {
			if (!(action instanceof ReopenProjectAction)) {
				continue;
			}
			ReopenProjectAction reopenProjectAction = (ReopenProjectAction) action;
			if (StringUtil.equals(reopenProjectAction.getProjectPath(), project.getBasePath())) {
				continue;
			}
			ProjectModel projectModel = new ProjectModel(reopenProjectAction.getProjectName(), reopenProjectAction.getProjectPath());
			if (!projectModelSet.contains(projectModel)) {
				recentProjectSet.add(projectModel);
			}
		}

		List<ProjectModel> recentProjectModelList = new ArrayList<>(recentProjectSet);
		List<ProjectModel> projectModelList = new ArrayList<>(projectModelSet);
		projectModelList.addAll(recentProjectModelList);

		ProjectModel separateProject = recentProjectModelList.isEmpty() ? null : recentProjectModelList.get(0);
		ProjectListPopupStep projectListPopupStep = new ProjectListPopupStep("", projectModelList, separateProject);
		ListPopup listPopup = JBPopupFactory.getInstance().createListPopup(projectListPopupStep);
		listPopup.showCenteredInCurrentWindow(project);
	}


}
