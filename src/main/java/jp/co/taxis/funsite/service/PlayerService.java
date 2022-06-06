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

	public void selectById(Integer id) {
		PlayerEntity player = playerRepository.findById(id).orElse(null);
		System.out.println(player);
	}

}
