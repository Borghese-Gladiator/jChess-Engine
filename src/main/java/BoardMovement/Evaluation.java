package BoardMovement;

import java.util.ArrayList;

public class Evaluation {
	private static int [][]pawn = new int [][]
	{
		{ 0,  0,  0,  0,  0,  0,  0,  0},
		{5, 10, 10,-25,-25, 10, 10,  5},
		{5, -5,-10,  0,  0,-10, -5,  5},
		{0,  0,  0, 25, 25,  0,  0,  0},
		{5,  5, 10, 27, 27, 10,  5,  5},
		{10, 10, 20, 30, 30, 20, 10, 10},
		{50, 50, 50, 50, 50, 50, 50, 50},
		{0,  0,  0,  0,  0,  0,  0,  0}
	};
	private static int [][]king = new int [][]
	{
		{20,  30,  10,   0,   0,  10,  30,  20},
		{20,  20,   0,   0,   0,   0,  20,  20},
		{-10, -20, -20, -20, -20, -20, -20, -10}, 
		{-20, -30, -30, -40, -40, -30, -30, -20},
		{-30, -40, -40, -50, -50, -40, -40, -30},	
		{-30, -40, -40, -50, -50, -40, -40, -30},
		{-30, -40, -40, -50, -50, -40, -40, -30},
		{-30, -40, -40, -50, -50, -40, -40, -30},

	};
	private static int [][]knight = new int [][]
	{
		{-50,-40,-20,-30,-30,-20,-40,-50},
		{-40,-20,  0,  5,  5,  0,-20,-40},
		{-30,  5, 10, 15, 15, 10,  5,-30},
		{-30,  0, 15, 20, 20, 15,  0,-30},
		{-30,  5, 15, 20, 20, 15,  5,-30},
		{-30,  0, 10, 15, 15, 10,  0,-30},
		{-40,-20,  0,  0,  0,  0,-20,-40},
		{-50,-40,-30,-30,-30,-30,-40,-50},

	};
	private static int [][]bishop= new int [][]
	{	  
		{-20,-10,-40,-10,-10,-40,-10,-20},
		{-10,  5,  0,  0,  0,  0,  5,-10},
		{-10, 10, 10, 10, 10, 10, 10,-10},
		{-10,  0, 10, 10, 10, 10,  0,-10},
		{-10,  5,  5, 10, 10,  5,  5,-10},
		{-10,  0,  5, 10, 10,  5,  0,-10},
		{-10,  0,  0,  0,  0,  0,  0,-10},
		{-20,-10,-10,-10,-10,-10,-10,-20},
	};
	private static int hightotal=0;
	private static Position pos;
	private static Position oldpos;
			
	public static void move( Position move, Position current, Piece[][]board){
		if(hightotal < board[move.getX()][move.getY()].gettotal() ){
			hightotal = board[move.getX()][move.getY()].gettotal();
			pos=move;
			oldpos = current;
		}
			
	}
	public static Arraylist<Position> gethighest(){
		return new ArrayList<Position>
	}
	


}
