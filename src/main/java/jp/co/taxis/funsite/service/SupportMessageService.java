package jp.co.taxis.funsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.SupportMessageEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.SupportMessageRepository;

@Service
@Transactional
public class SupportMessageService {

	@Autowired
	private SupportMessageRepository supportMessageRepository;

	/** メッセージを日付の若い順から取得するメソッド */
	public List<SupportMessageEntity> selectAll() {
		List<SupportMessageEntity> supportMessageList = supportMessageRepository.selectByMessage();
		
		if (supportMessageList == null || supportMessageList.size() == 0) {
			throw new ApplicationException("message_list.error");
		}
		
		return supportMessageList;
	}

	public SupportMessageEntity insertMessage(SupportMessageEntity supportMessage) {
		SupportMessageEntity result = supportMessageRepository.save(supportMessage);
		return result;
	}
	
	public List<SupportMessageEntity> MessageByTopicId(int id){
		List<SupportMessageEntity> messageList = supportMessageRepository.selectTopicMessage(id);
		return messageList;
	}
	
	public SupportMessageEntity insert(SupportMessageEntity message) {
		supportMessageRepository.save(message);
		return message;
		
	}

}
