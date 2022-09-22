// Following comments are the sudo java code which the prof wrote in class
// this code will be useful for constructing the code needed for the assignment


// abstract class Problem {
//     Object InitState;
//     Abstract bool goaltest(Obj s);
//     Abstract double step_cost(Obj from, Obj to);
//     Abstract double h(Obj s);
// }


// Class ProblemMap extends Problem {

//     // create map var
//     Map <String, Map <String, Double>> map;
    
//     // create straight line distance var
//     Map <String, Double> SVGAnimatedLength;

//     bool goal_test(Obj s) {
//         return goal_state.equals(s);
//     }

//     set <obj> get_succ(obj s) {
//         map // find succesor in this variable
//     }
// }


// class StateNPuzzle {
//     Int N;
//     int[][] puzzleArray;
//     int inot, jnot; // pos of blank tile

//     int equals(obj o) {
//         int hashCode() // because of graphSearch, set E, where E is the history of explored states
//     }
// }


// class Node {
//     Obj State;
//     Node parent;
//     double path_cost;
//     int depth;
//     int order = -1;
// }

// // Frontier FiFo, LiFo, PQ
// interface Frontier {
//     bool isEmpty();
//     Node remove();
//     void insertAll(Set<Node> S)
// }

