package biz;

import java.util.List;

import mybatis.Pagination.Pagination;
import parameter.ExpertBean;
import parameter.MessageBean;
import parameter.PatientBean;
import parameter.UserBean;

public interface UserBiz {
	
	// 获取全部用户
	List<UserBean> getAllUserInfoList(UserBean userBean, Pagination pagination);
	
	// 根据ID获取用户信息
	UserBean getUserInfo(String userId);
	
	// 根据ID获取患者信息
	PatientBean getPatientInfo(String patientId);
	
	// 管理员添加用户
	int addUserForAdmin(UserBean userBean);
	
	// 管理员修改用户信息
	int updUserForAdmin(UserBean userBean);
	
	// 用户审核通过
	int auditUserForAdmin(UserBean userBean);
	
	// 用户审核未通过
	int rejectUserForAdmin(UserBean userBean);
	
	// 删除用户
	int delUserForAdmin(UserBean userBean);
	
	// 删除专家
	int delExpertForAdmin(ExpertBean expertBean);
	
	//修改用户信息
	int updUserFront(UserBean userBean);
	
	//修改患者信息
	int updPatientFront(PatientBean patientBean);
	
	int updUserRegister(UserBean userBean);
	
	int addPatientRegister(UserBean userBean);
	
	// 验证密码
	UserBean checkPassword(UserBean userBean);
	// 修改密码
	int updPasswordFront(UserBean userBean);
	
	// 获取通知消息
	List<MessageBean> getMessageList(MessageBean messageBean, Pagination pagination);
	// 获取通知消息内容
	MessageBean getMessageInfo(MessageBean messageBean);
	// 修改通知消息状态
	int updMessageSta(MessageBean messageBean);
	
}
