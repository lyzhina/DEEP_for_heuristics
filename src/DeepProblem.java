import problemdomain.ProblemDomain;

public class DeepProblem extends ProblemDomain {

	private String filename;

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public DeepSolution evaluate(String heuristics) {
		DeepSolution is = new DeepSolution();
		is.setHeuCom(heuristics);
		is.createSolution(filename);
		return is;
	}

}
