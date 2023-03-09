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
- Uninstall the Jenkins service: brew services stop jenkins-lts && brew uninstall jenkins-lts

# Gestion des utilisateurs

### ***SRS-TWB-USR-001***:

```
L'utilisateur peut enregistrer un compte utilisateur dans le système (nom, tag, avatar).
``` 

### ***SRS-TWB-USR-002*** :

```
Lors de l'enregistrement d'un compte utilisateur, le tag et le nom de l'utilisateur sont
obligatoires.
```

### ***SRS-TWB-USR-003*** :

```
Le tag correspondant à un utilisateur est unique dans le système.
```

### ***SRS-TWB-USR-004*** :

```
L'utilisateur peut se connecter sur un compte préalablement enregistré.
```

### ***SRS-TWB-USR-005*** :

```
L'utilisateur connecté peut se déconnecter de l'application.
```

### ***SRS-TWB-USR-006*** :

```
L'utilisateur connecté peut consulter son profil.
```

### ***SRS-TWB-USR-007*** :

```
L'utilisateur connecté peut consulter la liste des utilisateurs enregistrés.
```

### ***SRS-TWB-USR-008*** :

```
L'utilisateur connecté peut rechercher un utilisateur.
```

### ***SRS-TWB-USR-009*** :

```
L'utilisateur connecté peut s'abonner à un utilisateur.
```

### ***SRS-TWB-USR-010*** :

```
L'utilisateur connecté peut se désabonner d'un utilisateur.
```

# Gestion des twits

### ***SRS-TWB-TWT-001*** :

```
L'utilisateur connecté peut envoyer un twit.
```

### ***SRS-TWB-TWT-002*** :

```
Le texte d'un twit ne dépasse pas 250 caractères.
```

### ***SRS-TWB-TWT-003*** :

```
L'utilisateur connecté peut rechercher des twits.
```

#### Note :

La recherche de twit s'effectue de la manière suivante :

- Si le symbole '@' est présent, la recherche retourne les twits émis par cet utilisateur et ceux
  dans lesquels l'utilisateur est cité.
- Si le symbole '#' est présent, la recherche retourne les twits contenant ce tag.
- Si aucun symbole n'est présent, la recherche s'effectue selon l'union des deux critères
  précédents.

### ***SRS-TWB-TWT-004*** :

```
Une notification avertit l'utilisateur connecté lorsqu'un utilisateur auquel il est abonné émet
un twit.
```
