package control;

public class DataAccessFactory {
	private static DataAccess iDataAccess;

	public static DataAccess getDataAccess() {
		if (iDataAccess == null) {
			iDataAccess = new DataAccessImpl();
		}
		return iDataAccess;
	}
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/daira
}