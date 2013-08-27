package vjudge.bean;

import vjudge.model.BaseModel;

public class Bean
{
	private BaseModel<?> model = null;
	
	public BaseModel<?> getModel()
	{
		return model;
	}
	
	public void setModel(BaseModel<?> model)
	{
		this.model = model;
	}
}
