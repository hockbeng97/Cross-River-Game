class Person {
  private var objName:String = "";
  private var objStatus:String = "";
  def this (name: String, status:String) = {
    this()
    objName = name;
    objStatus = status;
  }

  def getName():String = {
    var name = objName
    return name
  }

  def getStatus():String = {
    var status = objStatus
    return status
  }

  def setName(name: String):Unit = {
    objName = name
  }

  def setStatus(status: String):Unit = {
    objStatus = status
  }
}
