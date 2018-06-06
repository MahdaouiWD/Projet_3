package fr.plusoumoins;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.PropertiesFile;

public class POMChallenger {	    
	private PropertiesFile p;
	   static final Logger logger = LogManager.getLogger(POMChallenger.class.getName());

	public POMChallenger() {
		super();
		p = PropertiesFile.getInstance();
	}
	    
	public void init() {

	    	Utile utile = new Utile();
	    	int nbAleatoire = utile.genererNbAleatoire();		//l'ordinateur choisit ce qui sera la solution
	    	utile.modeDeveloppeur(nbAleatoire);					//le mode d�veloppeur, si activ�, affiche la r�ponse avant le d�but
	        boolean victoire = false;
	        boolean verificationNb = true;
	        int nbToursInitial = p.getNbTours();
	        String type = "null";								//type sert pour les modes duel, pour diff�rencier humain et ordi 
	        int nbTours = p.getNbTours();						//comme j'utilise les m�me m�thodes entre les diff�rents mode
	        while (nbTours > 0 && victoire == false)
	        {
	                String proposition = utile.phraseDeDebut();
	                verificationNb = utile.verificationNb(proposition);
	                if (verificationNb == true && victoire == false)
	               	{
	                		victoire = utile.comparaisonPlusOuMoins(nbAleatoire, nbTours, proposition, victoire, nbToursInitial, type);
	                		nbTours--;	                
	                }				//la boucle agit tant que la bonne r�ponse n'est pas trouv�, tout en d�cr�mentant le nbTours
	                else 
	                {
	                	logger.error("Erreur de saisie");
	                	utile.phraseErreurSaisie();
	                }    

	        }
	        utile.issueDeLaPartie(victoire, nbAleatoire);
	}


}
