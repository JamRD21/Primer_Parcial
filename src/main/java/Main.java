import static spark.Spark.*;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Main {
	static Logger log = LoggerFactory.getLogger(Main.class);
   
	public static void main(String[] args) {
		
    	staticFiles.location("/public");
    	port(4567);
    	
    	before((req, res) ->{
    		ArrayList<Usuario> lista = req.session().attribute("usuarios");
    		
    		if(lista == null) {
    			lista = new ArrayList<Usuario>();
    		}
    		// usuario administrador
    		boolean isAdminCreated = false;
    		for(Usuario u: lista) {
    			if(u.getNom() == "admin") {
    				isAdminCreated = true;
    			}
    		}
    		
    		if(!isAdminCreated) {
    			Usuario u = new Usuario ("admin", "0", "0");
    			lista.add(u);
    			log.info("admin creado");
    		}
    		
    		req.session().attribute("usuarios", lista);
    		
    	});
    	
    	before("/no-entre" ,(req,res) -> {  
    		
    		Usuario user = req.session().attribute("user");
    		
    		if (user == null) { halt(401, "devuelvase que se paso"); }
    	});
    	
    	
    	exception(SessionNoLogeada.class, (e,req,res) -> {
    		
    		res.status(401); 
    		res.body(e.toString());
    	});
    	
    	
        get("/no-permitido", (req,res) ->{ return "you are in :D"; });
    	
    	//home
    	get("/home", (req, res) -> {
    		res.status(200);
    		res.redirect("/index.html");
    		return null;
    	
    	});
    	
    	get("/login", (req, res) -> {
    		res.status(200);
    		res.redirect("/contact.html");
    		return "Claro";
    	});
    	
    	//contacto
        post("/login", (req, res)-> {
        	String nom = req.queryParams("nombres");
        	String edad = req.queryParams("edad");
        	String id = req.queryParamOrDefault("id", "No-Definida");
        	
        	Usuario user = new Usuario(nom, edad, id);
        	ArrayList<Usuario> lista = req.session().attribute("usuarios");
        	
        	if (lista == null) { lista = new ArrayList<Usuario>(); }
        	
        	lista.add(user);
        	req.session().attribute("usuarios", lista);
        	
        	return user.getInfo();
        });
        
        //-------------------------------------------------//
        
        before("/articulos/*", (request, response) -> {
        	halt(302, "Working on it!");
		
		});
        
        //registro
        get("/registro", (req, res) -> {  
        
        ArrayList<Usuario> lista = req.session().attribute("usuarios");
        
        if(lista == null) {
        	res.status(302);
        	return "NO HAY DATO A MOSTRAR...";
        }
        for(Usuario u : lista) { log.info(u.getInfo()); }	
		
        return "hola";
        });
        
        //articulos
        
        get("/articulos", (req, res)->{
        	res.status(302);
        	return null;
        });
        
        //me
        get("/me", (req, res)->{
        	res.status(200);
        	return null;
        });
		
		
        
		
	    
    }
}
