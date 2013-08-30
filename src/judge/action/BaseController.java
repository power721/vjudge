package judge.action;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;

import judge.tool.Tools;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;

public class BaseController extends Controller
{
	protected final Logger log = Logger.getLogger(getClass());

	/**
	 * 获得application
	 * 
	 * @return
	 */
	public ServletContext getApplication()
	{
		return getSession().getServletContext();
	}

	/**
	 * 获取真实IP地址
	 * 
	 * @return
	 */
	public String getRemoteAddr()
	{
		return Tools.getRemoteAddr(getRequest());
	}

	/**
	 * java EE6.0设置安全cookie的方法
	 * 
	 * @param name
	 * @param value
	 * @param maxAgeInSeconds
	 * @return
	 */
	public Controller setCookieHttpOnly(String name, String value, int maxAgeInSeconds)
	{
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		setCookie(cookie);
		return this;
	}

	public Logger getLog()
	{
		return log;
	}
}
