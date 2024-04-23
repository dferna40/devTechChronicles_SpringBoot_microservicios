package com.apiRest.autentication.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	private String username;
	private String password;

	// Usamos fetchType en Eager para que cada vez que se acceda o se extraiga un
	// usuario de la BD, este se traiga todos sus roles
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	/*
	 * Con @JoinTable estaremos creando una tabla que unirá la tabla de users y
	 * roles, con lo cual tendremos un total de 3 tablas relacionadas enn la tabla
	 * "user_roles", a traves de sus columnas "user_id" que apuntara al ID del
	 * usuario y "role_id" que apuntara al ID del role
	 */
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"))
	private List<Roles> roles = new ArrayList<Roles>();
}
