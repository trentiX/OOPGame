package com.game.controller;

import com.game.model.Player;
import com.game.model.Weapon;
import com.game.repository.WeaponRepository;
import com.game.interfaces.IPlayerRepository;
import java.util.List;
import java.util.Scanner;

public class EquipmentStore{
    private final WeaponRepository weaponRepository;
    private final IPlayerRepository playerRepository;
    private final Scanner scanner = new Scanner(System.in);

    public EquipmentStore(WeaponRepository wr, IPlayerRepository pr) {
        this.weaponRepository = wr;
        this.playerRepository = pr;
    }

    public void showMenu(Player player) throws Exception {
        while (true) {
            System.out.println("\n=== МАГАЗИН СНАРЯЖЕНИЯ ===");
            System.out.println("Ваше золото: " + player.getGold());
            System.out.println("1. Купить оружие");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            if (choice == 1) {
                buyWeaponMenu(player);
            } else if (choice == 0) {
                break;
            }
        }
    }

    private void buyWeaponMenu(Player player) throws Exception {
        List<Weapon> weapons = weaponRepository.getAllWeapons();

        System.out.println("\nДоступные товары:");
        for (int i = 0; i < weapons.size(); i++) {
            Weapon w = weapons.get(i);
            System.out.println((i + 1) + ". " + w.getName() + " (Урон: +" + w.getAdditionalDamage() + ") - Цена: " + w.getPrice());
        }
        System.out.println("0. Назад");

        int choice = scanner.nextInt();
        if (choice > 0 && choice <= weapons.size()) {
            Weapon selected = weapons.get(choice - 1);
            processPurchase(player, selected);
        }
    }

    private void processPurchase(Player player, Weapon weapon) throws Exception {
        if (player.getGold() >= weapon.getPrice()) {
            player.setGold(player.getGold() - weapon.getPrice());
            player.setWeaponId(weapon.getId());

            playerRepository.updatePlayer(player);

            System.out.println("Вы успешно купили " + weapon.getName() + "!");
        } else {
            System.out.println("Недостаточно золота!");
        }
    }
}