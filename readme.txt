//---------- Le projet est réalisé par Benamara Abdelkader Chihab Eddine 
//----------                                                Et
//----------                           Dissem Yassine

pour bien séparer les différentes parties du projet on a crée trois packages relatifes à chacune d'elles
Et pour un plus vous trouverez aussi un dossier script_py qui sert à génerer selon votre besoin des fichiers pour 
bien tester le fonctionnement de notre projet

Pour la partie 2 du projet : nous avions trouvé un moyen pour transformer la grammaire donnée en une
grammaire  LL(1) qui est la suivante : 

eps: epsilon 

programme   --> instruction ; programme | eps
instruction --> Avancer(expression) | Tourner(expression) | Ecrire(expression)
                | Si expression Alors programme Fin | TantQue expression Faire programme Fin

expression  --> Lire | nombre | (expression op expression)
op          --> + | - | > | < | = | Et | Ou
nombre      --> -?[1-9][0-9]* | 0

Cette grammaire nous semble inapropriée car elle pourra acceptée des conditions de type  ( 5 Ou 6 )par exemple.
Ce qui n'a pas de sens. 


NB: - dans la réalisation on a opté plutot pour la premiere solution suggérée
    - Le probleme de la nouvelle grammaire pourra etre résoulu à l'aide du typage.



Pour la partie 3 du projet nous avons décidé de faire une interface graphique pour bien visualiser le comportement 
du robot.

