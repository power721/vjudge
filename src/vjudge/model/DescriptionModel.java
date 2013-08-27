package vjudge.model;

import vjudge.bean.Description;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class DescriptionModel extends Model<DescriptionModel>
{
	public static final DescriptionModel dao = new DescriptionModel();
	
	public boolean addOrModify(Description description)
	{
		return true;
	}
}
