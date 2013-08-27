package vjudge.model;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("rawtypes")
public abstract class BaseModel<M extends BaseModel<?>> extends Model<BaseModel>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7567972423020592263L;

	public abstract boolean addOrModify(Object bean);

	public abstract boolean delete(Object bean);
}
