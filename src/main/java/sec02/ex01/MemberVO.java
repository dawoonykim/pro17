package sec02.ex01;

import java.sql.Date;
import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	public MemberVO() {
		System.out.println("MemberVO 생성자 호출");
	}

	public MemberVO(String id, String pwd, String name, String email) {
	
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}
	
	
}
