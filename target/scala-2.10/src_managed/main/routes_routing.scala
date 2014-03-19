// @SOURCE:/Users/zouliping/projects/WeiBoShengRiHuiServer/conf/routes
// @HASH:ceb431f88ff0ece7e03e43a47096a8bdefc3dbaa
// @DATE:Wed Mar 19 20:19:10 CST 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_login1 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("user/login"))))
        

// @LINE:10
private[this] lazy val controllers_Assets_at2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:13
private[this] lazy val controllers_MyUser_get3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("user/get"))))
        

// @LINE:14
private[this] lazy val controllers_MyUser_update4 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("user/update"))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """user/login""","""controllers.Application.login()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """user/get""","""controllers.MyUser.get(name:String)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """user/update""","""controllers.MyUser.update()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_login1(params) => {
   call { 
        invokeHandler(controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Nil,"PUT", """""", Routes.prefix + """user/login"""))
   }
}
        

// @LINE:10
case controllers_Assets_at2(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:13
case controllers_MyUser_get3(params) => {
   call(params.fromQuery[String]("name", None)) { (name) =>
        invokeHandler(controllers.MyUser.get(name), HandlerDef(this, "controllers.MyUser", "get", Seq(classOf[String]),"GET", """ User""", Routes.prefix + """user/get"""))
   }
}
        

// @LINE:14
case controllers_MyUser_update4(params) => {
   call { 
        invokeHandler(controllers.MyUser.update(), HandlerDef(this, "controllers.MyUser", "update", Nil,"PUT", """""", Routes.prefix + """user/update"""))
   }
}
        
}

}
     