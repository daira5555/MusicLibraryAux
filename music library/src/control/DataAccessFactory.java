package control;

public class DataAccessFactory {
	
	public static DataAccess getDataAccess() {
		return new DataAccessImpl();
	}
	
}
