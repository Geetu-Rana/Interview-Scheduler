package main;

//Enter code here

import java.util.*;

class Main{
  
  public static void main(String[] args){
	  
	  Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      int m = sc.nextInt();
      
      String[][] mat = new String[n][m];
      sc.nextLine();
      for(int i=0; i<n; i++){
         mat[i] = sc.nextLine().split(" ");
        //  System.out.println(Arrays.toString(mat[i]));
      }
      
      for(int i=0; i<m; i++){
          boolean flag = false;
          for(int j=0; j<n; j++){
              String c = mat[j][i];
              if(c.equals("a")||c.equals("e")||c.equals("i")||c.equals("o")||c.equals("u")||
              c.equals("A")||c.equals("E")||c.equals("I")||c.equals("O")|| c.equals("U")){
                      flag =true;
                      break;
                  }
          }
          
          if(flag == true){
              System.out.println("Yes");
          }else{
              System.out.println("No");
          }
      }
  }

}
