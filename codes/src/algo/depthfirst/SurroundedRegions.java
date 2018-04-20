package algo.depthfirst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurroundedRegions {
    public void solve(char[][] board) {
        if(board==null)return;
        int column = board[0].length;
        for(int i = 1;i < board.length-1; i ++){
            for(int j = 1; j < column; j ++){
                if(board[i][j] == 'x')continue;
                List<Integer> cached = new ArrayList<>();
                if(dfs(board, i*column+j, cached)){
                    for(int posi : cached){
                        int x = posi/column;
                        int y = posi%column;
                        board[x][y] = 'x';
                    }
                }
            }
        }
    }

    public boolean dfs(char[][] board, int val, List<Integer> cached){
        int row = board.length; int column = board[0].length;
        if(board[val/column][val%column] == 'X' || cached.contains(val))return true;
        System.out.println(val/column + " " + val%column);
        if(val/column == 0 || val/column == board.length-1 || val%column == 0 || val%column == board[0].length-1)
            return false;
        cached.add(val);
        int[] dirs = {-1, 1};
        for(int dir : dirs){
            if(!dfs(board, val + dir, cached))return false;
            if(!dfs(board, val + column*dir, cached))return false;
        }
        return true;
    }

    public void solve2(char[][] board) {
        if(board==null || board.length==0)return;
        int row = board.length;int colum = board[0].length;
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> status = new HashMap<>();
        for(int i = 0; i < row; i ++){
            for(int j = 0; j < colum; j ++){
                if(board[i][j] == 'X')continue;
                int index = i*colum + j;
                boolean flag = false;
                if(i==0 || j == 0 || i == row-1 || j == colum-1){
                    //status.put(index, -1);
                    flag = true;
                }
                int leftPar = -1;
                if(j > 0 && board[i][j-1] != 'X'){
                    leftPar = findParent(index-1, parent);
                }
                int topPar = -1;
                if(i > 0 && board[i-1][j] != 'X'){
                    topPar = findParent(index-colum, parent);
                }
                if(topPar != -1){
                    flag = flag || status.get(topPar)==-1;
                    parent.put(index, topPar);
                    if(leftPar != -1){
                        parent.put(leftPar, topPar);
                        flag =  flag|| status.get(leftPar)==-1;
                    }
                    if(flag)status.put(topPar, -1);
                }else if(leftPar != -1){
                    flag =  flag|| status.get(leftPar)==-1;
                    if(flag)status.put(leftPar, -1);
                    parent.put(index, leftPar);
                }else{
                    parent.put(index, index);
                    status.put(index, 1);
                    if(flag)status.put(index, -1);
                }
            }
        }
        for(int key : parent.keySet()){
            int par = findParent(key, parent);
            if(status.get(par) == 1){
                board[key/colum][key%colum] = 'X';
            }
        }
    }

    public int findParent(int index, Map<Integer, Integer> parent){
        if(parent.get(index) == index)return index;
        return findParent(parent.get(index), parent);
    }


    public void solve3(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        // first row
        for (int i=0; i<board[0].length; i++) if (board[0][i] == 'O') {
            //System.out.println("first row");
            dfs(board, 0, i);
        }
        // last row
        for (int i=0; i<board[0].length; i++) if (board[board.length-1][i] == 'O') {
            //System.out.println("last row");
            dfs(board, board.length-1, i);
        }
        // first col
        for (int i=1; i<board.length-1; i++)  if (board[i][0] == 'O') {
            //System.out.println("first col");
            dfs(board, i, 0);
        }
        // last row
        for (int i=1; i<board.length-1; i++) if (board[i][board[0].length-1] == 'O') {
            dfs(board, i, board[0].length-1);
            //System.out.println("last col");
        }

        for (int i=0; i< board.length; i++) {
            for (int j=0; j< board[0].length; j++) {
                if (board[i][j] == 'P') {
                    board[i][j] = 'O';
                    //System.out.println("changed");
                }
                else board[i][j] = 'X';
            }
        }
    }


    private void dfs(char[][] board, int i, int j) {
        if (i>=0 && j>=0 && i<board.length && j< board[0].length && board[i][j] == 'O') {
            //System.out.println("changing now");
            board[i][j] = 'P';
            dfs(board, i+1, j);
            dfs(board, i, j+1);
            dfs(board, i-1, j);
            dfs(board, i, j-1);
        }
    }

    public static void main(String[] args){
        char[][] data = {{'O','X','O','O','X','X'},{'O','X','X','X','O','X'},{'X','O','O','X','O','O'},{'X','O','X','X','X','X'}, {'O','O','X','O','X','X'}, {'X','X','O','O','O','O'}};
        new SurroundedRegions().solve2(data);
    }
}
