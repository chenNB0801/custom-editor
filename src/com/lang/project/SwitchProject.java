package com.lang.project;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.awt.RelativePoint;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guoliang
 * @description:
 * @company: https://www.huobi.com
 * @email guoliang@huobi.com
 * @date 6:30 PM 2018/12/5
 */
public class SwitchProject extends AnAction {

	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabledAndVisible(ProjectManager.getInstance().getOpenProjects().length > 0);
	}

	@Override
	public void actionPerformed(AnActionEvent e) {
		Project project = e.getData(CommonDataKeys.PROJECT);

		Project[] projects = ProjectManager.getInstance().getOpenProjects();
		List<Project> allProject = new ArrayList<>(Arrays.asList(projects));
		allProject.remove(project);
		if (projects.length == 1) {
			return;
		}
		ListPopup listPopup = JBPopupFactory.getInstance()
				.createListPopup(new ProjectListPopupStep("", allProject));
		listPopup.showInFocusCenter();
	}

}
