package judge.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProblemModel extends BaseModel<ProblemModel>
{
	private static final long serialVersionUID = -7026460598310425085L;

	private List<DescriptionModel> descriptions;
	private List<CproblemModel> cproblems;
	private List<SubmissionModel> submissions;

	public static final ProblemModel dao = new ProblemModel();

	@Override
	public boolean addOrModify()
	{
		ProblemModel problemModel = (ProblemModel) findFirst("SELECT * FROM t_problem WHERE C_originOJ=? AND C_originProb=? LIMIT 1", getOriginOJ(),
				getOriginProb());
		if (problemModel == null)
		{
			getLog().info("Insert new problem: " + getOriginOJ() + "-" + getOriginProb());
			return save();
		}

		setId(problemModel.getId());
		getLog().debug("Update problem(" + getId() + "): " + getOriginOJ() + "-" + getOriginProb());
		return update();
	}

	public boolean deleteModel()
	{
		List<DescriptionModel> descriptions = DescriptionModel.dao.find("SELECT * FROM t_description WHERE C_PROBLEM_ID=?", getId());
		if(descriptions != null)
		{
			Iterator<DescriptionModel> iterator = descriptions.iterator();
			DescriptionModel description;
			while (iterator.hasNext())
			{
				description = (DescriptionModel) iterator.next();
				description.delete();
			}
		}
		// TODO delete cproblems and submissions

		getLog().info("Delete problem: " + getOriginOJ() + "-" + getOriginProb());
		return delete();
	}

	public int getId()
	{
		return get("C_ID");
	}

	public void setId(int id)
	{
		set("C_ID", id);
	}

	public String getTitle()
	{
		return get("C_TITLE");
	}

	public void setTitle(String title)
	{
		set("C_TITLE", title);
	}

	public String getSource()
	{
		return get("C_SOURCE");
	}

	public void setSource(String source)
	{
		if (source != null)
		{
			source = source.trim();
			if (source.matches("(<[^<>]*>\\s*)*"))
			{
				source = "";
			}
		}
		set("C_SOURCE", source);
	}

	public String getUrl()
	{
		return get("C_URL");
	}

	public void setUrl(String url)
	{
		set("C_URL", url);
	}

	public String getOriginOJ()
	{
		return get("C_originOJ");
	}

	public void setOriginOJ(String originOJ)
	{
		set("C_originOJ", originOJ);
	}

	public String getOriginProb()
	{
		return get("C_originProb");
	}

	public void setOriginProb(String originProb)
	{
		set("C_originProb", originProb);
	}

	public int getMemoryLimit()
	{
		return get("C_MEMORYLIMIT");
	}

	public void setMemoryLimit(int memoryLimit)
	{
		set("C_MEMORYLIMIT", memoryLimit);
	}

	public int getTimeLimit()
	{
		return get("C_TIMELIMIT");
	}

	public void setTimeLimit(int timeLimit)
	{
		set("C_TIMELIMIT", timeLimit);
	}

	public Date getTriggerTime()
	{
		return get("C_TRIGGER_TIME");
	}

	public void setTriggerTime(Date triggerTime)
	{
		set("C_TRIGGER_TIME", triggerTime);
	}

	public List<DescriptionModel> getDescriptions()
	{
		return descriptions;
	}

	public void setDescriptions(List<DescriptionModel> descriptions)
	{
		this.descriptions = descriptions;
	}

	public List<CproblemModel> getCproblems()
	{
		return cproblems;
	}

	public void setCproblems(List<CproblemModel> cproblems)
	{
		this.cproblems = cproblems;
	}

	public List<SubmissionModel> getSubmissions()
	{
		return submissions;
	}

	public void setSubmissions(List<SubmissionModel> submissions)
	{
		this.submissions = submissions;
	}

}
