package winrar;

import java.io.IOException;

public class WinrarDecipher {

	private static String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static int longmax = 4;
	private static String archivo = "C:\\Users\\Alvaro\\Desktop\\texto.rar";
	private static String destino = "C:\\Users\\Alvaro\\Desktop\\carpeta";
	private Command cmd;
	private boolean exito;
	private String prueba = "";

	public WinrarDecipher() {

	}

	public WinrarDecipher(String s) {
		prueba = s;
	}

	public WinrarDecipher(String s, int longitud, String rutaarchivo,
			String rutadestino) {
		longmax = longitud;
		archivo = rutaarchivo;
		destino = rutadestino;
		prueba = s;

	}

	public String devCadena() {
		return prueba;
	}

	public void VA(String pr, int etapa) {
		StringBuilder cad = new StringBuilder(pr);
		exito = false;
		cmd = new Command();

		for (int i = 0; i < caracteres.length() && !exito; i++) {

			cad.append(caracteres.charAt(i));
			System.out.println(i + "/" + cad.toString());
			exito = comprobar(cad.toString(), cmd);
			if (cad.length() < longmax && !exito)
				VA(cad.toString(), etapa + 1);

			if (!exito) {
				cad = cad.replace(cad.length() - 1, cad.length(), "");
			} else {
				System.out.println("Encontrado:" + cad.toString());
			}

		}

	}

	public static boolean comprobar(String cadena, Command cmd) {
		cmd.execute("unrar x -p" + cadena + " " + archivo + " " + destino);

		if (cmd.frases().get(cmd.frases().size() - 1).equals("Todo correcto"))
			return true;

		return false;
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {

		WinrarDecipher r = new WinrarDecipher();

		double t1 = System.currentTimeMillis();
		r.VA(r.devCadena(), 0);
		double t2 = System.currentTimeMillis();

		System.out.println("Tiempo total:" + (t2 - t1));

	}
}
