package com.shop.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "shop_users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractAuditingEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7476106154425450292L;

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.shop.utils.IDGeneratorUtil")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "user_name", length = 191)
    private String userName;
    
    @JsonIgnore
    private String password;
    
    @Column(name = "email", length = 191)
    private String email;
    
    @Column(name = "gender", nullable = false)
    private boolean gender;
    
    @Column(name = "birthday")
    private Date birthday;
    
    @Column(name = "avatar", length = 255)
    private String avatar;
    
    @Column(name = "code", length = 255)
    private String code;
    
    @Column(name = "job_title", length = 255)
    private String jobTitle;
    
    @Column(name = "department", length = 255)
    private String department;
    
    @Column(name = "phone_number")
    private String mobileNumber;

    @Column(name = "address")
    private String address;
    
    @Column(name = "city", length = 255)
    private String city;
    
    @Column(name = "status")
    private Boolean status;

    @Column(name = "lang")
    private String lang;
    
    @ManyToMany
    @JoinTable(name = "shop_user_has_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> authorities;
    
}
