package heap;

import java.util.*;

/**
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 *
 */
public class FindKPairsWithSmallestSums
{
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return rst;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(k, new Comparator<int[]>(){
            // Overwrite the compare method
            public int compare(int[] a, int[] b) {
                return (a[0] + a[1]) - (b[0] + b[1]);
            }
        });
        // Initialize the minHeap
        // when offer the next element, we need to know the curr index of nums2.
        // So the array is consist of: the element in nums1, the element in nums2, the curr element's index in nums2.
        for (int i = 0; i < k && i < nums1.length; i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        // poll the peak element (minElement currently), and offer the next element
        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            rst.add(Arrays.asList(cur[0], cur[1]));
            // check the index of nums2 is out of bound or not
            if (cur[2] == nums2.length - 1) {
                continue;
            }
            pq.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
        }

        return rst;
    }

    /**
     *  Another approach
     */
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k)
    {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                Pair p = new Pair(i, j, nums1[i] + nums2[j]);
                pq.add(p);
            }
        }
        List<List<Integer>> li = new ArrayList<>();
        for (int i = 0; i < Math.min(k, nums1.length * nums2.length); i++) {
            Pair p = pq.poll();
            ArrayList<Integer> nli = new ArrayList<>();
            nli.add(nums1[p.ind1]);
            nli.add(nums2[p.ind2]);
            li.add(nli);
        }
        return (li);
    }


    public static void main(String arg[]){
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        FindKPairsWithSmallestSums f = new FindKPairsWithSmallestSums();
        f.kSmallestPairs2(nums1, nums2, 3).forEach( e->{
            e.forEach(y -> System.out.print(y));
            System.out.println("");

        });
    }
}


class Pair implements Comparable<Pair>{
    int ind1;
    int ind2;
    int sum;
    public Pair(int ind1,int ind2,int sum){
        this.ind1=ind1;
        this.ind2=ind2;
        this.sum=sum;
    }

    public int compareTo(Pair o){
        return(this.sum-o.sum);
    }

}//pair
