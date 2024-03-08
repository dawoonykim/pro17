package sec01.ex01.test;

import java.sql.Date;
import lombok.Data;

@Data
public class MemberVO1 {

	private int level;
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private String imageFileName;
	private String id;
	private Date writeDate;

	public MemberVO1() {
		System.out.println("MemberVO 생성자 호출");
	}

	public MemberVO1(int level, int articleNO, int parentNO, String title, String content, String imageFileName,
			String id) {

		this.level = level;
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.content = content;
		this.imageFileName = imageFileName;
		this.id = id;
	}

}
