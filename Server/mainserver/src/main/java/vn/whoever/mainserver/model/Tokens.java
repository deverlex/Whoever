package vn.whoever.mainserver.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Token", uniqueConstraints = @UniqueConstraint(columnNames = {""}))
public class Tokens {

	
}
