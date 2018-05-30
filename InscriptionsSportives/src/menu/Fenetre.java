package menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Box;

public class Fenetre extends JFrame{
  private Panneau pan = new Panneau();
  private JButton bouton = new JButton("Quitter");
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("Accueil");
  private JButton bouton2 = new JButton("test");  

  
//Compteur de clics
  private int compteur = 0;
  
  public Fenetre(){
	
	  
	  
    this.setTitle("Inscriptions Sportive");
    this.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    container.add(pan, BorderLayout.CENTER);
    
  //Nous ajoutons notre fenêtre à la liste des auditeurs de notre bouton

  //Ce sont les classes internes qui écoutent les boutons 
    bouton.addActionListener(new BoutonListener());
    bouton2.addActionListener(new Bouton2Listener());
    
    JPanel south = new JPanel();
    south.add(bouton);
    south.add(bouton2);
    container.add(south, BorderLayout.SOUTH);

    
  //Définition d'une police d'écriture

    Font police = new Font("Tahoma", Font.BOLD, 16);

    //On l'applique au JLabel

    label.setFont(police);

    //Changement de la couleur du texte

    label.setForeground(Color.red);

    //On modifie l'alignement du texte grâce aux attributs statiques

    //de la classe JLabel

    label.setHorizontalAlignment(JLabel.CENTER);
    
    container.add(label, BorderLayout.NORTH);
    this.setContentPane(container);
    
  //On crée un conteneur avec gestion horizontale

    Box b1 = Box.createHorizontalBox();

    b1.add(new JButton("Bouton 1"));

    //Idem

    Box b2 = Box.createHorizontalBox();

    b2.add(new JButton("Bouton 2"));
    //Idem

    Box b3 = Box.createHorizontalBox();

    b3.add(new JButton("Bouton 3"));

    //On crée un conteneur avec gestion verticale

    Box b4 = Box.createVerticalBox();

    b4.add(b1);

    b4.add(b2);

    b4.add(b3);
		
    this.getContentPane().add(b4);
    this.setVisible(true);    
    //go();
  }
  
  /*private void go(){  
    //Les coordonnées de départ de notre rond
    int x = pan.getPosX(), y = pan.getPosY();
    //Le booléen pour savoir si l'on recule ou non sur l'axe x
    boolean backX = false;
    //Le booléen pour savoir si l'on recule ou non sur l'axe y
    boolean backY = false;
    
    //Dans cet exemple, j'utilise une boucle while
    //Vous verrez qu'elle fonctionne très bien
    while(true){
      //Si la coordonnée x est inférieure à 1, on avance
      if(x < 1)backX = false;
      //Si la coordonnée x est supérieure à la taille du Panneau moins la taille du rond, on recule
      if(x > pan.getWidth()-50)backX = true;
      //Idem pour l'axe y
      if(y < 1)backY = false;
      if(y > pan.getHeight()-50)backY = true;
      
      //Si on avance, on incrémente la coordonnée
      if(!backX)
        pan.setPosX(++x);
      //Sinon, on décrémente
      else
        pan.setPosX(--x);
      //Idem pour l'axe Y
      if(!backY)
        pan.setPosY(++y);
      else
        pan.setPosY(--y);
        
      //On redessine notre Panneau
      pan.repaint();
      //Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
      try {
        Thread.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }    
  }
  */
  
  
  public static void main(String[] args){
      Fenetre fenetre = new Fenetre();
    }
/*
//Classe écoutant le premier bouton
  class BoutonListener implements ActionListener{
    //Redéfinition de la méthode actionPerformed()
    public void actionPerformed(ActionEvent arg0) {
    	//setEnabled(false);
    	Dialogue d = new Dialogue(new JFrame(), "Avertissement", "Voulez-vous vraiment quitter ?");
    	d.setSize(300, 150);
    }
  }
  class Bouton3Listener implements ActionListener{
	    //Redéfinition de la méthode actionPerformed()
	    public void actionPerformed(ActionEvent arg1) {
	    	System.exit(0);
	    }
	  }
  class Bouton4Listener implements ActionListener{
	    //Redéfinition de la méthode actionPerformed()
	    public void actionPerformed(ActionEvent arg2) {
	    	
	    }
	  }

      
*/
  //Classe écoutant le second bouton

  class Bouton2Listener implements ActionListener{
    //Redéfinition de la méthode actionPerformed()
    public void actionPerformed(ActionEvent e) {
      label.setText("Vous avez cliqué sur le bouton 2");    
    }
  }
  
  public class BoutonListener implements ActionListener{

	    public void actionPerformed(ActionEvent arg0) {     

	      JOptionPane jop = new JOptionPane();      

	      int option = jop.showConfirmDialog(null, 

	        "Voulez-vous quitter ?", 

	        "Avertissement", 

	        JOptionPane.YES_NO_OPTION, 

	        JOptionPane.QUESTION_MESSAGE);


	      if(option == JOptionPane.OK_OPTION){   
	    	  System.exit(0);
	      }
	    }    

	  }

}