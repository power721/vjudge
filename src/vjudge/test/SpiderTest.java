package vjudge.test;

import java.util.Date;

import vjudge.model.DescriptionModel;
import vjudge.model.ProblemModel;
import vjudge.spider.Spider;
import vjudge.action.BaseController;
import vjudge.service.BaseService;
import vjudge.service.IBaseService;
import vjudge.spider.POJSpider;

public class SpiderTest extends BaseController
{
	static public IBaseService baseService = BaseService.baseService;
	
	public void poj()
	{
		String pid = getPara(0);
		if(pid == null || pid.equals(""))
			pid = "1000";
		
		ProblemModel problem = new ProblemModel();
		problem.setOriginOJ("POJ");
		problem.setOriginProb(pid);
		problem.setTitle("Crawling……");
		baseService.addOrModify(problem);
		
		DescriptionModel description = new DescriptionModel();
		description.setUpdateTime(new Date());
		description.setAuthor("0");
		description.setRemarks("Initialization.");
		description.setVote(0);
		description.setProblem(problem);
		baseService.addOrModify(description);

		problem.setTimeLimit(1);
		problem.setTriggerTime(new Date());
		baseService.addOrModify(problem);

		Spider spider = new POJSpider();
		spider.setProblem(problem);
		spider.setDescription(description);
		spider.start();
		renderText("Sipder crawling " + pid + "……");
	}

}
