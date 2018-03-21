/**
 * 
  	<filter>
		<filter-name>TimeOutFilter</filter-name>
		<filter-class>com.ichengsi.cralis.filter.TimeOutFilter</filter-class>
		<init-param>
			<param-name>timeout</param-name>
			<param-value>/login.jsp, LoginAction.do, /timeout.jsp, *.js, *.css, image/, images/, *.gif</param-value>
		</init-param>
		<init-param>
			<param-name>timeoutpath</param-name>
			<param-value></param-value>
		</init-param>
		<init-param>
			<param-name>pathRoot</param-name>
			<param-value></param-value>
		</init-param>
		<init-param>
			<param-name>sessionFlag</param-name>
			<param-value></param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>TimeOutFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

 * 
 * 
 * 
 */
package filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TimeOutFilter implements Filter {
	
	private static TimeOutInner innerTimeOut;
	
	private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<HttpServletRequest>();

	public void init(FilterConfig config) throws ServletException {
		
		if (innerTimeOut != null) {
			return;
		}
		
		innerTimeOut = new TimeOutInner();
		
		innerTimeOut.setSessionFlag(config.getInitParameter("sessionFlag"));
		
		String timeoutpath = config.getInitParameter("timeoutpath");
		innerTimeOut.addTimeOutPathPattern(timeoutpath);
		if (timeoutpath!=null && timeoutpath.startsWith("/")) {
			boolean pathRoot = Boolean.parseBoolean(config.getInitParameter("pathRoot"));
			if (!pathRoot) {
				String subpath = config.getServletContext().getContextPath();
				if (subpath.endsWith("/")) {
					subpath = subpath.substring(0, subpath.length()-1);
				}
				timeoutpath = subpath + timeoutpath;
			}
		} 
		innerTimeOut.setTimeoutpath(timeoutpath);
		
		innerTimeOut.addTimeOutPathPattern(config.getInitParameter("timeout"));
		
	}

	public void doFilter(ServletRequest request, ServletResponse  response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest)request;
		
		requestThreadLocal.set(hreq);
		
		try {
		
	//		String url=hreq.getContextPath(); //    
	
	//		url=hreq.getQueryString(); //  
	//		url=hreq.getRequestURI(); //  
	//		url=hreq.getRequestURL().toString();    //    
	//		url=hreq.getServerName();    //  ip
	//		url=hreq.getServletPath();  //  
			
			String urlPathFile = hreq.getServletPath();
			
			if (!"/".equals(urlPathFile) && !innerTimeOut.isPass(urlPathFile)) {
				HttpSession session = hreq.getSession();
				if (session != null) {
					Object objAgent = session.getAttribute(innerTimeOut.getSessionFlag());
					if (objAgent == null) {
						((HttpServletResponse)response).sendRedirect(innerTimeOut.getTimeoutpath());
						return;
					}
				}
			}
			
			chain.doFilter(request, response);
		
		} catch (Exception e) {
			throw e;
		} finally {
			
			requestThreadLocal.remove();
			
		}
		
	}

	public void destroy() {
	}
//	
//	public static void setSessionLoginFlag(ServletRequest request, Object obj) {
//		HttpServletRequest hreq = (HttpServletRequest)request;
//		HttpSession session = hreq.getSession();
//		if (session != null) {
//			session.setAttribute(innerTimeOut.getSessionFlag(), obj);
//		}
//	}
//
//	
//	public static Object getSessionLoginFlag(ServletRequest request) {
//		HttpServletRequest hreq = (HttpServletRequest)request;
//		HttpSession session = hreq.getSession();
//		if (session != null) {
//			return session.getAttribute(innerTimeOut.getSessionFlag());
//		}
//		return null;
//	}

	public static void setSessionLoginFlag(Object obj) {
		HttpServletRequest hreq = requestThreadLocal.get();
		HttpSession session = hreq.getSession();
		if (session != null) {
			if (obj == null) {
				session.removeAttribute(innerTimeOut.getSessionFlag());
			} else {
				session.setAttribute(innerTimeOut.getSessionFlag(), obj);
			}
		}
	}

	public static Object getSessionLoginFlag() {
		HttpServletRequest hreq = requestThreadLocal.get();
		HttpSession session = hreq.getSession();
		if (session != null) {
			return session.getAttribute(innerTimeOut.getSessionFlag());
		}
		return null;
	}

	public static HttpSession getSession() {
		
		HttpServletRequest hreq = requestThreadLocal.get();
		
		return hreq.getSession();
	}
	
	public static HttpServletRequest getRequest() {
		
		HttpServletRequest hreq = requestThreadLocal.get();
		
		return hreq;
	}
	
	private class TimeOutInner {
		
		private String sessionFlag;

		public void setSessionFlag(String sessionFlag) {
			if (sessionFlag== null || "".equals(sessionFlag)) {
				sessionFlag = "com.ichengsi.filter.timeoutfilter";
			}
			this.sessionFlag = sessionFlag;
		}

		public String getSessionFlag() {
			return sessionFlag;
		}
		
		private String timeoutpath;

		public void setTimeoutpath(String timeoutpath) {
			if (timeoutpath == null) {
				timeoutpath = "/";
			}
			this.timeoutpath = timeoutpath;
		}

		public String getTimeoutpath() {
			return timeoutpath;
		}
		
		//忽略�??时�?�??�??路�?模�?

		private Set<String> noTimeOutPathPatternSet;
		
		public void addTimeOutPathPattern(String paths) {
			if (noTimeOutPathPatternSet == null) {
				noTimeOutPathPatternSet = new HashSet<String>();
			}
			
			if (paths != null) {
				String[] files = paths.split(", ");
				
				for (String file : files) {
					String match = createPatten(file);
					if (!noTimeOutPathPatternSet.contains(match)) {
						noTimeOutPathPatternSet.add(match);
					}
				}
			}
			
		}
		
		private String createPatten(String path) {
			if (path!=null && !path.trim().equals("")) {
				path = path.replaceAll("\\[", "[[]");
				path = path.replaceAll("\\]", "[]]");
				path = path.replaceAll("\\\\", "[\\]");
				path = path.replaceAll("\\.", "[.]");
				path = path.replaceAll("\\?", "[?]");
				path = path.replaceAll("\\|", "[|]");
				path = path.replaceAll("\\+", "[|]");
				path = path.replaceAll("\\*", ".*");
				if (path.endsWith("/")) {
					path += ".*$";
				} else {
					path += "$";
				}
				if (path.startsWith("/")) {
					path = "^" + path;
				} else {
					path = "^/.*" + path;
				}
				
				path += "(?i)";
				
				return path;
			}else {
				return null;
			}
		}

		public boolean isPass(String url) {
			
			if (noTimeOutPathPatternSet != null) {
				for (String paten: noTimeOutPathPatternSet) {
					if (Pattern.matches(paten, url)) {
						return true;
					}
				}
			}
			return false;
		}
		
	}
}
