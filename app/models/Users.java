package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Users extends Model {

	private static final long serialVersionUID = 5854422586239724109L;

	@Id
	public String uid;
	public String pwd;
	public String location;
	public String birthday;
	public String interesting;
	public Boolean gender;

	public static Finder<String, Users> find = new Finder<String, Users>(
			String.class, Users.class);
}
