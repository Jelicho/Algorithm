

import java.util.ArrayList;

class Solution {
    boolean cell[][][];
    final int col = 0;
    final int bo = 1;
    int n;
    boolean colOK(int x, int y){
        if(y==0)return true;
        else if(cell[x][y-1][col])return true;
        else if(cell[x][y][bo])return true;
        else if(x!=0&&cell[x-1][y][bo])return true;
        else return false;
    }
    boolean boOK(int x, int y){
        if(y==0)return false;
        else if(cell[x][y-1][col])return true;
        else if(x!=n&&cell[x+1][y-1][col])return true;
        else if(x!=0&&x!=n&&cell[x-1][y][bo]&&cell[x+1][y][bo])return true;
        else return false;
    }
    void deleteCol(int x, int y){
        if(!cell[x][y][col])return;
        cell[x][y][col]=false;
        if(
            (x!=0&&cell[x-1][y+1][bo]&&!boOK(x-1, y+1))
            || (cell[x][y+1][bo]&&!boOK(x,y+1))
            || (cell[x][y+1][col]&&!colOK(x, y+1))
        ){
            cell[x][y][col]=true;
            return;
        }else return;
    }
    void deleteBo(int x, int y){
        if(!cell[x][y][bo])return;
        cell[x][y][bo]=false;
        if(
            (x!=0&&cell[x-1][y][bo]&&!boOK(x-1, y))
            || (cell[x][y][col]&&!colOK(x,y))
            || (cell[x+1][y][bo]&&!boOK(x+1, y))
            || (cell[x+1][y][col]&&!colOK(x+1, y))
        ){
            cell[x][y][bo]=true;
            return;
        }else return;
    }
    public int[][] solution(int n, int[][] build_frame) {
        cell = new boolean[n+1][n+1][2];
        this.n = n;
        // System.out.println(build_frame.length);
        int loop_size = build_frame.length;
        int x,y;
        for(int idx = 0;idx<loop_size;idx++){
            x=build_frame[idx][0];
            y=build_frame[idx][1];
            if(build_frame[idx][3]==1){
                if(build_frame[idx][2]==col&&colOK(x,y))cell[x][y][col]=true;
                else if(build_frame[idx][2]==bo&&boOK(x,y))cell[x][y][bo]=true;
            }else{
                if(build_frame[idx][2]==col)deleteCol(x,y);
                else deleteBo(x,y);
            }
        }
        ArrayList<int[]> list = new ArrayList<int[]>();
        for(x=0;x<=n;x++){
            for(y=0;y<=n;y++){
                if(cell[x][y][0])list.add(new int[]{x,y,0});
                if(cell[x][y][1])list.add(new int[]{x,y,1});
            }
        }
        int[][] answer = new int[list.size()][3];
        for(int i=0;i<list.size();i++){
            for(int j=0;j<3;j++){
                answer[i][j]=list.get(i)[j];
            }
        }
        return answer;
    }
}