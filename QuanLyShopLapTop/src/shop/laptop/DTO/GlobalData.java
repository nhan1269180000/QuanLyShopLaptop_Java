package shop.laptop.DTO;

public class GlobalData {
	static String loggedUserName;
	static String loggedRole;

	public static String getLoggedUserName() {
		return loggedUserName;
	}

	public static void setLoggedUserName(String loggedUserName) {
		GlobalData.loggedUserName = loggedUserName;
	}

	public static String getLoggedRole() {
		return loggedRole;
	}

	public static void setLoggedRole(String loggedRole) {
		GlobalData.loggedRole = loggedRole;
	}

	
	
}
