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
import java.util.List;
import java.util.Objects;
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
public class ProjectListPopupStep extends BaseListPopupStep<Project> {
	private Project selectedProject;
	private Project separateProject;

	public ProjectListPopupStep(String title, List<Project> projects, Project separateProject) {
		super(title, projects);
		this.separateProject = separateProject;
	}

	public static void switchSpecifyProjectWindow(Project project) {
		if (null == project) {
			return;
		}

		ProjectManagerEx projectManagerEx = ProjectManagerEx.getInstanceEx();
		JFrame projectFrame = WindowManager.getInstance().getFrame(project);
		if (null == projectFrame) {
			projectManagerEx.openProject(project);
			projectFrame = WindowManager.getInstance().getFrame(project);
			if (null == projectFrame) {
				return;
			}
			JFrame finalProjectFrame = projectFrame;
			IdeFocusManager.getGlobalInstance().doWhenFocusSettlesDown(() -> {
				Component mostRecentFocusOwner = finalProjectFrame.getMostRecentFocusOwner();
				if (mostRecentFocusOwner != null) {
					IdeFocusManager.getGlobalInstance().requestFocus(mostRecentFocusOwner, true);
				}
			});
		} else {
			RecentProjectsManagerBase.getInstanceEx()
					.doOpenProject(Objects.requireNonNull(project.getBasePath()), project, true);

			JFrame finalProjectFrame1 = projectFrame;
			IdeFocusManager.getGlobalInstance().doWhenFocusSettlesDown(() -> {
				Component mostRecentFocusOwner = finalProjectFrame1.getMostRecentFocusOwner();
				if (mostRecentFocusOwner != null) {
					IdeFocusManager.getGlobalInstance().requestFocus(mostRecentFocusOwner, true);
				}
			});
		}

	}

	@Nullable
	@Override
	public Runnable getFinalRunnable() {
		return () -> switchSpecifyProjectWindow(selectedProject);
	}

	@Nullable
	@Override
	public ListSeparator getSeparatorAbove(Project value) {
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
		this.selectedProject = project;
		return FINAL_CHOICE;
	}
}
