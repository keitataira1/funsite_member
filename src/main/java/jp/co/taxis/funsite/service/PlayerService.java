package jp.co.taxis.funsite.service;

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
		System.out.println(player);
		return player;
	}

	/** 部分検索(名前) */
	public List<PlayerEntity> selectLikeName(String searchName) {
		List<PlayerEntity> playerList = playerRepository.playerSearch("%" + searchName + "%");
		return playerList;
	}

}
