package jp.co.taxis.funsite.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.repository.PlayerRepository;

@Transactional
@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public List<PlayerEntity> selectAll() {
		List<PlayerEntity> playerList = playerRepository.findAll();
		return playerList;
	}

	/** 選手詳細情報1件取得 */
	public PlayerEntity selectById(Integer id) {
		PlayerEntity player = playerRepository.findById(id).orElse(null);
		return player;
	}

	/** 部分検索(名前) */
	public List<PlayerEntity> selectLikeName(String searchName) {
		List<PlayerEntity> playerList = playerRepository.playerSearch("%" + searchName + "%");
		return playerList;
	}

	public String getPlayerSeiza(Integer id) {
		String[] seizaName = { "山羊座", "水瓶座", "魚座", "牡羊座", "牡牛座", "双子座", "蟹座", "しし座", "乙女座", "てんびん座", "蠍座", "射手座" };
		int[] border = { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 };

		LocalDate playerBirthday = selectById(id).getBirthday();
		int index = playerBirthday.getMonthValue() - 1;
		if (playerBirthday.getDayOfMonth() >= border[index]) {
			index++;
		}
		if (index == 12) {
			index = 0;
		}
		return seizaName[index];
	}

}
