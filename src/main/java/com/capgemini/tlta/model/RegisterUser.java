package com.capgemini.tlta.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="register_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterUser {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String emailId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "login",cascade = CascadeType.ALL)
	private Set<UserActivity> userActivity = new HashSet<UserActivity>();
	
	
}
