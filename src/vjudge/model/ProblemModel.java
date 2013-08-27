package vjudge.model;

import java.util.Iterator;

import vjudge.bean.Description;
import vjudge.bean.Problem;

public class ProblemModel extends BaseModel<ProblemModel>
{
	private static final long serialVersionUID = -7026460598310425085L;

	public static final ProblemModel dao = new ProblemModel();

	public ProblemModel find(Problem problem)
	{
		ProblemModel problemModel = (ProblemModel) dao.findFirst("SELECT * FROM t_problem WHERE C_originOJ=? AND C_originProb=? LIMIT 1",
				problem.getOriginOJ(), problem.getOriginProb());
		return problemModel;
	}

	@Override
	public boolean addOrModify(Object bean)
	{
		Problem problem = (Problem) bean;
		boolean insertFlag = false;
		ProblemModel problemModel = (ProblemModel) problem.getModel();
		if (problemModel == null)
		{
			problemModel = find(problem);
			if (problemModel == null)
			{
				problemModel = new ProblemModel();
				problem.setModel(problemModel);
				insertFlag = true;
			}
		}

		problemModel.set("C_TITLE", problem.getTitle());
		problemModel.set("C_SOURCE", problem.getSource());
		problemModel.set("C_URL", problem.getUrl());
		problemModel.set("C_originOJ", problem.getOriginOJ());
		problemModel.set("C_originProb", problem.getOriginProb());
		problemModel.set("C_MEMORYLIMIT", problem.getMemoryLimit());
		problemModel.set("C_TIMELIMIT", problem.getTimeLimit());
		problemModel.set("C_TRIGGER_TIME", problem.getTriggerTime());
		// what to do with descriptions, cproblems, submissions?

		if (insertFlag)
		{
			problemModel.save();
			problem.setId(problemModel.getInt("C_ID"));
			return problem.getId() != 0;
		}
		return problemModel.update();
	}

	@Override
	public boolean delete(Object bean)
	{
		Problem problem = (Problem) bean;

		Iterator<Description> iterator = problem.getDescriptions().iterator();
		Description description;
		while (iterator.hasNext())
		{
			description = (Description) iterator.next();
			DescriptionModel.dao.delete(description);
		}
		// TODO delete Cproblem and Submission ?

		return dao.deleteById(problem.getId());
	}

}
