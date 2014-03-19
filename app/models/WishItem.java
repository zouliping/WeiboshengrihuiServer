package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class WishItem extends Model {

	private static final long serialVersionUID = 3356712022908516637L;
	@Id
	public String wid;
	public String uid;
	public String title;
	public String description;
	public String type;

	public static Finder<String, WishItem> find = new Finder<String, WishItem>(
			String.class, WishItem.class);
}
