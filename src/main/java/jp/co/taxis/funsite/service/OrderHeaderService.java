package jp.co.taxis.funsite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.OrderHeaderEntity;
import jp.co.taxis.funsite.repository.OrderHeaderRepository;

@Transactional
@Service
public class OrderHeaderService {
	
	@Autowired
	private OrderHeaderRepository orderHeaderRepository;
	
	public List<OrderHeaderEntity> selectAll() {
		List<OrderHeaderEntity> orderHeaderList=orderHeaderRepository.findAll();
		return orderHeaderList;
	}
	
	public OrderHeaderEntity selectById(Integer id) {
		OrderHeaderEntity order = orderHeaderRepository.findById(id).orElse(null);
		return order;
	}
	
	public OrderHeaderEntity insert(OrderHeaderEntity orderHeaderEntity) {
		OrderHeaderEntity order=orderHeaderRepository.save(orderHeaderEntity);
		return order;
	}


}
