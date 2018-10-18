package algo.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i ++){
            Arrays.fill(board[i], '.');
        }
        List<List<String>> res = new LinkedList<>();
        dfs(board, res, 0, 0);
        return res;
    }

    public void dfs(char[][] boards, List<List<String>> res, int count, int row){
        int len = boards.length;
        for(int i = row; i < len; i ++){
            for(int j = 0; j < len; j ++){
                if(boards[i][j] == '.' && isValid(boards, i, j)){
                    boards[i][j] = 'Q';
                    dfs(boards, res, count+1, i);
                    boards[i][j] = '.';
                }
            }
        }
        if(count == len) {
            List<String> ans = new LinkedList<>();
            for (char[] b :
                    boards) {
                ans.add(new String(b));
            }
            res.add(ans);
        }
    }

    public boolean isValid(char[][] board, int row, int col){
        int len = board.length;
        for(int i = 0; i < len; i ++){
            if(board[i][col] == 'Q')return false;
            if(board[row][i] == 'Q')return false;
        }

        int i = row-1, j = col+1;
        while (i >= 0 && j < len){
            if(board[i--][j++] == 'Q')return false;
        }
        i = row+1;
        j = col-1;
        while (i < len && j >= 0){
            if(board[i++][j--] == 'Q')return false;
        }
        i = row+1;
        j = col+1;
        while (i < len && j < len){
            if(board[i++][j++] == 'Q')return false;
        }
        i = row-1;
        j = col-1;
        while (i >= 0 && j >= 0){
            if(board[i--][j--] == 'Q')return false;
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(new NQueens().solveNQueens(4).size());
    }



    List<List<String>> res = new ArrayList<List<String>>();
    public List<List<String>> solveNQueens2(int n) {
        if (n == 0) return res;
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                matrix[i][j] = '.';
            }
        nQueen(matrix, new boolean[n], new boolean[n * 2 - 1], new boolean[n * 2 - 1], 0);
        return res;
    }
    public void nQueen(char[][] matrix, boolean[] usedRow, boolean[] diagonalOne, boolean[] diagonalTwo, int col) {
        if (col >= matrix.length) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < matrix.length; i++) {
                list.add(String.valueOf(matrix[i]));
            }
            res.add(list);
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (!usedRow[i] && !diagonalOne[i+col] && !diagonalTwo[i-col+matrix.length-1]) {
                usedRow[i] = true;
                diagonalOne[i+col] = true;
                diagonalTwo[i-col+matrix.length-1] = true;
                matrix[i][col] = 'Q';

                nQueen(matrix, usedRow, diagonalOne, diagonalTwo, col + 1);

                matrix[i][col] = '.';
                diagonalOne[i+col] = false;
                diagonalTwo[i-col+matrix.length-1] = false;
                usedRow[i] = false;
            }
        }
    }
}
