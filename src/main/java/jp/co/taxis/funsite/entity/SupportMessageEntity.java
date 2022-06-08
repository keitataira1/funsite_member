package jp.co.taxis.funsite.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "support_message")
public class SupportMessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	
	@Column(name = "topic_id")
	private String topicId;
	
	@Column(name = "member_id")
	private String memberId;
	
	@Column(name = "send_datetime")
	private LocalDate sendDatetime;
	
	@Column(name = "message")
	private String message;
}
