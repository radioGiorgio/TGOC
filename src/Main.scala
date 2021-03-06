import scala.io.Source
import java.io._

object Main {

  def initGraphe(file : String) : Array[Array[Int]]= {
    val lines = Source.fromFile(file).getLines().toList

    val info = lines(0).split(" ")
    val head = Array("0") ++ lines(1).split(" ")
    val coordx = lines(2).split(" ")
    val coordy = lines(3).split(" ")
    val succ = Array("0") ++ lines(4).split(" ") ++ Array("0")
    val distance = Array("0") ++ lines(5).split(" ") ++ Array("0")
    val prospectus = Array("0") ++  lines(6).split(" ") ++ Array("0")
    Array(info.map(x => x.toInt),head.map(x => x.toInt),succ.map(x => x.toInt),distance.map(x => x.toInt),prospectus.map(x => x.toInt),coordx.map(x => x.toInt),coordy.map(x => x.toInt))
  }

  var result:Array[Int] = new Array[Int](1000) //SOlution dégeu pour y accéder à partir de java TODO

  def main(args: Array[String]){
    val graphInfo = initGraphe(args(0))
    val graph = new Graphe(graphInfo(1),graphInfo(2),graphInfo(3),graphInfo(4))
    var CCs = Methaeuristic.GRASP.compute(graphInfo(0)(2), graph, args(1).toInt)
    CCs.foreach(cc => println(Methaeuristic.computeEdges(cc)))
    //CCs.foreach(cc => println("Prospectus : "+ cc.prospectusCovered + ", distance : " + cc.distCovered + ", compacity : "+ Methaeuristic.compacity(cc)))

    /* Clem l'attrapeur */

    //Chaque entrée du tableau sera une liste de tuples (chaque compo connexe est une liste de tuples)
    var compoConnexesListes:Array[List[(Int,Int)]] = new Array[List[(Int,Int)]](graphInfo(0)(2))
    for(i<-0 to (graphInfo(0)(2)-1) ) {
        compoConnexesListes(i) = Methaeuristic.computeEdges(CCs(i))
    }


    result = new Array[Int](graphInfo(2).length-2) //Le tableau pour la sol version prof

    //On parcours les arretes
    var acc = 1
    var condition  = List[Tuple2[Int, Int]]()
    for(u<-1 to graphInfo(1).length-2) {
        for(j<-0 to (graphInfo(1)(u+1)-graphInfo(1)(u)-1 ) ) {
            for(k<-0 to (graphInfo(0)(2)-1)) {
                if(compoConnexesListes(k).contains(u,graphInfo(2)(acc))){
                    result(acc-1) = k+1
                }
            }

            acc += 1

        }
    }
    writeAnswer(result) // Write the answer in "result.txt"

  }

  def dijkstraTest(D: Array[Array[Int]]): Unit ={
    for(i<- 1 to D.length -1) {
      println("Plus courts chemins du sommet ", i, " à : ")
      for (j <- 1 to D(i).length - 1)
        if(D(i)(j) != Int.MaxValue)
          println("---> ", j, " = ", D(i)(j))
    }
  }

  /**
    *
    * @param T Array of Integer (result of the problem)
    */
  def writeAnswer(T: Array[Int]): Unit ={
    val file = new File("result.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    for (i<-0 to(T.length)-1) {
      bw.write(T(i).toString())
    }
    bw.close()
  }
}
