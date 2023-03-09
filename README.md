# IHM_TWI

Projet tweet

**IHM M2-TIIL**
###Spécifications de l'application

Sample commands macOS (Homebrew):

- Install the latest LTS version: brew install jenkins-lts
- Install a specific LTS version: brew install jenkins-lts@YOUR_VERSION
- Start the Jenkins service: brew services start jenkins-lts
- Restart the Jenkins service: brew services restart jenkins-lts
- Update the Jenkins version: brew upgrade jenkins-lts
- Uninstall the Jenkins service: brew services stop jenkins-lts && brew uninstall jenkins-lt
- Stop the Jenkins services : brew services stop jenkins-lts 

Une Gestion des utilisateurs et gestion des twits.


#### Note :

La recherche de twit s'effectue de la manière suivante :

- Si le symbole '@' est présent, la recherche retourne les twits émis par cet utilisateur et ceux
  dans lesquels l'utilisateur est cité.
- Si le symbole '#' est présent, la recherche retourne les twits contenant ce tag.
- Si aucun symbole n'est présent, la recherche s'effectue selon l'union des deux critères
  précédents.
