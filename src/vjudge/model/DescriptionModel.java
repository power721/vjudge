package vjudge.model;

import vjudge.bean.Description;

public class DescriptionModel extends BaseModel<DescriptionModel>
{
	private static final long serialVersionUID = -376409847087991812L;

	public static final DescriptionModel dao = new DescriptionModel();

	@Override
	public boolean addOrModify(Object bean)
	{
		Description description = (Description) bean;
		boolean insertFlag = false;
		DescriptionModel descriptionModel = (DescriptionModel) dao.findById(description.getId());
		if (descriptionModel == null)
		{
			descriptionModel = new DescriptionModel();
			insertFlag = true;
		}

		descriptionModel.set("C_DESCRIPTION", description.getDescription());
		descriptionModel.set("C_INPUT", description.getInput());
		descriptionModel.set("C_OUTPUT", description.getOutput());
		descriptionModel.set("C_SAMPLEINPUT", description.getSampleInput());
		descriptionModel.set("C_SAMPLEOUTPUT", description.getSampleOutput());
		descriptionModel.set("C_HINT", description.getHint());
		descriptionModel.set("C_PROBLEM_ID", description.getProblem().getId());
		descriptionModel.set("C_UPDATE_TIME", description.getUpdateTime());
		descriptionModel.set("C_AUTHOR", description.getAuthor());
		descriptionModel.set("C_REMARKS", description.getRemarks());
		descriptionModel.set("C_VOTE", description.getVote());

		if (insertFlag)
			return descriptionModel.save();
		return descriptionModel.update();
	}

	@Override
	public boolean delete(Object bean)
	{
		Description description = (Description) bean;
		return dao.deleteById(description.getId());
	}
}
