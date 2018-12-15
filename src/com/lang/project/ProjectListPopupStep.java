package com.lang.project;


import com.intellij.ide.RecentProjectsManagerBase;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.ui.popup.ListSeparator;
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
import org.jetbrains.annotations.Nullable;

/**
 * @author guoliang
 * @description:
 * @company: https://www.huobi.com
 * @email guoliang@huobi.com
 * @date 7:28 PM 2018/12/5
 */
public class ProjectListPopupStep extends BaseListPopupStep<ProjectModel> {
	private ProjectModel selectedProject;
	private ProjectModel separateProject;

	public ProjectListPopupStep(String title, List<ProjectModel> projects, ProjectModel separateProject) {
		super(title, projects);
		this.separateProject = separateProject;
	}

	public static void switchSpecifyProjectWindow(ProjectModel projectModel) {
		System.out.println("ProjectListPopupStep.switchSpecifyProjectWindow" + projectModel);
		if (null == projectModel) {
			return;
		}

		ProjectManagerEx projectManagerEx = ProjectManagerEx.getInstanceEx();
		Project project;
		try {
			project = projectManagerEx.loadProject(projectModel.path);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		if (null == project) {
			return;
		}
		JFrame projectFrame = WindowManager.getInstance().getFrame(project);
		if (null == projectFrame) {
			projectManagerEx.openProject(project);
			projectFrame = WindowManager.getInstance().getFrame(project);
			if (null == projectFrame) {
				return;
			}
			focusProject(projectFrame);
		} else {
			//noinspection ConstantConditions
			RecentProjectsManagerBase.getInstanceEx().doOpenProject(project.getBasePath(), project, true);

			focusProject(projectFrame);
		}

	}

	private static void focusProject(JFrame projectFrame) {
		System.out.println("projectFrame = [" + projectFrame + "]");
		IdeFocusManager.getGlobalInstance().doWhenFocusSettlesDown(() -> {
			Component mostRecentFocusOwner = projectFrame.getMostRecentFocusOwner();
			if (mostRecentFocusOwner != null) {
				IdeFocusManager.getGlobalInstance().requestFocus(mostRecentFocusOwner, true);
			}
		});
	}

	@Nullable
	@Override
	public Runnable getFinalRunnable() {
		return () -> switchSpecifyProjectWindow(selectedProject);
	}

	@Nullable
	@Override
	public ListSeparator getSeparatorAbove(ProjectModel value) {
		if (null != this.separateProject && value == this.separateProject) {
			return new ListSeparator("Recent Projects");
		}

		if (value == this.getValues().get(0)) {
			return new ListSeparator("Opened Projects");
		}
		return null;
	}

	@NotNull
	@Override
	public String getTextFor(ProjectModel value) {
		return value.name;
	}

	@Override
	public SpeedSearchFilter<ProjectModel> getSpeedSearchFilter() {
		return new SpeedSearchFilter<ProjectModel>() {
			@Override
			public boolean canBeHidden(ProjectModel value) {
				return true;
			}

			@Override
			public String getIndexedString(ProjectModel value) {
				return value.name;
			}
		};
	}

	@Override
	public boolean isSpeedSearchEnabled() {
		return true;
	}

	@Override
	public PopupStep onChosen(ProjectModel project, boolean finalChoice) {
		if (null == project || project.path == null) {
			return FINAL_CHOICE;
		}
		this.selectedProject = project;
		return FINAL_CHOICE;
	}
}
