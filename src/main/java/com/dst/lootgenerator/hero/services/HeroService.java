package com.dst.lootgenerator.hero.services;

import com.dst.lootgenerator.auth.models.User;
import com.dst.lootgenerator.auth.services.AuthService;
import com.dst.lootgenerator.hero.models.dtos.*;
import com.dst.lootgenerator.hero.models.entities.*;
import com.dst.lootgenerator.hero.repositories.HeroRepository;
import com.dst.lootgenerator.items.models.dtos.*;
import com.dst.lootgenerator.items.models.entities.*;
import com.dst.lootgenerator.items.models.enums.*;
import com.dst.lootgenerator.items.services.ItemService;
import com.dst.lootgenerator.player.models.dtos.PlayerHeroDto;
import com.dst.lootgenerator.player.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeroService {
    private final HeroRepository heroRepository;
    private final AuthService authService;

    public Hero saveHero(Hero hero) {
        return this.heroRepository.save(hero);
    }

    public HeroResponse getHeroById(Long heroId) {
        User loggedInUser = authService.getLoggedInUser();

        loggedInUser
                .getHeroes()
                .stream()
                .mapToLong(Hero::getId)
                .filter(value -> value == heroId)
                .findAny()
                .orElseThrow(() -> new AccessDeniedException("You do not have access to this hero"));

        Hero hero = this.heroRepository.findById(heroId).orElse(null);

        return hero != null ? mapHeroEntityToHeroResponse(hero) : null;

    }

    private HeroResponse mapHeroEntityToHeroResponse(Hero hero) {
        Stash stash = hero.getStash();
        Inventory inventory = hero.getInventory();
        Equipment equipment = hero.getEquipment();
        List<Skill> skills = hero.getSkills();
        List<Attribute> stats = hero.getStats();

        return HeroResponse
                .builder()
                .id(hero.getId())
                .level(hero.getLevel())
                .name(hero.getName())
                .heroClass(hero.getHeroClass())
                .experience(hero.getExperience())
                .stash(StashDto
                        .builder()
                        .id(stash.getId())
                        .gold(hero.getStash().getGold())
                        .items(stash.getItems().stream().map(this::mapItemEntityToItemDto).collect(Collectors.toList()))
                        .build()
                )
                .inventory(InventoryDto
                        .builder()
                        .id(inventory.getId())
                        .gold(inventory.getGold())
                        .items(inventory.getItems().stream().map(this::mapItemEntityToItemDto).collect(Collectors.toList()))
                        .build()
                )
                .equipment(EquipmentDto
                        .builder()
                        .id(equipment.getId())
                        .amulet(
                                equipment.getAmulet() != null ?
                                        JewelryDto
                                                .builder()
                                                .id(equipment.getAmulet().getId())
                                                .name(equipment.getAmulet().getName())
                                                .level(equipment.getAmulet().getLevel())
                                                .jewelryType(equipment.getAmulet().getType())
                                                .rarity(equipment.getAmulet().getRarity())
                                                .attributes(equipment.getAmulet().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .bodyArmor(
                                equipment.getBodyArmor() != null ?
                                        ArmorDto
                                                .builder()
                                                .id(equipment.getBodyArmor().getId())
                                                .name(equipment.getBodyArmor().getName())
                                                .level(equipment.getBodyArmor().getLevel())
                                                .armorType(equipment.getBodyArmor().getType())
                                                .rarity(equipment.getBodyArmor().getRarity())
                                                .attributes(equipment.getBodyArmor().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .gloves(
                                equipment.getGloves() != null ?
                                        ArmorDto
                                                .builder()
                                                .id(equipment.getGloves().getId())
                                                .name(equipment.getGloves().getName())
                                                .level(equipment.getGloves().getLevel())
                                                .armorType(equipment.getGloves().getType())
                                                .rarity(equipment.getGloves().getRarity())
                                                .attributes(equipment.getGloves().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .boots(
                                equipment.getBoots() != null ?
                                        ArmorDto
                                                .builder()
                                                .id(equipment.getBoots().getId())
                                                .name(equipment.getBoots().getName())
                                                .level(equipment.getBoots().getLevel())
                                                .armorType(equipment.getBoots().getType())
                                                .rarity(equipment.getBoots().getRarity())
                                                .attributes(equipment.getBoots().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .ringOne(
                                equipment.getRingOne() != null ?
                                        JewelryDto
                                                .builder()
                                                .id(equipment.getRingOne().getId())
                                                .name(equipment.getRingTwo().getName())
                                                .level(equipment.getRingOne().getLevel())
                                                .jewelryType(equipment.getRingOne().getType())
                                                .rarity(equipment.getRingOne().getRarity())
                                                .attributes(equipment.getRingOne().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .ringTwo(
                                equipment.getRingTwo() != null ?
                                        JewelryDto
                                                .builder()
                                                .id(equipment.getRingTwo().getId())
                                                .name(equipment.getRingTwo().getName())
                                                .level(equipment.getRingTwo().getLevel())
                                                .jewelryType(equipment.getRingTwo().getType())
                                                .rarity(equipment.getRingTwo().getRarity())
                                                .attributes(equipment.getRingTwo().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .shield(
                                equipment.getShield() != null ?
                                        ArmorDto
                                                .builder()
                                                .id(equipment.getShield().getId())
                                                .name(equipment.getShield().getName())
                                                .level(equipment.getShield().getLevel())
                                                .armorType(equipment.getShield().getType())
                                                .rarity(equipment.getShield().getRarity())
                                                .attributes(equipment.getShield().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .helmet(
                                equipment.getHelmet() != null ?
                                        ArmorDto
                                                .builder()
                                                .id(equipment.getHelmet().getId())
                                                .name(equipment.getHelmet().getName())
                                                .level(equipment.getHelmet().getLevel())
                                                .armorType(equipment.getHelmet().getType())
                                                .rarity(equipment.getHelmet().getRarity())
                                                .attributes(equipment.getHelmet().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .necklace(
                                equipment.getNecklace() != null ?
                                        JewelryDto
                                                .builder()
                                                .id(equipment.getNecklace().getId())
                                                .name(equipment.getNecklace().getName())
                                                .level(equipment.getNecklace().getLevel())
                                                .jewelryType(equipment.getNecklace().getType())
                                                .rarity(equipment.getNecklace().getRarity())
                                                .attributes(equipment.getNecklace().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .offhand(
                                equipment.getOffhand() != null ?
                                        OffhandDto
                                                .builder()
                                                .id(equipment.getOffhand().getId())
                                                .name(equipment.getOffhand().getName())
                                                .level(equipment.getOffhand().getLevel())
                                                .rarity(equipment.getOffhand().getRarity())
                                                .attributes(equipment.getOffhand().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .bracelet(
                                equipment.getBracelet() != null ?
                                        JewelryDto
                                                .builder()
                                                .id(equipment.getBracelet().getId())
                                                .name(equipment.getBracelet().getName())
                                                .level(equipment.getBracelet().getLevel())
                                                .jewelryType(equipment.getBracelet().getType())
                                                .rarity(equipment.getBracelet().getRarity())
                                                .attributes(equipment.getBracelet().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .oneHandedWeapon(
                                equipment.getOneHandedWeapon() != null ?
                                        WeaponDto
                                                .builder()
                                                .id(equipment.getOneHandedWeapon().getId())
                                                .name(equipment.getOneHandedWeapon().getName())
                                                .level(equipment.getOneHandedWeapon().getLevel())
                                                .weaponType(equipment.getOneHandedWeapon().getType())
                                                .weaponCategory(equipment.getOneHandedWeapon().getCategory())
                                                .attributes(equipment.getOneHandedWeapon().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .twoHandedWeapon(
                                equipment.getTwoHandedWeapon() != null ?
                                        WeaponDto
                                                .builder()
                                                .id(equipment.getTwoHandedWeapon().getId())
                                                .name(equipment.getTwoHandedWeapon().getName())
                                                .level(equipment.getTwoHandedWeapon().getLevel())
                                                .weaponType(equipment.getTwoHandedWeapon().getType())
                                                .weaponCategory(equipment.getTwoHandedWeapon().getCategory())
                                                .attributes(equipment.getTwoHandedWeapon().getAttributes().stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                                                .build()
                                        : null
                        )
                        .build()
                )
                .skills(
                    skills.stream().map(this::mapSkillToSkillDto).collect(Collectors.toList())
                )
                .attributes(stats.stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList()))
                .build();
    }

    private SkillDto mapSkillToSkillDto(Skill skill) {
        Effect primaryEffect = skill.getPrimaryEffect();
        return SkillDto
                .builder()
                .id(skill.getId())
                .name(skill.getName())
                .iconUrl(skill.getIconUrl())
                .offensive(skill.isOffensive())
                .activation(skill.getActivation())
                .primaryEffect(
                        EffectDto
                                .builder()
                                .statusEffect(primaryEffect.getStatusEffect())
                                .description(primaryEffect.getDescription())
                                .damagePerTurn(primaryEffect.getDamagePerTurn())
                                .description(primaryEffect.getDescription())
                                .heal(primaryEffect.getHeal())
                                .buff(
                                        primaryEffect.getBuff() != null ?
                                                BuffDto
                                                        .builder()
                                                        .name(primaryEffect.getBuff().getName())
                                                        .description(primaryEffect.getBuff().getDescription())
                                                        .type(primaryEffect.getBuff().getType())
                                                        .buffedAttribute(mapAttributeEntityToAttributeDto(primaryEffect.getBuff().getBuffedAttribute()))
                                                        .build()
                                                : null
                                )
                                .build()
                )
//                .additionalEffects(!skill.getAdditionalEffects().isEmpty() ?
//                )
                .cost(skill.getCost())
                .cooldown(skill.getCooldown())
                .schoolType(skill.getSchoolType())
                .build();
    }

    private ItemDto mapItemEntityToItemDto(Item item) {
        ArmorType armorType = null;
        CharmType charmType = null;
        GemType gemType = null;
        GemQuality gemQuality = null;
        ItemRarity rarity = null;
        JewelryType jewelryType = null;
        MiscType miscType = null;
        MiscSubType miscSubType = null;
        PotionType potionType = null;
        PotionSize potionSize = null;
        RuneType runeType = null;
        WeaponCategory weaponCategory = null;
        WeaponType weaponType = null;
        Integer potionRestoreValue = null;

        List<Attribute> attributeList = new ArrayList<>();

        switch (item) {
            case Weapon weapon -> {
                weaponType = weapon.getType();
                weaponCategory = weapon.getCategory();
                rarity = weapon.getRarity();
                attributeList = weapon.getAttributes();
            }
            case Armor armor -> {
                armorType = armor.getType();
                rarity = armor.getRarity();
                attributeList = armor.getAttributes();
            }
            case Jewelry jewelry -> {
                jewelryType = jewelry.getType();
                rarity = jewelry.getRarity();
                attributeList = jewelry.getAttributes();
            }
            case Charm charm -> {
                charmType = charm.getType();
                attributeList = charm.getAttributes();
            }
            case Gem gem -> {
                gemQuality = gem.getQuality();
                gemType = gem.getType();
                attributeList = gem.getAttributes();
            }
            case Rune rune -> {
                runeType = rune.getType();
                attributeList = rune.getAttributes();
            }
            case Offhand offhand -> {
                rarity = offhand.getRarity();
                attributeList = offhand.getAttributes();
            }
            case Potion potion -> {
                potionType = potion.getType();
                potionSize = potion.getSize();
                potionRestoreValue = potion.getRestoreValue();
            }
            case Miscellaneous misc -> {
                miscType = misc.getType();
                miscSubType = misc.getSubType();
            }
            default -> {

            }
        }

        return ItemDto
                .builder()
                .id(item.getId())
                .name(item.getName())
                .level(item.getLevel())
                .armorType(armorType)
                .charmType(charmType)
                .gemType(gemType)
                .gemQuality(gemQuality)
                .rarity(rarity)
                .jewelryType(jewelryType)
                .miscType(miscType)
                .miscSubType(miscSubType)
                .potionType(potionType)
                .potionSize(potionSize)
                .runeType(runeType)
                .weaponType(weaponType)
                .weaponCategory(weaponCategory)
                .potionRestoreValue(potionRestoreValue)

                .attributes(
                        attributeList.isEmpty() ? null :
                                attributeList.stream().map(this::mapAttributeEntityToAttributeDto).collect(Collectors.toList())
                )
                .build();

    }

    private AttributeDto mapAttributeEntityToAttributeDto(Attribute attribute) {
        return AttributeDto.builder()
                .name(attribute.getName())
                .value(attribute.getValue())
                .valueType(attribute.getValueType())
                .description(attribute.getDescription())
                .build();
    }
}
