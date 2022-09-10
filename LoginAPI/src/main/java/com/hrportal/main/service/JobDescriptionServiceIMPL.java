package com.hrportal.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrportal.main.domain.JobDescription;
import com.hrportal.main.repository.JobDescriptionRepositoryInterface;

@Service
public class JobDescriptionServiceIMPL implements JobDescriptionServiceInterface {
	@Autowired
	private JobDescriptionRepositoryInterface jobDescriptionRepositoryInterface;

	@Override
	public List<JobDescription> getallJobDescriptionDetails() {

		return jobDescriptionRepositoryInterface.getallJobDescriptionDetails();
	}

	@Override
	public JobDescription getJobDescriptionByEmployeeId(String employeeId) {
		
		return jobDescriptionRepositoryInterface.getJobDescriptionByEmployeeId(employeeId);
	}

	@Override
	public boolean addNewJobDescription(JobDescription jobdescription) {
		// TODO Auto-generated method stub
		return jobDescriptionRepositoryInterface.addNewJobDescription(jobdescription);
	}

}
