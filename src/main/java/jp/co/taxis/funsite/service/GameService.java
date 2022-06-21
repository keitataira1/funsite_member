package jp.co.taxis.funsite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.repository.GameRepository;

@Service
@Transactional
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	/**
	 * 試合一覧表示メソッド
	 * 
	 * @return
	 */
	public List<GameEntity> selectAll() {
		List<GameEntity> gameList = gameRepository.findAll();
		return gameList;
	}

	/**
	 * 試合詳細1件取得メソッド
	 * 
	 * @param id
	 * @return
	 */
	public GameEntity selectById(Integer id) {
		GameEntity game = gameRepository.findById(id).orElse(null);
		return game;
	}

}
