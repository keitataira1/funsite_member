package jp.co.taxis.funsite.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import jp.co.taxis.funsite.entity.MemberEntity;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	// selectByMailAddlessAndPasswordメソッドの単体テスト
	@Test
	@Transactional
	@Rollback
	@Sql(statements = { 
			"DELETE FROM member",
			"INSERT INTO member VALUES (1,'a@gmail.com','abc','山田太郎','山田',null,'1','東京都',true,1)" })
	public void testSelectByMailAddlessAndPassword_001_0件() {

		// テスト対象メソッド実行
		MemberEntity actual = memberRepository.selectByMailAddlessAndPassword("%なし%", "%なし%");

		// 期待値
		MemberEntity expected = null;

		// 検証
		assertEquals(expected,actual);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = {
			"DELETE FROM member",
			"INSERT INTO member VALUES (1,'a@gmail.com','abc','山田太郎','山田',null,'1','東京都',true,1)",
			"INSERT INTO member VALUES (2,'b@gmail.com','apple','佐藤一郎','佐藤',null,'2','兵庫県',true,1)"
			})
	public void testSelectByMailAddlessAndPassword_002_1件() {

		// テスト対象メソッド実行
		MemberEntity actual = memberRepository.selectByMailAddlessAndPassword("b@gmail.com", "apple");

		// 期待値
		MemberEntity expected = new MemberEntity(2,"b@gmail.com","apple","佐藤一郎","佐藤",null,"2","兵庫県",true,1);

		// 検証
		assertEquals(expected, actual);
	}

	// selectByMailAddlessメソッドの単体テスト
	@Test
	@Transactional
	@Rollback
	@Sql(statements = { 
			"DELETE FROM member",
			"INSERT INTO member VALUES (1,'a@gmail.com','abc','山田太郎','山田',null,'1','東京都',true,1)"
			})
	public void testSelectByMailAddless_003_0件() {

		// テスト対象メソッド実行
		MemberEntity actual = memberRepository.selectByMailAddless("%なし%");

		// 期待値
		MemberEntity expected =null;

		// 検証
		assertEquals(expected, actual);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = {
			"DELETE FROM member",
			"INSERT INTO member VALUES (1,'a@gmail.com','abc','山田太郎','山田',null,'1','東京都',true,1)",
			"INSERT INTO member VALUES (2,'b@gmail.com','apple','佐藤一郎','佐藤',null,'2','兵庫県',false,2)"
			})
	public void testSelectByMailAddless_004_1件() {

		// テスト対象メソッド実行
		MemberEntity actual = memberRepository.selectByMailAddless("a@gmail.com");

		// 期待値
		MemberEntity expected = new MemberEntity(1, "a@gmail.com", "abc", "山田太郎", "山田", null, "1", "東京都", true, 1);

		// 検証
		assertEquals(expected, actual);
	}

	// selectByNameメソッドの単体テスト
	@Test
	@Transactional
	@Rollback
	@Sql(statements = { 
			"DELETE FROM member",
			"INSERT INTO member VALUES (1,'a@gmail.com','abc','山田太郎','山田',null,'1','東京都',true,1)" 
			})
	public void testSelectByName_005_0件() {

		// テスト対象メソッド実行
		MemberEntity actual = memberRepository.selectByName("%なし%");

		// 期待値
		MemberEntity expected =null;

		// 検証
		assertEquals(expected, actual);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { 
			"DELETE FROM member",
			"INSERT INTO member VALUES (1,'a@gmail.com','abc','山田太郎','山田',null,'1','東京都',true,1)",
			"INSERT INTO member VALUES (2,'b@gmail.com','apple','佐藤一郎','佐藤',null,'2','兵庫県',false,2)"
			})
	public void testSelectByName_006_1件() {

		// テスト対象メソッド実行
		MemberEntity actual = memberRepository.selectByName("山田太郎");

		// 期待値
		MemberEntity expected = new MemberEntity(1, "a@gmail.com", "abc", "山田太郎", "山田", null, "1", "東京都", true, 1);
		
		// 検証
		assertEquals(expected, actual);
	}

}
