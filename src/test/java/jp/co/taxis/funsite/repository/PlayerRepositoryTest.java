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
import jp.co.taxis.funsite.repository.PlayerRepository;

@SpringBootTest
public class PlayerRepositoryTest {

	@Autowired
	PlayerRepository playerRepository;

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM player" })
	public void testPlayerSearch_001_0件() {

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerRepository.playerSearch("");

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM player", "INSERT INTO player VALUES (001,'山田太郎',2000-02-23,'キーパー','頑張ります','顔写真',1)"

	})
	public void testPlayerSearch_002_1件() {

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerRepository.playerSearch("%山田%");

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();
		//expected.add(new PlayerEntity(001, "山田太郎", null, "キーパー", "頑張ります", "顔写真", 1));// 星座エラー

		// 検証
		assertIterableEquals(expected, actualList);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM player",
			"INSERT INTO player VALUES (001,'山田太郎',2000-02-23,'キーパー','頑張ります','顔写真',1)",
			"INSERT INTO player VALUES (002,'佐藤一郎',2022-06-14,'フォワード','ありがとうございます','顔写真',1)",
			"INSERT INTO player VALUES (003,'田中次郎',2022-06-14,'ディフェンダー','こんにちは','顔写真',2)"

	})
	public void testPlayerSearch_003_複数件() {

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerRepository.playerSearch("%郎%");

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();
		//expected.add(new PlayerEntity(001, "山田太郎", null, "キーパー", "頑張ります", "顔写真", 1));
		//expected.add(new PlayerEntity(002, "佐藤一郎", null, "フォワード", "ありがとうございます", "顔写真", 1));
		//expected.add(new PlayerEntity(003, "田中次郎", null, "ディフェンダー", "こんにちは", "顔写真", 2));

		// 検証
		assertIterableEquals(expected, actualList);
	}

}
