package judge.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class GlobalInterceptor implements Interceptor
{

	@Override
	public void intercept(ActionInvocation ai)
	{
		Controller controller = ai.getController();
		HttpServletRequest request = controller.getRequest();

		StringBuilder sb = new StringBuilder().append(request.getScheme()).append("://").append(request.getServerName());
		if (request.getServerPort() != 80)
		{
			sb.append(":").append(request.getServerPort());
		}
		sb.append(request.getContextPath());

		String baseUrl = sb.toString();
		controller.setAttr("baseUrl", baseUrl);

		ai.invoke();
	}

}
