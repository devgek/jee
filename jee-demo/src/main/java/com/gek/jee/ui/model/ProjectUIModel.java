package com.gek.jee.ui.model;

import java.util.Collections;
import java.util.List;

import com.gek.jee.persistence.entity.Project;


@UIModel
public class ProjectUIModel {
	private List<Project> projects = Collections.EMPTY_LIST;

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
}
