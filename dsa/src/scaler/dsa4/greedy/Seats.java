package scaler.dsa4.greedy;


/*

Problem Description

There is a row of seats represented by string A. Assume that it contains N seats adjacent to each other.
There is a group of people who are already seated in that row randomly. i.e. some are sitting together & some are scattered.





An occupied seat is marked with a character 'x' and an unoccupied seat is marked with a dot ('.')

Now your target is to make the whole group sit together i.e. next to each other, without having any vacant seat between them in such a way that the total number of hops or jumps to move them should be minimum.

In one jump a person can move to the adjacent seat (if available).

NOTE: 1. Return your answer modulo 107 + 3.







Problem Constraints

1 <= N <= 1000000
A[i] = 'x' or '.'



Input Format

The first and only argument is a string A of size N.



Output Format

Return an integer denoting the minimum number of jumps required.



Example Input

Input 1:

 A = "....x..xx...x.."
Input 2:

 A = "....xxx"


Example Output

Output 1:

 5
Output 2:

 0


Example Explanation

Explanation 1:

 Here is the row having 15 seats represented by the String (0, 1, 2, 3, ......... , 14)
                 . . . . x . . x x . . . x . .
 Now to make them sit together one of approaches is -
                 . . . . . . x x x x . . . . .
 Steps To achieve this:
 1) Move the person sitting at 4th index to 6th index: Number of jumps by him =   (6 - 4) = 2
 2) Bring the person sitting at 12th index to 9th index: Number of jumps by him = (12 - 9) = 3
 So, total number of jumps made: 2 + 3 = 5 which is the minimum possible.

 If we other ways to make them sit together but the number of jumps will exceed 5 and that will not be minimum.

Explanation 2:

 They are already together. So, the cost is zero.


 TODO: **Trick: Store the position of all the occupied seats in arr. Now find the median of this array and towards both  side of this array try to bring all occupied together**

 */


import java.util.ArrayList;
import java.util.List;

public class Seats {

    public int seats(String A) {
        long mod = 10000003;
        int first = -1;
        int last = -1;
        int sz = A.length();
        List<Integer> idxs = new ArrayList<>();
        for(int i = 0; i < sz; i++) {
            if(A.charAt(i) == 'x') {
                idxs.add(i);
                if(first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        if(first == -1) {
            return 0;
        }
        int mid = idxs.get(idxs.size()/2);

        int left = mid-1;
        int right = mid+1;

        // System.out.print(first + " " + last + " " + left + " " + right + " ");

        long ans = 0;

        while(left > first){
            while(left > first && A.charAt(left) == 'x') {
                left--;
            }
            while(first < left && A.charAt(first) == '.') {
                first++;
            }
            ans += left-first;
            left--;
            first++;
            ans = ans%mod;
        }

        while(right < last){
            while(right < last && A.charAt(right) == 'x') {
                right++;
            }
            while(right < last && A.charAt(last) == '.') {
                last--;
            }
            ans += last-right;
            last--;
            right++;
            ans = ans%mod;
        }


        return (int)ans;
    }


    class Solution {
        public int seats(String A) {
            long count = 0;
            int n = A.length();
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i=0; i<n; i++){
                if(A.charAt(i)=='x'){
                    arr.add(i);
                }
            }
            int median = arr.size()/2;
            for(int i=0; i<arr.size(); i++){
                /*
                a = (Math.abs(arr.get(median)-arr.get(i))-1) => calculates the number of char(. or x) present between median and i
                b = (Math.abs(median-i)-1) => calculates the number of 'x' present between median and i;
                and finally subtracts a-b => which ultimately gives the number of '.' between median and i;
                 */
                long ans = (Math.abs(arr.get(median)-arr.get(i))-1) - (Math.abs(median-i)-1);
                count += ans;
            }
            return (int)(count%10000003);
        }
    }


}
