package jp.co.taxis.funsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Integer> {
	@Query("SELECT i FROM ItemEntity i WHERE i.name LIKE :searchWord")
	public List<ItemEntity> selectLikeName(@Param("searchWord") String searchWord);

}
