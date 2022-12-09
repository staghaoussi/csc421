import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSPBike extends CSP{
 
    static Set<Object> Bike = new HashSet<Object>(Arrays.asList(new String[] {"black", "blue", "green", "red", "white"}));
	static Set<Object> Name = new HashSet<Object>(Arrays.asList(new String[] {"Adrian", "Charles", "Henry", "Joel", "Richard"}));
	static Set<Object> Sandwich = new HashSet<Object>(Arrays.asList(new String[] {"bacon", "chicken", "cheese", "pepperoni", "tuna"}));
	static Set<Object> Juice = new HashSet<Object>(Arrays.asList(new String[] {"apple", "cranberry", "grapefruit", "orange", "pineapple"}));
	static Set<Object> Age = new HashSet<Object>(Arrays.asList(new String[] {"12years", "13years", "14years", "15years", "16years"}));
	static Set<Object> Sport = new HashSet<Object>(Arrays.asList(new String[] {"baseball", "basketball", "hockey", "soccer", "swimming"}));

    // complete following functions
    // isGood - todo
    // main - check


    // this function handles binary constrains, ensure binary constraints are present in is Good to find optimal valid solution
    public boolean isGood(Object X, Object Y, Object x, Object y){
        //if X is not even mentioned in by the constraints, just return true
		//as nothing can be violated
		if(!C.containsKey(X))
			return true;

        //check to see if there is an arc between X and Y
		//if there isn't an arc, then no constraint, i.e. it is good
		if(!C.get(X).contains(Y))
            return true;
        
    // binary constraints
        if((X.equals("Henry") && Y.equals("soccer") && (Integer)y - (Integer)x != 1) ||
            (X.equals("swimming") && Y.equals("baseball") && Math.abs((Integer)x - (Integer)y) != 1 ) ||
            (X.equals("Joel") && Y.equals("16years") && Math.abs((Integer)x - (Integer)y) != 1 ) ||
            (X.equals("Adrian") && Y.equals("pepperoni") && (Integer)y - (Integer)x != 1) ||
            (X.equals("hockey") && Y.equals("pepperoni") && !x.equals(y)) ||
            (X.equals("bacon") && Y.equals("white") && (Integer)y >= (Integer)x) || 
            (X.equals("16years") && Y.equals("cheese") && !x.equals(y)) ||
            (X.equals("baseball") && Y.equals("apple") && Math.abs((Integer)x-(Integer)y)!=1)
        )
            return false;

    // multi constraints
        // between 12-15 age
        if((X.equals("white") && Y.equals("15years") && (Integer)y >= (Integer)x ) ||
            (X.equals("white") && Y.equals("12years") && (Integer)x >= (Integer)y )
        )
            return false;
        // grapefruit between tuna and pineapple
        if((X.equals("grapefruit") && Y.equals("tuna") && (Integer)y >= (Integer)x) ||
            (X.equals("grapefruit") && Y.equals("pineapple") && (Integer)x >= (Integer)y)
        )
            return false;
        // between 14 age and OG juice
        if((X.equals("pineapple") && Y.equals("14years") && (Integer)y >= (Integer)x ) ||
            (X.equals("pineapple") && Y.equals("orange") && (Integer)x >= (Integer)y )
        )
            return false;
        // between blue and black bikes
        if ((X.equals("white") && Y.equals("blue") && (Integer)y >= (Integer)x) ||
            (X.equals("white") && Y.equals("black") && (Integer)x >= (Integer)y)
        )
            return false;
        // 12 year old between 14 and 16 year old
        if((X.equals("12years") && Y.equals("14years") && (Integer)y >= (Integer)x) ||
            (X.equals("12years") && Y.equals("16years") && (Integer)x >= (Integer)y)
        )
            return false;
        // between richard and red bike
        if((X.equals("white") && Y.equals("Richard") && (Integer)y >= (Integer)x) ||
            (X.equals("white") && Y.equals("red") && (Integer)x >= (Integer)y)
        )
            return false;
        // charles between richard and adrian
        if((X.equals("Charles") && Y.equals("Richard") && (Integer)y >= (Integer)x) ||
            (X.equals("Charles") && Y.equals("Adrian") && (Integer)x >= (Integer)y)
        )
            return false;

    // uniquess constraint

        // check if value is valid, then if index is same then if value is not equal
        if((Bike.contains(X) && Bike.contains(Y) && x.equals(y) && !X.equals(Y)) ||
            (Name.contains(X) && Name.contains(Y) && x.equals(y) && !X.equals(Y)) ||
            (Sandwich.contains(X) && Sandwich.contains(Y) && x.equals(y) && !X.equals(Y)) ||
            (Juice.contains(X) && Juice.contains(Y) && x.equals(y) && !X.equals(Y)) ||
            (Age.contains(X) && Age.contains(Y) && x.equals(y) && !X.equals(Y)) ||
            (Sport.contains(X) && Sport.contains(Y) && x.equals(y) && !X.equals(Y))
        )
            return false;
        return true;


    }

    public static void main(String[] args) throws Exception {
        CSPBike csp = new CSPBike();

        // five children are the domain
        Integer[] domain = {1,2,3,4,5};

    // add domain to all variables
        for(Object X : Sport) 
			csp.addDomain(X, domain);

		for(Object X : Age) 
			csp.addDomain(X, domain);

		for(Object X : Juice) 
			csp.addDomain(X, domain);

		for(Object X : Sandwich) 
			csp.addDomain(X, domain);

		for(Object X : Name) 
			csp.addDomain(X, domain);

		for(Object X : Bike) 
			csp.addDomain(X, domain);

    // remove values from domains of unary constraints

        // In the middle is the boy that likes Baseball.
        // The boy riding the Black bike is at the third position.
        for(int i = 1; i <= 5; i++){
            if(i != 3){
                csp.D.get("baseball").remove(i);
                csp.D.get("black").remove(i);
            }
        }
        // The boy that is going to drink Pineapple juice is at the fourth position.
        for(int i = 1; i <= 5; i++){
            if(i != 4){
                csp.D.get("pineapple").remove(i);
            }
        }
        // At one of the ends is the boy riding the Green bicycle.
        // The cyclist who is going to eat Tuna sandwich is at one of the ends.
        for(int i = 1; i <= 5; i++){
            if(i != 1 && i !=5){
                csp.D.get("tuna").remove(i);
                csp.D.get("green").remove(i);
            }
        }
        // In the fifth position is the 13-year-old boy.
        // The boy who likes Hockey is at the fifth position.
        for(int i = 1; i <= 5; i++){
            if(i !=5){
                csp.D.get("13years").remove(i);
                csp.D.get("hockey").remove(i);
            }
        }


    // add constraint arcs for binary constraints

        // Henry is exactly to the left of the Soccer fan.
        csp.addBidirectionalArc("Henry", "soccer");

        // The one who likes Swimming is next to the friend who likes Baseball.
        csp.addBidirectionalArc("swimming", "baseball");

        // The boy who likes the sport played on ice is going to eat Pepperoni sandwich.
        csp.addBidirectionalArc("hockey", "pepperoni");

        // Joel is next to the 16-year-old cyclist.
        csp.addBidirectionalArc("Joel", "16years");

        // Adrian is exactly to the left of the boy who is going to eat Pepperoni sandwich.
        csp.addBidirectionalArc("Adrian", "pepperoni");

        // The boy who is going to eat Bacon sandwich is somewhere to the right of the owner of the White bicycle.
        csp.addBidirectionalArc("bacon", "white");

        // The 16-year-old brought Cheese sandwich.
        csp.addBidirectionalArc("16years", "cheese");

        //The Baseball fan is next to the boy who is going to drink Apple juice.
        csp.addBidirectionalArc("baseball", "apple");

    // add constraint arcs for constraints larger than binary constraints

        // The owner of the White bike is somewhere between the 15-year-old boy and the youngest boy, in that order.
        csp.addBidirectionalArc("white", "15years");
        csp.addBidirectionalArc("white", "12years");

        // The boy who is going to drink Grapefruit juice is somewhere between who brought Tuna sandwich and who brought Pineapple juice, in that order.
        csp.addBidirectionalArc("grapefruit", "tuna");
		csp.addBidirectionalArc("grapefruit", "pineapple");

        // The cyclist that brought Pineapple juice is somewhere between the 14-year-old and the boy that brought Orange juice, in that order.
        csp.addBidirectionalArc("pineapple", "14years");
		csp.addBidirectionalArc("pineapple", "orange");

        // The boy riding the White bike is somewhere between the boys riding the blue and the black bicycles, in that order.
        csp.addBidirectionalArc("white", "blue");
		csp.addBidirectionalArc("white", "black");

        // The 12-year-old is somewhere between the 14-year-old and the oldest boy, in that order.
        csp.addBidirectionalArc("12years", "14years");	
		csp.addBidirectionalArc("12years", "16years");

        // The cyclist riding the White bike is somewhere between Richard and the boy riding the Red bike, in that order.
        csp.addBidirectionalArc("white", "Richard");
		csp.addBidirectionalArc("white", "red");

        //Charles is somewhere between Richard and Adrian, in that order.
        csp.addBidirectionalArc("Charles", "Richard");
		csp.addBidirectionalArc("Charles", "Adrian");


        // before searching for solution, we need to ensure that uniquess in our solutions will be found
        // to do this, add bidirectional arcs between corresponding "rows" or variables
        for(Object A : Sport){ 
            for(Object B : Sport){csp.addBidirectionalArc(A, B);}
        }
        for(Object A : Age){            
            for(Object B : Age){csp.addBidirectionalArc(A, B);}
        }
        for(Object A : Juice){ 
            for(Object B : Juice){csp.addBidirectionalArc(A, B);}
        }
        for(Object A : Sandwich){ 
            for(Object B : Sandwich){csp.addBidirectionalArc(A, B);}
        }
        for(Object A : Name){
            for(Object B : Name){csp.addBidirectionalArc(A, B);}
        }
        for(Object A : Bike){
            for(Object B : Bike){csp.addBidirectionalArc(A, B);}
        }

        // all constraints now added, search for solution
        Search search = new Search(csp);
        System.out.println(search.BacktrackingSearch());
    }
}
