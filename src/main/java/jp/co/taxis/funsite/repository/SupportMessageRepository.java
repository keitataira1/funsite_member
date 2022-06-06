package jp.co.taxis.funsite.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.SupportMessageEntity;

@Repository
public interface SupportMessageRepository extends JpaRepository<SupportMessageEntity, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM suport_message WHERE message ORDER BY sendDatetime")
	public List<SupportMessageEntity> selectByMessage(@Param("sendDatetime") LocalDate sendDatetime);

}
