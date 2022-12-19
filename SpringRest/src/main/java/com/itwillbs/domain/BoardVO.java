package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @Data : lombok 라이브러리를 사용해서 
// VO 객체 안에 set/get메서드 자동 구현,toString 자동생성(오버라이딩)

//@Setter
//@Getter
//@ToString
@Data
public class BoardVO {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private int viewcnt;
	private Timestamp regdate;
}
