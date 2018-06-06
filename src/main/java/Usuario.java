
public class Usuario {
	private String nom;
	private String edad;
	private String id;
	
	public Usuario(String nom, String edad, String id) {
		super();
		this.nom = nom;
		this.edad= edad;
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getInfo() {
		return this.nom + " " + this.edad+ " -- ID: " + this.id;
	}
	
}
	

