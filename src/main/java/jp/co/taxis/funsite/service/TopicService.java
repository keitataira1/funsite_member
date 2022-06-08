package jp.co.taxis.funsite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.repository.SupportMessageRepository;
import jp.co.taxis.funsite.repository.TopicRepository;

@Service
@Transactional
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private SupportMessageRepository supportMesseageRepositoty;

	public TopicEntity getTopic(Integer id) {
		TopicEntity topic = topicRepository.findById(id).orElse(null);

		return topic;
	}
	
	public List<TopicEntity> getLimit3Topic() {
		List<TopicEntity> topicList = new ArrayList<TopicEntity>();
		for(Integer topicId : supportMesseageRepositoty.selectTop3()) {
			TopicEntity topic = getTopic(topicId);
			topicList.add(topic);
		}
		return topicList;
	}

}
