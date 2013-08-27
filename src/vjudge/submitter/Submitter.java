package vjudge.submitter;

import javax.servlet.ServletContext;

import vjudge.service.BaseService;
import vjudge.service.IBaseService;

import vjudge.tool.ApplicationContainer;

import org.apache.commons.httpclient.HttpClient;

import vjudge.bean.Submission;

public abstract class Submitter extends Thread implements Cloneable {
	static public ServletContext sc = ApplicationContainer.sc;
	static public IBaseService baseService = BaseService.baseService;
	public Submission submission;

	protected HttpClient httpClient;
	protected int maxRunId = 0;
	protected int idx;

	public abstract void work();
	public abstract void waitForUnfreeze();

	public void run() {
		work();
		updateStanding();
		waitForUnfreeze();
	}
	
	private void updateStanding() {
		if (submission.getContest() != null){
			try {
				//judgeService.updateRankData(submission.getContest().getId(), true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Object clone() {
		Submitter o = null;
		try {
			o = (Submitter) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	
	
	public Submission getSubmission() {
		return submission;
	}
	public void setSubmission(Submission submission) {
		this.submission = submission;
	}

}
