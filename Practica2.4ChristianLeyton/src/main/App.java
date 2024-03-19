package main;
import interfaz.Saludo;
import ioc.ContenedorIoC;
import saludos.SaludoEspanol;
import saludos.SaludoIngles;

public class App {
	
	//Variable para identificar el saludo
	private Saludo saludo;
	
	//Constructor para asignar el saludo
	public App(Saludo saludo) {
		this.saludo = saludo;
	}
	
	//Metodo para ejecutar la accion por consola
	public void ejecutar() {
		String mensaje = saludo.saludar();
		System.out.println(mensaje);
	}
	
	public static void main(String[] args) {
		ContenedorIoC contenedor = new ContenedorIoC();
		
		//Registramos en nuestro contenedor el tipo de saludo que queremos almacenar
		contenedor.registrar(Saludo.class, new SaludoIngles());
		
		//Aqui realizaremos el cambio de implementacion a "SaludoEspanol" para comprobarlo
		
		//contenedor.registrar(Saludo.class, new SaludoEspanol());
		
		//Para poder hacer uso de la clase App gestionada por nuestro contenedor
		//deberemos registrarla en este antes
	    contenedor.registrar(App.class, new App(contenedor.obtener(Saludo.class)));

		//Obtenemos una instancia de esta clase desde nuestro contenedor
		App app = contenedor.obtener(App.class);
		
		//Ejecutamos la app para que env�e el mensaje por consola
		app.ejecutar();
		
		//[Dato de interes] En caso de ejecutar ambos a la vez, tan solo se mostrar� por pantalla
		//el ultimo saludo almacenado en nuestro contenedor
	}
}
