package www.xiaoyang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class declareWinner {
	public static String run(Fighter fighter1,Fighter fighter2,String name){
		/*Fighter a=fighter1, b=fighter2;
	    if (name.equals(fighter2.name)) {
	      a = fighter2; b = fighter1;
	    }    
	    while (true) {      
	      if ((b.health -= a.damagePerAttack) <= 0) return a.name;  // a wins
	      if ((a.health -= b.damagePerAttack) <= 0) return b.name;  // b wins
	    }*/
		
		String name1 = fighter1.name;
		String name2 = fighter2.name;

		String result = "";
		
		if(name.equals(name1)){
			while(true){
				fighter2.health = fighter2.health - fighter1.damagePerAttack;
				if(fighter2.health < 1){
					result = name1;
					break;
				}
				fighter1.health = fighter1.health - fighter2.damagePerAttack;
				if(fighter1.health < 1){
					result = name2;
					break;
				}
			}
		}else{
			while(true){
				fighter1.health = fighter1.health - fighter2.damagePerAttack;
				if(fighter1.health < 1){
					result = name2;
					break;
				}
				fighter2.health = fighter2.health - fighter1.damagePerAttack;
				if(fighter2.health  < 1){
					result = name1;
					break;
				}
			}
		}
		return result;
	}
	
	 public static int persistence2(long n) { //使用递归
		    long m = 1, r = n;

		    if (r / 10 == 0)
		      return 0;

		    for (r = n; r != 0; r /= 10)
		      m *= r % 10;

		    return persistence2(m) + 1;
		    
		  }
	
	public static int persistence(long n) {
		// your code
	    int count = 0;
	   Map<Boolean,Integer> map = runSum(n);
	   while(true){
	     if(map.containsKey(true)){
	       count++;
	     }else{
	       return count;
	     }
	     map = runSum(map.get(true));
	   }
	}
  
  private static Map<Boolean,Integer> runSum(long n){
    int length = String.valueOf(n).length();
    
    if(length == 1){
    	Map<Boolean, Integer> map = new HashMap<Boolean, Integer>();
    	map.put(false, 0);
      return map;
    }
    int sumA = 1;
    for(int i=0;i<length;i++){
      sumA *= (n/(int)Math.pow(10,i)) % 10;
    }
    Map<Boolean, Integer> map = new HashMap<Boolean, Integer>();
	map.put(true, sumA);
    return map;
  } 
  
  public static int[] race(int v1, int v2, int g) {
      // your code
      if(v1 >= v2){
        return null;
      }else{
	      int[] value = new int[3];
	     /* value[0] = (int)Math.floor(g / (v2 - v1));
	      value[1] = (int)Math.floor( ((double)g / (v2 -v1) - Math.floor(g / (v2 -v1))) *60 ) ;
	      double s = ((double)g / (v2 -v1) - Math.floor(g / (v2 -v1))) *60;
	      double s1 = Math.floor( ((double)g / (v2 -v1) - Math.floor(g / (v2 -v1))) *60 );
	      value[2] = (int)Math.floor( (s-s1) * 60 );*/
	      double time = (double)g / (v2 - v1);
	      value[0] = (int)Math.floor( time);
	      double mo = time - value[0];
	      value[1] = (int)Math.floor( mo * 60 );
	      double sc = (double)(mo * 60 - value[1]) ;
	      value[2] = (int)Math.floor( sc * 60 );
	       
	      return value;
      }
  }
	
  public static List<Integer> sumConsecutives(List<Integer> s) {
      // [1,4,4,4,0,4,3,3,1] # should return [1,12,0,4,6,1]
	  //[1, 12, 0, 4, 6, 1]
      List<Integer> result_list = new ArrayList<Integer>();
      int i = 0;
      for (i = 0; i < s.size(); i++) {
		int su = s.get(i);
		for (int j = i+1; j < s.size(); j++) {
				if( s.get(i) == s.get(j)){
					su += s.get(j);
				}else{
					i = j-1;
					break;
				}	
		}
		result_list.add(su);
      }
      return result_list;
  }
  
  public static List<Integer> sumConsecutives2(List<Integer> s) {
      int previous = Integer.MAX_VALUE;
      LinkedList<Integer> l = new LinkedList<Integer>();
      for (Integer v: s){
         l.add(v == previous? l.pollLast() + v : v); 
         previous = v;
      }
      return l; 
    }
  
  
  public static String abbreviate(String string) {
	    // abbreviate("elephant-rides are really fun!")   internationalization
	    //"e6t-r3s are r4y fun!"  i18n
	    if(string.length() <= 3){
	      return string;
	    }else{
	      String [] str = string.split(" ");
	      String result = "";
	      if(str.length > 1){
	         for(String s : str){
	           if(s.length() <= 3){
	        	   result += s+" ";
	           }
	           if(s.indexOf("!") != -1){
	        	   s = s.substring(0, s.length()-1);
	        	   if(s.length() <= 3){
	        		   result += s+"!";
	        	   }else{
	        		   result += s.substring(0,1)+s.valueOf(string.length()-2)+s.substring(s.length()-1,s.length())+" ";
	        	   }
	           }
	           if(s.length() > 3 && s.indexOf("!") == -1){
	        	   String [] s2 = s.split("-");
	        	   if(s2.length > 1){
	        		   for (String string2 : s2) {
	        			   result += string2.substring(0,1)+string2.valueOf(string.length()-2)+string2.substring(string2.length()-1,string2.length())+"-";
					}
	        	   }else{
	        		   result += s.substring(0,1)+s.valueOf(string.length()-2)+s.substring(s.length()-1,s.length())+" ";
	        	   }
	           }
	         }
	      }else{
	        return string.substring(0,1)+String.valueOf(string.length()-2)+string.substring(string.length()-1,string.length());
	      }
	      return result;
	    }
	  }
  
	public static void main(String[] args) {
//		List<Integer> i = Arrays.asList(1,4,4,4,0,4,3,3,1);
//		System.out.println(sumConsecutives(i));
		
//		String string = "internationalization";
//		//"e6t-r3s are r4y fun!"
//		//e28t-r28s-are r28y fun!
//		
//		string = "elephant-rides are really fun!";
//		String re = abbreviate(string);
//		System.out.println(re);
		
//		String s = "codewars";
//		for (int i = 0; i < s.length(); i++) {
//			String s2 = s.substring(i, i+1);
//			System.out.println(s2);
//		}
		
			String s2 = "codewars";
	       String p = "code" + "wars";
	       boolean res = false;
	       if(s2.length() == p.length()){
	    	   for(int i= 0;i<p.length();i++){
	  	         String s3 = p.substring(i,i+1);
	  	         if( s2.indexOf( s3 ) != -1 ){
	  	        	res = true;
	  	         }
	  	       }
	       }
	      
	       System.out.println(res);
	       
		
		
		//System.out.println(run(new Fighter("xiaoyang",12,3), new Fighter("lisi",8,4), "xiaoyang"));
		
		//System.out.println(persistence(25));
		/*int ss[] = race(80, 100, 40);
		System.out.println(ss[0]);
		System.out.println(ss[1]);
		System.out.println(ss[2]);*/
		
//		System.out.println((int)Math.floor(70 / (850 -720)));
//		
//		System.out.println((int)Math.floor( ((double)70 / (850 -720) - Math.floor(70 / (850 -720))) *60 ));
		
		
		//System.out.println(a);
		
		//System.out.println(String.valueOf(10).length());
		
		/*long sum = 999;
		int length = String.valueOf(sum).length();
		System.out.println(Math.pow(4, 2));
		int sumA = 1;
		for(int i=0;i<length;i++){
			sumA *= (sum/(int)Math.pow(10,i)) % 10;
		}
		System.out.println(sumA);*/
	}
}
