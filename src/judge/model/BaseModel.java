package judge.model;

import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.TableInfo;
import com.jfinal.plugin.activerecord.TableInfoMapping;

public abstract class BaseModel<M extends BaseModel<?>> extends Model<M>
{
	private static final long serialVersionUID = 7567972423020592263L;

	protected final Logger log = Logger.getLogger(getClass());

	public abstract boolean addOrModify();

	public boolean deleteModel()
	{
		return delete();
	}
	
	public String getPrimaryKey()
	{
		return getTableInfo().getPrimaryKey();
	}
	
	public String getTableName()
	{
		return getTableInfo().getTableName();
	}
	
	public TableInfo getTableInfo()
	{
		return TableInfoMapping.me().getTableInfo(getClass());
	}

	public Logger getLog()
	{
		return log;
	}
}
