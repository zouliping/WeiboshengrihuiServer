package controllers;

import java.util.Iterator;
import java.util.List;

import models.Friendship;
import models.Production;
import models.Users;
import models.WishItem;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonUtil;
import utils.SHA1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MyUser extends Controller {

	/**
	 * get user info
	 * 
	 * @param name
	 * @return
	 */
	public static Result get(String name) {
		Users user = Users.find.byId(name);
		if (user == null) {
			return badRequest(JsonUtil.getFalseJson());
		}

		ObjectNode on = Json.newObject();
		on.put("birthday", user.birthday);
		on.put("gender", user.gender);
		on.put("current_location", user.location);
		on.put("interesting", user.interesting);
		return ok(on);
	}

	/**
	 * update user info
	 * 
	 * @return
	 */
	public static Result update() {
		JsonNode json = request().body().asJson();
		System.out.println(json);

		Users users = Users.find.byId(json.get("individualname").textValue());

		if (users == null) {
			return badRequest(JsonUtil.getFalseJson());
		}

		for (Iterator<String> it = json.fieldNames(); it.hasNext();) {
			String pro = it.next();

			if ("classname".equals(pro) || "uid".equals(pro)
					|| "individualname".equals(pro)) {
			} else if ("u_current_location".equals(pro)) {
				users.location = json.get(pro).textValue();
			} else if ("u_gender".equals(pro)) {
				users.gender = json.get(pro).booleanValue();
			} else if ("u_birthday".equals(pro)) {
				users.birthday = json.get(pro).textValue();
			} else if ("u_interest".equals(pro)) {
				users.interesting = json.get(pro).textValue();
			}
		}
		users.save();
		return ok(JsonUtil.getTrueJson());
	}

	/**
	 * get goods & wishitem
	 * 
	 * @param type
	 * @param uid
	 * @return
	 */
	public static Result getProduction(String type, String uid) {
		ObjectNode result = Json.newObject();
		if ("Goods".equals(type)) {
			List<Production> productions = Production.find.where()
					.ilike("uid", uid).findList();

			for (Production tmp : productions) {
				ObjectNode on = Json.newObject();
				on.put("title", tmp.title);
				on.put("description", tmp.description);
				on.put("goods_type", tmp.type);

				result.put(tmp.pid, on);
			}
		} else if ("WishItem".equals(type)) {
			List<WishItem> wishItems = WishItem.find.where().ilike("uid", uid)
					.findList();

			for (WishItem tmp : wishItems) {
				ObjectNode on = Json.newObject();
				on.put("title", tmp.title);
				on.put("description", tmp.description);
				on.put("goods_type", tmp.type);

				result.put(tmp.wid, on);
			}
		} else {
			return badRequest(JsonUtil.getFalseJson());
		}
		return ok(result);
	}

	/**
	 * get friends
	 * 
	 * @param uid
	 * @return
	 */
	public static Result getFriends(String uid) {
		List<Friendship> friendships = Friendship.find.where()
				.ilike("aid", uid).findList();

		if (friendships.size() < 1) {
			return badRequest(JsonUtil.getFalseJson());
		}

		ObjectNode result = Json.newObject();
		for (Friendship tmp : friendships) {
			Users user = Users.find.byId(tmp.bid);

			if (user == null) {
				continue;
			}

			ObjectNode on = Json.newObject();
			on.put("nick", user.nick);
			on.put("birthday", user.birthday);
			on.put("gender", user.gender);
			on.put("current_location", user.location);
			on.put("interest", user.interesting);

			result.put(user.uid, on);
		}
		return ok(result);
	}

	/**
	 * send present to friend
	 * 
	 * @return
	 */
	public static Result sendPresent() {
		JsonNode jn = request().body().asJson();
		System.out.println(jn);
		String from = jn.get("from").textValue();
		String to = jn.get("to").textValue();
		String pid = jn.get("pid").textValue();

		Production production = Production.find.byId(pid);

		if (production == null) {
			return ok(JsonUtil.getFalseJson());
		}

		if (production.uid.equals(from)) {
			production.uid = SHA1.getSHA1String(to);
			production.save();
			System.out.println("send successfully");
		} else {
			return badRequest(JsonUtil.getFalseJson());
		}

		return ok(JsonUtil.getTrueJson());
	}

	/**
	 * remove friend
	 * 
	 * @return
	 */
	public static Result removeFriend() {
		JsonNode jn = request().body().asJson();
		System.out.println(jn);
		String aid = jn.get("indivi1").textValue();
		String bid = jn.get("indivi2").textValue();

		Friendship friendship = Friendship.find.where().ilike("aid", aid)
				.ilike("bid", bid).findUnique();
		friendship.delete();
		return ok(JsonUtil.getTrueJson());
	}
}
