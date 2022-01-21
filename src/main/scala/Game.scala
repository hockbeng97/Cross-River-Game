import java.io.{File, FileWriter, PrintWriter}
import java.util
import java.util.{Collections, Comparator}

import scala.collection.View.Empty.to
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.io.StdIn.readLine

object Game {

  var currentPath:String = "path1";
  var police = new Person("police", "Uncross");
  var criminal = new Person("criminal", "Uncross");
  var mother = new Person("mother", "Uncross");
  var father = new Person("father", "Uncross");
  var daughter1 = new Person("daughter1", "Uncross");
  var daughter2 = new Person("daughter2", "Uncross");
  var son1 = new Person("son1", "Uncross");
  var son2 = new Person("son2", "Uncross");

  var policeCurrentStatus = ""
  var criminalCurrentStatus = ""
  var fatherCurrentStatus = ""
  var motherCurrentStatus = ""
  var son1CurrentStatus = ""
  var son2CurrentStatus = ""
  var daughter1CurrentStatus = ""
  var daughter2CurrentStatus = ""

  var round: Int = 1;

  var characterArray = new Array [Person](8);

  def main (args: Array [String]): Unit = {


    characterArray(0) = police;
    characterArray(1) = criminal;
    characterArray(2) = mother;
    characterArray(3) = father;
    characterArray(4) = daughter1;
    characterArray(5) = daughter2;
    characterArray(6) = son1;
    characterArray(7) = son2;

    var checkAllNotCross = true;

    while (checkAllNotCross) {
      println()
      println(" ============================================= Round " + round + " ============================================= ")
      println()
       print cross and uncross character
      var crossCharacterList: String = "";
      var UnCrossCharacterList: String = "";

      for ( character <- characterArray) {
        if (character.getStatus == "Cross") {
          crossCharacterList += character.getName + ", "
        }
        if (character.getStatus == "Uncross") {
          UnCrossCharacterList += character.getName + ", "
        }
      }

      println("Path 1 (Current Path) : " + UnCrossCharacterList);
      println("Path 2 (Opposite River) : " + crossCharacterList);

      println()
      if (currentPath == "path1") {
        println("Which characters do you want to choose? Boat at Path 1 ")
      }
      else {
        println("Which characters do you want to choose? Boat at Path 2 ")
      }


      var checkFirstCharacterNotExist = true;
      var checkSecondCharacterNotExist = true;

      var char1: String = "";
      var char2: String = "";

       validate for character input
       just press enter for no character
      while (checkFirstCharacterNotExist) {
        print("Character 1: ");
        char1 = readLine().toLowerCase.trim;
        var characterNotExist = true
        for ( a <- characterArray) {
          if ((char1 == a.getName && characterNotExist) || char1 == "") {
            characterNotExist = false
          }
        }
        if (characterNotExist) {
          println("Character 1 not exists")
        } else {
          checkFirstCharacterNotExist = false
        }
      }

      while (checkSecondCharacterNotExist) {
        print("Character 2: ");
        char2 = readLine().toLowerCase.trim;
        var characterNotExist = true
        for ( a <- characterArray) {
          if ((char2 == a.getName && characterNotExist) || char2 == "") {
            characterNotExist = false
          }
        }
        if (characterNotExist) {
          println("Character 2 not exists")
        } else {
          checkSecondCharacterNotExist = false
        }
      }

      var selectedPerson1 = new Person();
      var selectedPerson2 = new Person();

      if (char1 == police.getName) {
        selectedPerson1 = police;
      }
      if (char2 == police.getName) {
        selectedPerson2 = police;
      }

      if (char1 == criminal.getName) {
        selectedPerson1 = criminal;
      }
      if (char2 == criminal.getName) {
        selectedPerson2 = criminal;
      }

      if (char1 == mother.getName) {
        selectedPerson1 = mother;
      }
      if (char2 == mother.getName) {
        selectedPerson2 = mother;
      }


      if (char1 == father.getName) {
        selectedPerson1 = father;
      }
      if (char2 == father.getName) {
        selectedPerson2 = father;
      }


      if (char1 == daughter1.getName) {
        selectedPerson1 = daughter1;
      }
      if (char2 == daughter1.getName) {
        selectedPerson2 = daughter1;
      }


      if (char1 == daughter2.getName) {
        selectedPerson1 = daughter2;
      }
      if (char2 == daughter2.getName) {
        selectedPerson2 = daughter2;
      }


      if (char1 == son1.getName) {
        selectedPerson1 = son1;
      }
      if (char2 == son1.getName) {
        selectedPerson2 = son1;
      }


      if (char1 == son2.getName) {
        selectedPerson1 = son2;
      }
      if (char2 == son2.getName) {
        selectedPerson2 = son2;
      }

      // get the current status of all characters
      policeCurrentStatus = police.getStatus
      criminalCurrentStatus = criminal.getStatus
      fatherCurrentStatus = father.getStatus
      motherCurrentStatus = mother.getStatus
      son1CurrentStatus = son1.getStatus
      son2CurrentStatus = son2.getStatus
      daughter1CurrentStatus = daughter1.getStatus
      daughter2CurrentStatus = daughter2.getStatus


      // At Path 1
      // check selected person at current path
      if (currentPath == "path1" &&
        (selectedPerson1.getStatus() == "Uncross" || selectedPerson1.getName() == "") &&
        (selectedPerson2.getStatus() == "Uncross" || selectedPerson2.getName() == ""))
      {

         check if adults on boat
        checkAdultOnBoat(selectedPerson1, selectedPerson2)

      }
      // crossed river (Opposite river)
      // At Path 2
      else if (currentPath == "path2" &&
        (selectedPerson1.getStatus() == "Cross" || selectedPerson1.getName() == "") &&
        (selectedPerson2.getStatus() == "Cross" || selectedPerson2.getName() == ""))
      {
        checkAdultOnBoat(selectedPerson1, selectedPerson2)
      }
      else {
        checkCharacterInCurrentPath(selectedPerson1.getStatus(), selectedPerson2.getStatus())
      }

      // check all characters are crossed
      if (police.getStatus == "Cross" && criminal.getStatus == "Cross" && mother.getStatus == "Cross"
        && father.getStatus == "Cross" && daughter1.getStatus == "Cross" && daughter2.getStatus == "Cross"
      && son1.getStatus == "Cross" && son2.getStatus == "Cross") {
        checkAllNotCross = false;
        println()
        println("YEAH !! GAME COMPLETED !!")
        println("Total rounds used = " + round)
        println()
        saveResult(round)
      }
    }




  def checkAdultOnBoat (selectedPerson1: Person, selectedPerson2: Person) = {
    if ((selectedPerson1 == police || selectedPerson1 == mother || selectedPerson1 == father) ||
      (selectedPerson2 == police || selectedPerson2 == mother || selectedPerson2 == father)) {
      println("Crossing River ..... ")

      if (currentPath == "path1") {
        setStatus("Cross", selectedPerson1, selectedPerson2, police, criminal, mother, father, daughter1, daughter2, son1, son2);
        checkCrossRiverCondition (father, son1, son2, mother, daughter1, daughter2, "path2");
      }
      else {
        setStatus("Uncross", selectedPerson1, selectedPerson2, police, criminal, mother, father, daughter1, daughter2, son1, son2);
        checkCrossRiverCondition (father, son1, son2, mother, daughter1, daughter2, "path1");
      }

    }
    else {
      println("ERR !! Cross River Failed !! Boat MUST HAVE at least one adult (father/mother/police)")
    }
  }

  def checkCharacterInCurrentPath(statusOfPerson1: String, statusOfPerson2: String) = {
    if (currentPath == "path1") {
      if (statusOfPerson1 != "Uncross" && statusOfPerson1 != "") {
        println("ERR !! Character 1 is not in current path")
      }
      if (statusOfPerson2 != "Uncross" && statusOfPerson2 != "") {
        println("ERR !! Character 2 is not in current path")
      }
    }
    else {
      if (statusOfPerson1 != "Cross" && statusOfPerson1 != "") {
        println("ERR !! Character 1 is not in current path")
      }
      if (statusOfPerson2 != "Cross" && statusOfPerson2 != "") {
        println("ERR !! Character 2 is not in current path")
      }
    }
  }

  def setStatus(status: String, selectedPerson1: Person, selectedPerson2: Person, police: Person,
                criminal: Person, mother: Person, father: Person, daughter1: Person,
                daughter2: Person, son1: Person, son2: Person):Unit = {

    // set status for character start
    if (selectedPerson1 == police || selectedPerson2 == police) {
      police.setStatus(status)
    }
    if (selectedPerson1 == criminal || selectedPerson2 == criminal) {
      criminal.setStatus(status)
    }
    if (selectedPerson1 == mother || selectedPerson2 == mother) {
      mother.setStatus(status)
    }
    if (selectedPerson1 == father || selectedPerson2 == father) {
      father.setStatus(status)
    }
    if (selectedPerson1 == daughter1 || selectedPerson2 == daughter1) {
      daughter1.setStatus(status)
    }
    if (selectedPerson1 == daughter2 || selectedPerson2 == daughter2) {
      daughter2.setStatus(status)
    }
    if (selectedPerson1 == son1 || selectedPerson2 == son1) {
      son1.setStatus(status)
    }
    if (selectedPerson1 == son2 || selectedPerson2 == son2) {
      son2.setStatus(status)
    }
    // set status for character end
  }

  def checkCrossRiverCondition (father: Person, son1: Person, son2: Person, mother: Person, daughter1: Person, daughter2: Person, path: String): Unit = {

    if (father.getStatus == "Uncross" && (son1.getStatus == "Cross" || son2.getStatus == "Cross") && mother.getStatus == "Cross") {
      println("ERR !! Father must along with sons(s) ")
      println("ERR !! Cross River Failed")
      resetStatus()
    }
    else if (father.getStatus == "Cross" && (son1.getStatus == "Uncross" || son2.getStatus == "Uncross") && mother.getStatus == "Uncross") {
      println("ERR !! Father must along with sons(s) ")
      println("ERR !! Cross River Failed")
      resetStatus()
    }
    else if (mother.getStatus == "Uncross" && (daughter1.getStatus == "Cross" || daughter1.getStatus == "Cross") && father.getStatus == "Cross") {
      println("ERR !! Mother must along with daughters(s) ")
      println("ERR !! Cross River Failed")
      resetStatus()
    }
    else if (mother.getStatus == "Cross" && (daughter2.getStatus == "Uncross" || daughter2.getStatus == "Uncross") && father.getStatus == "Uncross") {
      println("ERR !! Mother must along with daughter(s) ")
      println("ERR !! Cross River Failed")
      resetStatus()
    }
    else if (police.getStatus != criminal.getStatus &&
      (criminal.getStatus == father.getStatus || criminal.getStatus == mother.getStatus || criminal.getStatus == daughter1.getStatus ||
        criminal.getStatus == daughter2.getStatus || criminal.getStatus == son1.getStatus || criminal.getStatus == son2.getStatus)) {
      println("ERR !! Criminal cannot stay with family without police")
      println("ERR !! Cross River Failed")
      resetStatus()
    }
    else {
      currentPath = path
      round += 1
      println("Cross River Successfully")
    }
  }

  def resetStatus () = {
    father.setStatus(fatherCurrentStatus)
    son1.setStatus(son1CurrentStatus)
    son2.setStatus(son2CurrentStatus)
    mother.setStatus(motherCurrentStatus)
    daughter1.setStatus(daughter1CurrentStatus)
    daughter2.setStatus(daughter2CurrentStatus)
    police.setStatus(policeCurrentStatus)
    criminal.setStatus(criminalCurrentStatus)
  }

  def saveResult (totalRounds: Int): Unit = {
    val pw = new PrintWriter(new FileWriter("Result.txt", true ))
    pw.write(String.valueOf(totalRounds) + "\n")
    pw.close
    displayResult()
  }

  def displayResult (): Unit = {

    val source = Source.fromFile("Result.txt").getLines().toList

    val myArray = source.filterNot(_.isEmpty).map(_.toInt).toArray

    var maxLength = myArray.length - 2
    for (a <- 0 to maxLength) {
      for (b <- 0 to maxLength) {
        if (myArray(b) > myArray(b + 1)) {
          var temp = myArray(b)
          myArray(b) = myArray(b + 1)
          myArray(b + 1) = temp
        }
      }
    }

    println("---------------------------------------------------")
    println( "Ranking                  " + " Total Rounds Used")
    println("---------------------------------------------------")
    var rank = 1
    for (a <- myArray) {
      if (a > 0 && a < 10) {
        println( rank + "                            " +  a)
      }
      else {
        println( rank + "                          " +  a)
      }
      rank += 1;
    }

  }

}




