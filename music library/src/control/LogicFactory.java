package control;

public class LogicFactory {
	public static Logic getLogic() {
		return new LogicImpl();
	}
}
