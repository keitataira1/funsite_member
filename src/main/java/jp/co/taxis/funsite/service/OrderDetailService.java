package jp.co.taxis.funsite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.OrderDetailEntity;
import jp.co.taxis.funsite.repository.OrderDetailRepository;

@Transactional
@Service
public class OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	public List<OrderDetailEntity> selectAll() {
		List<OrderDetailEntity> orderDetailList=orderDetailRepository.findAll();
		return orderDetailList;
	}
	
	public OrderDetailEntity selectById(Integer id) {
		OrderDetailEntity order = orderDetailRepository.findById(id).orElse(null);
		return order;
	}


}
