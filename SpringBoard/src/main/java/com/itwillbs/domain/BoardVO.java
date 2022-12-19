package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

//@Setter  				// => Set메서드만 사용 
//@Getter  				// => Get메서드만 사용
//@ToString 			// => ToString만 사용

@Data   
public class BoardVO {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private int viewcnt;
	private Timestamp regdate;
	
	
	
	
	// VO 객체 안에 set/get메서드 자동 구현, toString 자동생성(오버라이딩)
	
	// @Data => 직접적으로 구현 하지 않았지만 한 것처럼  get/set메소드 사용가능.  점점 직접적으로 구현하는 시대는 사라지고 있음.
	// 적절한 어노테이션으로 다 구현 가능.
	
	
	// 클래스 위에 커서 + F4 => 상속관계
	
	// 동작에는 영향을 안 주지만, Integer라고 주는 애들은 형변환이 생길 수 있음,  int는 형변환 없이 그냥 숫자로서 카운트 하는 애들 
	// 어차피 auto boxing/ unboxing을 지원해서 아무거나 써도 됨.
	
	// mvnrepository.com 들어가서 lombok받아서 추가하면 됨.
	
}
