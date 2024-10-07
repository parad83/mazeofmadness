# Backlog

| Name | How to demo | Notes |
| ---- | --- | --- |
| Playable Character | Press the "Start Game" button. A red square will appear on the map, representing the character. Keyboard input (arrow keys) can be used to control it. | - | 
| Procedural Generation | On each game start or level completion, a new world will be procedurally generated. | - |
| Obstacles | Each randomly generated world will contain visible obstacles that block the path to the level's goal. | - |
| Collision | Bumping into an obstacle should result in a restart of the level. | The game timer, based on which the player receives score, will also be reset. |
| Level Save System | On level completion, the player will have the option to save the randomly generated level to disk. In the game's main menu, they can choose to replay any of the saved levels at any time. | This system is intertwined with the score system design-wise, as it allows the betterment of scores. The player's high-score will be displayed alongside each level. |
| Score System | After reaching the end of the level, the player will receive a score based on how quickly they completed it. | Scores from different levels are not comparable between each other. Instead, they serve the purpose of giving the player an incentive to retry a level in hopes of a better score. |
