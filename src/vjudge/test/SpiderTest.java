package vjudge.test;

import java.util.Date;

import vjudge.spider.Spider;
import vjudge.action.BaseController;
import vjudge.bean.Description;
import vjudge.bean.Problem;
import vjudge.service.BaseService;
import vjudge.service.IBaseService;
import vjudge.spider.POJSpider;

public class SpiderTest extends BaseController
{
	static public IBaseService baseService = BaseService.baseService;
	
	public void poj()
	{
		Problem problem = new Problem();
		problem.setOriginOJ("POJ");
		problem.setOriginProb("1000");
		problem.setTitle("Crawling……");
		baseService.addOrModify(problem);
		
		Description description = new Description();
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
		renderText("Sipder crawling……");
	}

}
