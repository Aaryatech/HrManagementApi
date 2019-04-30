package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.leave.repo.ProjectRepository;
import com.ats.hrmgt.model.ClaimAuthority;
import com.ats.hrmgt.model.ClaimType;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.ProjectHeader;
import com.ats.hrmgt.model.ProjectType;
import com.ats.hrmgt.repository.ProjectHeaderRpo;

@RestController
public class ProjectApiController {

	@Autowired
	ProjectRepository projectRepository;

	// -------------Project Type------------------------

	@RequestMapping(value = { "/saveProjectType" }, method = RequestMethod.POST)
	public @ResponseBody ProjectType saveProjectType(@RequestBody ProjectType projectType) {

		ProjectType save = new ProjectType();
		try {

			save = projectRepository.saveAndFlush(projectType);
			if (save != null) {
				save.setError(false);
			} else {

				save = new ProjectType();
				save.setError(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getProjectList" }, method = RequestMethod.GET)
	public @ResponseBody List<ProjectType> getProjectList() {

		List<ProjectType> list = new ArrayList<ProjectType>();
		try {

			list = projectRepository.findByDelStatusOrderByProjectTypeIdDesc(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteProjectType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteProjectType(@RequestParam("projectTypeId") int projectTypeId) {

		Info info = new Info();

		try {

			int delete = projectRepository.deleteProjectType(projectTypeId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

	@RequestMapping(value = { "/getProjectById" }, method = RequestMethod.POST)
	public @ResponseBody ProjectType getProjectById(@RequestParam("projectTypeId") int projectTypeId) {

		ProjectType projectType = new ProjectType();
		try {

			projectType = projectRepository.findByProjectTypeIdAndDelStatus(projectTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return projectType;

	}

	@RequestMapping(value = { "/getProjectListByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProjectType> getProjectListByCompanyId(@RequestParam("companyId") int companyId) {
		System.out.println(companyId);
		List<ProjectType> list = new ArrayList<ProjectType>();
		try {

			list = projectRepository.findByDelStatusAndCompanyId(1, companyId);
			System.out.println(list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	// harsha

	// -----------------Project Header------------------

	@Autowired
	ProjectHeaderRpo projectHeaderRpo;

	@RequestMapping(value = { "/getProjectsListByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProjectHeader> getProjectsListByCompanyId(@RequestParam("companyId") int companyId) {
		System.out.println(companyId);
		List<ProjectHeader> list = new ArrayList<ProjectHeader>();
		try {

			list = projectHeaderRpo.findByDelStatusAndCompanyId(1, companyId);
			System.out.println(list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

}
