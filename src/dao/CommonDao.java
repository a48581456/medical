package dao;

import java.sql.Timestamp;
import java.util.List;

import mybatis.Pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import parameter.AdvBean;
import parameter.CaseBean;
import parameter.CurriculumBean;
import parameter.ExpertBean;
import parameter.MessageBean;
import bean.CodeBean;


public interface CommonDao {

	// 获取科室下拉菜单
	List<CodeBean> getDepartmentList();
	
	// 获取科室下拉菜单
	List<CodeBean> getDepartmentPageList(@Param("pageing") Pagination pagination);
	
	// 获取状态下拉菜单
	List<CodeBean> getStatusList();
	
	// 获取病例下拉菜单
	List<CodeBean> getCaseStatusList();
	
	// 获取权限下拉菜单
	List<CodeBean> getPermissList();
	
	//获取上传类型下拉菜单
	List<CodeBean> getUploadTypeList();
	
	//获取疾病类型下拉菜单
	List<CodeBean> getDiseaseTypeList();
	
	//获取疾病类型下拉菜单(分页)
	List<CodeBean> getDiseaseTypePageList(@Param("pageing") Pagination pagination);
	
	//获取专家下拉菜单
	List<CodeBean> getExpertList();
	
	//获取专家下拉菜单(分页)
	List<ExpertBean> getExpertPageList(@Param("pageing") Pagination pagination);
	
	//获取专家名字
	String getExpertName(@Param("expert") String expert);
	
	//获取医院下拉菜单
	List<CodeBean> getHospitalList();
	
	//获取医院下拉菜单(分页)
	List<CodeBean> getHospitalPageList(@Param("pageing") Pagination pagination);
	
	// 获取年龄下拉菜单
	List<CodeBean> getAgeList();
	
	// 获取年龄下拉菜单
	List<CodeBean> getTitleList();
	
	//获取微课堂信息
	List<CurriculumBean> getCurriculumListByDate(@Param("date") Timestamp date);
	
	//根据开始时间获取活动信息
	List<CaseBean> getActivityListByStartDate(@Param("date") Timestamp date);
	
	//根据结束时间获取活动信息
	List<CaseBean> getActivityListByEndDate(@Param("date") Timestamp date);
	
	//根据结束时间获取预约信息
	List<Integer> getReserveListByEndDate(@Param("date") Timestamp date);
	
	// 更新活动状态
	int updActivity(@Param("status") String status, @Param("id") int id);
	
	// 更新预约状态
	int updReserve(@Param("id") int id);
	
	// 插入message
	int addMessageInfo(@Param("message") MessageBean messageBean);
	
	// 更新在线状态
	int updLoginStatus(@Param("userId") String userId, @Param("status")String status);
	
	//获取悬浮广告信息
	List<AdvBean> getAdvSuspenInfo();
	
	int getCaseCount(@Param("departmentid") String departmentid);
	
	int getAchievementsCount(@Param("departmentid") String departmentid);
	
	int getDrugsCount(@Param("departmentid") String departmentid);
	
    int getVideoCount(@Param("departmentid") String departmentid);
	
	int getPatentCount(@Param("departmentid") String departmentid);
	
	int getUserCount(@Param("departmentid") String departmentid);
	
	AdvBean getNoticeType();
	
	AdvBean getNoticeId();
	
	AdvBean getNoticeContent();
}
