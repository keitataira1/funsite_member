package jp.co.taxis.funsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Integer> {


}
