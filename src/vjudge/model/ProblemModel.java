package vjudge.model;

import vjudge.bean.Problem;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class ProblemModel extends Model<ProblemModel>
{
	public static final ProblemModel dao = new ProblemModel();
	
	public boolean addOrModify(Problem problem)
	{
		return true;
	}
	
	public boolean delete(Problem problem)
	{
		return true;
	}
}
