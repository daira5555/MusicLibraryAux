package control;

public class LogicFactory {
	private static Logic iLogic;

	public static Logic getLogic() {
		if (iLogic == null) {
			iLogic = new LogicImpl();
		}
		return iLogic;
	}
}
