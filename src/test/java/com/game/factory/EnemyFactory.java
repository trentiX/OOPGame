package com.game.factory;

import com.game.model.Enemy;
import com.game.repository.EnemyRepository;

public class EnemyFactory {

    public static Enemy createRandom(EnemyRepository repo) throws Exception {
        return repo.getRandomEnemy();
}
}