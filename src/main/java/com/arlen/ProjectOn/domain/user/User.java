package com.arlen.ProjectOn.domain.user;

import com.arlen.ProjectOn.domain.board.Post;
import com.arlen.ProjectOn.domain.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@Builder
public class User extends BaseTimeEntity{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50) 
	private String username;
	
	@Column(nullable = false) 
	private String password;
	
	@Column(nullable = false, length = 20) 
	private String role;
	
	public void setPassword(String password) {
		this.password = password;
	}
}
