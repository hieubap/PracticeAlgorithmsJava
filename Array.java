package Array;

import java.util.ArrayList;

public class Array {
	/* bài hữu ích:
	 * bài 1 có sử dụng đệ quy khá hữu ích (đã được đưa về dạng tổng quát
	 * 							để giải các bài tương tự)
	 * bài 2: có quan hệ với bài 1 khá hay
	 * 
	 * 
	 * bài dễ:
	 * bài 3 (đọc bài 1 là đủ)
	 * bài 4: sắp dãy nhị phân để các số 0 nằm hết về một phía
	 * 					EZ quá bỏ
	 * bài 5 (có 2 ý tưởng khá hay đọc chú thích để biết chi tiết =)) )
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
		testbai5();
		
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
}
