package jp.co.taxis.funsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.SupportMessageEntity;

@Repository
public interface SupportMessageRepository extends JpaRepository<SupportMessageEntity, Integer> {

	@Query(nativeQuery=true,value="SELECT * FROM support_message ORDER BY send_datetime DESC LIMIT 10")
	public List<SupportMessageEntity> selectByMessage();

	@Query(nativeQuery = true, value = "SELECT topic_id FROM support_message GROUP BY topic_id ORDER BY COUNT( topic_id ) DESC limit 3")
	public List<Integer> selectTop3();
	
	@Query(nativeQuery = true, value = "SELECT * FROM support_message WHERE topic_id = :id ORDER BY send_datetime DESC")
	public List<SupportMessageEntity> selectTopicMessage(@Param("id") int id);
}

