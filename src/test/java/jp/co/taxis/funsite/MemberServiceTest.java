package jp.co.taxis.funsite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.service.MemberService;

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	@Test
	@Rollback
	@Sql(statements = { "DELETE FROM member",
			"INSERT INTO member VALUES (1,'test','test','test','test',null,'test','test',true,1)" })
	void testInsert_001_異常系() {

		// テスト対象の引数
		MemberEntity targetArg = new MemberEntity(2, "test", "test", "test", "a", null, "test", "test", true, 1);

		// 期待値
		String expected = "register_mail.error";

		try {
			// テスト対象メソッド実行
			memberService.insert(targetArg);
		} catch (ApplicationException e) {
			// 検証
			String actual = e.getMessage();
			assertEquals(expected, actual);
		}

	}

	@Test
	@Rollback
	@Sql(statements = { "DELETE FROM member",
			"INSERT INTO member VALUES (1,'test','test','test','test',null,'test','test',true,1)" })
	void testInsert_002_異常系() {

		// テスト対象の引数
		MemberEntity targetArg1 = new MemberEntity(1, "a", "test", "test", "test", null, "test", "test", true, 1);

		// 期待値
		String expected = "register_name.error";

		try {
			// テスト対象メソッド実行
			memberService.insert(targetArg1);
		} catch (ApplicationException e) {
			// 検証
			String actual = e.getMessage();
			assertEquals(expected, actual);
		}

	}

}
