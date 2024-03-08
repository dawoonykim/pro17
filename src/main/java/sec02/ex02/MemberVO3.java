package sec02.ex02;

import java.sql.Date;
import lombok.Data;

@Data
public class MemberVO3 {

	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	public MemberVO3() {
		System.out.println("MemberVO 생성자 호출");
	}

	public MemberVO3(String id, String pwd, String name, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}

	public MemberVO3(String id, String pwd, String name, String email, Date joinDate) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.joinDate = joinDate;
	}
	
	
	
	
}
