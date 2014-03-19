package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Production extends Model {

	private static final long serialVersionUID = 5955099679888044770L;
	@Id
	public String pid;
	public String title;
	public String description;
	public String type;

	public static Finder<String, Production> find = new Finder<String, Production>(
			String.class, Production.class);
}
