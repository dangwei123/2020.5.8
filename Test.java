斐波那契数列一系列问题
public class FabinoProblems {
    //求第n个斐波那契数
    public int nthFabino(int n){
        if(n<1) return 0;
        if(n==1||n==2) return 1;
        int f1=1;
        int f2=1;
        int f3=1;
        for(int i=3;i<=n;i++){
            f3=f1+f2;
            f1=f2;
            f2=f3;
        }
        return f3;
    }

    //跳台阶，一次只能跳一阶或两阶,跳n阶台阶一共有多少种方法
    public int swapFabnio(int n){
        if(n<1) return 0;
        if(n<3) return n;
        int f1=1;
        int f2=2;
        int f3=3;
        for(int i=3;i<=n;i++){
            f3=f1+f2;
            f1=f2;
            f2=f3;
        }
        return f3;
    }


    //统计每月的兔子：一开始有一只未成熟的兔子，兔子每到第三个月开始就成熟了，生一只兔子
    public int numOfRabbit(int n){
        if(n<3) return 1;
        if(n==3) return 2;
        return numOfRabbit(n-1)+numOfRabbit(n-2);
    }

    //统计每年的牛：一开始有一只成熟的牛，牛出生3年后就成熟了，之后每年都生一只牛
    public int numOfNow(int n){
        if(n<4) return 1;
        if(n==4) return 2;
        return numOfNow(n-1)+numOfNow(n-3);
    }
}


给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。
class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        if(m==0) return 0;
        int n=grid[0].length;
        int[][] dp=new int[m][n];
        dp[0][0]=grid[0][0];
        for(int i=1;i<m;i++){
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }
        for(int i=1;i<n;i++){
            dp[0][i]=dp[0][i-1]+grid[0][i];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
	
	//优化：空间O(M)
    public int minPathSum1(int[][] grid) {
        int m=grid.length;
        if(m==0) return 0;
        int n=grid[0].length;
        int big=Math.max(m,n);
        int small=Math.min(m,n);
        boolean down=n==small;
        int[] dp=new int[small];
        dp[0]=grid[0][0];
        for(int i=1;i<small;i++){
            dp[i]=dp[i-1]+(down?grid[0][i]:grid[i][0]);
        }
        for(int i=1;i<big;i++){
            dp[0]=dp[0]+(down?grid[i][0]:grid[0][i]);
            for(int j=1;j<small;j++){
                dp[j]=Math.min(dp[j-1],dp[j])+(down?grid[i][j]:grid[j][i]);
            }
        }
        return dp[small-1];
    }
}

在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m=matrix.length;
        if(m==0) return 0;
        int n=matrix[0].length;
        int[][] dp=new int[m+1][n+1];
        int res=0;
        for(int i=0;i<m;i++){
            dp[i][0]=matrix[i][0]-'0';
            if(dp[i][0]==1) res=1;
        }
        for(int i=0;i<n;i++){
            dp[0][i]=matrix[0][i]-'0';
            if(dp[0][i]==1) res=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j]=='1'){
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    res=Math.max(res,dp[i][j]);
                }
            }
        }
        return res*res;
    }
}


给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public int coinChange(int[] coins, int amount) {
        int n=coins.length;
        int[] dp=new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0]=0;
        for(int i=0;i<n;i++){
            for(int j=coins[i];j<=amount;j++){
                dp[j]=Math.min(dp[j-coins[i]]+1,dp[j]);
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }
	
	给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
	public int change(int amount, int[] coins) {
        int[] dp=new int[amount+1];
        dp[0]=1;
        for(int i=0;i<coins.length;i++){
            for(int j=coins[i];j<=amount;j++){
                
                dp[j]+=dp[j-coins[i]];
            }
        }
        return dp[amount];
    }
	
	