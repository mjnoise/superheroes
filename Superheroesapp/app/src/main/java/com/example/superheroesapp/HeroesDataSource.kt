package com.example.superheroesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.model.HeroesRepository

@Composable
fun SuperheroListItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.padding(16.dp,8.dp),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(72.dp)
        ) {
            Column(
                modifier = Modifier.padding(end = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(
                    id = hero.nameRes
                ),
                modifier = Modifier.clip(MaterialTheme.shapes.small),
            )
        }
    }
}

@Composable
fun SuperHeroInfo() {
    LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
        items(HeroesRepository.heroes) {
            SuperheroListItem(hero = it)
        }
    }
}


