package controllers;

import models.Users;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonUtil;

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
}
