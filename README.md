# Mine2D

Première configuration :

git clone https://github.com/Adralith1/Mine2D.git
(EN DEHORS DE INTELLIJ, Avec le CMD de Git)

Un dossier "Mine2D" va se créer.
Ouvrir le dossier Mine2D avec IntelliJ.

git remote -v pour voir quelles sont vos branches distantes,
Vous verrez par exemple :
origin  https://github.com/PSEUDO/REPOS.git (fetch)

Si il ne s'agit pas de la branche de Mine2D, supprimez la avec :
git remote rm origin
Si c'est :
origin  https://github.com/Adralith1/Mine2D.git (fetch)
Ne faites rien.

Rappels :
Pour avoir la version du projet à jour (recommandé avant de faire des push)
git pull

Si vous venez de faire des changements et désirez les envoyer :
git add .      (Le point est important)
git commit -m "MON MESSAGE DE CHANGEMENTS"
git push -u origin master

Vous êtes prêts !

Bug :
Si la JVM ne démarre pas, retirez ou ajoutez cette ligne à la fin du
fichier "gradle.properties" (En dernière ligne):
org.gradle.jvmargs=-Xmx1024m

