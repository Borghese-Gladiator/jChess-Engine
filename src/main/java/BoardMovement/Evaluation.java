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
	private static int hightotal=-100;
	private static Position pos ;
	private static Position oldpos;
	public static void move( Position old, Position current, Piece[][]board, boolean s){
		System.out.println("enter"+old+current);
		int total = 0;
		if(s== true){
			total = 100;
		}
		if(board[old.getX()][old.getY()] instanceof Pawn){
			total = board[old.getX()][old.getY()].getTotal();
			total += pawn[old.getX()][old.getY()];
		}
		else if(board[old.getX()][old.getY()] instanceof King){
			total = board[old.getX()][old.getY()].getTotal() - 50;
			total += king[old.getX()][old.getY()];
		} 
		else if(board[old.getX()][old.getY()] instanceof Knight){
			total = board[old.getX()][old.getY()].getTotal();
			total += knight[old.getX()][old.getY()];
		} 
		else if(board[old.getX()][old.getY()] instanceof Bishop){
			total = board[old.getX()][old.getY()].getTotal();
			total += bishop[old.getX()][old.getY()];
		} 
		else{
			total = board[old.getX()][old.getY()].getTotal();
		}
		System.out.println("hightotal" + hightotal);
		System.out.println("total" + total);

		if(hightotal < total){
			System.out.println(pos);
			System.out.println(oldpos);
			hightotal = total;
			pos=current;
			oldpos = old;
		}
		board[old.getX()][old.getY()].reset();
			
	}
	public static ArrayList<Position> gethighest(){
		System.out.println("output" + oldpos + pos);
		ArrayList<Position> list = new ArrayList<Position>();
		list.add(oldpos);
		list.add(pos);

		return list;
	}
	public static void reset(){
		hightotal = -100;
		pos= null;
		oldpos=null;
	}
	


}
