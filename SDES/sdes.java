import java.util.Scanner;

public class sdes{
	public static String[][] S0={{"01","00","11","10"},{"11","10","01","00"},{"00","10","01","11"},{"11","01","11","10"}};
	public static String[][] S1={{"00","01","10","11"},{"10","00","01","11"},{"11","00","01","00"},{"10","01","00","11"}};
	public static int[] p10={2,4,1,6,3,9,0,8,7,5};
	public static int[] p8={5,2,6,3,7,4,9,8};
	public static int[] IP={1,5,2,0,3,7,4,6};
	public static int[] IPI={3,0,2,4,6,1,7,5};
	public static int EP[]={3,0,1,2,1,2,3,0};
	public static int p4[]={1,3,2,0};
	public static String ls1(String bitString){
		char c=bitString.charAt(0);
		String result="";
		for(int i=1;i<bitString.length();++i){
			result+=bitString.charAt(i);
		}
		result+=c;
		return result;
	}
	public static String XOR(String bitString1,String bitString2){
		String result="";
		for(int i=0;i<bitString1.length();++i){
			if(bitString1.charAt(i)==bitString2.charAt(i)){
				result+="0";
			}
			else 
				result+="1";
		}
		return result;
	}
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int[] key=new int[10];
		System.out.println("Enter key:");
		String tmp=s.next();
		for(int i=0;i<10;++i){
			key[i]=tmp.charAt(i)-48;
		}
		
		String p10key="";
		for(int i=0;i<10;++i){
			p10key+=key[p10[i]];
		}
		//p10key
		System.out.println("p10key:"+p10key);
		String L=ls1(p10key.substring(0,5));
		String R=ls1(p10key.substring(5,10));
		//lsL & lsR
		System.out.println("L+R:"+L+R);
		
		String LR=L+R,k1="";
		for(int i=0;i<8;++i){//apply p8
			k1+=LR.charAt(p8[i]);
		}
		//k1 
		System.out.println("k1:"+k1);
		LR=ls1(ls1(L))+ls1(ls1(R));
		String k2="";
		for(int i=0;i<8;++i){//apply p8
			k2+=LR.charAt(p8[i]);
		}
		//k2 
		System.out.println("k2:"+k2);
		System.out.println("Enter bit block to encrypt");
		String input=s.next();
		
		String IPp="";
		for(int i=0;i<8;++i){
			IPp+=input.charAt(IP[i]);
		}
		//IPp 10101001
		System.out.println(IPp);
		L=IPp.substring(0,4);
		R=IPp.substring(4,8);
		String orgR=R;
		//L & R
		System.out.println("L & R:"+L+R);
		
		//expand & permute R 11000011
	    tmp="";
		for(int i=0;i<8;++i){
			tmp+=R.charAt(EP[i]);
		}
		R=tmp;
		System.out.println("Expanded & permutated R:"+R);
		//XOR 11000011
		String k1XORr=XOR(R,k1);
		System.out.println("k1XORr:"+k1XORr);
		String S0L=k1XORr.substring(0,4);
		String S1R=k1XORr.substring(4,8);
		int row=Integer.parseInt(""+S0L.charAt(0)+S0L.charAt(3),2);
		int col=Integer.parseInt(""+S0L.charAt(1)+S0L.charAt(2),2);
		S0L=S0[row][col];
		//System.out.println("Row:"+row+" Col:"+col);
		System.out.println("S0L:"+S0L);
		row=Integer.parseInt(""+S1R.charAt(0)+S1R.charAt(1),2);
		col=Integer.parseInt(""+S1R.charAt(2)+S1R.charAt(3),2);
		//System.out.println("Row:"+row+" Col:"+col);
		S1R=S1[row][col];
		System.out.println("S1R:"+S1R);
		String S0S1=S0L+S1R;
		
		tmp="";
		for(int i=0;i<4;++i){ //p4 permute
			tmp+=S0S1.charAt(p4[i]);
		}
		R=tmp;
		System.out.println("p4R:"+R);
		//LXORr
		L=XOR(L,R);
		System.out.println("LXORr:"+L);
		//sw
		R=L;
		L=orgR;
		String sw=L+R;
		System.out.println("sw:"+sw);
		tmp="";
		for(int i=0;i<EP.length;++i){
			tmp+=R.charAt(EP[i]);
		}
		R=tmp;
		System.out.println("EPr:"+R);
		//XOR
		String k2XORr=XOR(k2,R);
		System.out.println("k2XORr:"+k2XORr);
		 S0L=k2XORr.substring(0,4);
		 S1R=k2XORr.substring(4,8);
		row=Integer.parseInt(""+S0L.charAt(0)+S0L.charAt(3),2);
		col=Integer.parseInt(""+S0L.charAt(1)+S0L.charAt(2),2);
		S0L=S0[row][col];
		//System.out.println("Row:"+row+" Col:"+col);
		System.out.println("S0L:"+S0L);
		row=Integer.parseInt(""+S1R.charAt(0)+S1R.charAt(1),2);
		col=Integer.parseInt(""+S1R.charAt(2)+S1R.charAt(3),2);
		//System.out.println("Row:"+row+" Col:"+col);
		S1R=S1[row][col];
		System.out.println("S1R:"+S1R);
		R=S0L+S1R;
		//p4
		tmp="";
		for(int i=0;i<p4.length;++i){
			tmp+=R.charAt(p4[i]);
		}
		R=tmp;
		System.out.println("p4R:"+R);
		R=XOR(R,sw.substring(0,4));
		System.out.println("swlXORr:"+R);
		LR=R+sw.substring(4,8);
		tmp="";
		for(int i=0;i<IPI.length;++i){
			tmp+=LR.charAt(IPI[i]);
		}
		LR=tmp;
		System.out.println("cipher text:"+LR);
	}
}
