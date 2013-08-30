package judge.core;

import judge.action.MainController;
import judge.model.*;
import judge.test.SpiderTest;
import judge.tool.ApplicationContainer;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

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
		me.add("/spider", SpiderTest.class, "test");
	}

	@Override
	public void configPlugin(Plugins me)
	{
		// TODO Auto-generated method stub
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);
		arp.addMapping("t_user",  "C_ID", UserModel.class); // 映射t_user表到 UserModel模型
		arp.addMapping("t_problem","C_ID", ProblemModel.class);
		arp.addMapping("t_contest", "C_ID", ContestModel.class);
		arp.addMapping("t_cproblem", "C_ID", CproblemModel.class);
		arp.addMapping("t_submission", "C_ID", SubmissionModel.class);
		arp.addMapping("t_description", "C_ID", DescriptionModel.class);
		arp.addMapping("t_replay_status", "C_ID", ReplayStatusModel.class);
		me.add(arp);
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
		ApplicationContainer.sc = JFinal.me().getServletContext();
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
