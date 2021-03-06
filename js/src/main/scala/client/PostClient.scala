package client

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow

import org.scalajs.dom

import upickle.MapW



object PostClient extends autowire.Client[String, upickle.Reader, upickle.Writer] {

  override def doCall(req: Request): Future[String] = {
    val url = req.path.mkString("/")    
    val r = dom.ext.Ajax.post(
      url = "http://localhost:8080/" + url,
      data = upickle.write(req.args))
    r.map(_.responseText)
  }

  def read[Result: upickle.Reader](p: String) = upickle.read[Result](p)

  def write[Result: upickle.Writer](r: Result) = upickle.write(r)
}
