package commons;

import java.io.File;

public class GlobalConstants {
	public static final String ADMIN_PAGE_URL = "https://chungcu.tuitentuan.com/";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.pathSeparator + "uploadFiles" + File.pathSeparator;
	public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.pathSeparator + "downloadFiles" + File.pathSeparator;
	public static final String REPORTNG_SCREENSHOTS = PROJECT_PATH + File.pathSeparator + "ReportNGScreenShots" + File.pathSeparator;
	public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.pathSeparator + "browserLogs";
	public static final String DRAG_DROP_HTML = PROJECT_PATH + File.pathSeparator + "";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.pathSeparator + "autoIT";
	public static final String JAVA_VERSION = System.getProperty("java.version");
}
