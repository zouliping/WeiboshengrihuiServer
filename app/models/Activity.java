package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Activity extends Model {

	private static final long serialVersionUID = 276731460236835597L;

	@Id
	public String aid;
	public String title;
	public String location;
	public String description;
	public String start_time;
	public String end_time;

	public static Finder<String, Activity> find = new Finder<String, Activity>(
			String.class, Activity.class);
}
