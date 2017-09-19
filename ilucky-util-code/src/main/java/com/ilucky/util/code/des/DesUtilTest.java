package com.ilucky.util.code.des;

public class DesUtilTest {
	
	public static void main(String[] args) throws Exception {
		DesUtil des = new DesUtil();
		System.out.println(des.encrypt("{\"account_id\":107,\"target_id\":\"1958463315108856\",\"uuid\":\"d45395f4-735a-436a-aaff-eab8d8f460f4\"}"));
		System.out.println(des.decrypt("J45Engw88NclmszFDvz22jXEyTqb6p2mPKyPuLFoEAMwKwXL7w6stSF32mxcgLK3EJCeeFJIb**O8k0cQaKjq**U4JOJppC9EdJDjNjxOtSwuIbp05Yt**RwP3V8CpRkOnA"));
		
		System.out.println(des.encrypt("{\"account_id\":107\"}"));
		System.out.println(des.decrypt("J45Engw88NclmszFDvz22qDAhES4B8zq"));
	} 
}
/**
J45Engw88NclmszFDvz22jXEyTqb6p2mPKyPuLFoEAMwKwXL7w6stSF32mxcgLK3EJCeeFJIb**O8k0cQaKjq**U4JOJppC9EdJDjNjxOtSwuIbp05Yt**RwP3V8CpRkOnA
{"account_id":107,"target_id":"1958463315108856","uuid":"d45395f4-735a-436a-aaff-eab8d8f460f4"}
J45Engw88NclmszFDvz22qDAhES4B8zq
{"account_id":107"}
*/