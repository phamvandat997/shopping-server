package com.shop.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "shop_roles")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends AbstractAuditingEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7703294374458373447L;
	
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.shop.utils.IDGeneratorUtil")
    @Column(name = "id", nullable = false)
    private String id;

	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "display_name", length = 255)
	private String displayName;
	
	@ManyToMany(mappedBy = "authorities")
	private Set<User> users;
	
}
