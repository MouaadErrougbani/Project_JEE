package controller.servlets;

import java.io.IOException;
import java.util.List;

import controller.dao.CommentaireService;
import controller.dao.PosteService;
import controller.dao.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Commentaire;
import model.Poste;
import model.User;

@WebServlet("/livreDor")
public class ServletServer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String contextPath = "http://localhost:8080/Livre_Dor"; // Récupère le chemin racine de l'application

    public ServletServer() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        System.out.println("this is servlet action = " + action);

        // Si l'action est null, rediriger vers la page de connexion
        if (action == null) {
            response.sendRedirect(contextPath + "/view/login.jsp");
            return;
        }

        // Vérification de l'authentification pour les actions autres que "login"
        if (!"login".equals(action)) {
            if (user == null) {
                response.sendRedirect(contextPath + "/view/login.jsp");
                return;
            }
        }

        // Switch pour gérer les différentes actions
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "poste":
                poste(request, response);
                break;
            case "addPoste":
                addPoste(request, response);
                break;
            case "updatePoste":
                updatePoste(request, response);
                break;
            case "deletePoste" : 
            	deletePoste(request, response);
            	break;
            case "commentaires":
            	commentaires(request, response);
            	break;
            case "addCommentaire" :
            	addCommentaire(request, response);
            	break;
            	
            case "deleteCommentaire" :
            	deleteCommentaire(request, response);
            	break;	
            	
            case "deconnect" :
            	deconnect(request, response);
            	break;	
            default:
                response.sendRedirect(contextPath + "/view/error.jsp");
                break;
        }
    }

    private void deconnect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(contextPath + "/livreDor");
	}

	private void deleteCommentaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCommentaire = request.getParameter("idCommentaire");
    	if( !"".equals(idCommentaire.trim())) {
    		CommentaireService commentaireService = new CommentaireService();
    		commentaireService.deleteCommentaire(Integer.parseInt(idCommentaire));
    	}
    	commentaires(request, response);
		
	}

	private void addCommentaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String auteur = request.getParameter("auteur");
    	String idPoste = request.getParameter("idPoste");
    	String contene = request.getParameter("contene");
    	
    	if(auteur == null || idPoste == null|| contene == null 
    		|| auteur.trim().equals("") || idPoste.trim().equals("") || contene.trim().equals("")
    			) {
    		    		
    	}else {
    		
    		CommentaireService commentaireService = new CommentaireService();
    		commentaireService.addCommentaire(new Commentaire(0,Integer.parseInt(idPoste), auteur, contene , null));
    	}
    	commentaires(request, response);
	}

	private void commentaires(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idPoste");
		if(!"".equals(id)) {
			int idPoste = Integer.parseInt(id);
			CommentaireService commentaireService = new CommentaireService();
			PosteService posteService = new PosteService();
			List<Commentaire> commentaires = commentaireService.getAllCommentaire(idPoste);
			Poste poste = posteService.getPoste(idPoste);
			
			request.setAttribute("commentaires", commentaires);
			request.setAttribute("poste", poste);
			request.getRequestDispatcher("/view/commentaires.jsp").forward(request, response);
			return;
		}
    	response.sendRedirect(contextPath + "/view/postes.jsp");
    	return;
		
	}

	private void deletePoste(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
    	String id = request.getParameter("idPoste");
    	if(!id.equals("")) {
    		PosteService posteService = new PosteService();
    		CommentaireService commentaireService = new CommentaireService();
    		commentaireService.deleteCommentaireDePoste(Integer.parseInt(id));
    		posteService.deletePoste(Integer.parseInt(id));
    		
    	}
    	poste(request, response);
	}

	private void updatePoste(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idPoste");

        // Vérifier si l'ID est valide
        int idPoste = 0;
        try {
            idPoste = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "ID de poste invalide");
            request.getRequestDispatcher("/view/form.jsp").forward(request, response);
            return;
        }

        if (idPoste == 0) {
            request.setAttribute("message", "Problème avec l'ID du poste");
            request.getRequestDispatcher("/view/form.jsp").forward(request, response);
            return;
        }

        PosteService posteService = new PosteService();
        Poste poste = posteService.getPoste(idPoste);

        // Vérifier si le poste existe
        if (poste == null) {
            request.setAttribute("message", "Le poste avec l'ID " + idPoste + " n'existe pas.");
            request.getRequestDispatcher("/view/form.jsp").forward(request, response);
            return;
        }

        request.setAttribute("poste", poste);
        request.getRequestDispatcher("/view/form.jsp").forward(request, response);
    }

    private void addPoste(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String auteur = request.getParameter("auteur");
        String titel = request.getParameter("titel");
        String content = request.getParameter("content");
        String idPost = request.getParameter("idPoste");  // Récupère l'ID du poste en tant que chaîne
        
        // Validation des champs
        if (auteur == null || titel == null || content == null ||
            auteur.trim().equals("") || titel.trim().equals("") || content.trim().equals("")) {
            request.setAttribute("message", "Veuillez remplir tous les champs");
            request.getRequestDispatcher("/view/form.jsp").forward(request, response);
            return;
        }

        // Validation de l'ID du poste (si c'est une chaîne vide, on le met à 0)
        int idPoste = 0;
        try {
            if (!idPost.trim().equals("")) {
                idPoste = Integer.parseInt(idPost);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "ID du poste invalide");
            request.getRequestDispatcher("/view/form.jsp").forward(request, response);
            return;
        }

        PosteService posteService = new PosteService();
        Poste poste = new Poste(idPoste, auteur, titel, content, null, null);

        if (idPoste == 0) {
            poste = posteService.addPoste(poste);
        } else {
            poste = posteService.updatePoste(poste);
        }
        
        poste(request, response); // Afficher la liste des postes après ajout ou mise à jour
    }



	private void poste(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	PosteService posteService = new PosteService();
    	List<Poste> postes = posteService.getAllPoste();
    	session.setAttribute("postes", postes);
    	request.getRequestDispatcher("/view/postes.jsp").forward(request, response);
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String isExiste = request.getParameter("isExiste");
        String contextPath = request.getContextPath(); // Récupère le chemin racine de l'application
        User user;
        
        if(name == null || pwd == null) {
            response.sendRedirect(contextPath + "/view/login.jsp");
        	return;
        }
        user = new User();
        user.setName(name);
        user.setPwd(pwd);
        
        if (isExiste != null && "true".equals(isExiste)) {
	        	UserService userService = new UserService();
	            user = userService.getUser(name);
	            if (user != null && name.equals(user.getName()) && pwd.equals(user.getPwd())) {
	                HttpSession session = request.getSession();
	                session.setAttribute("user", user);
	               poste(request, response);
	                return; 
	            }
	            request.setAttribute("message", "Error pour le nom de utilisateur ou mot de passe ");
            	request.getRequestDispatcher("/view/login.jsp").forward(request, response);
                return;
        		
        	}else {
        		UserService userService = new UserService();
	            User userExist = userService.getUser(name);
	            if(userExist == null){
	            	userService.ajouterUser(user);
	            	HttpSession session = request.getSession();
	                session.setAttribute("user", user);
	            	poste(request, response);
	                return;
	            }else {
	            	request.setAttribute("message", "Ce nom déjà existe");
	            	request.getRequestDispatcher("/view/login.jsp").forward(request, response);
	                return;
	            }
	            
        	
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
