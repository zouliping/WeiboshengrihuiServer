package controllers;

import models.Users;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonUtil;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

	public static Result index() {
		return ok("hello,weiboshengrihui");
	}

	public static Result login() {
		JsonNode json = request().body().asJson();
		System.out.println(json.toString());
		String uid = json.findPath("id").textValue();
		String pwd = json.findPath("password").textValue();

		Users user = Users.find.byId(uid);

		if (user == null) {
			return badRequest(JsonUtil.getFalseJson());
		}
		if (user.pwd.equals(pwd)) {
			return ok(JsonUtil.getTrueJson());
		} else {
			return badRequest(JsonUtil.getFalseJson());
		}
	}
}
