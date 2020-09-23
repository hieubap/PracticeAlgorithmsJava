package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Array {
	/*
	 * bài 1 có sử dụng đệ quy khá hữu ích
	 * 
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
		testbai1();
		testbai2();
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
}
