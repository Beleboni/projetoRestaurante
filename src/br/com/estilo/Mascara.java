package br.com.estilo;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Mascara {
	
	public static MaskFormatter getTelefoneMask()  {
		try {
			return new MaskFormatter("(##) ####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static MaskFormatter getCpfMask()  {
		try {
			return new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
