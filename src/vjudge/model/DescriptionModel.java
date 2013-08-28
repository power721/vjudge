package vjudge.model;

import java.util.Date;
import java.util.Set;

public class DescriptionModel extends BaseModel<DescriptionModel>
{
	private static final long serialVersionUID = -376409847087991812L;

	private ProblemModel problem;
	private Set<CproblemModel> cproblems;

	public static final DescriptionModel dao = new DescriptionModel();

	@Override
	public boolean addOrModify()
	{
		if (problem != null)
		{
			set("C_PROBLEM_ID", problem.getId());
		}
		DescriptionModel descriptionModel = (DescriptionModel) findFirst("SELECT * FROM t_description WHERE C_PROBLEM_ID=? AND C_AUTHOR=?",
				get("C_PROBLEM_ID"), getAuthor());
		if (descriptionModel == null)
		{
			getLog().info("Insert new problem description: " + get("C_PROBLEM_ID") + "-" + getAuthor());
			return save();
		}

		setId(descriptionModel.getId());
		return update();
	}

	/**
	 * 去除空标签
	 * 
	 * @param string
	 * @return
	 */
	private String trans(String string)
	{
		if (string != null)
		{
			string = string.replaceAll("(?i)(?<=(\\b|java))script\\b", "ｓcript").trim();
			if (!string.contains("img") && !string.contains("IMG") && !string.contains("iframe") && string.matches("(<[^<>]*>\\s*)*"))
			{
				string = "";
			}
		}
		return string;
	}

	// @JSON(deserialize=false,serialize=false)
	public ProblemModel getProblem()
	{
		return problem;
	}

	public void setProblem(ProblemModel problem)
	{
		this.problem = problem;
	}

	public int getId()
	{
		return get("C_ID");
	}

	public void setId(int id)
	{
		set("C_ID", id);
	}

	public String getDescription()
	{
		return get("C_DESCRIPTION");
	}

	public void setDescription(String description)
	{
		set("C_DESCRIPTION", trans(description));
	}

	public String getInput()
	{
		return get("C_INPUT");
	}

	public void setInput(String input)
	{
		set("C_INPUT", trans(input));
	}

	public String getOutput()
	{
		return get("C_OUTPUT");
	}

	public void setOutput(String output)
	{
		set("C_OUTPUT", trans(output));
	}

	public String getSampleInput()
	{
		return get("C_SAMPLEINPUT");
	}

	public void setSampleInput(String sampleInput)
	{
		set("C_SAMPLEINPUT", trans(sampleInput));
	}

	public String getSampleOutput()
	{
		return get("C_SAMPLEOUTPUT");
	}

	public void setSampleOutput(String sampleOutput)
	{
		set("C_SAMPLEOUTPUT", trans(sampleOutput));
	}

	public String getHint()
	{
		return get("C_HINT");
	}

	public void setHint(String hint)
	{
		set("C_HINT", trans(hint));
	}

	// @JSON(format="yyyy-MM-dd")
	public Date getUpdateTime()
	{
		return get("C_UPDATE_TIME");
	}

	public void setUpdateTime(Date updateTime)
	{
		set("C_UPDATE_TIME", updateTime);
	}

	public String getAuthor()
	{
		return get("C_AUTHOR");
	}

	public void setAuthor(String author)
	{
		set("C_AUTHOR", author);
	}

	public String getRemarks()
	{
		return get("C_REMARKS");
	}

	public void setRemarks(String remarks)
	{
		set("C_REMARKS", remarks);
	}

	public int getVote()
	{
		return get("C_VOTE");
	}

	public void setVote(int vote)
	{
		set("C_VOTE", vote);
	}

	// @JSON(deserialize=false,serialize=false)
	public Set<CproblemModel> getCproblems()
	{
		return cproblems;
	}

	public void setCproblems(Set<CproblemModel> cproblems)
	{
		this.cproblems = cproblems;
	}

}
