package biz;

import java.util.List;

import mybatis.Pagination.Pagination;
import parameter.ExpertBean;
import parameter.MessageBean;
import parameter.PatientBean;
import parameter.UserBean;
import dao.UserDao;

public class UserBizI implements UserBiz {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<UserBean> getAllUserInfoList(UserBean userBean, Pagination pagination) {
		
		return userDao.getAllUserInfoList(userBean,pagination);
	}

	public UserBean getUserInfo(String userId) {
		
		return userDao.getUserInfo(userId);
	}
	
	public PatientBean getPatientInfo(String patientId) {
		
		return userDao.getPatientInfo(patientId);
	}

	public int addUserForAdmin(UserBean userBean) {
		
		return userDao.addUserForAdmin(userBean);
	}

	public int updUserForAdmin(UserBean userBean) {
		
		return userDao.updUserForAdmin(userBean);
	}

	public int auditUserForAdmin(UserBean userBean) {
		
		return userDao.auditUserForAdmin(userBean);
	}
	
	public int rejectUserForAdmin(UserBean userBean) {
		
		return userDao.rejectUserForAdmin(userBean);
	}
	
	public int delUserForAdmin(UserBean userBean) {
		
		return userDao.delUserForAdmin(userBean);
	}

	public int delExpertForAdmin(ExpertBean expertBean) {
		
		return userDao.delExpertForAdmin(expertBean);
	}
	
	public int updUserFront(UserBean userBean) {
		
		return userDao.updUserFront(userBean);
	}
	
	public int updPatientFront(PatientBean patientBean) {
		
		return userDao.updPatientFront(patientBean);
	}
	
	public int updUserRegister(UserBean userBean) {
		
		return userDao.updUserRegister(userBean);
	}
	
	public int addPatientRegister(UserBean userBean) {
		
		return userDao.addPatientRegister(userBean);
	}

	public UserBean checkPassword(UserBean userBean) {
		return userDao.checkPassword(userBean);
	}

	public int updPasswordFront(UserBean userBean) {
		return userDao.updPasswordFront(userBean);
	}

	public List<MessageBean> getMessageList(MessageBean messageBean, Pagination pagination) {
		
		return userDao.getMessageList(messageBean, pagination);
	}

	public int updMessageSta(MessageBean messageBean) {
		
		return userDao.updMessageSta(messageBean);
	}

	public MessageBean getMessageInfo(MessageBean messageBean) {
		return userDao.getMessageInfo(messageBean);
	}

}
