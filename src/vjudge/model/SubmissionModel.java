package vjudge.model;

import java.util.Date;

public class SubmissionModel extends BaseModel<SubmissionModel>
{

	private static final long serialVersionUID = -2840983971733565777L;

	private ProblemModel problem; // 外键 题目
	private UserModel user; // 外键 提交人
	private ContestModel contest; // 外键 比赛

	@Override
	public boolean addOrModify()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getIsOpen()
	{
		return get("C_ISOPEN");
	}

	public void setIsOpen(int isOpen)
	{
		set("C_ISOPEN", isOpen);
	}

	public String getLanguage()
	{
		return get("C_LANGUAGE");
	}

	public void setLanguage(String language)
	{
		set("C_LANGUAGE", language);
	}

	public String getSource()
	{
		return get("C_SOURCE");
	}

	public void setSource(String source)
	{
		set("C_SOURCE", source);
	}

	public int getId()
	{
		return get("C_ID");
	}

	public void setId(int id)
	{
		set("C_ID", id);
	}

	public String getStatus()
	{
		return get("C_STATUS");
	}

	public void setStatus(String status)
	{
		set("C_STATUS", status);
	}

	public int getTime()
	{
		return get("C_TIME");
	}

	public void setTime(int time)
	{
		set("C_TIME", time);
	}

	public int getMemory()
	{
		return get("C_MEMORY");
	}

	public void setMemory(int memory)
	{
		set("C_MEMORY", memory);
	}

	public Date getSubTime()
	{
		return get("C_SUBTIME");
	}

	public void setSubTime(Date subTime)
	{
		set("C_SUBTIME", subTime);
	}

	public String getDispLanguage()
	{
		return get("C_DISP_LANGUAGE");
	}

	public void setDispLanguage(String dispLanguage)
	{
		set("C_DISP_LANGUAGE", dispLanguage);
	}

	public String getUsername()
	{
		return get("C_USERNAME");
	}

	public void setUsername(String username)
	{
		set("C_USERNAME", username);
	}

	public ProblemModel getProblem()
	{
		return problem;
	}

	public void setProblem(ProblemModel problem)
	{
		this.problem = problem;
	}

	public UserModel getUser()
	{
		return user;
	}

	public void setUser(UserModel user)
	{
		this.user = user;
	}

	public ContestModel getContest()
	{
		return contest;
	}

	public void setContest(ContestModel contest)
	{
		this.contest = contest;
	}

	public String getOriginOJ()
	{
		return get("C_ORIGIN_OJ");
	}

	public void setOriginOJ(String originOJ)
	{
		set("C_ORIGIN_OJ", originOJ);
	}

	public String getOriginProb()
	{
		return get("C_ORIGIN_PROB");
	}

	public void setOriginProb(String originProb)
	{
		set("C_ORIGIN_PROB", originProb);
	}

	public int getIsPrivate()
	{
		return get("C_IS_PRIVATE");
	}

	public void setIsPrivate(int isPrivate)
	{
		set("C_IS_PRIVATE", isPrivate);
	}

	public String getAdditionalInfo()
	{
		return get("C_ADDITIONAL_INFO");
	}

	public void setAdditionalInfo(String additionalInfo)
	{
		if (additionalInfo != null && additionalInfo.length() > 10000)
		{
			additionalInfo = additionalInfo.substring(0, 10000) + "\n\n…………";
		}
		set("C_ADDITIONAL_INFO", additionalInfo);
	}

	public String getRealRunId()
	{
		return get("C_REAL_RUNID");
	}

	public void setRealRunId(String realRunId)
	{
		set("C_REAL_RUNID", realRunId);
	}
	
}
