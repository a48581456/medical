package action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import listener.ServletContextListener;
import mybatis.Pagination.PgsqlPagination;
import parameter.ExpertBean;
import parameter.MessageBean;
import parameter.ScheduApplyBean;
import parameter.ScheduBean;
import parameter.UserBean;
import util.Util;
import biz.ExpertBiz;

import com.opensymphony.xwork2.Action;
import common.CommonFunction;
import common.Constants;
import common.Doc2Html;
import common.DocConverter;

import filter.TimeOutFilter;

//, ServletRequestAware
public class ExpertAction implements Action {

	private String errormessage;
	
	private String okmessage;

	private String weekFirDate;
	
	private String theFrom;
	
	private String mainBack;
	
	private UserBean userBean = new UserBean();
	
	private ExpertBean expertBean = new ExpertBean();
	
	private ExpertBean seaExpert = new ExpertBean();
	
	private ScheduApplyBean scheduApplyBean = new ScheduApplyBean();
	
	private ScheduApplyBean seaApplyBean = new ScheduApplyBean();

	private HttpServletRequest request;

	private ExpertBiz expertBiz;
	
	private List<ExpertBean> expertBeanList;
	
	private List<String> weekList;
	
	private List<ScheduApplyBean> scheduApplyBeanList;
	
	private PgsqlPagination pagination = new PgsqlPagination();
	
	private String tmpUser;
	
	public void setServletRequest(HttpServletRequest arg0) {

		request = arg0;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	
	public String getOkmessage() {
		return okmessage;
	}

	public void setOkmessage(String okmessage) {
		this.okmessage = okmessage;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	
	public ExpertBean getExpertBean() {
		return expertBean;
	}

	public void setExpertBean(ExpertBean expertBean) {
		this.expertBean = expertBean;
	}

	public ScheduApplyBean getScheduApplyBean() {
		return scheduApplyBean;
	}

	public void setScheduApplyBean(ScheduApplyBean scheduApplyBean) {
		this.scheduApplyBean = scheduApplyBean;
	}

	public ExpertBean getSeaExpert() {
		return seaExpert;
	}

	public void setSeaExpert(ExpertBean seaExpert) {
		this.seaExpert = seaExpert;
	}

	public ExpertBiz getExpertBiz() {
		return expertBiz;
	}

	public void setExpertBiz(ExpertBiz expertBiz) {
		this.expertBiz = expertBiz;
	}

	public List<ExpertBean> getExpertBeanList() {
		return expertBeanList;
	}

	public void setExpertBeanList(List<ExpertBean> expertBeanList) {
		this.expertBeanList = expertBeanList;
	}

	public List<String> getWeekList() {
		return weekList;
	}

	public void setWeekList(List<String> weekList) {
		this.weekList = weekList;
	}
	
	public List<ScheduApplyBean> getScheduApplyBeanList() {
		return scheduApplyBeanList;
	}

	public void setScheduApplyBeanList(List<ScheduApplyBean> scheduApplyBeanList) {
		this.scheduApplyBeanList = scheduApplyBeanList;
	}

	public String getWeekFirDate() {
		return weekFirDate;
	}

	public void setWeekFirDate(String weekFirDate) {
		this.weekFirDate = weekFirDate;
	}

	public String getTheFrom() {
		return theFrom;
	}

	public void setTheFrom(String theFrom) {
		this.theFrom = theFrom;
	}

	public ScheduApplyBean getSeaApplyBean() {
		return seaApplyBean;
	}

	public void setSeaApplyBean(ScheduApplyBean seaApplyBean) {
		this.seaApplyBean = seaApplyBean;
	}

	public String getMainBack() {
		return mainBack;
	}

	public void setMainBack(String mainBack) {
		this.mainBack = mainBack;
	}

	public String execute() throws Exception {

		return SUCCESS;
	}
	
	public PgsqlPagination getPagination() {
		return pagination;
	}

	public void setPagination(PgsqlPagination pagination) {
		this.pagination = pagination;
	}

	public String getTmpUser() {
		return tmpUser;
	}

	public void setTmpUser(String tmpUser) {
		this.tmpUser = tmpUser;
	}

	/**
	 * 专家一览检索
	 * @return
	 * @throws Exception
	 */
	public String expertList() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			// 科室绑定
			if (!Constants.ADMIN.equals(sessUser.getPermiss())) {
				seaExpert.setDepartmentid(sessUser.getDepartmentid());
			}
			
			// 只检索专家
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
//			tmpUser = "";
//			for (int i = 0; i < expertBeanList.size(); i++) {
//				tmpUser = tmpUser + expertBeanList.get(i).getExpert_id() + ",";
//			}
//			if (!"".equals(tmpUser)) {
//				tmpUser = tmpUser.substring(0, tmpUser.length() - 1);
//			}
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "expertList";
	}
	
	/**
	 * 专家一览检索
	 * @return
	 * @throws Exception
	 */
	public String expertSearchList() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			// 科室绑定
			if (!Constants.ADMIN.equals(sessUser.getPermiss())) {
				seaExpert.setDepartmentid(sessUser.getDepartmentid());
			}
			
			pagination = new PgsqlPagination();
			
			// 只检索专家
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "expertList";
	}
	
	/**
	 * 进入添加新用户页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return "addExpert";
	}
	
	/**
	 * 添加新用户
	 * @return
	 * @throws Exception
	 */
	public String addExpertForAdmin() throws Exception {
		try {
			errormessage = "";
			okmessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			// 添加用户
			expertBean.setCreate_user(sessUser.getUser_id());
			expertBean.setUpdate_user(sessUser.getUser_id());
			
			CommonFunction commonFunction = new CommonFunction();
			
			if(expertBean.getFile()!=null){
				
				File file=expertBean.getFile();
				String fileExt = expertBean.getFileFileName().substring(expertBean.getFileFileName().lastIndexOf(".") + 1).toLowerCase();
				SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = "expert_" + sessUser.getUser_id() + "_" + df.format(new Date()) + "." + fileExt; 
				errormessage = commonFunction.saveFile(expertBean.getComment(),newFileName,file,Constants.EXPERT,Constants.UPLOAD_FILE);
				if ("".equals(errormessage)) {
					
					Doc2Html doc2Html = new Doc2Html();
					expertBean.setComment(doc2Html.toHtmlString(newFileName, Constants.EXPERT));
					
					commonFunction.deleteFile(newFileName, Constants.EXPERT);
					
					expertBean.setFlag(seaExpert.getFlag());
					
					expertBiz.addExpertForAdmin(expertBean);
					// 修改用户身份
					userBean.setUser_id(expertBean.getExpert_id());
					userBean.setPermiss("2");
					userBean.setUpdate_user(sessUser.getUser_id());
					
					expertBiz.updPermissForAdmin(userBean);
					
					// 插入message信息--------start
					MessageBean message = new MessageBean();
					// 对应内容ID
					message.setCommentid(userBean.getUser_id());
					// 机能ID
					message.setFunctionid(Constants.EXPERT);
					// 消息内容
					message.setMessage(Constants.ADD_EXPERT);
					// 用户ID
					message.setUserid(userBean.getUser_id());
					// 标题
					message.setTitle(Constants.TITLE_ADD_EXPERT);
					
					message.setCreate_user(sessUser.getUser_id());
					message.setUpdate_user(sessUser.getUser_id());
					
					Util.addMessageInfo(message);
					
					// 插入message信息--------end
					
					expertBean = new ExpertBean();
					
					okmessage = "专家添加成功！";
					
				}
			}
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "addExpert";
	}
	
	/**
	 * 更新画面信息查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updInfo() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			expertBean = expertBiz.getExpertInfo(expertBean.getExpert_id());
			
			// 文件转换
//			DocConverter docConverter = new DocConverter(ServletContextListener.getWEB_ABSOLUTE_PATH() +  Constants.UPLOAD_PATH + "/" + Constants.EXPERT + "/" +expertBean.getComment());
//			docConverter.conver();
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "updExpert";
	}
	
	/**
	 * 详情画面信息查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String detailInfo() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			expertBean = expertBiz.getExpertInfo(expertBean.getExpert_id());
			
			// 文件转换
//			DocConverter docConverter = new DocConverter(ServletContextListener.getWEB_ABSOLUTE_PATH() +  Constants.UPLOAD_PATH + "/" + Constants.EXPERT + "/" +expertBean.getComment());
//			docConverter.conver();
			
//			Doc2Html doc2Html = new Doc2Html();
//			expertBean.setCoprofile(doc2Html.toHtmlString(new File(ServletContextListener.getWEB_ABSOLUTE_PATH() +  Constants.UPLOAD_PATH + "/" + Constants.EXPERT + "/" +expertBean.getComment()), ServletContextListener.getWEB_ABSOLUTE_PATH() +  Constants.UPLOAD_PATH + "/" + Constants.EXPERT + "/"));
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "detailExpert";
	}
	
	/**
	 * 更新用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updExpertForAdmin() throws Exception {
		try {
			errormessage = "";
			okmessage = "";
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			expertBean.setUpdate_user(sessUser.getUser_id());
			
			if(expertBean.getFile()!=null){
				CommonFunction commonFunction = new CommonFunction();
				File file=expertBean.getFile();
				String fileExt = expertBean.getFileFileName().substring(expertBean.getFileFileName().lastIndexOf(".") + 1).toLowerCase();
				SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = "expert_" + sessUser.getUser_id() + "_" + df.format(new Date()) + "." + fileExt; 
				errormessage = commonFunction.saveFile(expertBean.getComment(),newFileName,file,Constants.EXPERT,Constants.UPLOAD_FILE);
				if ("".equals(errormessage)) {
					
					Doc2Html doc2Html = new Doc2Html();
					expertBean.setComment(doc2Html.toHtmlString(newFileName, Constants.EXPERT));
					
					commonFunction.deleteFile(newFileName, Constants.EXPERT);
					
//					expertBean.setComment(newFileName);
				}
			}
			// 修改用户
			expertBiz.updExpertForAdmin(expertBean);
			
			okmessage = "专家修改成功！";
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "updExpert";
	}
	
	/**
	 * 删除用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteExpert() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			// 去掉专家身份
			expertBiz.delExpertForAdmin(expertBean);
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			userBean.setUser_id(expertBean.getExpert_id());
			userBean.setUpdate_user(sessUser.getUser_id());
			
			expertBiz.updPermissForAdmin(userBean);
			
			// 插入message信息--------start
			MessageBean message = new MessageBean();
			// 对应内容ID
			message.setCommentid(userBean.getUser_id());
			// 机能ID
			message.setFunctionid(Constants.EXPERT);
			// 消息内容
			message.setMessage(Constants.DEL_EXPERT);
			// 用户ID
			message.setUserid(userBean.getUser_id());
			
			// 标题
			message.setTitle(Constants.TITLE_DEL_EXPERT);
			
			message.setCreate_user(sessUser.getUser_id());
			message.setUpdate_user(sessUser.getUser_id());
			
			Util.addMessageInfo(message);
			
			// 插入message信息--------end
			
			okmessage = "专家删除成功！";
			
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			
			if (expertBeanList.size() == 0) {
				pagination.setPage(pagination.getPage() - 1);
				expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			}
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "expertList";
	}
	
	/**
	 * 专家一览检索（用户）
	 * @return
	 * @throws Exception
	 */
	public String expertFrontList() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			pagination.setPageSize(8);
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			// 科室绑定
			if (!Constants.ADMIN.equals(sessUser.getPermiss())) {
				seaExpert.setDepartmentid(sessUser.getDepartmentid());
			}
			
			// 只检索专家
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "expertFrontList";
	}
	
	/**
	 * 专家一览检索（用户）
	 * @return
	 * @throws Exception
	 */
	public String expertSearchFrontList() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			pagination = new PgsqlPagination();
			pagination.setPageSize(8);
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			// 科室绑定
			if (!Constants.ADMIN.equals(sessUser.getPermiss())) {
				seaExpert.setDepartmentid(sessUser.getDepartmentid());
			}
			
			// 只检索专家
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "expertFrontList";
	}
	
	/**
	 * 详情画面信息查询（用户）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String detailFrontInfo() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			expertBean = expertBiz.getExpertInfo(expertBean.getExpert_id());
			
			// 文件转换
			
//			DocConverter docConverter = new DocConverter(ServletContextListener.getWEB_ABSOLUTE_PATH() +  Constants.UPLOAD_PATH + "/" + Constants.EXPERT + "/" +expertBean.getComment());
//			docConverter.conver();
			
//			Doc2Html doc2Html = new Doc2Html();
//			expertBean.setCoprofile(doc2Html.toHtmlString(new File(ServletContextListener.getWEB_ABSOLUTE_PATH() +  Constants.UPLOAD_PATH + "/" + Constants.EXPERT + "/" +expertBean.getComment()), ServletContextListener.getWEB_ABSOLUTE_PATH() +  Constants.UPLOAD_PATH + "/" + Constants.EXPERT + "/"));
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "detailFrontInfo";
	}
	
	/**
	 * 专家排班
	 * @return
	 * @throws Exception
	 */
	public String expertSchedu() throws Exception {
		try {
//			okmessage = "";
//			errormessage = "";
			
			UserBean loginUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			if ("front".equals(theFrom)) {
				
				expertBean.setExpert_id(loginUser.getUser_id());
			}
			
			Date date = new Date();
			
			SimpleDateFormat sdf = new SimpleDateFormat(Util.YYYYMMDD);
			
			if (weekFirDate != null && !"".equals(weekFirDate)) {
				date = sdf.parse(weekFirDate);
			}
			
			weekFirDate = sdf.format(date);
			
			// 获取当前周的日期
			weekList = Util.getTimeIntervalList(date, Util.YYYYMMDD);
			
			// 科室绑定
			if (!Constants.ADMIN.equals(loginUser.getPermiss())) {
				expertBean.setDepartmentid(loginUser.getDepartmentid());
			}
			
			// 只检索专家
			expertBeanList = expertBiz.getExpertScheduList(expertBean);
			
			if (expertBeanList != null && expertBeanList.size() != 0) {
				
				// 获取已经排班的list
				for (int i = 0; i < expertBeanList.size(); i++) {
					List<ScheduBean> scheduBeanList = expertBeanList.get(i).getScheduBeanList();
					List<String> scheduList = new ArrayList<String>();
					
					// 如果已经有排班的情况
					if (scheduBeanList != null && scheduBeanList.size() != 0) {
						
						 for (int j = 0; j < weekList.size(); j++) {
							 scheduList.add("none");
							for (int n = 0; n < scheduBeanList.size(); n++) {
								
								if (scheduBeanList.get(n).getSchedu_date().equals(weekList.get(j))) {
									scheduList.remove(j);
									scheduList.add(scheduBeanList.get(n).getSchedu_date());
									break;
								} 
							}
						}
					} else {
						scheduList.add("none");
						scheduList.add("none");
						scheduList.add("none");
						scheduList.add("none");
						scheduList.add("none");
						scheduList.add("none");
						scheduList.add("none");
					}
					
					expertBeanList.get(i).setScheduList(scheduList);
				}
				
			}
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		if ("front".equals(theFrom)) {
			return "expertScheduFront";
		} else if ("user".equals(theFrom)) {
			return "expertScheduUser";
		} 
		
		return "expertSchedu";
	}
	
	/**
	 * 专家排班
	 * @return
	 * @throws Exception
	 */
	public String scheduSubmit() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			List<ExpertBean> expertList = expertBean.getExpertBeanList();
			
			UserBean loginUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			// 专家集合
			if (expertList != null && expertList.size() != 0) {
				for (int i = 0; i < expertList.size(); i++) {
					ExpertBean expert = expertList.get(i);
					// 排班集合
					List<ScheduBean> scheduList = expert.getScheduBeanList();
					
					boolean scheduFlag = false;
					
					for (int j = 0; j < scheduList.size(); j++) {
						
						ScheduBean schedu = scheduList.get(j);
						// 专家ID
						schedu.setExpert_id(expert.getExpert_id());
						// 登录人
						schedu.setCreate_user(loginUser.getUser_id());
						// 更新人
						schedu.setUpdate_user(loginUser.getUser_id());
						
						// 未选中的状态
						if ("1".equals(schedu.getDelete_flag())) {
							// 判断是否已存在
							ScheduBean dbSchedu = expertBiz.getScheduInfo(schedu);
							if (dbSchedu != null) {
								expertBiz.delSchedu(schedu);
								scheduFlag = true;
							}
							// 选中的状态
						} else {
							// 判断是否已存在
							ScheduBean dbSchedu = expertBiz.getScheduInfo(schedu);
							if (dbSchedu == null) {
								expertBiz.addSchedu(schedu);
								scheduFlag = true;
							}
						}
					}
					
					if (scheduFlag) {
						
						// 插入message信息--------start
						MessageBean message = new MessageBean();
						// 对应内容ID
						message.setCommentid(expert.getExpert_id());
						// 机能ID
						message.setFunctionid(Constants.EXPERT);
						// 消息内容
						message.setMessage(Constants.EXPERT_SCHEDU);
						// 用户ID
						message.setUserid(expert.getExpert_id());
						// 标题
						message.setTitle(Constants.TITLE_EXPERT_SCHEDU);
						// 链接
						message.setLink(Constants.SERVER_PATH + "expertAction!expertSchedu?theFrom=front&&expertBean.expert_id="+expert.getExpert_id()+"&&weekFirDate="+ weekFirDate);
						
						message.setCreate_user(loginUser.getUser_id());
						message.setUpdate_user(loginUser.getUser_id());
						
						Util.addMessageInfo(message);
						
						// 插入message信息--------end
					}
				}
			}
			
			okmessage = "排班修改成功。";
			
		} catch (Exception ex) {
			errormessage = "排班失败。";
			throw new Exception(ex);
		}
		
		return expertSchedu();
	}
	
	/**
	 * 调休申请
	 * 
	 * @return
	 * @throws Exception
	 */
	public String scheduApplyInfo() throws Exception {
		try {
			errormessage = "";
			okmessage = "";
			
			ScheduApplyBean dbScheduApply = expertBiz.getScheduApplyInfo(scheduApplyBean);
			
			if (dbScheduApply != null) {
				scheduApplyBean = dbScheduApply;
			}
			
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "scheduApply";
	}
	
	/**
	 * 调休申请提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String scheduApply() throws Exception {
		try {
			errormessage = "";
			okmessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			scheduApplyBean.setCreate_user(sessUser.getName());
			scheduApplyBean.setUpdate_user(sessUser.getName());
			
			if (!"".equals(scheduApplyBean.getId()) && scheduApplyBean.getId() != null) {
				// 修改申请
				expertBiz.updScheduApply(scheduApplyBean);
				okmessage = "申请修改成功！";
			} else {
				// 提交申请
				expertBiz.addScheduApply(scheduApplyBean);
				okmessage = "申请提交成功！";
			}
			
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "scheduApply";
	}
	
	/**
	 * 调休申请List
	 * 
	 * @return
	 * @throws Exception
	 */
	public String scheduApplyList() throws Exception {
		try {
			errormessage = "";
			okmessage = "";
			
			
			scheduApplyBeanList =  expertBiz.getScheduApplyList(seaApplyBean, pagination);
			
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "scheduApplyList";
	}
	
	/**
	 * 调休申请List
	 * 
	 * @return
	 * @throws Exception
	 */
	public String scheduApplySearchList() throws Exception {
		try {
			errormessage = "";
			okmessage = "";
			
			
			scheduApplyBeanList =  expertBiz.getScheduApplyList(seaApplyBean, pagination);
			
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "scheduApplyList";
	}
	
	/**
	 * 调休申请详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String scheduApplyDetail() throws Exception {
		try {
			errormessage = "";
			okmessage = "";
			
			
			scheduApplyBean =  expertBiz.getScheduApplyInfoById(scheduApplyBean);
			
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "scheduApplyDetail";
	}
	
	/**
	 * 在线咨询
	 * @return
	 * @throws Exception
	 */
	public String userIm() throws Exception{
		try{
		
			expertBean = expertBiz.getExpertInfo(expertBean.getExpert_id()); 
			
		}catch(Exception ex){
			throw new Exception(ex);
		}
		
		return "userIm";
	}
	
	
	/**
	 * 专家风采检索
	 * @return
	 * @throws Exception
	 */
	public String expertShowList() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			// 科室绑定
			if (!Constants.ADMIN.equals(sessUser.getPermiss())) {
				seaExpert.setDepartmentid(sessUser.getDepartmentid());
			}
			// 首页展示
			seaExpert.setShow_flag("1");
			
			// 只检索专家
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "expertShowList";
	}
	
	/**
	 * 专家风采检索
	 * @return
	 * @throws Exception
	 */
	public String expertShowSeachList() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			// 科室绑定
			if (!Constants.ADMIN.equals(sessUser.getPermiss())) {
				seaExpert.setDepartmentid(sessUser.getDepartmentid());
			}
			// 首页展示
			seaExpert.setShow_flag("1");
			
			pagination = new PgsqlPagination();
			
			// 只检索专家
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "expertShowList";
	}
	
	/**
	 * 专家风采删除
	 * @return
	 * @throws Exception
	 */
	public String deleteExpertShow() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			expertBean.setUpdate_user(sessUser.getUser_id());
			
			expertBiz.deleteExpertShow(expertBean);
			
			okmessage = "专家删除成功！";
			
			
			// 科室绑定
			if (!Constants.ADMIN.equals(sessUser.getPermiss())) {
				seaExpert.setDepartmentid(sessUser.getDepartmentid());
			}
			// 首页展示
			seaExpert.setShow_flag("1");
			
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			
			if (expertBeanList.size() == 0) {
				pagination.setPage(pagination.getPage() - 1);
				expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			}
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "expertShowList";
	}
	
	/**
	 * 专家风采检索
	 * @return
	 * @throws Exception
	 */
	public String diaShowSeachList() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			// 科室绑定
			if (!Constants.ADMIN.equals(sessUser.getPermiss())) {
				seaExpert.setDepartmentid(sessUser.getDepartmentid());
			}
			// 首页展示
			seaExpert.setShow_flag("0");
			
			pagination = new PgsqlPagination();
			
			pagination.setPageSize(5);
			
			// 只检索专家
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "diaShowList";
	}
	
	/**
	 * 专家风采检索
	 * @return
	 * @throws Exception
	 */
	public String diaShowList() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			// 科室绑定
			if (!Constants.ADMIN.equals(sessUser.getPermiss())) {
				seaExpert.setDepartmentid(sessUser.getDepartmentid());
			}
			// 首页展示
			seaExpert.setShow_flag("0");
			
			pagination.setPageSize(5);
			
			// 只检索专家
			expertBeanList = expertBiz.getAllExpertInfoList(seaExpert, pagination);
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "diaShowList";
	}
	
	
	/**
	 * 专家风采删除
	 * @return
	 * @throws Exception
	 */
	public String addExpertShow() throws Exception {
		try {
			okmessage = "";
			errormessage = "";
			
			UserBean sessUser = (UserBean) TimeOutFilter.getSessionLoginFlag();
			
			
			String strAddShow = expertBean.getShow_flag();
			
			String[] addShow = strAddShow.split(",");
			
			for (int i = 0; i < addShow.length; i++) {
				ExpertBean tmp = new ExpertBean();
				tmp.setExpert_id(addShow[i]);
				tmp.setUpdate_user(sessUser.getUser_id());
				expertBiz.addExpertShow(tmp);
			}
			
			okmessage = "新的风采已添加";
			
			 
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return "diaShowList";
	}
	
}
