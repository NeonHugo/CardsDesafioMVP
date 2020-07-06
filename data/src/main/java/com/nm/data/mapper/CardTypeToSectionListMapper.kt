package com.nm.data.mapper

import com.nm.commons.net.data.Mapper
import com.nm.domain.entity.CardTypes
import com.nm.domain.entity.Option
import com.nm.domain.entity.Section

class CardTypeToSectionListMapper : Mapper<CardTypes, List<Section>>() {
    override fun transform(item: CardTypes): List<Section> {
        val sections = ArrayList<Section>()

        sections.add(
            Section("Classe", createOptions("Classe", item.classes))
        )

        sections.add(
            Section("Faction", createOptions("Faction", item.factions))
        )

        sections.add(
            Section("Quality", createOptions("Quality", item.qualities))
        )

        sections.add(
            Section("Race", createOptions("Race", item.races))
        )

        sections.add(
            Section("Type", createOptions("Type", item.types))
        )

        return sections
    }

    private fun createOptions(name: String, items: List<String>): ArrayList<Option> {
        return items.map {
            Option(
                name,
                it
            )
        } as ArrayList<Option>
    }
}