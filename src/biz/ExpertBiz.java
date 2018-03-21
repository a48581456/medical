package biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mybatis.Pagination.Pagination;
import parameter.ExpertBean;
import parameter.ScheduApplyBean;
import parameter.ScheduBean;
import parameter.UserBean;

public interface ExpertBiz {
	
	ExpertBean getExpertInfo(String expertId);
	
	// 获取全部专家
	List<ExpertBean> getAllExpertInfoList(ExpertBean expertBean, Pagination pagination);
	
	int addExpertForAdmin(ExpertBean expertBean);
	
	int updExpertForAdmin(ExpertBean expertBean);
	
	int updExpertStaForAdmin(ExpertBean expertBean);
	
	int updExpertSchedulingForAdmin(ExpertBean expertBean);
	
	int delExpertForAdmin(ExpertBean expertBean);
	
	int updPermissForAdmin(UserBean userBean);
	
	List<ExpertBean> getMainExpertInfoList(String date);
	
	List<ExpertBean> getExpertScheduList(ExpertBean expertBean);
	
	int addSchedu(ScheduBean scheduBean);
	
	int delSchedu(ScheduBean scheduBean);
	
	ScheduBean getScheduInfo(ScheduBean scheduBean);
	
	// 提交调休申请
	int addScheduApply(ScheduApplyBean scheduApplyBean);
	// 修改调休申请
	int updScheduApply(ScheduApplyBean scheduApplyBean);
	
	// 获取调休申请List
	List<ScheduApplyBean> getScheduApplyList(ScheduApplyBean scheduApplyBean, Pagination pagination);
	// 获取调休申请信息
	ScheduApplyBean getScheduApplyInfo(ScheduApplyBean scheduApplyBean);
	
	// 通过ID获取调休申请信息
	ScheduApplyBean getScheduApplyInfoById(ScheduApplyBean scheduApplyBean);
	
	// 删除专家风采
	int deleteExpertShow(ExpertBean expertBean);
	
	// 添加专家风采
	int addExpertShow( ExpertBean expertBean);
}
