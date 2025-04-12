package intefaces;

public class GooglePageUIs {
	public static final String SEARCH_TEXTBOX = "//input[@title='Tìm kiếm']";
	public static final String SEARCH_TITLE = "//div[@id='search']//h3[contains(text(),'Selenium')]";
	public static final String SEARCH_VIDEO = "//video-voyager//span[contains(text(),'Selenium')]";
	public static final String RESULT = "//h2[text()='Kết quả phép tính']/parent::div//span[@id='cwos']";
}
