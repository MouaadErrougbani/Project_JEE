```markdown
# livreDor

Application web "Livre d'or" réalisée en Java (JSP + JDBC + Sockets).

Courte description
Une petite application web permettant aux utilisateurs de laisser des messages dans un livre d'or. Technologies principales : Java (Servlet/JSP), JDBC (connexion à une base de données), sockets (pour notifications en temps réel), déploiement en WAR/Tomcat ou via un IDE comme Eclipse.

## Structure du projet
- src/main/java : code Java (servlets, sockets, DAO, modèles)
- src/main/webapp : pages JSP, fichiers statiques (CSS/JS)
- build/ : fichiers de build (ne pas committer)
- .settings/ : paramètres IDE Eclipse (ignorés par .gitignore)

## Prérequis
- Java JDK 8+ installé
- Apache Tomcat (ou un autre conteneur servlet) ou un IDE (Eclipse) pour déployer
- Base de données (MySQL / PostgreSQL / autre) accessible
- Outils optionnels : Maven/Gradle si vous avez transformé le projet en projet buildable

## Configuration (ne pas committer de secrets)
Créez un fichier de configuration local qui contient les paramètres JDBC (ne le commitez jamais). Exemple : `src/main/resources/db.properties` ou utilisez les variables d'environnement.

Exemple (fichier `.env` local — NE PAS COMMIT) :
```
DB_URL=jdbc:mysql://localhost:3306/livredor
DB_USER=root
DB_PASSWORD=motdepasse
```

J’ai inclus `.env.example` dans le repo pour référence (sans mot de passe).

## Build & Déploiement
Option A — via Eclipse:
1. Importez le projet en tant que "Dynamic Web Project" ou "Existing Maven Project" si vous avez un pom.xml.
2. Configurez votre connexion JDBC (context.xml ou fichier de propriétés local).
3. Déployez sur Tomcat depuis Eclipse.

Option B — via ligne de commande (si projet Maven/Gradle) :
```bash
# Maven
mvn clean package
# puis déployez le .war dans Tomcat/webapps
```

Option C — si pas de build tool : copiez le dossier du projet dans un workspace Eclipse et exécutez sur Tomcat.

## Utilisation
- Ouvrez le navigateur sur `http://localhost:8080/<context-path>/` après déploiement.
- L’interface permet de lire/ajouter des messages, et de voir les notifications en temps réel grâce aux sockets.

## Tests
Expliquez ici comment lancer vos tests unitaires/integ s’il y en a : `mvn test` ou `./gradlew test`.

## Sécurité
- Ne committez jamais de fichiers contenant des mots de passe ou des clefs (.env, *.keystore, etc.).
- Si vous avez déjà committé un secret, révoquez-le puis nettoyez l’historique (BFG / git filter-repo).

## Contribution
Si vous voulez partager ou accepter des contributions, ajoutez CONTRIBUTING.md et CODE_OF_CONDUCT.md.

## Licence
Ce projet est sous licence MIT — voir le fichier LICENSE pour le texte.
```