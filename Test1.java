给定一个无序的整数数组，找到其中最长上升子序列的长度。
public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int[] arr=new int[n];
        int j=0;
        for(int i=0;i<n;i++){
            int left=0;
            int right=j;
            while(left<right){
                int mid=left+(right-left)/2;
                if(arr[mid]<nums[i]){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
            arr[left]=nums[i];
            if(left==j) j++;
        }
        return j;
    }
	
	给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

若这两个字符串没有公共子序列，则返回 0。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-common-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public int longestCommonSubsequence(String text1, String text2) {
        int m=text1.length();
        int n=text2.length();
        int[][] dp=new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
	
//最长公共子串
    public String longestCommon(String text1,String text2){
        int row=0;
        int col=text2.length()-1;
        int max=0;
        int end=0;
        while(row<text1.length()){
            int i=row;
            int j=col;
            int len=0;
            while(i<text1.length()&&j<text2.length()){
                if(text1.charAt(i)==text2.charAt(j)){
                    len++;
                }else{
                    len=0;
                }
                if(len>max){
                    max=len;
                    end=i;
                }
                i++;
                j++;
            }
            if(col>0){
                col--;
            }else{
                row--;
            }
        }
        return text1.substring(end-max+1,end+1);
    }
	
给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符
 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/edit-distance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public int minDistance(String word1, String word2) {
        int m=word1.length();
        int n=word2.length();
        int[][] dp=new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            dp[i][0]=i;
        }
        for(int i=0;i<=n;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
            }
        }
        return dp[m][n];
    }
	
给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
	public boolean isInterleave(String s1, String s2, String s3) {
        int m=s1.length();
        int n=s2.length();
        if((m+n)!=s3.length()) return false;
        boolean[][] dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=1;i<=m;i++){
            if(s1.charAt(i-1)==s3.charAt(i-1)){
                dp[i][0]=true;
            }else{
                break;
            }
        }
        for(int i=1;i<=n;i++){
            if(s2.charAt(i-1)==s3.charAt(i-1)){
                dp[0][i]=true;
            }else{
                break;
            }
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if((dp[i-1][j]&&s3.charAt(i+j-1)==s1.charAt(i-1))||
                    (dp[i][j-1]&&s3.charAt(i+j-1)==s2.charAt(j-1))){
                        dp[i][j]=true;
                    }
            }
        }
        return dp[m][n];
    }