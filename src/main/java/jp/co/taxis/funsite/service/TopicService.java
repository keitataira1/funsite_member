package jp.co.taxis.funsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.repository.TopicRepository;

@Service
@Transactional
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	public TopicEntity getTopic(Integer id) {
		TopicEntity topic = topicRepository.findById(id).orElse(null);

		return topic;
	}

}
