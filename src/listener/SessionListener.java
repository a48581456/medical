package listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import common.Constants;

import parameter.UserBean;
import util.Util;

public class SessionListener implements HttpSessionListener {

	static List<HttpSession> httpSessionList = new ArrayList<HttpSession>(); 
	
	
	public void sessionCreated(HttpSessionEvent hse) {
		
		httpSessionList.add(hse.getSession());
		
		//hse.getSession();

	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		
		HttpSession hsess = hse.getSession();
		httpSessionList.remove(hsess);
		Object obj = hsess.getAttribute("com.ichengsi.filter.timeoutfilter");
		if (obj != null) {
			UserBean userBean = (UserBean)obj;
			Util.updLoginStatus(userBean.getUser_id(), Constants.UNLOGIN);
		}

	}

	// 清除已登录
	public static void clearLogin(String userId) {
		for(HttpSession hsess : httpSessionList){
			Object obj = hsess.getAttribute("com.ichengsi.filter.timeoutfilter");
			if (obj != null) {
				UserBean userBean = (UserBean)obj;
				if (userId.equals(userBean.getUser_id())) {
					hsess.removeAttribute("com.ichengsi.filter.timeoutfilter");
				}
			}
		}
	}
	
}
