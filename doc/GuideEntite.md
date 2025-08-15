# Helpeur pour les Entités

Ce document a pour but d'aider a la création de nouvelles entités pour s'assurer de ne rien oublier.

### Creation de l'entité 
1. Utiliser BlockBench : Modded Entity
2. S'assurer que tous les bloques soient bien placé dans le repertoire `./bone`
3. Exporter avec : Export Java Entity
4. S'assurer que le nom de la classe soit `'Le nom de l'entité'Model`

### Creation des fichiers
1. Creer/Utiliser un repertoire comprennant les sous repertoires `./declaration` et `./render`
2. Placer la classe créer avec BlockBench dans `./render`
3. Creer une classe  `'Le nom de l'entité'` dans `./declaration` et `'Le nom de l'entité'Renderer` dans `./render`

### Utilisation des fichiers
1. `'Le nom de l'entité'` : Contient la logique de l'entité par exemple pour un avion son comportement en vole
2. `'Le nom de l'entité'Model` : Contient la structure de l'entité directement exporté de BlockBench
3. `'Le nom de l'entité'Renderer` : Contient les méthodes de rendue de l'entité, a noter que cette classe n'est pas forcément indispensable dépendamant du type d'entité.

### Implementation 
Maintenant que les 3 fichiers sont créer il faut s'assurere que l'entité est bien enregistré dans Minecraft.

1. Enregistrer l'entité dans `fr.Eidolyth.entity.ModEntities`
2. Enregistrer le model dans `fr.Eidolyth.entity.ModModelLayers`
3. Enregistrer l'evenement qui permet d'ajouter l'entité au client dans `fr.Eidolyth.event.ClientModEvents`
4. Enregistrer l'evenement qui permet d'ajouter le model l'entité au client dans `fr.Eidolyth.event.ModEventBusEvents`