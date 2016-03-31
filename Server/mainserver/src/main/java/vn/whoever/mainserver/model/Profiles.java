package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import vn.whoever.support.model.utils.Genders;
import vn.whoever.support.model.utils.Privacies;

@Entity
@Table(name = "Profiles", uniqueConstraints = @UniqueConstraint(columnNames = { "idUser" }))
public class Profiles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 17074287424L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idProfile;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", nullable = false)
	private Users users;

	@Column(name = "nickName")
	private String nickName;

	private Date birthday;

	private Genders genders = Genders.unknown;

	private String mobile;

	private String email;

	private Privacies privacy = Privacies.normal;

}
