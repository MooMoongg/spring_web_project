package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{
	
	@Setter(onMethod_= @Autowired)
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		
		log.info("register..."+vo);
		
		return mapper.insert(vo);
		
	}

	@Override
	public ReplyVO get(long rno) {
		
		log.info("get..."+rno);
		
		return mapper.read(rno);
	}
	
	@Override
	public int remove(long rno) {
		
		log.info("remove..."+rno);
		
		return mapper.delete(rno);
	}
	
	@Override
	public int modify(ReplyVO vo) {
		
		log.info("modify..."+vo);
		
		return mapper.update(vo);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, long bno){
		
		log.info("get Reply List of a Board"+bno);
		
		return mapper.getListWithPaging(cri, bno);
	}
	
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		
		return new ReplyPageDTO(
				mapper.getCountByBno(bno),
				mapper.getListWithPaging(cri, bno));
	}
}
