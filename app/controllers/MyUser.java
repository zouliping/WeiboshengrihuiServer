package controllers;

import java.util.Iterator;
import java.util.List;

import models.Production;
import models.Users;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonUtil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MyUser extends Controller {

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

		ObjectNode result = Json.newObject();
		result.put(name, on);
		return ok(result);
	}

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
			} else if ("current_location".equals(pro)) {
				users.location = json.get(pro).textValue();
			} else if ("gender".equals(pro)) {
				users.gender = json.get(pro).booleanValue();
			} else if ("birthday".equals(pro)) {
				users.birthday = json.get(pro).textValue();
			} else if ("interesting".equals(pro)) {
				users.interesting = json.get(pro).textValue();
			}
		}
		users.save();
		return ok(JsonUtil.getTrueJson());
	}

	public static Result getProduction(String type, String uid) {
		if ("Goods".equals(type)) {
			List<Production> productions = Production.find.where()
					.ilike("uid", uid).findList();
			System.out.println(productions.size() + "---"
					+ productions.get(0).title + productions.get(1).title);
		} else if ("WishItem".equals(type)) {

		}
		return ok();
	}
}
