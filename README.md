# Changed More Creatures - Minecraft 1.20.1 Forge Mod

- This mod is an add-on for Changed and its add-ons. 
It adds various entities, blocks, items, 
and many other interesting things to the game. 
However, this mod is no longer being made with **MCreator**, 
so the MCreator-generated code and some messy parts need to be cleaned up 
and rewritten. If you could **help** me write some code and submit pull 
requests, **<u>I would be very happy and grateful!</u>**
![Changed More Creatures 展示图](https://i1.hdslb.com/bfs/new_dyn/11222436aca49dadbd2a60b10bdd804d1561962848.png)

---

## How to build this mod?
- First clone this repo
- Then you can see a bat file in the repo, run 
[Bulid.bat](Build.bat) will built this mod.
- At last, you can see `build/libs/` 
to find you build mod file. (Yeah, My English was very bad) 

## Assigning abilities to transfur variants

Ability assignments can be added with datapack JSON files in
`data/<pack_namespace>/ability/` (for example,
`data/changed_creatures/ability/`). Each file assigns one or more registered abilities (up to four) to the
variants listed in `values`:

```json
{
  "ability": [
    "changed_creatures:damage_resistance",
    "changed_creatures:enrage"
  ],
  "values": [
    "changed_creatures:form_latex_eyro_end_dragon"
  ]
}
```

`ability` may also be a single resource ID string for backwards compatibility.
Only the first four array entries are read; extras are ignored with a warning.
The ability and variant values must be resource IDs. Multiple files can add
abilities to the same variant, up to four total. Once a variant has JSON
assignments, those assignments define its full ability list and take
precedence over built-in variant mappings and the default list. Assignments
are reloaded with datapacks and are also sent to clients when the player logs
in, changes variant, or opens the ability screen.

The ability assignment data provider generates the built-in assignments at
`data/changed_creatures/ability/default.json`. In the provider, use
`simpleadd(abilityRegistryObject, variantRegistryObjects, "file_name")` for
one ability or `simpleadd(abilityRegistryObjects, variantRegistryObjects,
"file_name")` for an ability list; pass the registered objects directly, not
string IDs.

<!-- 注释注释注释 -->
<!-- 明明是libs好吗 -->
<!-- ~~(Yes, **very bad**)~~ -->
<!-- 信达雅你是一个都没做到啊 -->

## License
- Source code: [**MIT**](LICENSE-src)
- Model/texture: [**CC BY-NC-ND 4.0**](LICENSE-models)

## Disclaimer

This mod is provided "as is". The author assumes no responsibility for 
any damages or losses arising from the use of this mod.

---

## Contact

For questions or permission requests, please contact the
[**author - EyroIceDragon**](https://github.com/EyroIceDragon) and 
[**art - chengxiii114**](https://github.com/chengxiii114).

<!-- who is the author and who is the art? -->
<!-- 回答我, look in my eyes, tell me, who! -->
