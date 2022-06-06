package jp.co.taxis.funsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.SupportMessageEntity;
import jp.co.taxis.funsite.repository.SupportMessageRepository;

@Service
@Transactional
public class SupportMessageService {

	@Autowired
	private SupportMessageRepository supportMessageRepository;

	/** メッセージを日付の若い順から取得するメソッド */
	public List<SupportMessageEntity> selectAll() {
		List<SupportMessageEntity> supportMessageList = supportMessageRepository.selectByMessage();
		return supportMessageList;
	}

	public SupportMessageEntity insertMessage(SupportMessageEntity supportMessage) {
		SupportMessageEntity result = supportMessageRepository.save(supportMessage);
		return result;
	}

}
