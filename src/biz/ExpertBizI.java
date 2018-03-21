package biz;

import java.util.List;

import mybatis.Pagination.Pagination;
import parameter.ExpertBean;
import parameter.ScheduApplyBean;
import parameter.ScheduBean;
import parameter.UserBean;
import dao.UserDao;

public class ExpertBizI implements ExpertBiz {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<ExpertBean> getAllExpertInfoList(ExpertBean expertBean, Pagination pagination) {
		
		return userDao.getAllExpertInfoList(expertBean, pagination);
	}

	public int addExpertForAdmin(ExpertBean expertBean) {
		
		return userDao.addExpertForAdmin(expertBean);
	}

	
	public int updExpertForAdmin(ExpertBean expertBean) {

		return userDao.updExpertForAdmin(expertBean);
	}

	public int updExpertStaForAdmin(ExpertBean expertBean) {
		return userDao.updExpertStaForAdmin(expertBean);
	}

	public int updExpertSchedulingForAdmin(ExpertBean expertBean) {
		return userDao.updExpertSchedulingForAdmin(expertBean);
	}

	public int delExpertForAdmin(ExpertBean expertBean) {
		return userDao.delExpertForAdmin(expertBean);
	}

	public int updPermissForAdmin(UserBean userBean) {
		return userDao.updPermissForAdmin(userBean);
	}

	public ExpertBean getExpertInfo(String expertId) {
		
		return userDao.getExpertInfo(expertId);
	}

	public List<ExpertBean> getMainExpertInfoList(String date) {
		
		return userDao.getMainExpertInfoList(date);
	}

	public List<ExpertBean> getExpertScheduList(ExpertBean expertBean) {
		
		return userDao.getExpertScheduList(expertBean);
	}

	public int addSchedu(ScheduBean scheduBean) {
		
		return userDao.addSchedu(scheduBean);
	}

	public int delSchedu(ScheduBean scheduBean) {
		return userDao.delSchedu(scheduBean);
	}

	public ScheduBean getScheduInfo(ScheduBean scheduBean) {
		
		return userDao.getScheduInfo(scheduBean);
	}

	public int addScheduApply(ScheduApplyBean scheduApplyBean) {
		
		return userDao.addScheduApply(scheduApplyBean);
	}

	public int updScheduApply(ScheduApplyBean scheduApplyBean) {
		
		return userDao.updScheduApply(scheduApplyBean);
	}

	public List<ScheduApplyBean> getScheduApplyList(ScheduApplyBean scheduApplyBean, Pagination pagination) {
		
		return userDao.getScheduApplyList(scheduApplyBean, pagination);
	}

	public ScheduApplyBean getScheduApplyInfo(ScheduApplyBean scheduApplyBean) {
		
		return userDao.getScheduApplyInfo(scheduApplyBean);
	}

	public ScheduApplyBean getScheduApplyInfoById(ScheduApplyBean scheduApplyBean) {
		
		return userDao.getScheduApplyInfoById(scheduApplyBean);
	}

	public int deleteExpertShow(ExpertBean expertBean) {
		return userDao.deleteExpertShow(expertBean);
	}
	
	public int addExpertShow(ExpertBean expertBean) {
		return userDao.addExpertShow(expertBean);
	}
}
