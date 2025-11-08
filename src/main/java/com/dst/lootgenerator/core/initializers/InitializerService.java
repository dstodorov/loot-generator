package com.dst.lootgenerator.core.initializers;

import com.dst.lootgenerator.auth.models.DTO.RegisterRequest;
import com.dst.lootgenerator.auth.models.User;
import com.dst.lootgenerator.auth.services.AuthService;
import com.dst.lootgenerator.hero.models.entities.*;
import com.dst.lootgenerator.hero.models.enums.*;
import com.dst.lootgenerator.hero.services.HeroService;
import com.dst.lootgenerator.items.models.entities.*;
import com.dst.lootgenerator.items.models.enums.*;
import com.dst.lootgenerator.items.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InitializerService {
    private final AttributeService attributeService;
    private final HeroService heroService;
    private final EquipmentService equipmentService;
    private final InventoryService inventoryService;
    private final StashService stashService;
    private final SkillService skillService;
    private final EffectService effectService;
    private final BuffService buffService;
    private final AuthService authService;

    public void loadInitialData() {

        // Inventory Items for Silvershaper
        Item bodyArmorOne = createArmorEntity("Steel Breastplate", 5, ArmorType.BODY_ARMOR, ItemRarity.MAGIC,
                createAttributes(
                        createAttribute("AttackPower", "Increases attack damage", ValueType.DECIMAL, 15.5f),
                        createAttribute("Defense", "Reduces incoming damage", ValueType.DECIMAL, 20.0f),
                        createAttribute("CriticalChance", "Chance to deal double damage", ValueType.PERCENT, 5.0f)
                ));

        Item bodyArmorTwo = createArmorEntity("Steel Armor", 5, ArmorType.BODY_ARMOR, ItemRarity.MAGIC,
                createAttributes(
                        createAttribute("LifeSteal", "Steals a percentage of dealt damage as health", ValueType.PERCENT, 8.0f),
                        createAttribute("ManaRegeneration", "Restores mana per second", ValueType.FLOATING, 3.2f)
                ));

        Item weaponOne = createWeaponEntity("Cool weapon", 12, WeaponType.ONE_HANDED, WeaponCategory.MACE, ItemRarity.RARE,
                createAttributes(
                        createAttribute("Stamina", "Increases endurance for longer fights", ValueType.DECIMAL, 30.0f),
                        createAttribute("Speed", "Affects movement speed", ValueType.FLOATING, 1.15f),
                        createAttribute("Evasion", "Chance to completely avoid an attack", ValueType.PERCENT, 12.0f),
                        createAttribute("FireResistance", "Reduces fire damage taken", ValueType.PERCENT, 18.0f)
                ));

        // Stash items for Silvershaper
        Item gloves = createArmorEntity("Leather Gloves", 2, ArmorType.GLOVES, ItemRarity.UNIQUE,
                createAttributes(
                        createAttribute("Dexterity", "Increases agility", ValueType.DECIMAL, 5.0f)
                ));

        Item weaponTwo = createWeaponEntity("Sharp Sword", 8, WeaponType.TWO_HANDED, WeaponCategory.SWORD, ItemRarity.NORMAL,
                createAttributes(
                        createAttribute("AttackDamage", "Increases base attack damage", ValueType.DECIMAL, 25.0f)
                ));

        // Equipment items for Silvershaper
        Armor equipmentGloves = createArmorEntity("Silver Gloves", 5, ArmorType.GLOVES, ItemRarity.UNIQUE,
                createAttributes(
                        createAttribute("Intellect", "Increases endurance for longer fights", ValueType.DECIMAL, 30.0f),
                        createAttribute("Health", "Affects total health points", ValueType.FLOATING, 1.15f)
                ));
        Armor equipmentBoots = createArmorEntity("Silvershaper Good Boots", 8, ArmorType.BOOTS, ItemRarity.RARE,
                createAttributes(
                        createAttribute("Health", "Affects movement speed", ValueType.FLOATING, 1.15f),
                        createAttribute("Fast walking", "Affects movement speed", ValueType.DECIMAL, 25.0f)
                ));
        Armor equipmentBodyArmor = createArmorEntity("Armor of Gods", 12, ArmorType.BODY_ARMOR, ItemRarity.CRAFTED,
                createAttributes(
                        createAttribute("Defence", "Bonus defence", ValueType.FLOATING, 3.5f),
                        createAttribute("Burn resistance", "Gain burn resistance", ValueType.DECIMAL, 5.0f),
                        createAttribute("Intellect", "Increases endurance for longer fight", ValueType.DECIMAL, 5.0f)
                ));

        Armor equipmentHelmet = createArmorEntity("Silver Helmet", 12, ArmorType.BODY_ARMOR, ItemRarity.SET,
                createAttributes(
                        createAttribute("Defence", "Bonus defence", ValueType.FLOATING, 3.5f),
                        createAttribute("Burn resistance", "Gain burn resistance", ValueType.DECIMAL, 5.0f)
                ));
        Weapon equipmentOneHandedWeapon = createWeaponEntity("Oculus Rift", 25, WeaponType.TWO_HANDED, WeaponCategory.SWORD, ItemRarity.RARE,
                createAttributes(
                        createAttribute("Magic Damage", "Increases base magic damage", ValueType.DECIMAL, 25.0f),
                        createAttribute("+Intellect", "Increases Intellect", ValueType.DECIMAL, 25.0f)
                ));

        Jewelry equipmentRingOne = createJewelryEntity("Ring of Death", 11, JewelryType.RING, ItemRarity.MAGIC,
                createAttributes(
                        createAttribute("Magic Damage", "Increases base magic damage", ValueType.DECIMAL, 25.0f),
                        createAttribute("+Intellect", "Increases Intellect", ValueType.DECIMAL, 25.0f)
                ));
        Jewelry equipmentRingTwo = createJewelryEntity("Ring of Speed", 11, JewelryType.RING, ItemRarity.MAGIC,
                createAttributes(
                        createAttribute("+Speed", "Increases walking speed", ValueType.DECIMAL, 25.0f)
                ));
        Jewelry equipmentAmulet = createJewelryEntity("Amulet of Mordor", 4, JewelryType.AMULET, ItemRarity.UNIQUE,
                createAttributes(
                        createAttribute("+Fire damage", "Increases fire damage", ValueType.DECIMAL, 25.0f)
                ));
        Jewelry equipmentBracelet = createJewelryEntity("Bracelet of the thin wrist", 6, JewelryType.BRACELET, ItemRarity.UNIQUE,
                createAttributes(
                        createAttribute("+Lightning damage", "Increases lightning damage", ValueType.DECIMAL, 25.0f)
                ));
        Jewelry equipmentNecklace = createJewelryEntity("Bracelet of the thin nech", 6, JewelryType.NECKLACE, ItemRarity.UNIQUE,
                createAttributes(
                        createAttribute("+Cold damage", "Increases cold damage", ValueType.DECIMAL, 25.0f)
                ));

        Offhand equipmentOffhand = createOffhandEntity("Mighty Globe Of Fortune", 6, ItemRarity.CRAFTED,
                createAttributes(
                        createAttribute("+Cold damage", "Increases cold damage", ValueType.DECIMAL, 25.0f),
                        createAttribute("+Lightning damage", "Increases cold damage", ValueType.DECIMAL, 25.0f),
                        createAttribute("+Fire damage", "Increases cold damage", ValueType.DECIMAL, 4.0f),
                        createAttribute("+Magic find", "Increases magic find", ValueType.FLOATING, 14.5f),
                        createAttribute("+Speed", "Increases walk speed", ValueType.PERCENT, 0.50f),
                        createAttribute("+Health", "Increases Health", ValueType.PERCENT, 10.0f)
                ));

        List<Item> silvershaperInventoryItems = Arrays.asList(bodyArmorOne, bodyArmorTwo, weaponOne);
        List<Item> silvershaperStashItems = Arrays.asList(gloves, weaponTwo);
        Equipment silverShaperEquipment = Equipment.builder()
                .bodyArmor(equipmentBodyArmor)
                .gloves(equipmentGloves)
                .boots(equipmentBoots)
                .helmet(equipmentHelmet)
                .amulet(equipmentAmulet)
                .bracelet(equipmentBracelet)
                .necklace(equipmentNecklace)
                .ringOne(equipmentRingOne)
                .ringTwo(equipmentRingTwo)
                .oneHandedWeapon(equipmentOneHandedWeapon)
                .offhand(equipmentOffhand)
                .build();


        // Silvershaper skills
        List<Skill> skills = new ArrayList<>();

        Skill skill1 = createSkill(
                1,
                SkillActivation.ACTIVE,
                "Cast a fireball to the selected target",
                createEffect(
                        EffectType.DAMAGE,
                        StatusEffect.BURNT,
                        "Apply burn to the target",
                        1,
                        0,
                        null
                ),
                null,
                2,
                "/images/fireball.png",
                true,
                SchoolType.FIRE,
                SkillTarget.ENEMY
        );

        Skill skill2 = createSkill(
                2,
                SkillActivation.ACTIVE,
                "Cast a frost bolt to the selected target",
                createEffect(
                        EffectType.DAMAGE,
                        StatusEffect.BURNT,
                        "Freezing to the target",
                        1,
                        0,
                        null
                ),
                Arrays.asList(
                        createEffect(
                                EffectType.DOT,
                                StatusEffect.FROZEN,
                                "Chill the target",
                                1,
                                0,
                                null
                        ),
                        createEffect(
                                EffectType.DOT,
                                StatusEffect.FROZEN,
                                "Frost the target",
                                2,
                                0,
                                null
                        )
                ),
                2,
                "/images/frostball.png",
                true,
                SchoolType.FROST,
                SkillTarget.ENEMY
        );

        Skill buff = createSkill(
                1,
                SkillActivation.ACTIVE,
                "Create frozen armor around the hero",
                createEffect(
                        EffectType.BUFF,
                        StatusEffect.FROZEN,
                        "Create frozen armor",
                        1,
                        0,
                        createBuff(
                                "Frost armor",
                                "Cast frost armor",
                                BuffType.FROST_ARMOR,
                                createAttribute(
                                        "Defense",
                                        "Defense",
                                        ValueType.DECIMAL,
                                        100
                                )
                        )
                ),
                null,
                2,
                "/images/frost_armor.png",
                true,
                SchoolType.FROST,
                SkillTarget.SELF
        );

        skills.add(skill1);
        skills.add(skill2);
        skills.add(buff);

        // Silvershaper stats

        List<Attribute> stats = new ArrayList<>(createAttributes(
                createAttribute("Strength", "Base Strength", ValueType.DECIMAL, 20.0f),
                createAttribute("Intelligence ", "Base Intelligence", ValueType.DECIMAL, 50.0f),
                createAttribute("Dexterity", "Base Dexterity", ValueType.DECIMAL, 25.0f),
                createAttribute("Agility", "Base Agility", ValueType.DECIMAL, 25.0f)
        ));

        Hero silvershaper = createHero("Silvershaper",
                15,
                1231241L,
                HeroClass.WIZARD,
                15000L,
                10000L,
                silvershaperInventoryItems,
                silvershaperStashItems,
                silverShaperEquipment,
                skills,
                stats
        );

        // Create a test player and assign Silvershaper hero to him

        RegisterRequest registerRequest = new RegisterRequest("test_user@example.com", "testPassword123");
        User savedUser = authService.register(registerRequest, null);
        savedUser.setHeroes(List.of(silvershaper));
        authService.saveUserData(savedUser);

//        createHero("Alistor", 5, 1145522471L, HeroClass.WARRIOR, 254L, 0L, Collections.emptyList(), Collections.emptyList());
//        createHero("Leviathan", 21, 8975520214L, HeroClass.HUNTER, 10521L, 500L, Collections.emptyList(), Collections.emptyList());
//        createHero("Tern", 1, 7105522888L, HeroClass.WIZARD, 3L, 0L, Collections.emptyList(), Collections.emptyList());
//        createHero("Boggie", 13, 6335522800L, HeroClass.WARRIOR, 8932L, 150L, Collections.emptyList(), Collections.emptyList());

    }


    private Hero createHero(String name, int level, Long experience, HeroClass heroClass, Long stashGold, Long inventoryGold, List<Item> inventoryItems, List<Item> stashItems, Equipment equipment, List<Skill> skills, List<Attribute> stats) {
        Hero hero = Hero.builder().name(name).level(level).experience(experience).heroClass(heroClass).build();
        Hero savedHero = heroService.saveHero(hero);

        Inventory inventory = Inventory.builder().gold(inventoryGold).items(new ArrayList<>(inventoryItems)).build();
        Stash stash = Stash.builder().gold(stashGold).items(new ArrayList<>(stashItems)).build();
        //List<Skill> skills = new ArrayList<>();

        Equipment savedEquipment = equipmentService.saveEquipment(equipment);
        Inventory savedInventory = inventoryService.saveInventory(inventory);
        Stash savedStash = stashService.saveStash(stash);
        List<Skill> savedSkills = skillService.saveSkills(skills);

        savedHero.setEquipment(savedEquipment);
        savedHero.setInventory(savedInventory);
        savedHero.setStash(savedStash);
        savedHero.setSkills(savedSkills);
        savedHero.setStats(stats);

        return heroService.saveHero(savedHero);
    }

    private Armor createArmorEntity(String name, int level, ArmorType type, ItemRarity rarity, List<Attribute> attributes) {
        return Armor.builder().name(name).level(level).type(type).rarity(rarity).attributes(attributes).build();
    }

    private Jewelry createJewelryEntity(String name, int level, JewelryType type, ItemRarity rarity, List<Attribute> attributes) {
        return Jewelry.builder().name(name).level(level).rarity(rarity).attributes(attributes).type(type).build();
    }

    private Weapon createWeaponEntity(String name, int level, WeaponType type, WeaponCategory category, ItemRarity rarity, List<Attribute> attributes) {
        return Weapon.builder().name(name).level(level).type(type).category(category).rarity(rarity).attributes(attributes).build();
    }

    private Offhand createOffhandEntity(String name, int level, ItemRarity rarity, List<Attribute> attributes) {
        return Offhand.builder().name(name).level(level).rarity(rarity).attributes(attributes).build();
    }

    private Skill createSkill(int cost, SkillActivation activation, String description, Effect primaryEffect, List<Effect> additionalEffects, int cooldown, String iconUrl, boolean offensive, SchoolType schoolType, SkillTarget skillTarget) {
        return Skill.builder()
                .cost(cost)
                .activation(activation)
                .description(description)
                .primaryEffect(primaryEffect)
                .additionalEffects(additionalEffects)
                .cooldown(cooldown)
                .iconUrl(iconUrl)
                .offensive(offensive)
                .schoolType(schoolType)
                .target(skillTarget)
                .build();
    }

    private List<Skill> createSkills(Skill... skills) {
        return skillService.saveSkills(Arrays.asList(skills));
    }

    private Effect createEffect(EffectType effectType, StatusEffect statusEffect, String description, int damagePerTurn, int heal, Buff buff) {
        return effectService.saveEffect(
                Effect.builder()
                        .effectType(effectType)
                        .statusEffect(statusEffect)
                        .description(description)
                        .damagePerTurn(damagePerTurn)
                        .heal(heal)
                        .buff(buff)
                        .build()
        );
    }

    private List<Effect> createEffects(Effect... effects) {
        return effectService.saveEffects(Arrays.asList(effects));
    }

    private Buff createBuff(String name, String description, BuffType type, Attribute buffedAttribute) {
        return buffService.saveBuff(Buff.builder()
                .name(name)
                .description(description)
                .type(type)
                .buffedAttribute(buffedAttribute)
                .build()
        );
    }

    private Attribute createAttribute(String name, String description, ValueType valueType, float value) {
        return Attribute.builder().name(name).description(description).valueType(valueType).value(value).build();
    }

    private List<Attribute> createAttributes(Attribute... attributes) {
        return attributeService.saveAll(Arrays.asList(attributes));
    }

//    private static <T> List<T> getRandomSubset(List<T> list) {
//        if (list == null || list.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        Random random = new Random();
//        int subsetSize = random.nextInt(5) + 1; // Random number between 1 and 5
//        Set<T> subset = new HashSet<>(); // Използваме Set за уникалност
//        List<T> shuffledList = new ArrayList<>(list);
//        Collections.shuffle(shuffledList, random);
//
//        // Добавяме елементи към Set, докато не достигнем желания размер или изчерпим списъка
//        for (T element : shuffledList) {
//            if (subset.size() >= subsetSize) {
//                break;
//            }
//            subset.add(element);
//        }
//
//        return new ArrayList<>(subset); // Връщаме списък от уникални елементи
//    }
}
