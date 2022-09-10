package com.hrportal.main.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hrportal.main.domain.JobDescription;

@Repository
public class JobDescriptionRepositoryIMPL implements JobDescriptionRepositoryInterface {
	private static final String SELECT_ALL_JOB_DESCRIPTION = "SELECT jd.job_id,jd.project_id,jd.technical_skills_1,jd.technical_skills_2,jd.technical_skills_3,jd.required_candidates,jd.remaining_budget,jd.status,jd.employee_id,ed.employee_first_name,ed.employee_last_name,ed.contact_no,ed.hiredate,ed.highest_qualification,ed.login_id,ed.gender,ed.date_of_birth,ed.salary,ed.ctc,ed.on_workbench,ed.designation,ed.reports_to,ed.technical_skills_1,ed.technical_skills_2,ed.technical_skills_3,pd.project_name,pd.emp_count,pd.project_start_date,pd.project_end_date,pd.status,pd.overall_budget FROM JOB_DESCRIPTION jd , employee_details ed , project_details pd where jd.employee_id =ed.employee_id and jd.project_id=pd.project_id";
	private static final String SELECT_SINGLE_JOB_DESCRIPTION = "SELECT jd.job_id,jd.project_id,jd.technical_skills_1,jd.technical_skills_2,jd.technical_skills_3,jd.required_candidates,jd.remaining_budget,jd.status,jd.employee_id,ed.employee_first_name,ed.employee_last_name,ed.contact_no,ed.hiredate,ed.highest_qualification,ed.login_id,ed.gender,ed.date_of_birth,ed.salary,ed.ctc,ed.on_workbench,ed.designation,ed.reports_to,ed.technical_skills_1,ed.technical_skills_2,ed.technical_skills_3,pd.project_name,pd.emp_count,pd.project_start_date,pd.project_end_date,pd.status,pd.overall_budget FROM JOB_DESCRIPTION jd , employee_details ed , project_details pd where jd.employee_id =ed.employee_id and jd.project_id=pd.project_id and ed.employee_id=?";
	private static final String INERT_NEW_JOB_DESCRIPTION="INSERT INTO JOB_DESCRIPTION(job_id,project_id,technical_skills_1,technical_skills_2,technical_skills_3,required_candidates,status)VALUES(job_id_seq.NEXTVAL,?,?,?,?,?)";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<JobDescription> getallJobDescriptionDetails() {
		List<JobDescription> allrequests = jdbcTemplate.query(SELECT_ALL_JOB_DESCRIPTION,
				new JobDescriptionRowMapper());
		return allrequests;
	}

	@Override
	public JobDescription getJobDescriptionByEmployeeId(String employeeId) {

		return this.jdbcTemplate.queryForObject(SELECT_SINGLE_JOB_DESCRIPTION, new Object[] { employeeId },
				new JobDescriptionRowMapper());

	}

	@Override
	public boolean addNewJobDescription(JobDescription jobdescription) {
		Object [] params= {jobdescription.getProjectDetails().getProjectId() ,jobdescription.getTechnicalSkills1(),jobdescription.getTechnicalSkills2(),jobdescription.getTechnicalSkills3(),jobdescription.getRequiredCandidates(),jobdescription.getStatus()};
		int result=jdbcTemplate.update(INERT_NEW_JOB_DESCRIPTION,params);
		if(result>0) {
			return true;
		}
		return false;
	}

}
