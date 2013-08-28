package judge.submitter;

import javax.servlet.ServletContext;

import judge.model.SubmissionModel;
import judge.service.BaseService;
import judge.service.IBaseService;
import judge.tool.ApplicationContainer;

import org.apache.commons.httpclient.HttpClient;

public abstract class Submitter extends Thread
{
	static public ServletContext sc = ApplicationContainer.sc;
	static public IBaseService baseService = BaseService.baseService;
	public SubmissionModel submission;

	protected HttpClient httpClient;
	protected int maxRunId = 0;
	protected int idx;

	public abstract void work();

	public abstract void waitForUnfreeze();

	public void run()
	{
		work();
		updateStanding();
		waitForUnfreeze();
	}

	private void updateStanding()
	{
		if (submission.getContest() != null)
		{
			try
			{
				// judgeService.updateRankData(submission.getContest().getId(),
				// true);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public SubmissionModel getSubmission()
	{
		return submission;
	}

	public void setSubmission(SubmissionModel submission)
	{
		this.submission = submission;
	}

}
