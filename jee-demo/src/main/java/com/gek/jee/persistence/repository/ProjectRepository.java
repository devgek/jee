package com.gek.jee.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.gek.jee.persistence.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
}
