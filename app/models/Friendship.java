package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Friendship extends Model {

	private static final long serialVersionUID = -2596701516702084821L;

	@Id
	public String fid;
	public String aid;
	public String bid;

	public static Finder<String, Friendship> find = new Finder<String, Friendship>(
			String.class, Friendship.class);

}
