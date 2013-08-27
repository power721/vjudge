package vjudge.spider;

import vjudge.bean.Description;
import vjudge.bean.Problem;
import vjudge.service.BaseService;
import vjudge.service.IBaseService;

public abstract class Spider extends Thread implements Cloneable
{
	static public IBaseService baseService = BaseService.baseService;

	public Problem problem;
	public Description description;

	public Object clone()
	{
		Spider o = null;
		try
		{
			o = (Spider) super.clone();
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return o;
	}

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
			// ProblemModel.dao.addOrModify(problem);
			// DescriptionModel.dao.addOrModify(description);
		} catch (Exception e)
		{
			e.printStackTrace();
			if (problem.getUrl() == null)
			{
				// 本次是第一次抓取，且失败，认为输入OJ题号错误，删除
				// ProblemModel.dao.delete(problem);
				baseService.delete(problem);
			} else
			{
				// 本次虽失败，但因为题目本来是好的，估计是网络问题，故不删
				problem.setTimeLimit(2);
				// ProblemModel.dao.addOrModify(problem);
				baseService.addOrModify(problem);
			}
		}
	}

	public Problem getProblem()
	{
		return problem;
	}

	public void setProblem(Problem problem)
	{
		this.problem = problem;
	}

	public Description getDescription()
	{
		return description;
	}

	public void setDescription(Description description)
	{
		this.description = description;
	}

}
