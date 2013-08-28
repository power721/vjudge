package judge.spider;

import judge.model.DescriptionModel;
import judge.model.ProblemModel;
import judge.service.BaseService;
import judge.service.IBaseService;

public abstract class Spider extends Thread
{
	static public IBaseService baseService = BaseService.baseService;

	public ProblemModel problem;
	public DescriptionModel description;

	/**
	 * 抓取题目,对problem和description进行赋值
	 * 
	 * @throws Exception
	 */
	public abstract void crawl() throws Exception;

	public void run()
	{
		try
		{
			crawl();
			description.setProblem(problem);
			baseService.addOrModify(problem);
			baseService.addOrModify(description);
		} catch (Exception e)
		{
			e.printStackTrace();
			if (problem.getUrl() == null)
			{
				// 本次是第一次抓取，且失败，认为输入OJ题号错误，删除
				baseService.delete(problem);
			} else
			{
				// 本次虽失败，但因为题目本来是好的，估计是网络问题，故不删
				problem.setTimeLimit(2);
				baseService.addOrModify(problem);
			}
		}
	}

	public ProblemModel getProblem()
	{
		return problem;
	}

	public void setProblem(ProblemModel problem)
	{
		this.problem = problem;
	}

	public DescriptionModel getDescription()
	{
		return description;
	}

	public void setDescription(DescriptionModel description)
	{
		this.description = description;
	}

}
