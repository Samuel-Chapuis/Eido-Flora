# Test Commands for EidoPlants Custom Oak

## Pour placer manuellement un arbre custom :
/place feature eidoplants:custom_oak

## NOUVEAU : Test de génération naturelle avec spawn massif :
⚠️ ATTENTION : Configuration temporaire avec 5 arbres par chunk !

1. Reconstruire le mod avec `.\gradlew build`
2. Créer un NOUVEAU monde (très important !)
3. Chercher un biome forest (minecraft:forest)
4. Vous devriez voir BEAUCOUP d'arbres custom (5 par chunk)

## Pour vérifier que la feature est correctement enregistrée :
/place feature eidoplants:custom_oak ~ ~ ~

## Coordonnées suggérées pour tester :
- /tp @s 0 100 0
- Chercher un biome forest
- Les arbres devraient être TRÈS visibles maintenant

## Si ça marche avec count=5, on réduira à une valeur normale après
