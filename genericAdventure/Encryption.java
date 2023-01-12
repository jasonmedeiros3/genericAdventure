package genericAdventure;

import java.io.BufferedReader;
import java.io.FileReader;

public class Encryption {
	public static String encrypt(String input) throws Exception {
		/*====================================================================
		|  String encrypt(String input)    									 |
		|--------------------------------------------------------------------|
		|  returns encrypted - A string representing the encrypted version of|
		|  the input string										       		 |
		|--------------------------------------------------------------------|
		|  String input - This parameter is the string to be encrypted  	 |
		|--------------------------------------------------------------------|
		|  Encrypts a string of any length using the encryptionKey file as a |
		|  reference														 |
		====================================================================*/
		char[] inputArray=input.toCharArray();
		String encrypted="";
		String s;
		for(char c:inputArray) {
			BufferedReader br=new BufferedReader(new FileReader("encryptionKey.txt"));
			while((s=br.readLine())!=null) {
				s.trim();
				if(c==s.charAt(0)) {
					encrypted+=s.substring(1);
				}
				encrypted.trim();
			}
		}
		return encrypted;
	}
	public static String encryptln(String input) throws Exception {
		/*====================================================================
		|  String encryptln(String input)    								 |
		|--------------------------------------------------------------------|
		|  returns encrypted - A string representing the encrypted version of|
		|  the input string, plus a newline character			      		 |
		|--------------------------------------------------------------------|
		|  String input - This parameter is the string to be encrypted  	 |
		|--------------------------------------------------------------------|
		|  Calls the encryption algorithm to encrypt a string, then adds a   |
		|  newline character	 											 |
		====================================================================*/
		return encrypt(input)+"\n";
	}
	public static char decrypt(String input) throws Exception {
		/*====================================================================
		|  String decrypt(String input)    								 	 |
		|--------------------------------------------------------------------|
		|  returns decrypted - A char representing the decrypted version of  |
		|  the input string										      		 |
		|--------------------------------------------------------------------|
		|  String input - This parameter is the string to be decrypted, with |
		|  a length of exactly 3 characters	 								 |
		|--------------------------------------------------------------------|
		|  Compares the input string to each line of the key, and converts	 |
		|  it to a single character if a match is found 					 |
		====================================================================*/
		String s;
		BufferedReader br=new BufferedReader(new FileReader("encryptionKey.txt"));
		while((s=br.readLine())!=null) {
			s.trim();
			if(input.equals(s.substring(1))) {
				return s.charAt(0);
			}
		}
		return 0000;
	}
	public static String decryptString(String input) throws Exception {
		/*====================================================================
		|  String decryptString(String input)    							 |
		|--------------------------------------------------------------------|
		|  returns decrypted - A string representing the decrypted version of|
		|  the input string										      		 |
		|--------------------------------------------------------------------|
		|  String input - This parameter is the string to be decrypted, with |
		|  any length  	 													 |
		|--------------------------------------------------------------------|
		|  Sorts the input string into segments of 3 characters and stores   |
		|  them in a list, then decrypts them individually 					 |
		====================================================================*/
		int i=0;
		if(input==null) {
			return null;
		}
		String decrypted="";
		String segment="";
		char[] inputArray=input.toCharArray();
		for(char c:inputArray) {
			segment+=c;
			i++;
			if(i>=3) {
				decrypted+=decrypt(segment);
				segment="";
				i=0;
			}
		}
		return decrypted;
	}
}
