package jp.co.taxis.funsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topic")
public class TopicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	
	@Column(name = "player_id")
	private String playerId;
	
	@Column(name = "topic")
	private String topic;
	
	@Column(name = "invalid_flg")
	private boolean invalidFig;
	
	@Column(name = "version")
	@Version
	private int version;
}
