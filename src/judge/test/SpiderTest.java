package judge.test;

import java.util.Date;

import com.jfinal.plugin.activerecord.Page;

import judge.action.BaseController;
import judge.model.BaseModel;
import judge.model.DescriptionModel;
import judge.model.ProblemModel;
import judge.service.BaseService;
import judge.service.IBaseService;
import judge.spider.*;

public class SpiderTest extends BaseController
{
	static public IBaseService baseService = BaseService.baseService;

	@SuppressWarnings("rawtypes")
	public void result()
	{
		int pageNumber = 1;
		if (isParaExists("p"))
			pageNumber = getParaToInt("p", 1);
		else
			pageNumber = Integer.parseInt(getCookie("pageNumber", "1"));
		int pageSize = getParaToInt("s", 20);

		Page<BaseModel> problemList = ProblemModel.dao.paginate(pageNumber, pageSize, "SELECT *", "FROM t_problem");
		setAttr("problemList", problemList);

		render("spider_result.html");
	}

	public void all()
	{
		run("Aizu", AizuSpider.class);

		run("CodeForces", "10A", new CodeForcesSpider());

		run("CSU", CSUSpider.class);

		run("FZU", FZUSpider.class);

		run("HDU", HDUSpider.class);

		run("HUST", HUSTSpider.class);

		run("HYSBZ", HYSBZSpider.class);

		run("LightOJ", LightOJSpider.class);

		run("NBUT", NBUTSpider.class);

		run("POJ", POJSpider.class);

		run("SCU", SCUSpider.class);

		run("SGU", "100", new SGUSpider());

		run("SPOJ", "ABA12B", new SPOJSpider());

		run("UESTC", UESTCSpider.class);

		run("URAL", URALSpider.class);

		run("UVALive", "2000", new UVALiveSpider());

		run("UVA", "10000", new UVASpider());

		run("ZOJ", ZOJSpider.class);

		run("ZTrening", ZTreningSpider.class);
	}

	public void aizu()
	{
		run("Aizu", AizuSpider.class);
	}

	public void codeForces()
	{
		run("CodeForces", "10A", new CodeForcesSpider());
	}

	public void csu()
	{
		run("CSU", CSUSpider.class);
	}

	public void fzu()
	{
		run("FZU", FZUSpider.class);
	}

	public void hdu()
	{
		run("HDU", HDUSpider.class);
	}

	public void hust()
	{
		run("HUST", HUSTSpider.class);
	}

	public void hysbz()
	{
		run("HYSBZ", HYSBZSpider.class);
	}

	public void light()
	{
		run("LightOJ", LightOJSpider.class);
	}

	public void nbut()
	{
		run("NBUT", NBUTSpider.class);
	}

	public void poj()
	{
		run("POJ", POJSpider.class);
	}

	public void scu()
	{
		run("SCU", SCUSpider.class);
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
		run("UESTC", UESTCSpider.class);
	}

	public void ural()
	{
		run("URAL", URALSpider.class);
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
		run("ZOJ", ZOJSpider.class);
	}

	public void zTrening()
	{
		run("ZTrening", ZTreningSpider.class);
	}

	private void run(String OJ, Class<? extends Spider> spider)
	{
		String pid1 = "1001";
		if (!isParaBlank(0))
			pid1 = getPara(0);
		String pid2 = pid1;
		if (!isParaBlank(1))
			pid2 = getPara(1);
		int l = Integer.parseInt(pid1), r = Integer.parseInt(pid2), tmp;
		if (l > r)
		{
			tmp = l;
			l = r;
			r = tmp;
		}
		for (Integer a = l; a <= r; ++a)
		{
			try
			{
				run(OJ, a.toString(), spider.newInstance());
			} catch (InstantiationException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		redirect("/spider/result");
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
