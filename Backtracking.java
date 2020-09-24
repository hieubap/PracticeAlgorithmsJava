package Backtracking;

import java.util.Arrays;

public class Backtracking {
	/* bài 1 : in ra các vị trí 8 quân hậu trên bàn cờ
	 * để chúng không ăn nhau
	 * 
	 * baì 2 : tìm thứ tự di chuyển của quân ngựa trong
	 * bàn cờ n x n để nó đi hết được các ô trong 1 lượt
	 * ( khá giống ý tưởng ở bài 1 )
	 * 
	 * bài 3 : tìm đường đi ngắn nhất giữa hai điểm trong ma trận
	 * ( đã được ứng dụng vào game pacman)
	 * 
	 * 
	 */
	public static void main(String[] args) {
//		testbai1();
		testbai2();
	}
	
	
	public static void thamkhaobai1() { // chỉ in đc 1 trường hợp
		int[] data = new int[8];
		Arrays.fill(data, -1);
		
		int k=0;
		while(k<8) {
			data[k]++;
			
			while(!check(data,k,8)) {
				data[k]++;
				while(data[k]>=8) {
					data[k]=-1;
					k--;
					if(k<0)
						return;
					data[k]++;
				}
			}
			
			k++;
		}
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++)
				if(data[i] == j)
					System.out.print("Q ");
				else
					System.out.print("- ");
			System.out.println();
		}
	}
	public static int countQueen = 0;
	/* hàm này là hàm chuẩn in ra tất cả các trường hợp có thể
	 * giải thích việc dùng data 8 phần tử:
	 * vd data[3] = 6 tức là có một con hậu ở hàng 3 cột 6
	 * 
	 */
	public static void nQueen(int[] data,int r,int n) {
		
		if(r == n) { // phần in ra màn hình
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++)
					if(data[i] == j)
						System.out.print("Q ");
					else
						System.out.print("- ");
				System.out.println();
			}
			System.out.println(countQueen++);
			return;
		}
		
		for(int j=0;j<n;j++) { // kiểm tra 8 vị trí tại hàng hiện tại
			data[r]=j;
			if(check(data,r,n)) { // vị trí nào thỏa mãn thì duyệt tới cột tiếp theo
				nQueen(data,r+1,n);
			}
		}
		data[r]=-1;
	}
	public static boolean check(int[] data,int r,int n) {
		for(int i=0;i<data.length;i++) {
			if(i != r&& data[i]>=0)
				if(data[r] == data[i] || data[r] == data[i]-Math.abs(r-i) || data[r] == data[i]+Math.abs(r-i))
					return false;
		}
		return true;
	}
	public static void testbai1() {
		int[] data = new int[5];// có thể thay đổi kích thước n x n
		Arrays.fill(data, -1);
		
		nQueen(data,0,data.length);
	}
	public static int[] row = {1,1,2,2,-1,-1,-2,-2};
	public static int[] col = {-2,2,-1,1,-2,2,-1,1};
	public static int countKnight = 0;
	
	public static boolean isVisit(int[][] data,int x,int y,int n) {
		if( x < 0 || x >= n || y < 0 || y >= n)
			return true;
		if(data[x][y] != 0)
			return true;
		return false;
	}
	public static void KnightTour(int[][] map,int x,int y,int count,int n) {
		map[x][y] = count;
		if(count == n*n) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++)
					System.out.print(" "+map[i][j]);
				System.out.println();
			}
			map[x][y] = 0;
			System.out.println("*"+ ++countKnight);
			return;
		}
		for(int i=0;i<row.length;i++) {
			int newX = x + row[i];
			int newY = y + col[i];
			
			if(!isVisit(map, newX, newY,n)) {
				KnightTour(map, newX, newY, count+1, n);
				
			}
		}
		map[x][y] = 0;
	}
	public static void testbai2() {
		int n = 5;
		int[][] data = new int[n][n];
		KnightTour(data, 1, 1, 1, n);
	}
}
