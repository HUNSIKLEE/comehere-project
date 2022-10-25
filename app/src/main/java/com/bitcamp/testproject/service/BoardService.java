package com.bitcamp.testproject.service;

import java.util.List;
import com.bitcamp.testproject.vo.BoardAttachedFile;
import com.bitcamp.testproject.vo.Board;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface BoardService {

  void add(Board board) throws Exception;

  boolean update(Board board) throws Exception;

  Board get(int no) throws Exception;

  boolean delete(int no) throws Exception;

  List<Board> list() throws Exception;

  BoardAttachedFile getAttachedFile(int fileNo) throws Exception;

  boolean deleteAttachedFile(int fileNo) throws Exception;

}








