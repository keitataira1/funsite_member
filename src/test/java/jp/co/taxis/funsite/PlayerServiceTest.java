package jp.co.taxis.funsite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.repository.PlayerRepository;
import jp.co.taxis.funsite.service.PlayerService;

@ExtendWith(SpringExtension.class)
public class PlayerServiceTest {

	@Mock
	private PlayerRepository playerRepository;

	@InjectMocks
	private PlayerService playerService;

	@Test
	void testSelectLikeName_001_パーセント付与の確認() {

		// モックの引数
		String mockPlayer1 = "%searchName%";
		// モックの戻り値
		List<PlayerEntity> playerList = new ArrayList<PlayerEntity>();
		playerList.add(new PlayerEntity(1, "test1",null,"a","b","c", 1));
		playerList.add(new PlayerEntity(2, "test2",null,"a","b","c", 1));
		// モックの設定
		when(playerRepository.playerSearch(mockPlayer1)).thenReturn(playerList);

		// テスト対象の引数
		String targetPlayer1 = "searchName";

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerService.selectLikeName(targetPlayer1);

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();
		expected.add(new PlayerEntity(1, "test1",null,"a","b","c", 1));
		expected.add(new PlayerEntity(2, "test2",null,"a","b","c", 1));

		// 検証
		assertIterableEquals(expected, actualList);
	}

}
