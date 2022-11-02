package com.bitcamp.testproject.vo;

public class Criteria {

  private int page; // 현재 페이지의 번호 (ex [1], [2], [3], [4], [5])
  private int perPageNum; // 한 페이지 당 보여줄 게시글의 개수

  public int getPagesStart() {
    return (this.page-1) * perPageNum; // 2페이지면 5니까 10행부터 게시글을 출력하겠다. 
  }

  // 최초 게시판 목록에 들어왔을 때의 기본 셋팅 (1페이지에 10개의 게시글 출력)
  public Criteria() {
    this.page = 1;
    this.perPageNum = 5;
  }

  // getter/setter 셋팅
  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    if(page <=0) {// 페이지가 잘 못된 값이 되지않게 설정
      this.page = 1;
    } else {
      this.page = page;      
    }
  }

  public int getPerPageNum() {
    return perPageNum;
  }
  public void setPerPageNum(int pageCount) {
    int cnt = this.perPageNum; // 페이지당 보여줄 게시글 수가 변하지 않게 설정
    if(pageCount != cnt) {
      this.perPageNum = cnt;
    } else {
      this.perPageNum = pageCount;
    }
  }





}
