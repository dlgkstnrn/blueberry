package com.blackberry.s20240130103.kph.service;

import java.util.List;
import java.util.Map;

import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUsers;

public interface KphProjectService {
	
	Map<String, Object> mainLogic(Long user_no);

	int projectAdd(KphProject project);

	List<KphUsers> userListByProjectNoExceptOwn(KphUserProject kphUserProject);

	int eval(KphEval eval);

	List<KphUsers> addressUserList(Long user_no);

	List<KphUsers> addressUserListByName(KphUsers user);

	List<KphProjectTask> totalProjectTaskList(KphProjectTask kphProjectTask);

	int totalProjectTaskCountByKeyword(KphProjectTask kphProjectTask);

	Map<String, Object> detailProject(KphTask kphTask);

	List<KphUsers> projectMemberList(Long project_no);

}
