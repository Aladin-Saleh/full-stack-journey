

# Application web de suivi et de gestion de collection de jeux vidéo.
## Stack technique
- Front : React (Typescript)
- Back : Node.js 
- Base de données : MongoDB
- API : RAWG
- Authentification : JWT

Dependences pour le back :
- JWT 
- Mongoose
- Express
- Axios
- BodyParser
- dotenv
- bcrypt
- cors
- nodemon



## Fonctionnalités

- [X] Inscription et connexion : Authentification des utilisateurs pour accéder et gérer leur collection.

- [X] Ajout de jeux à la collection : Les utilisateurs peuvent ajouter des jeux à leur collection personnelle avec des détails comme le titre, la plateforme, le genre, et le statut (par exemple, terminé, en cours, souhaite jouer).

- [X] Recherche et filtre : Fonctionnalité de recherche pour trouver des jeux par titre ou par filtres (plateforme, genre, statut).

- [X] API externe : Intégration avec une API de jeux vidéo comme IGDB (Internet Game Database) pour récupérer des informations sur les jeux lors de l'ajout à la collection.

- [X] Interface utilisateur dynamique : Utilisation de React avec des animations et transitions pour une expérience utilisateur fluide et réactive.

### Optionnel

- [ ] Page d'accueil : Présentation de l'application et des fonctionnalités principales.












# Journal de bord
**Jour 1 :** (7h21)   
Mise en place du systeme d'inscription et de connexion avec les jetons JWT.  
Utilisation de tailwind et material ui pour le style.  
L'authentification utilise un provider, l'interet de sauvegarder le contexte de l'utilisateur, donc on vas effectuer une requete vers le serveur qui nécessite un jeton valide, l'accès au ressources dépend de la réponse renvoyer par le serveur.  
Cela permet d'avoir une double sécurité, par le jeton + par le serveur.  
De plus, le contexte permettra d'accéder directement au données utilisateur depuis tous les composants !

**Jour 2 :**  (5h44)  
Mise en place de la base de données MongoDB.  
Création du model User.  
Création de l'interface avec les différentes fonctionnalités de l'application (ajout de jeux, recherche, page de profil).  
Intégration de l'API RAWG pour récupérer les informations sur les jeux.  
Mise en place des routes sur l'interface avec react-router-dom.  
Il ne reste plus qu'à ajouter le filtrage, la modification (pour noté les jeux), la suppression et la recherche du profil d'un autre utilisateur.

**Jour 3 :** (56min)  
Le projet est terminé, il ne reste plus qu'à le déployer sur Heroku.  
Il faudra également ajouter une page d'accueil pour présenter l'application et les fonctionnalités principales.  

Video preview :  
https://github.com/Aladin-Saleh/full-stack-journey/assets/67257097/f2e88805-1414-466d-ac90-c6e2a0d21ce3


