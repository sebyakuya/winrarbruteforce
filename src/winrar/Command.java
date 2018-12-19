package winrar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Command {
	private ArrayList<String> frases=new ArrayList<String>();
	
	public void execute(String comando) {
		try {
			String linea;
			Process p = Runtime.getRuntime().exec(comando);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			while ((linea = input.readLine()) != null) {
				frases.add(linea);
				//System.out.println(linea);
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	
	public ArrayList<String> frases(){
		return frases;
	}
}