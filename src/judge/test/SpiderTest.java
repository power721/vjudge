package judge.test;

import java.util.Date;

import judge.action.BaseController;
import judge.model.DescriptionModel;
import judge.model.ProblemModel;
import judge.service.BaseService;
import judge.service.IBaseService;
import judge.spider.*;

public class SpiderTest extends BaseController
{
	static public IBaseService baseService = BaseService.baseService;
	
	public void result()
	{
		// TODO list the problems
	}
	
	public void all()
	{
		run("Aizu", new AizuSpider());

		run("CodeForces", "10A", new CodeForcesSpider());

		run("CSU", new CSUSpider());

		run("FZU", new FZUSpider());

		run("HDU", new HDUSpider());

		run("HUST", new HUSTSpider());

		run("HYSBZ", new HYSBZSpider());

		run("LightOJ", new LightOJSpider());

		run("NBUT", new NBUTSpider());

		run("POJ", new POJSpider());
		
		run("SCU", new SCUSpider());
		
		run("SGU", "100", new SGUSpider());

		run("SPOJ", "ABA12B", new SPOJSpider());

		run("UESTC", new UESTCSpider());

		run("URAL", new URALSpider());

		run("UVALive", "2000", new UVALiveSpider());

		run("UVA", "10000", new UVASpider());

		run("ZOJ", new ZOJSpider());

		run("ZTrening", new ZTreningSpider());
	}
	
	public void aizu()
	{
		run("Aizu", new AizuSpider());
	}
	
	public void codeForces()
	{
		run("CodeForces", "10A", new CodeForcesSpider());
	}
	
	public void csu()
	{
		run("CSU", new CSUSpider());
	}
	
	public void fzu()
	{
		run("FZU", new FZUSpider());
	}
	
	public void hdu()
	{
		run("HDU", new HDUSpider());
	}
	
	public void hust()
	{
		run("HUST", new HUSTSpider());
	}
	
	public void hysbz()
	{
		run("HYSBZ", new HYSBZSpider());
	}
	
	public void light()
	{
		run("LightOJ", new LightOJSpider());
	}
	
	public void nbut()
	{
		run("NBUT", new NBUTSpider());
	}
	
	public void poj()
	{
		run("POJ", new POJSpider());
	}
	
	public void scu()
	{
		run("SCU", new SCUSpider());
	}
	
	public void sgu()
	{
		run("SGU", "100", new SGUSpider());
	}
	
	public void spoj()
	{
		run("SPOJ", "ABA12B", new SPOJSpider());
	}
	
	public void uestc()
	{
		run("UESTC", new UESTCSpider());
	}
	
	public void ural()
	{
		run("URAL", new URALSpider());
	}
	
	public void uvaLive()
	{
		run("UVALive", "2000", new UVALiveSpider());
	}
	
	public void uva()
	{
		run("UVA", "10000", new UVASpider());
	}
	
	public void zoj()
	{
		run("ZOJ", new ZOJSpider());
	}
	
	public void zTrening()
	{
		run("ZTrening", new ZTreningSpider());
	}
	
	private void run(String OJ, Spider spider)
	{
		String pid = getPara(0);
		if(pid == null || pid.equals(""))
			pid = "1001";
		run(OJ, pid, spider);
	}

	private void run(String OJ, String pid, Spider spider)
	{
		ProblemModel problem = new ProblemModel();
		problem.setOriginOJ(OJ);
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

		spider.setProblem(problem);
		spider.setDescription(description);
		spider.start();
		
		getLog().info("Sipder crawling " + OJ + "-" + pid + "……");
		renderText("Sipder crawling " + OJ + "-" + pid + "……");
	}
}
