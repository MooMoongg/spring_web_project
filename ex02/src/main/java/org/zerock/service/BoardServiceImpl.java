package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;



@Service
@Log4j

public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;


	@Override
	public void register(BoardVO board) {
		
		log.info("register....."+board);
		
		mapper.insertSelectKey(board);
	}

//	@Override
//	public List<BoardVO> getList() {
//		
//		log.info("getList.......이거는 BoardServiceImpl의 로그");
//		return mapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria cri){
		log.info("get List with criteria: "+cri);
		
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public BoardVO get(Long bno) {
		
		log.info("get.......이거는 BoardServiceImpl의 로그"+bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		
		log.info("modify.......이거는 BoardServiceImpl의 로그"+board);
		return mapper.update(board)==1;
	}

	@Override
	public boolean remove(Long bno) {
		
		log.info("modify.......이거는 BoardServiceImpl의 로그"+bno);
		return mapper.delete(bno)==1;
	}

	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

}
