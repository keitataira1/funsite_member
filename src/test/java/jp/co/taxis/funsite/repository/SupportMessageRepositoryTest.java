package jp.co.taxis.funsite.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.entity.SupportMessageEntity;
import jp.co.taxis.funsite.entity.TopicEntity;

@SpringBootTest
public class SupportMessageRepositoryTest {

	@Autowired
	SupportMessageRepository supportMessageRepository;

	// selectByMessageメソッドの単体テスト
	@Test
	@Transactional
	@Rollback
	@Sql(statements = { 
			"DELETE FROM support_message",
			"INSERT INTO support_message VALUES (1,1,1,'2020-06-13 14:00:00','こんにちは')",
			"INSERT INTO member VALUES (1,'a@gmail.com','abc','山田太郎','山田',null,'1','東京都',true,1)",
			"INSERT INTO player VALUES (1,'本田',null,'キーパー','頑張ります','顔写真',1)",
			"INSERT INTO topic VALUES (1,1,'topic',true,1)"
			})

	public void testSelectByMessage_001_0件() {

		// テスト対象メソッド実行
		List<SupportMessageEntity> actualList = supportMessageRepository.selectByMessage();

		// 期待値
		List<SupportMessageEntity> expected =new ArrayList<SupportMessageEntity>();

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM support_message", 
			"INSERT INTO support_message VALUES (1,1,1,'2000-06-17 15:00:00','お疲れ様です')",
			"INSERT INTO member VALUES (1,'a@gmail.com','abc','山田太郎','山田',null,'1','東京都',true,1)",
			"INSERT INTO player VALUES (1,'本田',null,'キーパー','頑張ります','顔写真',1)",
			"INSERT INTO topic VALUES (1,1,'topic',true,1)"
			})

	public void testSelectByMessage_002_1件() {
		
		String strDate="2000/06/17 15:00:00";
		DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDate date=LocalDate.parse(strDate,df);

		MemberEntity member = new MemberEntity(1, null, null, null, null, null, null, null, true, 1);
		TopicEntity topic = new TopicEntity(1, null, null, true, 1);

		// テスト対象メソッド実行
		List<SupportMessageEntity> actualList = supportMessageRepository.selectByMessage();

		// 期待値
		List<SupportMessageEntity> expected = new ArrayList<SupportMessageEntity>();
		expected.add(new SupportMessageEntity(1,topic,member,date,"お疲れ様です"));

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM support_message", "INSERT INTO support_message VALUES (1,1,1,null,'お疲れ様です')",
			"INSERT INTO support_message VALUES (2,2,2,null,'ありがとうございます')",
			"INSERT INTO support_message VALUES (3,3,3,null,'こんにちは')" })

	public void testSelectByMessage_003_3件() {

		MemberEntity member = new MemberEntity(1, null, null, null, null, null, null, null, true, 1);
		TopicEntity topic = new TopicEntity(1, null, null, true, 1);

		// テスト対象メソッド実行
		List<SupportMessageEntity> actualList = supportMessageRepository.selectByMessage();

		// 期待値
		List<SupportMessageEntity> expected = new ArrayList<SupportMessageEntity>();
		expected.add(new SupportMessageEntity(1, topic, member, LocalDate.now(), "お疲れ様です"));
		expected.add(new SupportMessageEntity(2, topic, member, LocalDate.now(), "ありがとうございます"));
		expected.add(new SupportMessageEntity(3, topic, member, LocalDate.now(), "こんにちは"));

		// 検証
		assertIterableEquals(expected, actualList);
	}

	// selectTop3メソッドの単体テスト
	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM support_message"
			})

	public void testSelectTop3_004_0件() {

		// テスト対象メソッド実行
		List<Integer> actualList = supportMessageRepository.selectTop3();

		// 期待値
		List<Integer> expected = new ArrayList<Integer>();

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM support_message",
			"INSERT INTO support_message VALUES (1,1,1,'2000-06-17 15:00:00','お疲れ様です')" })

	public void testSelectTop3_005_1件() {

		MemberEntity member = new MemberEntity(1, null, null, null, null, null, null, null, true, 1);
		TopicEntity topic = new TopicEntity(1, null, null, true, 1);

		// テスト対象メソッド実行
		List<Integer> actualList = supportMessageRepository.selectTop3();

		// 期待値
		List<Integer> expected = new ArrayList<Integer>();
		expected.equals(new SupportMessageEntity(1, topic, member, LocalDate.now(), "お疲れ様です"));// add?

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM support_message", "INSERT INTO support_message VALUES (1,1,1,null,'お疲れ様です')",
			"INSERT INTO support_message VALUES (2,2,2,null,'ありがとうございます')",
			"INSERT INTO support_message VALUES (3,3,3,null,'こんにちは')" })

	public void testSelectTop3_006_複数件() {

		MemberEntity member = new MemberEntity(1, null, null, null, null, null, null, null, true, 1);
		TopicEntity topic = new TopicEntity(1, null, null, true, 1);

		// テスト対象メソッド実行
		List<Integer> actualList = supportMessageRepository.selectTop3();

		// 期待値
		List<Integer> expected = new ArrayList<Integer>();
		expected.equals(new SupportMessageEntity(1, topic, member, null, "お疲れ様です"));
		expected.equals(new SupportMessageEntity(2, topic, member, null, "ありがとうございます"));
	    expected.equals(new SupportMessageEntity(3, topic, member, null, "こんにちは"));

		// 検証
		assertIterableEquals(expected, actualList);
	}

	// selectTopicMessageメソッドの単体テスト
	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM support_message" })

	public void testSelectTopicMessage_007_0件() {

		// テスト対象メソッド実行
		List<SupportMessageEntity> actualList = supportMessageRepository.selectTopicMessage(0);

		// 期待値
		List<SupportMessageEntity> expected = new ArrayList<SupportMessageEntity>();

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM support_message", "INSERT INTO support_message VALUES (1,1,1,'2000-06-17 15:00:00','お疲れ様です')" })

	public void testSelectTopicMessage_008_1件() {

		MemberEntity member = new MemberEntity(1, null, null, null, null, null, null, null, true, 1);
		TopicEntity topic = new TopicEntity(1, null, null, true, 1);

		// テスト対象メソッド実行
		List<SupportMessageEntity> actualList = supportMessageRepository.selectTopicMessage(0);

		// 期待値
		List<SupportMessageEntity> expected = new ArrayList<SupportMessageEntity>();
		expected.add(new SupportMessageEntity(1, topic, member, LocalDate.now(), "お疲れ様です"));// 型の書き方

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM support_message", "INSERT INTO support_message VALUES (1,1,1,'2000-06-17 15:00:00','お疲れ様です')",
			"INSERT INTO support_message VALUES (2,2,2,null,'ありがとうございます')",
			"INSERT INTO support_message VALUES (3,3,3,null,'こんにちは')" })

	public void testSelectTopicMessage_009_複数件() {

		MemberEntity member = new MemberEntity(1, null, null, null, null, null, null, null, true, 1);
		TopicEntity topic = new TopicEntity(1, null, null, true, 1);

		// テスト対象メソッド実行
		List<SupportMessageEntity> actualList = supportMessageRepository.selectTopicMessage(0);

		// 期待値
		List<SupportMessageEntity> expected = new ArrayList<SupportMessageEntity>();
		expected.add(new SupportMessageEntity(1, topic, member, LocalDate.now(), "お疲れ様です"));
		expected.add(new SupportMessageEntity(2, topic, member, LocalDate.now(), "ありがとうございます"));
		expected.add(new SupportMessageEntity(3, topic, member, LocalDate.now(), "こんにちは"));

		// 検証
		assertIterableEquals(expected, actualList);
	}

}
