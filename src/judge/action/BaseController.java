package judge.action;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;

public class BaseController extends Controller
{
	protected final Logger log = Logger.getLogger(getClass());
	
	public Logger getLog()
	{
		return log;
	}
}
