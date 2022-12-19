package com.itwillbs.domain;

// 페이징 처리에 필요한 정보를 관리하는 객체
public class PageVO {

	private int page; // 페이지
	private int perPageNum; // 페이지 크기

	public PageVO() {
		// 페이징 처리의 정보가 없을때(기본값)
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		// 0보다 작은 페이지 정보 호출
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;

//		if(page <= 0) {
//		     this.page = 1;
//	    }else {
//		     this.page = page;
//	    }		
	}

	public void setPerPageNum(int perPageNum) {
		// 한번에 보여줄 글의 수
		if (perPageNum < 10 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}

		this.perPageNum = perPageNum;
	}

	public int getPage() {
		return page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	// mapper의 # { pageStart } 해당 요소를 위한 메서드
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", perPageNum=" + perPageNum + "]";
	}

}
