package listener;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;

import common.CommonFunction;
import common.Constants;
import filter.TimeOutFilter;
import parameter.CaseBean;
import parameter.CurriculumBean;
import parameter.MessageBean;
import parameter.UserBean;
import util.Util;

public class ServletContextListener implements javax.servlet.ServletContextListener {

	private static String WEB_ABSOLUTE_PATH;
	
	public static String getWEB_ABSOLUTE_PATH() {
		return WEB_ABSOLUTE_PATH;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		WEB_ABSOLUTE_PATH = arg0.getServletContext().getRealPath("/");
		
		try {
			// 第一次开始时间设定
			Calendar calendar = Calendar.getInstance();
			// 一分钟后
			calendar.add(Calendar.MINUTE, 1);
			// 当前时间
			Date curDate = new Date();
			
			Date minDateTmp = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
			SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Date minDate = sd.parse(sdf.format(minDateTmp) + ":00");
			
	    	// 开始定时任务
			Runnable runnable = new Runnable() {  
	            public void run() {  
	    			try {
	    				// 当前时间
	    				Date curDate = new Date();
	    				
	    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
	    				SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
						Date minDate = sd.parse(sdf.format(curDate) + ":00");
						
						// 微课堂信息
						List<CurriculumBean> curriculumList = Util.getCurriculumListByDate(new Timestamp(minDate.getTime()));
						
						if (curriculumList != null && curriculumList.size() != 0) {
							
							for (int i = 0; i < curriculumList.size(); i++) {
								CurriculumBean curriculumBean = curriculumList.get(i);
								
								// 插入message信息--------start
								//获取医患故事详情
								MessageBean message = new MessageBean();
								// 对应内容ID
								message.setCommentid(String.valueOf(curriculumBean.getCurriculum_id()));
								// 机能ID
								message.setFunctionid(Constants.CURRICULUM);
								// 消息内容
								message.setMessage("您的微课堂即将开始，请前往个人中心开启课程！");
								//标题
								message.setTitle("微课堂即将开始");
								// 用户ID
								message.setUserid(curriculumBean.getLecturesuser());
								
								message.setCreate_user("admin");
								message.setUpdate_user("admin");
								
								Util.addMessageInfo(message);
								
								// 插入message信息--------end
								
								//TODO
//								System.out.println(curriculumBean.getStart_date());
							}
						}
						
						
						// 病例活动信息
						List<CaseBean> endActivity = Util.getActivityListByEndDate(new Timestamp(minDate.getTime()));
						
						if (endActivity != null) {
							
							for (CaseBean caseBean : endActivity) {
								Util.updActivity("3", caseBean.getActivity_id());
								System.out.println("活动已结束");
							}
						}
						
						endActivity = Util.getActivityListByStartDate(new Timestamp(minDate.getTime()));
						
						if (endActivity != null) {
							
							for (CaseBean caseBean : endActivity) {
								Util.updActivity("2", caseBean.getActivity_id());
								System.out.println("活动已开始");
							}
							
						}
						
						// 预约信息
						List<Integer> reserveList = Util.getReserveListByEndDate(new Timestamp(curDate.getTime()));
						
						if (reserveList != null) {
							for (Integer reserveId : reserveList) {
								Util.updReserve(reserveId);
							}
						}
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
	            }  
	        };  
	        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
	        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
	        service.scheduleAtFixedRate(runnable, (minDate.getTime() - curDate.getTime())/1000+1, 60, TimeUnit.SECONDS); 
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
