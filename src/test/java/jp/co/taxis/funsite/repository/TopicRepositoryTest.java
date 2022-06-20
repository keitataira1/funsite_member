package jp.co.taxis.funsite.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.entity.TopicEntity;

@SpringBootTest
public class TopicRepositoryTest {
	
	@Autowired
	private TopicRepository topicRepository;

	@Test
	@Rollback
//	@Sql(statements = { "DELETE FROM topic", "INSERT INTO topic VALUES (1,1,'test1',true,1)"})
	void test() {
		List<TopicEntity> autual = topicRepository.findAll();
		System.out.println(autual.size());
		
		PlayerEntity playerId = new PlayerEntity(1,null,null,null,null,null,null);
		TopicEntity expected = new TopicEntity(1,playerId,"test1",true,1);
		
		assertEquals(expected, autual);
	}
}
