package org.zerock.mapper;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	private long[] bnoArr = {234L, 233L, 231L, 230L, 229L};
	
	@Setter(onMethod_= @Autowired)
	private ReplyMapper mapper;
	
	//@Test
	public void testMapper() {
		
		log.info(mapper);
	}
	
	@Test
	public void testCreate() {
		
		IntStream.rangeClosed(1, 10).forEach(i -> {
			
			ReplyVO vo = new ReplyVO();
			
			//게시물의 번호
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트"+i);
			vo.setReplyer("replyer"+i);
			
			mapper.insert(vo);
		});
	}
	
}
