object Main {

  def main(args: Array[String]){
    val graph = new Graphe(Array(0, 1, 3, 6, 9, 11, 14, 18, 22, 25, 28, 32, 36, 39, 41, 44, 47, 49), Array(0, 2, 5, 1, 3, 6, 2, 4, 7, 3, 8, 1, 6, 9, 2, 5, 7, 10, 3, 6, 8, 11, 4, 7, 12, 5, 10, 13, 6, 9, 11, 14, 7, 10, 12, 15, 8,
    11, 16, 9, 14, 10, 13, 15, 11, 14, 16, 12, 15,0), Array(0, 260, 200, 260, 100, 200, 100, 400, 200, 400, 200, 200, 260, 250, 200, 260, 100, 250, 200,
    100, 400, 250, 200, 400, 250, 250, 260, 300, 250, 260, 100, 300, 250, 100, 400, 300, 250,
    400, 300, 300, 260, 300, 260, 100, 300, 100, 400, 300, 400,0), Array(0, 350, 140, 350, 250, 250, 250, 350, 170, 350, 150, 140, 160, 115, 250, 160, 50, 315, 170, 50,
    250, 275, 150, 250, 215, 115, 260, 477, 315, 260, 150, 375, 275, 150, 550, 175, 215, 550,
    275, 477, 360, 375, 360, 120, 175, 120, 650, 275, 650,0))
    graph.fullDijkstraUpdate() ; dijkstraTest(graph.D)
    println("Arrêtes : " ,Methaeuristic.computeEdges(graph)) ;println("Compacity of the Graph : ", Methaeuristic.compacity(graph))
    var cc = graph.createCC((1,2))
    cc.addEdge((2,6)) ; cc.addEdge((6,10)) ; cc.addEdge((1,5)) ; cc.addEdge((5,6))
    cc.fullDijkstraUpdate() ;dijkstraTest(cc.D)
    cc.succ.foreach(i => print(i))
    println() ;println("Arrêtes de la composante connexe : "+ Methaeuristic.computeEdges(cc))
    cc.subEdge((6,10)) ;cc.subEdge((2,6))
    cc.fullDijkstraUpdate()
    println("Retrait de l'arrête (6,10)")
    cc.fullDijkstraUpdate() ;dijkstraTest(cc.D)
    cc.succ.foreach(i => print(i)) ; println()
    println("Compacity of the CC : " + Methaeuristic.compacity(cc))
    println("Edge possibilities : " + cc.computeEdgePossibilities())
    var CCs = Methaeuristic.GRASP.compute(3, graph, 10)
    CCs.foreach(cc => println(Methaeuristic.computeEdges(cc)))
  }

  def dijkstraTest(D: Array[Array[Int]]): Unit ={
    for(i<- 1 to D.length -1) {
      println("Plus courts chemins du sommet ", i, " à : ")
      for (j <- 1 to D(i).length - 1)
        if(D(i)(j) != Int.MaxValue)
          println("---> ", j, " = ", D(i)(j))
    }
  }

}
