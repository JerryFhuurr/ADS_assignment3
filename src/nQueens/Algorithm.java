package nQueens;

/*
    notes for myself:
    N 皇后问题是一个古老而著名的问题， 是回溯算法（back track）的典型案例。问题描述如下：

    N x N 的棋盘上放置N个皇后。 要求同一行中， 同一列中， 以及对角线上(包括正负对角线)只能有一个皇后， 否则就会发生clash的情况而失败。 问解决方案？

    解决思路如下：

    （1）逐列扫描， 从最左边的列开始， 总共有N个queens。

    （2）如果所有的queens 都被安全放置了， 就返回true。

    （3）对于给定的列， 尝试所有的行， 此时行是变量， 注意此时此列的左边的所有的列都放置了皇后， 此时我们只需要对当前列的所有行进行扫描，
    寻找放置的可行地行，所谓可行就是不会与左边的皇后产生clash， 条件为三个方向， 不同列（当然会满足）， 不同行， 不同上斜对角线， 不同下斜对角线。

    （4） 一旦放置好当前的列一个的皇后， 就进入下一列放置。 但是一旦没有产生可行解的时候， 就要回溯（backtrack）到上一列， 重新放置上一列的皇后， 继续重复上述步骤， 直至成功， 否则输出失败。

    计算方法：
    各皇后不能处于同一对角线位置：假设两皇后位置坐标分别为(i, j) 、（l, k），那么根据直线斜率公式：

    (i - l) / (j - k) = 1 求解得 i - l == j - k ①
    (i - l) / (j - k) = -1 求解得 i - l == k - j ②
    这两种情况出现时表明处于同一对角线，那么要满足摆放规则就必须满足
    | i - l | != | j - k |
     */

public class Algorithm {
    //number of queens
    private int queenNum;
    private int[] results;
    //the number of results
    private int sum = 0;
    private final boolean needFullResult;

    public Algorithm(int qN, boolean needFullResult) {
        this.queenNum = qN;
        this.needFullResult = needFullResult;
        this.results = new int[qN + 1];
        for (int i = 0; i < results.length; results[i++] = -1) ; //initial
        placeQueen(1);
    }

    private void placeQueen(int m) {
        if (m > queenNum) {
            //如果摆到了n+1行了，说明前n行都是不冲突的
            sum++;
            if (needFullResult){
                System.out.print(sum + " : ");
                for (int i = 1; i <= queenNum; i++) {
                    //每个数字的含义为每一行的第几列
                    //如：2413 -> 第一行第二列、第二行第四列、第三行第一列、第四行第三列
                    System.out.print(results[i]);
                }
                System.out.println();
            }
        }

        for (int i = 1; i <= queenNum; i++) {
            /*
            check the column is conflict with former ones or not
            if so, check the next column until find a non-conflict column
            or until the last column ,return;
             */
            if (isConflict(m, i)) {
                continue;
            } else {
                //如果检测到第i列不冲突，是安全的
                results[m] = i;//将皇后m放在第i列
                placeQueen(m + 1);//再放皇后m+1,
                //如果皇后m+1放完并返回了
                //两种可能：
                //1：冲突，返回了
                //2.一直将所有的皇后全部放完并安全返回了
                //将皇后m回溯，探索新的可能或者安全的位置
                results[m] = -1;
                //其实这里没必要将m重新赋值的，因为检测到下一个
                //安全位置的时候会把hash[m]覆盖掉的
                //但是为了更好地体现“回溯”的思想，在这里画蛇添足了
            }
        }
    }

    private boolean isConflict(int row, int column) {
        //一行一个皇后，第n个皇后也代表着第n行
        if (row == 1) {
            //第1行永远不会冲突
            return false;
        }
        //只需要保证与那些已经就位的皇后不冲突即可
        for (int i = 1; i < row; i++) {  //当前的行数
            if (results[i] == column || (column - row) == (results[i] - i) || (row - column) == (i - results[i])   //以前行数减列数与现在的是否相等
                    || (row + column) == (results[i] + i)) {
                return true;
            }
        }
        return false;
    }

    public int getSum() {
        return sum;
    }
}
