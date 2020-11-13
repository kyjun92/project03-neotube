package com.mega.mvc01;

import java.util.Random;



public class RandomVideo {
	
	public String[] id() {
		Random a = new Random();
		String v1[] = { "-1H7s3KkTXc", "-9ObkvtSb7g", "-rx_pSt7OG4", "-uVocM3Mcac", "-vRHDIHSi6E", "1DrvrxEdBLs",
				"1oQrHWm3ntM", "29Yvxtx6iIs", "2euA63uNQTQ", "2i7CdZa13yw", "2Is_F7scJ30", "2uKoznV0Z5c", "3D42yBGemTA",
				"5hAKL88OGQI", "5mNUn_KowCQ", "5pzMuSH4Zr8", "65pljTszuvY", "6drN74Gy8IA", "8CbZN4iFYUQ", "8ZSATlFyn9k",
				"9-OoIXj0EyI", "92_L9ejxW1k", "97qObTY_Kik", "9D_5KGCn2Es", "9kXoTQrv8fY", "A84UUXLsAm0", "aYzL9HQOgjY",
				"B0dYzIULeds", "bpZKXNboyoI", "b_FavH1dfY4", "cCBgfjvEPFY", "cj-02I_v960", "cRuiVUO290g", "DPpZAa9cMPU",
				"ENQv2uH2PcQ", "EWFgMBcUlSg", "ewyRaxdFZcQ", "FqL_O8GNoHE", "fSZ91E0FQ84", "GZV5hsEaMGQ", "Hozv2RBGbQ4",
				"hQYIxyhvh-4", "hU-cOvkZsjk", "IgvOIEWqV-Q", "iJ5FENS0qTE", "jAaPDzYCVzA", "jJEto5ATn8c", "JVPB8ZxXSm4",
				"Ka6cgYojUjc", "kDisy5usXIA", "Km7nA3SoWw8", "kye2PSFzLo0", "l3wTEqtm1NA", "LjEn80hP6vM", "lM-LYpqD44E",
				"MoLHQOZnSto", "Mu4CWfoJa5s", "m_Xcl-Kgs88", "nbFGla48qVI", "o1rBTqsio84", "O24w5VDtWYo", "oaIbukSHbYA",
				"Pk8T-JYqJFE", "QF9US7nV0xo", "rlyIbSqCVFw", "RN8ZzLdo6ks", "sHHBE46wtr0", "sIriTIIhhts", "snGeeQFj0io",
				"SVzl6G3zxgQ", "sXBMOkgdSnk", "tD8z6Dcnn0U", "tuKQZabybkI", "TuWsf5CcCks", "UHKSrQImAFY", "uI8D8Za1zfw",
				"uttOy9Qy6jI", "V-fjIfaMGjY", "vCe1YnQyUEc", "VHPbhQYasv8", "vhPFNR8DsMw", "vIcfMN47U8I", "Vs6mrh9OTNo",
				"vu7KcVmV9is", "wiGJ9wgAk0g", "xb74UqH43mc", "XEv9B7g3uiQ", "Xn-tXgoWN3U", "XRKbzKPaUWs", "xWtB9RaCgcM",
				"YBLp8LPyhns", "ydFi8IXe8lY", "zbSP6_JGWEg", "Zfm2YAtqf1s", "zjry32y4cOE", "Zna-5OdJTEw", "zZpnkSniOhg",
				"_PmR8rNjaOA", "_q4LSXuAQbg", "_XVCeumpfG8" };
		double b = 0;
		int c = 0;
		String[] v2 = new String[1000];
		
		for (int i = 0; i < 1000; i++) {
			b = a.nextGaussian() * 16 + 50;
			if (b > 100 || b < 1) {
				continue;
			}
			c = (int) b;
			v2[i] = v1[c];
			System.out.println(v2[i]);
		}

		return v2;
	}
//	public static void main(String[] args) {
//		RandomVideo r = new RandomVideo();
//		String[] aa = r.id();
//		for (int i = 0; i < aa.length; i++) {
//			System.out.println(aa[i]);
//		}
//		
//	}
}
