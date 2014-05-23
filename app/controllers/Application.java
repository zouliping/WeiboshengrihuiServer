package controllers;

import models.Users;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonUtil;
import utils.SHA1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Application extends Controller {

	public static Result index() {
		return ok("hello,weiboshengrihui");
	}

	/**
	 * login
	 * 
	 * @return
	 */
	public static Result login() {
		JsonNode json = request().body().asJson();
		System.out.println(json.toString());
		String uid = json.findPath("id").textValue();
		String pwd = json.findPath("password").textValue();

		Users user = Users.find.byId(SHA1.getSHA1String(uid));

		if (user == null) {
			return badRequest(JsonUtil.getFalseJson());
		}
		if (user.pwd.equals(pwd)) {
			ObjectNode on = Json.newObject();
			on.put("result", SHA1.getSHA1String(uid));
			return ok(on);
		} else {
			return badRequest(JsonUtil.getFalseJson());
		}
	}
}
