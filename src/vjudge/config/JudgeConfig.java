package vjudge.config;

import vjudge.action.MainController;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;

public class JudgeConfig extends JFinalConfig
{

	@Override
	public void configConstant(Constants me)
	{
		// TODO Auto-generated method stub
		loadPropertyFile("vjudge.properties");
		me.setDevMode(getPropertyToBoolean("devMode", false));
		me.setBaseViewPath("/WEB-INF/pages");
	}

	@Override
	public void configRoute(Routes me)
	{
		// TODO Auto-generated method stub
		me.add("/", MainController.class);
	}

	@Override
	public void configPlugin(Plugins me)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void configInterceptor(Interceptors me)
	{
		// TODO Auto-generated method stub
		me.add(new SessionInViewInterceptor());
	}

	@Override
	public void configHandler(Handlers me)
	{
		// TODO Auto-generated method stub

	}
	
	@Override
	public void afterJFinalStart()
	{
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		JFinal.start("WebRoot", 80, "/", 5);
	}

}
