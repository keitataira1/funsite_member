package jp.co.taxis.funsite.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import jp.co.taxis.funsite.entity.PlayerEntity;

@SpringBootTest
public class PlayerRepositoryTest {

	@Autowired
	PlayerRepository playerRepository;

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM player", "INSERT INTO player VALUES (1,'山田太郎',null,'キーパー','頑張ります',null,1)",
			"INSERT INTO player VALUES (2,'佐藤一郎',null,'フォワード','ありがとうございます','顔写真',1)",
			"INSERT INTO player VALUES (3,'田中次郎',null,'ディフェンダー','こんにちは','顔写真',1)" })
	public void testPlayerSearch_001_0件() {

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerRepository.playerSearch("%原%");

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM player", "INSERT INTO player VALUES (1,'山田太郎',null,'キーパー','頑張ります','顔写真',1)"

	})
	public void testPlayerSearch_002_1件() {

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerRepository.playerSearch("%山田%");

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();
		expected.add(new PlayerEntity(1, "山田太郎", null, "キーパー", "頑張ります", "顔写真", 1));

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM player", "INSERT INTO player VALUES (1,'山田太郎',null,'キーパー','頑張ります','顔写真',1)",
			"INSERT INTO player VALUES (2,'佐藤一郎',null,'フォワード','ありがとうございます','顔写真',1)",
			"INSERT INTO player VALUES (3,'田中次郎',null,'ディフェンダー','こんにちは','顔写真',1)"

	})
	public void testPlayerSearch_003_複数件() {

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerRepository.playerSearch("%郎%");

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();
		expected.add(new PlayerEntity(1, "山田太郎", null, "キーパー", "頑張ります", "顔写真", 1));
		expected.add(new PlayerEntity(2, "佐藤一郎", null, "フォワード", "ありがとうございます", "顔写真", 1));
		expected.add(new PlayerEntity(3, "田中次郎", null, "ディフェンダー", "こんにちは", "顔写真", 1));

		// 検証
		assertIterableEquals(expected, actualList);
	}

}
