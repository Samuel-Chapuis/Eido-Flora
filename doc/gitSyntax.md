# Syntaxe des messages de commit

Les messages de commit doivent suivre une convention claire et standardisée afin de garantir la lisibilité et la traçabilité du projet. Ce standard s'inspire des [Conventional Commits](https://www.conventionalcommits.org/) et vise à clarifier le type de changement, son contexte, et à structurer le développement.

## Format général

- **type** : Le type de changement (voir la liste ci-dessous).
- **scope** (optionnel) : La partie du projet concernée par le changement (par ex. un service, un module).
- **message** : Une courte description des changements (à l’impératif et au présent).

## Types de commits

- **feat** : Ajout d’une nouvelle fonctionnalité.
  - Exemple : `feat(auth-utilisateur) : ajout de la fonctionnalité de connexion`

- **fix** : Correction de bug.
  - Exemple : `fix(paiement) : corrige le problème d’arrondi dans le calcul des factures`

- **chore** : Tâches mineures sans impact sur le code applicatif (ex. mise à jour de dépendances).
  - Exemple : `chore : mise à jour des dépendances`

- **docs** : Modifications de la documentation.
  - Exemple : `docs(readme) : ajout des instructions d’utilisation`

- **style** : Modifications de style de code (indentation, mise en forme) sans impact fonctionnel.
  - Exemple : `style : correction de l’indentation`

- **refactor** : Refactorisation de code sans modification du comportement.
  - Exemple : `refactor(service-commande) : simplifie le déroulement d’une méthode`

- **test** : Ajout ou modification de tests.
  - Exemple : `test(service-utilisateur) : ajout de tests unitaires`

- **perf** : Amélioration des performances.
  - Exemple : `perf(api) : optimisation des requêtes vers la base de données`

- **ci** : Modifications liées à l’intégration continue ou aux outils de build.
  - Exemple : `ci : mise à jour de la configuration GitHub Actions`

## Bonnes pratiques

- **Messages clairs et précis** : Le message doit être explicite et tenir en une seule ligne. Les détails supplémentaires peuvent figurer dans le corps du commit.
- **Présent à l’impératif** : Utilisez le présent à l’impératif pour décrire ce que fait le commit, ex. "ajoute", "corrige", "met à jour".
- **Scope optionnel** : Le scope est utile pour indiquer la zone du projet concernée, mais peut être omis si non pertinent.
- **Évitez les termes vagues** : Ne pas utiliser des termes comme `update`, `change` ou `improve` sans précision.

## Exemples de commit

- **Ajout d’une fonctionnalité** :
