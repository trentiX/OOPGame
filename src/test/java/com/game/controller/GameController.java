package com.game.controller;

import com.game.factory.EnemyFactory;
import com.game.interfaces.IPlayerRepository;
import com.game.model.*;
import com.game.repository.*;
import com.game.service.GameService;
import com.game.util.DBConnection;

import java.sql.Connection;
import java.util.Scanner;

public class GameController {
    private final Scanner scanner = new Scanner(System.in);
    private final GameService gameService = GameService.getInstance();
    private Weapon currentWeapon;
    private User currentUser = new User("Player1", Role.PLAYER); // Реализуйте Конструктор пж

    public void start() {
        try (Connection conn = DBConnection.getConnection()) {
            IPlayerRepository playerRepo = new PlayerRepository(conn);
            EnemyRepository enemyRepo = new EnemyRepository(conn);
            WeaponRepository weaponRepo = new WeaponRepository(conn);
            InventoryRepository invRepo = new InventoryRepository(conn);

            Player player = playerRepo.getPlayerById(1);
            if (player == null) {
                player = new Player(1, "Кайсар", 100, 0, 1);
                playerRepo.createPlayer(player);
            } else if (player.getHp() <= 0) {
                System.out.println("Боги воскресили вас!");
                player.setHp(100);
                playerRepo.updatePlayer(player);
            }
            System.out.println("С возвращением, " + player.getName() + "!");

            currentWeapon = weaponRepo.getWeaponById(player.getWeaponId());

            if (currentWeapon == null) {
                currentWeapon = weaponRepo.getWeaponById(1);
                if (currentWeapon == null) {
                    currentWeapon = new Weapon(1, "Ржавый нож", "SLASH", 5);
                }
                player.setWeaponId(currentWeapon.getId());
                playerRepo.updatePlayer(player);
            }

            System.out.println("=== RPG: ПОДЗЕМЕЛЬЕ СУДЬБЫ ===");
            System.out.println("Ваше оружие: " + currentWeapon.getName());

            boolean running = true;
            while (running && player.getHp() > 0) {
                printStatus(player, currentWeapon, invRepo);
                int choice = getChoice();

                switch (choice) {
                    case 1 -> explore(player, enemyRepo, playerRepo, invRepo, weaponRepo);
                    case 2 -> drinkPotion(player, playerRepo, invRepo);
                    case 3 -> {
                        if (currentUser.getRole() == Role.ADMIN) {
                            PlayerFull pf = playerRepo.getFullPlayer(player.getId());
                            System.out.println("Полные данные (JOIN): " + pf.getPlayer().getName() + " владеет " + pf.getWeapon().getName());
                        } else {
                            System.out.println("Ошибка: Только ADMIN может смотреть полные данные.");
                        }
                    }
                    case 0 -> {
                        System.out.println("Вы покинули подземелье. Прогресс сохранен.");
                        running = false;
                    }
                    default ->  {
                        System.out.println("-------------------------------------------------");
                    System.out.println("[ОШИБКА]: Вы ввели '" + choice + "'. Допустимы только числа 0, 1, 2, 3.");
                    System.out.println("Пожалуйста, выберите действие из списка.");
                    System.out.println("-------------------------------------------------");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printStatus(Player p, Weapon w, InventoryRepository invRepo) throws Exception {
        int potions = invRepo.countItem(p.getId(), "POTION");
        System.out.println("\n------------------------------------------------");
        System.out.println(String.format("[ %s | HP: %d | Золото: %d | Зелья: %d ]",
                p.getName(), p.getHp(), p.getGold(), potions));
        System.out.println("[ Оружие: " + w.getName() + " (Урон +" + w.getAdditionalDamage() + ") ]");
        System.out.println("1. Идти вглубь подземелья");
        System.out.println("2. Выпить зелье лечения");
        System.out.println("3. Посмотреть полные характеристики персонажа");
        System.out.println("0. Выход");
    }

    private void explore(Player p, EnemyRepository er, PlayerRepository pr, InventoryRepository ir, WeaponRepository wr) throws Exception {
        double roll = Math.random();
        if (roll < 0.7) {
            handleBattle(p, er, pr);
        } else {
            handleChest(p, ir, wr, pr);
        }
    }

    private void handleBattle(Player p, EnemyRepository er, PlayerRepository pr) throws Exception {
        Enemy e = EnemyFactory.createRandom(er);
        System.out.println("\nНа вас напал " + e.getName() + "!");

        while (e.getHp() > 0 && p.getHp() > 0) {
            int dmgToEnemy = gameService.calculateDamage(currentWeapon, e);
            e.setHp(e.getHp() - dmgToEnemy);
            System.out.println(">> Вы бьете " + e.getName() + " на " + dmgToEnemy + " урона.");

            if (e.getHp() > 0) {
                p.setHp(p.getHp() - e.getDamage());
                System.out.println("<< Враг бьет вас на " + e.getDamage() + ". Ваше HP: " + p.getHp());
                pr.updatePlayer(p);
            }
        }

        if (p.getHp() > 0) {
            System.out.println (e.getName() + " повержен! Вы получили " + e.getExpReward() + " золота.");
            p.setGold(p.getGold() + e.getExpReward());
            pr.updatePlayer(p);
        }
    }

    private void handleChest(Player p, InventoryRepository ir, WeaponRepository wr, PlayerRepository pr) throws Exception {
        System.out.println("\nВы нашли старый сундук!");
        String lootType = gameService.getRandomLootType();

        if (lootType.equals("POTION")) {
            ir.addItem(p.getId(), "POTION", 1);
            System.out.println("Вы нашли Зелье Лечения! Добавлено в инвентарь.");
        }
        else if (lootType.equals("WEAPON")) {
            // Находим случайное оружие в БД (ID 1, 2 или 3)
            int randomWeaponId = 1 + (int)(Math.random() * 3);
            Weapon foundWeapon = wr.getWeaponById(randomWeaponId);

            System.out.println("Внутри лежит: " + foundWeapon.getName());
            System.out.println("Тип: " + foundWeapon.getType() + " | Бонус урона: +" + foundWeapon.getAdditionalDamage());
            System.out.println("Ваше текущее оружие: " + currentWeapon.getName());

            // Сделайте сравнение для оружий здесь и принтите данные

            System.out.print("Заменить текущее оружие? (1-Да, 2-Нет): ");

            if (getChoice() == 1) {
                currentWeapon = foundWeapon;
                p.setWeaponId(foundWeapon.getId());
                pr.updatePlayerWeapon(p.getId(), foundWeapon.getId());
                System.out.println("Вы вооружились " + foundWeapon.getName() + "!");
            } else {
                System.out.println("Вы решили оставить старое оружие.");
            }
        } else {
            System.out.println("Сундук оказался пустым...");
        }
    }

    private void drinkPotion(Player p, PlayerRepository pr, InventoryRepository ir) throws Exception {
        int count = ir.countItem(p.getId(), "POTION");
        if (count > 0) {
            p.setHp(Math.min(100, p.getHp() + 30));
            ir.usePotion(p.getId());
            pr.updatePlayer(p);
            System.out.println("HP восстановлено! Текущее HP: " + p.getHp());
        } else {
            System.out.println("У вас нет зелий!");
        }
    }

    private int getChoice() {
        System.out.print("> ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("> ");
        }
        return scanner.nextInt();
    }
}