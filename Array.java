package Array;

import java.util.ArrayList;
import java.util.Random;

public class Array {
	/* bài hữu ích:
	 * bài 1 có sử dụng đệ quy khá hữu ích (đã được đưa về dạng tổng quát
	 * 							để giải các bài tương tự)
	 * bài 2: có quan hệ với bài 1 khá hay
	 * bài 9: quicksort sắp xếp
	 * bài 10,11 : mergesort
	 * 
	 * bài dễ:
	 * bài 3 (đọc bài 1 là đủ)
	 * bài 4: sắp dãy nhị phân để các số 0 nằm hết về một phía
	 * 					EZ quá bỏ
	 * bài 5 (có 2 ý tưởng khá hay đọc chú thích để biết chi tiết =)) )
	 * bài 6: làm gần tương tự bài 2
	 * bài 7: hiểu bài 2 là oke
	 * 
	 * bài 8: tìm 2 số để đc tích lớn nhất 
	 * bài 12: tìm vị trí của 0 để có dãy nhị phân con có độ dài lớn nhất
	 * bài 13: sáo trộn mảng
	 * bài 14: sắp xếp chéo nhau (bỏ)
	 * bài 15: tìm vị trí chia mảng thành hai phần có tổng bằng nhau
	 */
public static void main(String[] args) {
		/* bai 1 và bài 2 gần giống nhau đều tìm một
		 * bộ các phần tử thỏa mãn có tổng bằng một
		 * giá trị cho trước
		 * khác nhau ở bài một giới hạn số phần tử của bộ
		 * còn bài 2 thì không giới hạn số phần tử đó và nó
		 * không yêu cầu các phần tử bất kì mà các phần
		 * tử liên tiếp do đó dễ hơn bài 1 về độ phức tạp
		 * cũng như có thể tối ưu bằng 1 for
		 * 
		 */
//		testbai1();
//		testbai2();
//		testbai5();
//		testbai6();
//		testbai7();
//		testbai8();
//		testbai9();
//		testbai10();
		
//		testbai12();
//		testbai13();
		testbai15();
		
		
	}
	/* find n node with given sum in an array
	* hàm tìm tất cả targetCount cặp giá trị trong mảng thỏa mãn tổng sum cho trước 
	* array là mảng cần tìm
	* back là mảng lưu lại các bộ thỏa mãn ( lưu chỉ số tương ứng trong array)
	* n độ dài mảng ( thường là array.length)
	* sum là tổng đề bài cho
	* count là biến đếm từ 0 đến targetCount (truyền vào giá trị 0 khi gọi hàm)
	* targetCount là số cặp cần tìm
	* 
	* note: hàm chỉ tìm các dãy và in ra do đó nếu không tìm được dãy nào cả thì
	* hàm sẽ không in ra gì hết nếu muốn in ra thêm dòng không tìm thấy
	* thì cx ez thui chỉnh sau =))  
	*/
	public static void bai1(int[] array,int[] back,int n,int sum,int count,int targetCount) {
		/* code có tham khảo dùng đệ quy
		 * còn việc in ra dãy và bổ sung back để lưu tự viết =))
		 * 
		 * targetSum va targetCount có thể bỏ trong từng bài cụ thể
		 * có thể bỏ back nếu đầu ra là kiểu boolean
		 * mảng back có tác dụng lưu lại toàn bộ các bộ chỉ số của array
		 * thỏa mãn tổng bằng sum của hàm
		 */
		int[] bk = new int[targetCount];
		for(int i=0;i<targetCount;i++) {
			bk[i]= back[i];
		}
		if(count == targetCount && sum == 0) {
			show1( array,back);
			return; // có thể return true nếu yêu cầu đầu ra là boolean
		}
		if(count > targetCount || n == 0) {
			return;
		}
		if(count < targetCount) // có thể bỏ nếu đầu ra là boolean
		bk[count] = n-1;
		/* nếu yêu cầu đầu ra boolean thì return
		 * return function1(array,bk, n-1, sum-array[n-1], count+1, targetCount)
		 *  ||function1(array,bk, n-1, sum, count, targetCount);
		 *  thay cho gọi lại đệ quy 2 lần ở dưới
		 */
		bai1(array,bk, n-1, sum-array[n-1], count+1, targetCount);
		bai1(array,bk, n-1, sum, count, targetCount);
		
	}
	// hàm in ra các phần tử, hỗ trợ hiển thị khi tìm thấy các bộ thỏa mãn ở bài 1 
	public static void show1(int[] data,int[] array) {
		System.out.print("[");
		for(int i : array) {
			System.out.print(" "+data[i]);
		}
		System.out.println(" ]");
	}
	public static void testbai1() {
		int[] array = {1,2,4,5,6,7,12,15,16};
		int[] back = new int[5];
		System.out.println("***** bai 1.1: tim 5 cap thoa man tong bang 30");
		bai1(array,back,array.length, 30,0,5);
		System.out.println("***** bai 1.2: tim 3 cap thoa man tong bang 3");
		bai1(array,back,array.length, 3,0,3);
		
	}
	/* ** bài toán gốc tìm cặp các giá trị liên tiếp có tổng bằng 0
	 * khi có một đoạn có tổng bằng 0 thì khi cộng tới phần tử cuối cùng của đoạn đó 
	 * tổng của dãy lúc đó sẽ bằng với tổng dãy trước khi cộng phần tử đầu tiên của đoạn
	 * vd:
	 * 1 2 3 -5 2
	 * 1 
	 * . 3
	 * . . 6
	 * . . .  1
	 * 
	 * tổng khi chưa cộng thêm 2 là 1
	 * khi ta cộng đến -5 thì vì cả đoạn 2 3 -5 tổng bằng 0
	 * nên lúc này tổng hiện tại sẽ quay lại 1
	 * 
	 * dựa trên ý tưởng đó ta tạo một bài tổng quát hơn
	 * đó là tìm dãy liên tiếp có tổng bằng một giá trị nào đó bất kì
	 * 
	 * code mẫu bài toán gốc ( tham khảo ):
	 * Set<Integer> set = new HashSet<>();
	 * 
	set.add(0);
	int sum = 0;
	for(int value : A) {
		sum += value;
		
		System.out.println("sum = " + sum);
		if(set.contains(sum)) {
			System.out.println("have sum = 0");
			return;
		}
		set.add(sum);
	}
	System.out.println("khong co tong bang 0");
	
	* nhận xét: dùng set để lưu lại các tổng mỗi khi cộng 
	* thêm 1 phần tử nên tốn bộ nhớ
	* 
	* sumtarget là tổng đề bài cho
	* A là mảng cho trước
	*/
	public static void bai2(int[] A,int sumtarget) {
		boolean none = true;// biến kiểm tra có tồn tại dãy hay không để in không tồn tại
		for(int i=0;i<A.length;i++) {
			int sum = 0;
			for(int j=i;j<A.length;j++) {
				sum += A[j];
				if(sum == sumtarget) {
					none = false;
					System.out.print("SubArray [" + i + ".." + j +"] = [ ");
					for(int k=i;k<=j;k++) {
						System.out.print(A[k]+" ");
					}
					System.out.println("]");
				}
			}
		}
		if(none) {
			System.out.println("khong ton tai day co tong bang "+sumtarget);
		}
				
	}
	public static void testbai2() {
		int[] A = {1,2,3,-6,1,-1,1,3,2,-2,-1};
		System.out.println("***** bai 2.1: tim day co tong bang 0");
		bai2(A,0);
		System.out.println("***** bai 2.2: tim day co tong bang 6");
		bai2(A,6);
		System.out.println("***** bai 2.3: tim day co tong bang 10");
		bai2(A,10);
	}
	/* bài toán gốc là tìm trong dãy từ 1 .. đến n-1
	 * phần tử bị lặp lại
	 * 
	 * Ý TƯỞNG 1: cũng khá ez là dùng một mảng boolean để lưu lại
	 * các giá trị đã đi qua và kiểm tra khi bị lặp lại
	 * 
	 * Ý TƯỞNG 2: phần này còn có 1 ý tưởng khác khá nhanh
	 * đó là theo toán học ta có công thức tính tổng dãy từ 1 .. n-1
	 *  là n(n-1)/2 (gọi là tổng lý thuyết)
	 * tính tổng của dãy dùng 1 for (gọi là tổng thực tế)
	 * 
	 ** sau đó lấy thực tế trừ đi lý thuyết để kiểm tra xem lệch nhau bn
	 ** thì lượng lệch đó chính là giá trị cần tìm
	 **    ý tưởng này còn có thể làm một số dạng như cho n tấm vé
	 **    liên tiếp bị mất 1 vé tìm vé mất
	 * 
	 * nhưng ý tưởng này chỉ dành cho trường hợp đặc biệt là
	 * dãy liên tiếp từ 1 đến n và chỉ có 1 cặp lặp
	 * 
	 * nên khi muốn tổng quát hơn nhiều cặp và các giá trị bất
	 * kì lại không đúng
	 * 
	 * *** ta phát triển bài toán thành một mảng bất kì 
	 * với các giá trị bất kì
	 * 
	 * vẫn theo ý tưởng trên cũng khá tốt
	 * để thực hiện tuy nhiên nếu mảng đầu vào
	 * phần tử lớn nhất có giá trị khá lớn sẽ dẫn đến
	 * tốn bộ nhớ khi thực hiện
	 * 
	 * tuy nhiên mình chỉ cần 1 for để duyệt mảng thoy
	 * nên sẽ nhanh hơn nếu phải sài 2 for hoặc while
	 * lồng nhau
	 * 
	 *  cơ mà thoy kệ vẫn sài tốt =))
	 * 
	 * ý tưởng hàm là:
	 * tìm giá trị lớn nhất trong mảng (1 for)
	 * rồi thực hiện theo ý tưởng trên (1 for)
	 * 
	 * phần bài toán gốc được code trong hàm bai5_
	 */
	public static void bai5_(int[] array) {
		int sum = 0;
		for(int i:array) {
			sum += i;
		}
		int out = sum - (array.length-1)*array.length/2;
		System.out.println("giá trị bị lặp lại là: " + out);
		
	}
	public static void bai5(int[] array) {
		int max = array[0];
		for(int i:array) {
			if(i>max)
				max = i;
		}
		
		boolean[] visit = new boolean[max+1];
		ArrayList<Integer> save = new ArrayList<Integer>();
		
		for(int i:array) {
			if(visit[i]) {
				save.add(i);
			}
			visit[i] = true;
		}
		System.out.print("các giá trị bị lặp lại là: ");
		for(int i: save) {
			System.out.print(" "+i);
		}
		System.out.println();
	}
	public static void testbai5() {
		int[] ar = {1,2,3,4,5,4,6,7};
		System.out.println("***** bai 5.1: phan tu lap 1 2 3 4 5 4 6 7");
		bai5_(ar);
		
		int[] arr = {30,41,50,20,11,41,33,20,100,101,33};
		System.out.println("***** bai 5.2: phan tu lap 30 41 50 20 11 41 33 20 100 101 33");
		bai5(arr);
	}
	public static void bai6(int[] A,int sumtarget) {
		boolean none = true;// biến kiểm tra có tồn tại dãy hay không để in không tồn tại
		int c=0,d=0;
		for(int i=0;i<A.length;i++) {
			int sum = 0;
			
			for(int j=i;j<A.length;j++) {
				sum += A[j];
				if(sum == sumtarget && (j - i) > (d - c)) {
					none = false;
					c = i;
					d = j;
				}
			}
		}
		
		if(none) {
			System.out.println("khong ton tai day co tong bang "+sumtarget);
		}
		else {
			System.out.print("[ ");
			for(int k=c; k<=d ;k++) {
				System.out.print(A[k]+" ");
			}
			System.out.println("]");
		}
	}
	public static void testbai6() {
		int[] array = {1,2,-4,5,6,7,9,1,2,3,4,5,1,-1,1,5};
		System.out.println("***** bai 6.1: day co do dai lon nhat co tong bang 16 la");
		bai6(array,16);
		
	}
	/* đề là tìm dãy con lớn nhất có tổng các số 0 và số 1 bằng nhau
	 * 
	 * EZ dựa vào bài 2
	 * nếu là 1 thì tổng cộng 1 còn 0 thì tổng trừ 1 nếu tổng
	 * bằng 0 thì dãy có các số 0 và số 1 bằng nhau
	 * 
	 * thêm một cái mảng lưu lại mảng tìm được lớn nhất và
	 * cho kích thước mảng này thừa thêm 1 đơn vị để dùng 
	 * lưu kích thước mảng (vì không thích dùng mảng động)
	 * 
	 * đỡ phải thêm một biến int vào
	 * 
	 */
	public static void bai7(int[] A,int sumtarget,int[] answer) {
		boolean none = true;// biến kiểm tra có tồn tại dãy hay không để in không tồn tại
		for(int i=0;i<A.length;i++) {
			int sum = 0;
			for(int j=i;j<A.length;j++) {
				sum += (A[j] == 1) ? 1 : -1;
				if(sum == sumtarget && j - i+1 > answer[answer.length-1]) {
					none = false;
					for(int k=0;k<=j-i;k++) {
						answer[k]= A[i+k];
					}
					answer[answer.length-1] = j-i+1;
				}
			}
		}
		if(none) {
			System.out.println("khong ton tai day co tong bang "+sumtarget);
		}
		else {
			System.out.print("[");
			for(int i=0;i<answer[answer.length-1];i++) {
				System.out.print(" "+answer[i]);
			}
			System.out.println(" ]");
		}
	}
	public static void testbai7() {
		int[] A = {1,0,1,0,1,1,0,0,1,0,1,1,0,1,1,1,0};
		int[] answer = new int[A.length+1];
		System.out.println("***** bai 7.1: tim day lon nhat co tong 1 va 0 bang nhau");
		bai7(A,0,answer);
		int[] B = {1,1,1,1,1,1,1,1,1,1};
		answer = new int[A.length+1];
		System.out.println("***** bai 7.2: tim day lon nhat co tong 1 va 0 bang nhau ");
		bai7(B,0,answer);
	}
	public static void bai8(int[] A) {
		int max = 0;
		for(int i = 0;i<A.length-1;i++)
			for(int j=i+1;j<A.length;j++) {
				if(A[i]*A[j] > max) {
					max =A[i]*A[j]; 
			}
		}
		
		for(int i = 0;i<A.length-1;i++)
			for(int j=i+1;j<A.length;j++) {
				if(A[i]*A[j] == max) {
					System.out.println("[ "+A[i] +" " + A[j]+" ]"); 
			}
		}
	}
	public static void testbai8() {
		int[] A = {1,-9,-8,2,3,9,5};
		System.out.println("bai 8: tìm hai số nguyên có tích lớn nhất trong mảng");
		bai8(A);
		
	}
	public static void bai9(int[] input ,int a,int b) {
			if(a<b){
			int i=a+1,j=b;
			
			for(;i<j+1;i++){
				if(input[i]>input[a])
					for(;j>i;j--)
						{
						if(input[j]<input[a]) {
							swap(input,i,j);
							break;
							}
						}
				
				if(i==j) break;
			}
				
			if(input[a]<input[i]) i--;
			swap(input,a,i);
			
			bai9(input,a,i-1);
			bai9(input,i+1,b);
			}
	}
	public static void swap(int[] A,int a,int b) {
		int swap = A[a];
		A[a] = A[b];
		A[b] = swap;
	}
	public static void testbai9() {
		int[] A = {1,9,6,5,8,4,3,2,5,61,4,5,2,1,5,4,1,2,5,4,1,2,5,8,9,6,5,2,3,6,5,8,10,9,45,12,45,12,3,59,48};
		System.out.println("bai 9: quicksort");
		bai9(A,0,A.length-1);
		for(int i=0;i<A.length;i++)
			System.out.print(" "+A[i]);
	}
	/* bài 10 không nhập thành một mảng mà chia thành hai phần
	 * mảng A và B nên có thể mergesort vào mảng mergesortArray
	 * rồi tách mảng này cho vào A và B
	 * 
	 * bài 11 cũng dùng mergesort nhưng lược bỏ giá trị 0
	 * nên cũng ez
	 * 
	 */
	public static void bai10(int[] A,int[] B) {
		int[] mergeArray = new int[A.length+B.length]; // mảng sau khi nhập 2 mảng A và B
		int i=0;
		int j=0;
		int k=0;
		while(true) {
			if(j == B.length||i==A.length)
				break;
			if(A[i] < B[j]) {
				mergeArray[k++] = A[i++];
			}
			else {
				mergeArray[k++] = B[j++];
			}
		}
		if(A.length == i) {
			for(;j<B.length;j++)
				mergeArray[k++] = B[j];
		}
		else {
			for(;i<A.length;i++)
				mergeArray[k++] = A[i];
		}
		for(int l=0;l<mergeArray.length;l++)
			System.out.print(" "+mergeArray[l]);
	}
	public static void testbai10() {
		int[] A = {1,2,3,8,9,10,15,16,20};
		int[] B = {2,3,4,6,9,12,13,16,17,21};
		
		System.out.println("bai 10: mergesort");
		bai10(A,B);
	}
	/* đề : tìm vị trí của 0 để nếu thay 0 đó thành 1 thì được dãy
	 * có số lượng số 1 liên tiếp là lớn nhất
	 * 
	 * ý tưởng: cho nó một biến boolean kiểu true khi duyệt các
	 * số 1 liên tiếp mà gặp số 0 thì coi như nó là số 1 và boolean
	 * thành false 
	 * 
	 */
	public static void bai12(int[] A) {
		int index = 0;
		int max = 0;
		for(int i=0;i<A.length;i++) {
			boolean one = true; // biến cho phép thế giá trị 1 một lần
			int count = 0;
			int t = 0;// biến lưu lại giá trị tạm thời của chỉ số
			for(int j=i;j<A.length;j++) {
				if(A[j] == 1) {
					count ++;
				}
				else if(one) {
					one =false;
					count ++;
					t = j;
				}
				else break;
			}
			if(count > max ){
				max = count;
				index = t;
			}
		}
		System.out.println("index = "+index+"\nmax   = " + max);
	}
	public static void testbai12() {
		int[] data = {0,1,0,1,0,1,1,1,0,1,1,0,0,1,1,1,1,1,1,1,1,0};
		System.out.println("bai 12: tim vi tri so 0 de neu thay 1 thi day 1 lon nhat");
		bai12(data);
	}
	/* sáo trộn phần tử từ cuối cùng lên đầu thoy
	 * 
	 */
	public static void bai13(int[] A) {
		for(int i=A.length-1;i>0;i--) {
			int r = (int)(Math.random()*i);
			swap(A, i, r);
		}
	}
	public static void testbai13() {
		int[] A = {0,1,2,3,4,5,6,7,8,9};
		System.out.println("bai 13:");
		bai13(A);
		for(int i=0;i<A.length;i++)
			System.out.print(" "+A[i]);
		System.out.println();
		bai13(A);
		for(int i=0;i<A.length;i++)
			System.out.print(" "+A[i]);
		
	}
	/* dựa vào tổng dãy thoy
	 * 
	 */
	public static void bai15(int[] A) {
		int sum = 0;
		for(int i: A) {
			sum += i;
		}
		int left = 0;
		for(int i=0;i<A.length;i++) {
			if(left == sum - left - A[i]) {
				System.out.println("diem can bang la: "+ i);
			}
			left += A[i];
			
		}
	}
	public static void testbai15() {
		int[] data = {0,1,2,-3,0,4,5,-8,0,-1,0,3,2,-5,0};
		System.out.println("bai 15:");
		bai15(data);
		
	}
}
