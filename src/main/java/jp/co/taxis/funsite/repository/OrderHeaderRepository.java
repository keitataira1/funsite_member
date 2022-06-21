package jp.co.taxis.funsite.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.OrderHeaderEntity;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeaderEntity, Integer> {
	
	
}
