package dao;

import java.util.List;

import mybatis.Pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import parameter.ExpertBean;
import parameter.MessageBean;
import parameter.PatientBean;
import parameter.ScheduApplyBean;
import parameter.ScheduBean;
import parameter.UserBean;


public interface UserDao {

	UserBean getUserInfo(@Param("userId") String userId);
	
	PatientBean getPatientInfo(@Param("patientId") String patientId);
	
	int addUserInfo(@Param("user") UserBean userBean);
	
	List<UserBean> getAllUserInfoList(@Param("user") UserBean userBean, @Param("pageing") Pagination pagination);
	
	// 管理员添加用户
	int addUserForAdmin(@Param("user") UserBean userBean);

	// 管理员修改用户信息
	int updUserForAdmin(@Param("user") UserBean userBean);
	
	// 用户审核通过
	int auditUserForAdmin(@Param("user") UserBean userBean);
	
	// 删除用户
	int delUserForAdmin(@Param("user") UserBean userBean);
	
	// 用户未通过审核
	int rejectUserForAdmin(@Param("user") UserBean userBean);
	
	int updPermissForAdmin(@Param("user") UserBean userBean);

	List<ExpertBean> getAllExpertInfoList(@Param("expert") ExpertBean expertBean, @Param("pageing") Pagination pagination);
	
	int addExpertForAdmin(@Param("expert") ExpertBean expertBean);
	
	int updExpertForAdmin(@Param("expert") ExpertBean expertBean);
	
	int updExpertStaForAdmin(@Param("expert") ExpertBean expertBean);
	
	int updExpertSchedulingForAdmin(@Param("expert") ExpertBean expertBean);
	
	int delExpertForAdmin(@Param("expert") ExpertBean expertBean);
	
	ExpertBean getExpertInfo(@Param("expertId") String expertId);
	
	List<ExpertBean> getMainExpertInfoList(@Param("date") String date);
	
	List<ExpertBean> getExpertScheduList(@Param("expert") ExpertBean expertBean);
	
	int addSchedu(@Param("schedu") ScheduBean scheduBean);
	
	int delSchedu(@Param("schedu") ScheduBean scheduBean);
	
	ScheduBean getScheduInfo(@Param("schedu") ScheduBean scheduBean);
	
	// 修改用户信息
	int updUserFront(@Param("user") UserBean userBean);
	//修改患者信息
	int updPatientFront(@Param("patient") PatientBean patientBean);
	// 注册用户信息
	int updUserRegister(@Param("user") UserBean userBean);
	// 注册患者信息
	int addPatientRegister(@Param("user") UserBean userBean);
	// 验证密码
	UserBean checkPassword(@Param("user") UserBean userBean);
	// 修改密码
	int updPasswordFront(@Param("user") UserBean userBean);
	
	// 获取通知消息List
	List<MessageBean> getMessageList(@Param("message") MessageBean messageBean, @Param("pageing") Pagination pagination);
	// 获取通知消息内容
	MessageBean getMessageInfo(@Param("message") MessageBean messageBean);
	// 修改通知消息状态
	int updMessageSta(@Param("message") MessageBean messageBean);
	
	// 提交调休申请
	int addScheduApply(@Param("scheduApply") ScheduApplyBean scheduApplyBean);
	
	// 修改调休申请
	int updScheduApply(@Param("scheduApply") ScheduApplyBean scheduApplyBean);
	
	// 获取调休申请List
	List<ScheduApplyBean> getScheduApplyList(@Param("scheduApply") ScheduApplyBean scheduApplyBean, @Param("pageing") Pagination pagination);
	
	// 获取调休申请信息
	ScheduApplyBean getScheduApplyInfo(@Param("scheduApply") ScheduApplyBean scheduApplyBean);
	
	// 通过ID获取调休申请信息
	ScheduApplyBean getScheduApplyInfoById(@Param("scheduApply") ScheduApplyBean scheduApplyBean);
	
	// 删除专家风采
	int deleteExpertShow(@Param("expert") ExpertBean expertBean);
	
	// 添加专家风采
	int addExpertShow(@Param("expert") ExpertBean expertBean);
	
}
