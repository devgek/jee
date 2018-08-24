package com.gek.jee.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.gek.jee.persistence.entity.Project;
import com.gek.jee.persistence.repository.ProjectRepository;

@Named
public class ProjectService {
	@Inject
	ProjectRepository projectRepository;
	
	public List<Project> getProjects() {
		return (List<Project>) projectRepository.findAll();
	}
}
