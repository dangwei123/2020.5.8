一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。

骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。

有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。

为了尽快到达公主，骑士决定每次只向右或向下移动一步。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/dungeon-game
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m=dungeon.length;
        int n=dungeon[0].length;
        int[][] dp=new int[m][n];
        dp[m-1][n-1]=dungeon[m-1][n-1]>0?1:1-dungeon[m-1][n-1];
        for(int i=m-2;i>=0;i--){
            dp[i][n-1]=Math.max(1,dp[i+1][n-1]-dungeon[i][n-1]);
        }
        for(int i=n-2;i>=0;i--){
            dp[m-1][i]=Math.max(1,dp[m-1][i+1]-dungeon[m-1][i]);
        }
        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                dp[i][j]=Math.max(1,Math.min(dp[i+1][j],dp[i][j+1])-dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}

给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public int translateNum(int num) {
        String s=String.valueOf(num);
        int n=s.length();
        if(n==1) return 1;
        int[] dp=new int[n];
        dp[0]=1;
        if(s.substring(0,2).compareTo("26")>=0){
            dp[1]=1;
        }else{
            dp[1]=2;
        }
        for(int i=2;i<n;i++){
            if(s.charAt(i-1)=='0'||s.substring(i-1,i+1).compareTo("26")>=0){
                dp[i]=dp[i-1];
            }else{
                dp[i]=dp[i-1]+dp[i-2];
            }
        }
        return dp[n-1];
    }
	
博弈问题
public class Winner {
    public int winner(int[] arr){
        int n=arr.length;
        int[][] first=new int[n][n];
        int[][] second=new int[n][n];
        for(int i=0;i<n;i++){
            first[i][i]=arr[i];
        }
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                first[i][j]=Math.max(arr[i]+second[i+1][j],arr[j]+second[i][j-1]);
                second[i][j]=Math.min(first[i+1][j],first[i][j-1]);
            }
        }
        return Math.max(first[0][n-1],second[0][n-1]);
    }
}


N皇后
private int count;
    public int totalNQueens(int n) {
        char[][] board=new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i],'.');
        }
        back(board,n,0);
        return count;
    }
    private void back(char[][] board,int n,int row){
        if(row==n){
            count++;
            return;
        }
        for(int i=0;i<n;i++){
            if(isValid(board,row,i,n)){
                board[row][i]='Q';
                back(board,n,row+1);
                board[row][i]='.';
            }
        }
    }
    private boolean isValid(char[][] board,int x,int y,int n){
        for(int i=0;i<n;i++){
            if(board[i][y]=='Q') return false;
        }
        for(int i=x-1,j=y-1;i>=0&&j>=0;i--,j--){
            if(board[i][j]=='Q') return false;
        }
        for(int i=x+1,j=y-1;i<n&&j>=0;i++,j--){
            if(board[i][j]=='Q') return false;
        }
        for(int i=x-1,j=y+1;i>=0&&j<n;i--,j++){
            if(board[i][j]=='Q') return false;
        }
        for(int i=x+1,j=y+1;i<n&&j<n;i++,j++){
            if(board[i][j]=='Q') return false;
        }
        return true;
    }
	
	public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            String[] arr=new String[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.next();
            }
            Arrays.sort(arr, (a, b) -> {
                if(a.length()!=b.length()){
                    return a.length()-b.length();
                }
                return a.compareTo(b);
            });
            for(int i=0;i<n;i++){
                System.out.println(arr[i]);
            }
        }
    }
	
	
奇偶校验
private static void check(String s){
        for(int i=0;i<s.length();i++){
            int num=s.charAt(i);
            StringBuilder sb=new StringBuilder();
            int count=0;
            while(num!=0){
                int bit=(num&1);
                sb.append(bit);
                if(bit==1) count++;
                num>>=1;
            }
            while(sb.length()<7)sb.append("0");
            if((count&1)==0) sb.append("1");
            else sb.append("0");
            System.out.println(sb.reverse());
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s=sc.next();
            check(s);
        }
    }