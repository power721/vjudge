package vjudge.bean;

import java.util.Date;

/**
 * 提交记录
 * 
 * @author Isun
 * 
 */
public class Submission
{
	private int id; // Hibernate统编ID
	private String status; // 状态
	// Pending,
	// Judging,
	// Accepted,
	// Wrong Answer,
	// Time Limit Exceeded,
	// Memory Limit Exceeded
	// Output Limit Exceeded
	// Runtime Error
	// Compile Error

	private String additionalInfo; // 额外信息，记录编译错误等信息
	private String realRunId; // 在原OJ的RunId

	private int time; // 运行时间(未AC提交为空 单位:ms)
	private int memory; // 运行内存(未AC提交为空 单位:KB)
	private Date subTime; // 提交时间

	private String language; // 语言
	private String source; // 源代码
	private int isOpen; // 代码是否公开

	private String dispLanguage;// 用于显示的语言
	private String username; // 提交者用户名

	private String originOJ; // 原始OJ
	private String originProb; // 原始OJ题号
	private int isPrivate; // 是否不公开

	private Problem problem; // 外键 题目
	private User user; // 外键 提交人
	private Contest contest; // 外键 比赛

	public int getIsOpen()
	{
		return isOpen;
	}

	public void setIsOpen(int isOpen)
	{
		this.isOpen = isOpen;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public int getTime()
	{
		return time;
	}

	public void setTime(int time)
	{
		this.time = time;
	}

	public int getMemory()
	{
		return memory;
	}

	public void setMemory(int memory)
	{
		this.memory = memory;
	}

	public Date getSubTime()
	{
		return subTime;
	}

	public void setSubTime(Date subTime)
	{
		this.subTime = subTime;
	}

	public String getDispLanguage()
	{
		return dispLanguage;
	}

	public void setDispLanguage(String dispLanguage)
	{
		this.dispLanguage = dispLanguage;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public Problem getProblem()
	{
		return problem;
	}

	public void setProblem(Problem problem)
	{
		this.problem = problem;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Contest getContest()
	{
		return contest;
	}

	public void setContest(Contest contest)
	{
		this.contest = contest;
	}

	public String getOriginOJ()
	{
		return originOJ;
	}

	public void setOriginOJ(String originOJ)
	{
		this.originOJ = originOJ;
	}

	public String getOriginProb()
	{
		return originProb;
	}

	public void setOriginProb(String originProb)
	{
		this.originProb = originProb;
	}

	public int getIsPrivate()
	{
		return isPrivate;
	}

	public void setIsPrivate(int isPrivate)
	{
		this.isPrivate = isPrivate;
	}

	public String getAdditionalInfo()
	{
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo)
	{
		if (additionalInfo != null && additionalInfo.length() > 10000)
		{
			additionalInfo = additionalInfo.substring(0, 10000) + "\n\n…………";
		}
		this.additionalInfo = additionalInfo;
	}

	public String getRealRunId()
	{
		return realRunId;
	}

	public void setRealRunId(String realRunId)
	{
		this.realRunId = realRunId;
	}
}
