/**
 	<filter>
		<filter-name>EncodingFilter</filter-name>
		<filter-class>com.ichengsi.cralis.filter.EncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>ignore</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>EncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

 */
package filter;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private String encoding;

	private boolean ignore = true;
	
	public void init(FilterConfig config) throws ServletException {
		
		String value = config.getInitParameter("ignore");
		if (value == null) {
			this.ignore = true;
		} else {
			ignore = Boolean.parseBoolean(value);
		}
		
		this.encoding = config.getInitParameter("encoding");
		if (encoding == null) {
			this.ignore = false;
		} else {
			if (!Charset.isSupported(encoding)) {
				this.ignore = false;
			}
		}
	}

	public void doFilter(ServletRequest request, ServletResponse  response, FilterChain chain)
			throws IOException, ServletException {

		if (ignore) {
			request.setCharacterEncoding(encoding);
		}
		
		chain.doFilter(request, response);
		
	}

	public void destroy() {
		this.encoding = null;
	}

}
