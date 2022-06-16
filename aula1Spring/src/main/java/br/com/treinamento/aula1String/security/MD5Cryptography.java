package br.com.treinamento.aula1String.security;


import java.security.MessageDigest;

public class MD5Cryptography {
	
	// Atributo para gerar a criptografia em MD5
	private static MessageDigest messageDigest = null;
	
	// Método estático para iniciar o atributo MessageDigest
	static {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	// Método para converter um valor criptografado de byte[] para string hexadecimal
	private static char[] hexCodes(byte[] text) {
		char[]hexOutput = new char[text.length * 2];
		String hexString;
		
		for(int i = 0; i < text.length; i++) {
			hexString = "00" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
		}
		return hexOutput;
	}
	
	// Método para realizar a criptografia
	
	public static String encrypt(String value) {
		if(value != null) {
			return new String(hexCodes(messageDigest.digest(value.getBytes()))).toLowerCase();	
			}
		return null;
		}
	}
