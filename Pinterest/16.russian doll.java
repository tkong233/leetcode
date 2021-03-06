//555ms 20%
// O(n^2)
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
        int len = envelopes.length;
        Arrays.sort(envelopes, (int[] a, int[] b) -> a[0] - b[0]);
        // 把上面这个lambda表达式 改一下 写成正常的new compartor 会快一点 记得Comparator要大写
        // 467ms 30%
        // Arrays.sort(envelopes, new Comparator<int[]>(){
        //     public int compare(int[] a, int[] b) {
        //         if(a[0] > b[0]) return 1;
        //         else if (a[0] < b[0]) return -1;
        //         else return a[1]-b[1];
        //     }
        // });
        int[] dp = new int[len];
        int res = 0;
        for(int i = 0; i < len; i++) {
            int temp = 1;
            for(int j = 0; j < i; j++) {
                if((envelopes[i][0] > envelopes[j][0]) && (envelopes[i][1] > envelopes[j][1]))
                    temp = Math.max(temp, dp[j]+1);
            }
            dp[i] = temp;
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}

// 20ms 76%
// O(n log n)
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
        int len = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if(a[0] > b[0]) return 1;
                else if (a[0] < b[0]) return -1;
                else return b[1]-a[1];
            }
        });
        int[] dp = new int[len];
        int size = 0;
        for(int[] e: envelopes) {
            int index = Arrays.binarySearch(dp,0,size,e[1]);
            if(index < 0) index = -(index+1);
            dp[index] = e[1];
            if(index == size) size++;
        }
        return size;
    }
}